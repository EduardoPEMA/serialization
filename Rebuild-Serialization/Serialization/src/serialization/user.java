/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialization;
import java.io.Serializable;

/**
 *
 * @author atlas
 */
public class user implements Serializable{
    private String id = "";
    private String name = "";
    private String address = "";
    private String email = "";
    private String phone = "";
    
    
    public void set(user u){
        this.setId(u.getId());
        this.setName(u.getName());
        this.setAddress(u.getAddress());
        this.setPhone(u.getPhone());
        this.setEmail(u.getEmail());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
