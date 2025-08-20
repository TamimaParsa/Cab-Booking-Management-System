/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author P A R S A
 */
public class Tables {
    public static void main(String[] args){
        Connection con= null;
        Statement st =null;
        
        try{
            con = ConnectionProvider.getCon();
            st = con.createStatement();
            //st.executeUpdate("create table customer(customer_pk int AUTO_INCREMENT primary key,username varchar(200),name varchar(200),date date,email varchar(200),address varchar(200),phoneNumber varchar(50),password varchar(200))");
            //st.executeUpdate("create table admin(appuser_pk int AUTO_INCREMENT primary key,userRole varchar(50),name varchar(200),email varchar(200),password varchar(50),address varchar(200),phoneNumber varchar(50))");
            //st.executeUpdate("insert into admin(userRole,name,email,password,address,phoneNumber) values('SuperAdmin','SuperAdmin','admin@gmail.com','asdfg','Bangladesh','097365738')");
            //st.executeUpdate("create table intracityDriver(driver_pk int AUTO_INCREMENT primary key,drivername varchar(200),city varchar(200),source varchar(200),destination varchar(200),car varchar(50),price int,phoneNumber varchar(50))");
            //st.executeUpdate("create table intercityDriver(driver_pk int AUTO_INCREMENT primary key,drivername varchar(200),source varchar(200),destination varchar(200),car varchar(50),price int,phoneNumber varchar(50))");
            //st.executeUpdate("create table transportDriver(driver_pk int AUTO_INCREMENT primary key,drivername varchar(200),source varchar(200),destination varchar(200),truck varchar(50),price int,phoneNumber varchar(50))");
            //st.executeUpdate("create table singUp(user_pk int AUTO_INCREMENT primary key,username varchar(200),name varchar(200),password varchar(200),phoneNumber varchar(50))");
            st.executeUpdate("create table intraBooking(booking_id INT PRIMARY KEY,userName VARCHAR(50),doBooking DATE, city VARCHAR(255),source VARCHAR(255),destination VARCHAR(255),driver_name VARCHAR(255),car VARCHAR(255),price DECIMAL(10, 2),address VARCHAR(500))");
            //st.executeUpdate("create table interBooking(booking_id INT PRIMARY KEY,userName VARCHAR(50),doBooking date, time VARCHAR(10), ampm VARCHAR(6), source VARCHAR(255),destination VARCHAR(255),driver_name VARCHAR(255),car VARCHAR(255),price DECIMAL(10, 2),advance_payment DECIMAL(10, 2),cash_on_delivery DECIMAL(10, 2),address VARCHAR(500))");
            //st.executeUpdate("create table transportBooking(booking_id INT PRIMARY KEY,userName VARCHAR(50),doBooking date, time VARCHAR(10), ampm VARCHAR(6), source VARCHAR(255),destination VARCHAR(255),driver_name VARCHAR(255),car VARCHAR(255),price DECIMAL(10, 2),advance_payment DECIMAL(10, 2),cash_on_delivery DECIMAL(10, 2),address VARCHAR(500))");
           
            JOptionPane.showMessageDialog(null, "Table created successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
           try{
                con.close();
                st.close();
           } 
           catch(Exception e){
               
           }
        }
    }
}
