package com.doido.todolistback.exception.CustomsExceptions;

public class UserNotFoundHandler extends RuntimeException{

    public UserNotFoundHandler(){
        super("User not found");
    }
    
    public UserNotFoundHandler(String message){
        super(message);
    }
}
