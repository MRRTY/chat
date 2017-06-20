package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  GetListServlet extends HttpServlet {
	Users users = Users.getUsers();
	private MessageList msgList;

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		System.out.println("str:"+fromStr);
		int hash = Integer.parseInt(req.getParameter("hash"));
		User handler = Users.getOnline().getUser(hash);
		if(hash == 0){
			msgList = MessageList.getInstance();
		}else{
			if(handler!= null){
				msgList = handler.getMsgList();
			}else {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}

		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}
		System.out.println(from);
		String json = msgList.toJSON(from);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);
		}
	}
}
