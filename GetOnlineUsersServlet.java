package ua.kiev.prog;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Greg on 05.03.2017.
 */
public class GetOnlineUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for(User user: Users.getOnline().getUsers()) {
            resp.getWriter().println(user.getLogin());
        }
    }
}
