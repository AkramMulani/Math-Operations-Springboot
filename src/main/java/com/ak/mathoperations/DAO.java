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
        long i;
        long id = getNewId();
        if (!operation.getName().equals("") && !operation.getExpression().equals("")) {
            try {
                String query = "INSERT INTO "+table+" VALUES("
                    +id+",'"+operation.getName()+"','"
                    +operation.getExpression()+"','"+operation.getTimestamp()+"')";
                statement.executeUpdate(query);
                // System.err.println("Adding operation: "+i);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        if (checkId(id)==1){
            i = 1;
        } else {
            i = -1;
        }
        return i;
    }

    public Long deleteOperation(Long id) {
        long i;
        if (checkId(id)==-1) {
            i = -1;
        } else {
            i = 1;
        try {
            String query = "DELETE FROM "+table
                +" WHERE id="+id;
            long res = statement.executeUpdate(query);
            if (res>=0) i = 1;
            // System.err.println("Deleting operation: "+i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }}
        return i;
        
    }

    public List<MathOperation> getOperations() {
        List<MathOperation> operations = new ArrayList<MathOperation>();
        try {
            String query = "SELECT * FROM "+table;
            set = statement.executeQuery(query);
            while (set.next()) {
                long id = set.getInt("id");
                String name = set.getString("op_name");
                String expression = set.getString("op_expression");
                String timestamp = set.getString("op_timestamp");
                MathOperation operation = new MathOperation(name,expression);
                operation.setId(id);
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

    private int checkId(long id) {
        try {
            String query = "SELECT id FROM "+table;
            set = statement.executeQuery(query);
            while(set.next()) {
                if (id==set.getInt("id")){
                    return 1;
                }
            }
            return -1;
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }
}
