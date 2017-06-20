package ua.kiev.prog;

/**
 * Created by Greg on 02.03.2017.
 */
public class Room {
    private MessageList msgList;
    private String name;

    public Room(MessageList msgList, String name) {
        this.msgList = msgList;
        this.name = name;
    }

    public MessageList getMsgList() {
        return msgList;
    }

    public void setMsgList(MessageList msgList) {
        this.msgList = msgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
