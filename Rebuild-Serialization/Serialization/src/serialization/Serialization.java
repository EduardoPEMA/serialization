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
import java.util.Scanner;

/**
 *
 * @author atlas
 */
public class Serialization {
    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    public Serialization(){}
    
    public void save(user u) throws IOException{
        try
        {
            output = new  ObjectOutputStream(new FileOutputStream("object.txt", true));
            output.writeObject(u);
            if(output!=null)
            {
                output.close();
            }
            
        }catch  (FileNotFoundException ex){}
    }
    
    public void read()throws IOException, ClassNotFoundException{
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("object.txt"))){
            while(true){
                try{
                    user ob = (user)input.readObject();
                    System.out.println(ob.getId());
                    System.out.println(ob.getName());
                    System.out.println(ob.getAddress());
                    System.out.println(ob.getEmail());
                    System.out.println(ob.getPhone());
                    System.out.println("\n");
            
                }catch (Exception ex){}
                if(input!=null){
                    input.close();
                }
            }
            
        }catch(FileNotFoundException ex){}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Serialization ser = new Serialization();
        user u = new user();
            u.setId("29");
            u.setName("Cesar Eduardo Perez Madriz");
            u.setAddress("Sabino 2218");
            u.setEmail("eduardopro00@gmail.com");
            u.setPhone("3318492391");
            ser.save(u);
            ser.read();
        
        
        
                    
    }
    
}
