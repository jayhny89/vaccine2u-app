package com.example.demo.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String id){
        super("No Record Found For Id: " + id);
    }
}
