/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Conexao {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        //Class.forName("org.postgresql.Driver");
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "umc@2018");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doceafeto","root", "");
        return con;
    }

}
