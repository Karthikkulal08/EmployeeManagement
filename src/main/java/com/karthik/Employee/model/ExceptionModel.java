package com.karthik.Employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionModel {
    private byte[] message1;
    private String message2;
    private String message3;
    private Throwable getCause;
    //private String  printStackTrace;
    private String getLocalisedMessage;
}
