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
public class ChangeRoomServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = AddServlet.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        JsonElement element = (new JsonParser()).parse(bufStr);
        String user = element.getAsJsonObject().get("user").toString();
        String room = element.getAsJsonObject().get("room").toString();
        room = room.substring(1,room.length()-1);
        if(user!=null && room!=null){
            User temp = Users.getOnline().getUser(Integer.parseInt(user));
            if(temp!= null) {
                temp.getRooms().add(Rooms.getRooms().getRoomByName(room));
            }
            else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
