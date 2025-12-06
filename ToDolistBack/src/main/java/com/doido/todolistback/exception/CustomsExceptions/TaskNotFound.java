package com.doido.todolistback.exception.CustomsExceptions;

public class TaskNotFound extends RuntimeException {

    public TaskNotFound(){
        super("Task not found");
    }
    
    public TaskNotFound(String menssage){
        super(menssage);
    }
}
