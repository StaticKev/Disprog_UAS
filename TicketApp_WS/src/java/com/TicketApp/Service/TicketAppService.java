package com.TicketApp.Service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import TicketApp.models.User;

@WebService(serviceName = "TicketAppWS")
public class TicketAppService {

    User user;
    
    public TicketAppService(){
        user = new User();
    } 
    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param username
     * @param password
     * @param email
     */
    @WebMethod(operationName = "register")
    public void register(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
        user = new User(username, password, email);
        user.insertData();
    }

    /**
     * Web service operation
     * @param username
     * @param password
     * @return 
     */
    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        user = new User(username, password);
        if (user.read_User())return user;
        else return null;
    }

    /**
     * Web service operation
     * @param username
     * @param email
     * @return 
     */
    @WebMethod(operationName = "UserAlreadyExists")
    public Boolean UserAlreadyExists(@WebParam(name = "username") String username, @WebParam(name = "email") String email) {
        user = new User();
        user.setEmail(email);
        user.setUsername(username);
        return user.read_UserIsExist();
    }
}