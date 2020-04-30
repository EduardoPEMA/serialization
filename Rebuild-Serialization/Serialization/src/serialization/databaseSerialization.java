/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author atlas
 */
public class databaseSerialization {
    ArrayList<user> users;
    ObjectOutputStream outputUsers;
    ObjectInputStream inputUsers;
    
     public databaseSerialization() throws IOException, ClassNotFoundException {

        users = new ArrayList<>();
        try{
            outputUsers = new  ObjectOutputStream(new FileOutputStream("users.txt", true));
            outputUsers.close();
        
        }catch  (FileNotFoundException ex){}
         
        loadDB();

    }
  public void loadDB() throws IOException, ClassNotFoundException{
      user u = null; 
    try{   
        ObjectInputStream inputUsers = new ObjectInputStream(new FileInputStream("users.txt"));
        u = (user)inputUsers.readObject();
         
        while(u != null){
            if(u instanceof user){
                users.add(u);
            }
            u = (user) inputUsers.readObject();
               
        }        
    }catch (Exception ex){}  
  }
  
  void updateUsers() throws FileNotFoundException, IOException {
    FileOutputStream clearFile = new FileOutputStream("users.txt");
    outputUsers = new ObjectOutputStream(new FileOutputStream("users.txt", true));   
         
      
    for (user u : users)
    {
        outputUsers.writeObject(u);         
    } 
  }
  
  public void modifyUser(user user) throws IOException{
        for(user u : users){
            if(u.getId() == null ? user.getId() == null : u.getId().equals(user.getId())){
                u.set(user);
                break;
            }
        }
        updateUsers();
    }
  
  public user searchUser(String find) {

        user notFound = new user();

        for (user u : users) {
            String isThere = u.getName();
            if (isThere.equals(find)) {
                return u;
            }
        }
        return notFound;
  }  
  
  public void saveUser(user u) throws IOException {
        users.add(u);
        updateUsers();
    }
  
 public void deleteUser(String id) throws IOException {

        int i = 0;
        for (user u : users) {

            if (u.getId() == null ? id == null : u.getId().equals(id)) {
                users.remove(i);
                break;
            }
            ++i;
        }
        updateUsers();
    }
 
 public int getSizeUser(){
        if (users.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(users.get(users.size() - 1).getId());
    }
 
 public ArrayList<user> getUsers() {
        return users;
    }
    
    
    
}
