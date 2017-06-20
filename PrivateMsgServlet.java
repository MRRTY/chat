package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Greg on 04.03.2017.
 */
public class PrivateMsgServlet extends HttpServlet{
    Users users = Users.getUsers();
    Online online = Users.getOnline();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = AddServlet.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Message msg = Message.fromJSON(bufStr);

            if (msg != null) {
                User handler = online.getUser(msg.getTo().hashCode());
                if(handler!=null) {
                    boolean flag = false;
                    for (Room r : handler.getRooms()){
                        if(r.getName().equals(msg.getFrom())){
                           r.getMsgList().add(msg);
                        }
                    }
                    if(!flag){
                        MessageList messageList = new MessageList();
                        messageList.add(msg);
                       handler.getRooms().add(new Room(messageList,msg.getFrom()));

                    }
                    resp.setStatus(HttpServletResponse.SC_OK);
                }else {
                    resp.setStatus(HttpServletResponse.SC_RESET_CONTENT);
                }
            } else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}
