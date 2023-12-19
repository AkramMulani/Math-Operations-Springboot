package com.ak.mathoperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private String url = "jdbc:mysql://localhost:3306/kpit";
    private String user = "sample";
    private String password = "sample@123";
    private Connection connection;
    private Statement statement;
    private ResultSet set;
    private String table = "math_operations";

    DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public long addOperation(MathOperation operation) {
        long i=-1;
        try {
            String query = "INSERT INTO "+table+" VALUES("
                +getNewId()+",'"+operation.getName()+"','"
                +operation.getExpression()+"','"+operation.getTimestamp()+"')";
            i = statement.executeUpdate(query);
            // System.err.println("Adding operation: "+i);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return i;
    }

    public long deleteOperation(String name) {
        long i = -1;
        try {
            String query = "DELETE FROM "+table
                +" WHERE op_name='"+name+"'";
            i = statement.executeUpdate(query);
            // System.err.println("Deleting operation: "+i);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return i;
    }

    public List<MathOperation> getOperations() {
        List<MathOperation> operations = new ArrayList<MathOperation>();
        try {
            String query = "SELECT * FROM "+table;
            set = statement.executeQuery(query);
            while (set.next()) {
                String name = set.getString("op_name");
                String expression = set.getString("op_expression");
                String timestamp = set.getString("op_time_stamp");
                MathOperation operation = new MathOperation(name,expression);
                operation.setTimestamp(timestamp);
                operations.add(operation);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return operations;
    }

    private long getNewId() {
        long i=0;
        try {
            String query = "SELECT id FROM "+table;
            set = statement.executeQuery(query);
            while(set.next()) {
                i = set.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i+1;
    }
}
