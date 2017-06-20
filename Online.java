package ua.kiev.prog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Greg on 04.03.2017.
 */
public class Online {

    private Map<Integer,User> users = new HashMap<>();

    public void addUser(int hash,User user){
        users.put(hash,user);
    }
    public User getUser(int hash){
        return users.get(hash);
    }
    public HashSet<User> getUsers(){
        HashSet<User> hashSet = new HashSet<>();
        for (Integer i : users.keySet()){
            hashSet.add(users.get(i));
        }
        return hashSet;

    }
}
