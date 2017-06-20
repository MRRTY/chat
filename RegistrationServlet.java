package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Greg on 02.03.2017.
 */
public class RegistrationServlet extends HttpServlet {
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
        String sage = element.getAsJsonObject().get("age").toString();
        String ssex = element.getAsJsonObject().get("sex").toString();
        int age;
        try {
            age = Integer.parseInt(sage);
        }catch (NumberFormatException ex){
            age = 0;
        }
        boolean sex = Boolean.parseBoolean(ssex);
        User user = new User(login,pass,age,sex);

            User temp = users.getUser(user.getLogin());
            if(temp!=null){
                resp.setStatus(HttpServletResponse.SC_RESET_CONTENT);
            }else {

                users.addUser(user);
                resp.setStatus(HttpServletResponse.SC_OK);
            }


    }

}
