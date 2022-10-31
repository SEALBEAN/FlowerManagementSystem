/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author SEAL
 */
public class Account {
    private int accid;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private int status;
    private int role;

    public Account() {
    }

    public Account(int accid, String fullname, String email, String password, String phone, int status, int role) {
        this.accid = accid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "accid=" + accid + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", phone=" + phone + ", status=" + status + ", role=" + role + '}';
    }
    
    
}
