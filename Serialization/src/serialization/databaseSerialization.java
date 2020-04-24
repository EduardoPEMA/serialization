/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialization;

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
    ObjectOutputStream outputUsers = null;
    ObjectInputStream inputUsers = null;
    
     public databaseSerialization() throws IOException, ClassNotFoundException {

        users = new ArrayList<>();
        try{
            outputUsers = new  ObjectOutputStream(new FileOutputStream("users.txt", true));
            outputUsers.close();
        
        }catch  (FileNotFoundException ex){}
         
        loadDB();

    }
  public void loadDB() throws IOException, ClassNotFoundException{
      user u;
      
      
      try
      {
          u = (user)inputUsers.readObject();
          
         
          
           while (inputUsers.available() > 0) {
               
               u.setId(inputUsers.readInt());
               u.setName(inputUsers.readUTF());
               u.setAddress(inputUsers.readUTF());
               u.setEmail(inputUsers.readUTF());
               u.setPhone(inputUsers.readUTF());
               
               
               users.add(u);
               
               
           }
          
      }catch (Exception ex){}
  }
  
  void updateUsers() throws FileNotFoundException, IOException {
      outputUsers = new ObjectOutputStream(new FileOutputStream("users.txt", true));
      FileOutputStream clearFile = new FileOutputStream("users.txt");

            for (user u : users) {
                outputUsers.writeObject(u);
                    
               
            }
      
  }
  
  public void modifyUser(user user) throws IOException{
        for(user u : users){
            if(u.getId() == user.getId()){
                u.set(user);
                break;
            }
        }
        updateUsers();
    }
  
  
  public void saveUser(user u) throws IOException {
        users.add(u);
        updateUsers();
    }
    
    
    
}
/*
public class RandomAccess {
    RandomAccessFile raf = null;
    File file;
    public void addBeginning(double n) throws Exception{
        file = new File("fichero.obj");
        raf = new RandomAccessFile(file,"rw");
        raf.seek(0);
        raf.writeDouble(n);
        raf.close();
    }
    
    public void createFile() throws Exception{
        file = new File("fichero.obj");
        raf = new RandomAccessFile(file, "rw");
        if(raf != null)
            raf.close();
        
    }
    public void addEnd(double n) throws Exception{
        file = new File("fichero.obj");
        raf = new RandomAccessFile(file, "rw");
        raf.seek(raf.length());
        raf.writeDouble(n);
        raf.close();
    }
    
   public void print() throws Exception{
       file = new File("fichero.obj");
       raf = new RandomAccessFile(file, "rw");
       raf.seek(0);
       try{
           while(true){
               System.out.println((raf.readDouble()));
           }
       }catch(IOException ex){
       }
   }
       
    public void readFile() throws Exception{
            File fichero = new File("fichero.obj");
            raf = new RandomAccessFile(fichero, "r");
            if(raf != null){
                raf.close();
            }
    } 
      
*/

