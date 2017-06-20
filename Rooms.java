package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 02.03.2017.
 */
public class Rooms {
    private static final Rooms rooms = new Rooms();
    private List<Room> roomList = new ArrayList<>();

    private Rooms() {
        roomList.add(new Room(MessageList.getInstance(),"home"));
    }
    public static Rooms getRooms(){return rooms;}
    public void addNewRoom(String name){
        roomList.add(new Room(new MessageList(),name));
    }
    public  Room getRoomByName(String name) {
        try {
            return roomList.stream().filter(room1 -> room1.getName().equals(name)).findAny().get();
        }catch (Exception ex){
            return null;
        }
    }

}
