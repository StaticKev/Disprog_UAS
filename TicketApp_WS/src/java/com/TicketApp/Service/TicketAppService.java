package com.TicketApp.Service;

import TicketApp.models.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "TicketAppWS")
public class TicketAppService {

    private User user;
    
    public TicketAppService(){
        user = new User();
    } 
    /**
     * Web service operation
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
    public boolean login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        user = new User(username, password);
        return user.read_User();
    }

    /**
     * Web service operation
     * @param username
     * @param email
     * @return 
     */
    @WebMethod(operationName = "userAlreadyExists")
    public boolean userAlreadyExists(@WebParam(name = "username") String username, @WebParam(name = "email") String email) {
        user = new User();
        user.setEmail(email);
        user.setUsername(username);
        return user.read_UserIsExist();
    }
}