/*

public class Database {
    
    ArrayList<User> users;
    ArrayList<User> seller;

    File usersFile, sellerFile;
    RandomAccessFile usersRaf, sellerRaf;

    public Database() throws IOException {

        users = new ArrayList<>();
        seller = new ArrayList<>();

        usersFile = new File("users.obj");
        sellerFile = new File("sellers.obj");
        usersRaf = null;
        sellerRaf = null;
        loadDB();

    }

    void loadDB() throws FileNotFoundException, IOException {

        User u;
       
        usersRaf = new RandomAccessFile(usersFile, "rw");
        usersRaf.seek(0);
        
        sellerRaf = new RandomAccessFile(sellerFile, "rw");
        sellerRaf.seek(0);
        
        while (usersRaf.getFilePointer() != usersRaf.length()) {
            u = new User();
           
            u.setID(usersRaf.readInt());            
            u.setName(usersRaf.readUTF());
            u.setLast(usersRaf.readUTF());
            u.setSLast(usersRaf.readUTF());
            u.setSex(usersRaf.readUTF());
            u.setAddress(usersRaf.readUTF());
            u.setEmail(usersRaf.readUTF());
            u.setCity(usersRaf.readUTF());
            u.setPassword(usersRaf.readUTF());

            users.add(u);

        }
       
        
        while (sellerRaf.getFilePointer() != sellerRaf.length()) {
            u = new User();
           
            u.setID(sellerRaf.readInt());            
            u.setName(sellerRaf.readUTF());
            u.setLast(sellerRaf.readUTF());
            u.setSLast(sellerRaf.readUTF());
            u.setSex(sellerRaf.readUTF());
            u.setEmail(sellerRaf.readUTF());
            u.setCity(sellerRaf.readUTF());
            u.setPassword(sellerRaf.readUTF());
            

            seller.add(u);

        }

    }

    void updateUsers() throws FileNotFoundException, IOException {
        
            FileOutputStream clearUsers = new FileOutputStream("users.obj");
            usersRaf = new RandomAccessFile(usersFile, "rw");
            usersRaf.seek(0);
            
            for (User u : users) {
                usersRaf.writeInt(u.getID());
                usersRaf.writeUTF(u.getName());
                usersRaf.writeUTF(u.getLast());
                usersRaf.writeUTF(u.getSLast());
                usersRaf.writeUTF(u.getAddress());
                usersRaf.writeUTF(u.getSex());
                usersRaf.writeUTF(u.getEmail());
                usersRaf.writeUTF(u.
            getCity());
                usersRaf.writeUTF(u.getPassword());
            }
            usersRaf.close();
    }
    
    void updateSellers() throws FileNotFoundException, IOException {
        
            FileOutputStream clearSellers = new FileOutputStream("sellers.obj");
            sellerRaf = new RandomAccessFile(sellerFile, "rw");
            sellerRaf.seek(0);
            
            for (User u : seller) {
                sellerRaf.writeInt(u.getID());
                sellerRaf.writeUTF(u.getName());
                sellerRaf.writeUTF(u.getLast());
                sellerRaf.writeUTF(u.getSLast());
                sellerRaf.writeUTF(u.getSex());
                sellerRaf.writeUTF(u.getEmail());
                sellerRaf.writeUTF(u.getCity());
                sellerRaf.writeUTF(u.getPassword());
            }
            
            sellerRaf.close();
    }

    public void saveUser(User u) throws IOException {
        users.add(u);
        updateUsers();
    }
    
    public void saveSeller(User u) throws IOException {
        seller.add(u);
        updateSellers();
    }
    
    public User searchUser(String nombre){
        User notFound = new User();
        
        for(User u : users){
            if(nombre.equals(u.getName()+ " " + u.getLast() + " " + u.getSLast())){
                return u;
            }
        }       
        return notFound;
    }
    
    public User searchSeller(String nombre){
        User notFound = new User();
        
        for(User u : seller){
            if(nombre.equals(u.getName()+ " " + u.getLast() + " " + u.getSLast())){
                return u;
            }
        }       
        return notFound;
    }
    
    
    public void modifyUser(User user) throws IOException{
        for(User u : users){
            if(u.getID() == user.getID()){
                u.set(user);
                break;
            }
        }
        updateUsers();
    }
    
    public void modifySeller(User user) throws IOException{
        for(User u : seller){
            if(u.getID() == user.getID()){
                u.set(user);
                break;
            }
        }
        updateSellers();
    }
   
    public void deleteUser(int id) throws IOException{        
        for(int i = 0; i < users.size(); ++i){
            if(users.get(i).getID() == id){
                users.remove(i);
                break;
            }
        }
        updateUsers();
    }
    
    public void deleteSeller(int id) throws IOException{        
        for(int i = 0; i < seller.size(); ++i){
            if(seller.get(i).getID() == id){
                seller.remove(i);
                break;
            }
        }
        updateSellers();
    }
   
    
     public boolean validateUser(String email, String password){
        boolean valid = false;
        
        for(User u: users){
            if(u.getEmail().equals(email)){
                if(u.getPassword().equals(password)){
                    valid = true;
                }
                break;
            }
        }
        return valid;
    }
    
    public boolean validateSeller(String email, String password){
        boolean valid = false;
        
        for(User u: seller){
            if(u.getEmail().equals(email)){
                if(u.getPassword().equals(password)){
                    valid = true;
                }
                break;
            }
        }
        return valid;
    }
    
    public int getSizeUsers(){
        if (users.isEmpty()) {
            return 0;
        }
        return users.get(users.size() - 1).getID();
    }
    
    public int getSizeSeller(){
        if (seller.isEmpty()) {
            return 0;
        }
        return seller.get(seller.size() - 1).getID();
    }
    
}

*/
