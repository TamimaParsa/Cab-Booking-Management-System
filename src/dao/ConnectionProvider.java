/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author P A R S A
 */
public class ConnectionProvider {
    public static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabmanagement?useSSL=false&allowPublicKeyRetrieval=true","root","WJ28@krhps");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
