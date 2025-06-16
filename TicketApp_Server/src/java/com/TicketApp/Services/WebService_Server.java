/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.TicketApp.Services;

import com.TicketApp.model.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author William
 */
@WebService(serviceName = "WebService_Server")
public class WebService_Server {

    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "email") String email, @WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        User u = new User(username, password, email);
        if (!User.read_AccountIsExist(email, username)){
           return String.valueOf(User.insert_User(u));
        } else {
            return "else";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        User u = new User(username, password);
        return User.read_User(u);
    }


}
