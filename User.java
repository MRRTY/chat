package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 02.03.2017.
 */
public class User {
    private String login;
    private String pass;
    private int age;
    private boolean sex;
    private ArrayList<Room> rooms = new ArrayList<>();
    private MessageList msgList = new MessageList();

    public User(String login,String pass, int age, boolean sex) {
        this.pass = pass;
        this.login = login;
        this.age = age;
        this.sex = sex;
        rooms.add(Rooms.getRooms().getRoomByName("home"));

    }

    public MessageList getMsgList() {
        for(Room r : rooms){
            msgList.addMsgList(r.getMsgList());
        }
        return msgList;
    }

    public void setMsgList(MessageList msgList) {
        this.msgList = msgList;
    }

    @Override

    public String toString() {
        return login+System.lineSeparator()+age+System.lineSeparator()+sex;
    }
    public void getInfo(){
        System.out.println(this.toString());
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
