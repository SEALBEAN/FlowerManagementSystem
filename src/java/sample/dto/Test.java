/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import sample.dto.Account;
import sample.dao.AccountDao;
import java.util.ArrayList;
import sample.dao.PlantDAO;

/**
 *
 * @author SEAL
 */
public class Test {
    public static void main(String[] args) {
        try {
            Account acc = AccountDao.getAccount("test@gmail.com", "test");
            if (acc!= null){
                if(acc.getRole()==1)
                    System.out.println("i am an admin");
                else
                    System.out.println("i am an user");
            } else{
                System.out.println("login fail");
            }
            
            
            ArrayList<Account> listacc = AccountDao.getAccounts();
            for (Account account : listacc) {
                System.out.println(account);
            }
            
            
            boolean check = AccountDao.updateAccountStatus("quang@gmail.com", 1);
            if (check == true){
                System.out.println("Update successfully");
            } else{
                System.out.println("Update fail");
            }
            
            
            check = AccountDao.updateAccount("quang@gmail.com","Quang", "0123");
            if (check == true){
                System.out.println("Update account infomation successfully");
            } else{
                System.out.println("Update account infomation fail");
            }
            
            
//            check = AccountDao.insertAccount("abc@gmail.com", "12343", "AbC", "012345", 0, 0);
//            if (check == true){
//                System.out.println("Insert new account successfully");
//            } else{
//                System.out.println("Insert new account fail");
//            }
            
            ArrayList<Plant> listplant = PlantDAO.getPlants("on", "byname");
            for (Plant plant : listplant) {
                System.out.println(plant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
