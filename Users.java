package ua.kiev.prog;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Greg on 02.03.2017.
 */
public class Users {
    private final static Users users = new Users();
    private final static Online online = new Online();
    private Map<String,User> usersMap = new HashMap<>();
    private Users(){ }
    public static Users getUsers(){return users;}
    public void addUser(User user){
        usersMap.put(user.getLogin(),user);
    }
    public User getUser(String login){
        return usersMap.get(login);
    }
    public void info(){
        for(String name: usersMap.keySet()){
           usersMap.get(name).getInfo();
        }
    }
    public static Online getOnline(){
        return online;
    }

}
