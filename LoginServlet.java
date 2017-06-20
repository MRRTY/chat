package ua.kiev.prog;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Greg on 04.03.2017.
 */
public class LoginServlet extends HttpServlet {
    private static final Users users = Users.getUsers();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        byte[] buf = AddServlet.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        JsonElement element = (new JsonParser()).parse(bufStr);
        String login = element.getAsJsonObject().get("login").toString();
        login = login.substring(1,login.length()-1);
        String pass = element.getAsJsonObject().get("pass").toString();
        pass = pass.substring(1,pass.length()-1);

        User temp = users.getUser(login);
        if(temp == null){
            resp.setStatus(HttpServletResponse.SC_RESET_CONTENT);
            resp.getWriter().print(0);
        }else {
            if (temp.getPass().equals(pass)) {
                users.getOnline().addUser(temp.getLogin().hashCode(), temp);
                resp.getWriter().print(temp.getLogin().hashCode());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_RESET_CONTENT);
                resp.getWriter().print(0);

            }
        }

    }
}
