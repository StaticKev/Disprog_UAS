/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketApp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public abstract class Model {
    
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;
    
    public Model(){
        Model.conn = this._getConnection();
    }
    public Connection _getConnection(){
        if(Model.conn == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                return DriverManager.getConnection("jdbc:mysql://localhost/isadp","root","");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return Model.conn;
    }
    
    public abstract void insertData();
    public abstract void updateData();
    public abstract void deleteData();
    public abstract ArrayList<Object> viewListData();
    
}
