package ua.kiev.prog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList homeMsgList = new MessageList();
    private static final int LIMIT = 100;

    private  Gson gson = new GsonBuilder().create();
	private final List<Message> list = new LinkedList<Message>();
	
	public static MessageList getInstance() {
		return homeMsgList;
	}
  
  	public MessageList() {

	}
	public void addMsgList(MessageList msgList){
		for (Message m : msgList.list){
			if(list.indexOf(m)==-1)
				list.add(m);
		}
	}
	
	public synchronized void add(Message m) {

		list.add(m);
	}
	
	public synchronized String toJSON(int n) {
		if (n == list.size()) return null;
		for (Message m : list){
			System.out.println(m.getText());
		}
		return gson.toJson(new JsonMessages(list, n));
	}
	public List<Message> getList(){
		return list;
	}
}
