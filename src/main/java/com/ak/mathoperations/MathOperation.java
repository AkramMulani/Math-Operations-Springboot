package com.ak.mathoperations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MathOperation {
    private String name;
    private String expression;
    private String datetime;
    private int literals;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    MathOperation(String name,String expression) {
        this.name = name;
        this.expression = expression;
        datetime = LocalDateTime.now().format(formatter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTimestamp() {
        return datetime;
    }

    public void setTimestamp(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "MathOperation [name=" + name + ", expression=" + expression + ", timestamp=" + datetime + "]";
    }

    public int getLiterals() {
        return literals;
    }

    public void setLiterals(int literals) {
        this.literals = literals;
    }
    
    
}
