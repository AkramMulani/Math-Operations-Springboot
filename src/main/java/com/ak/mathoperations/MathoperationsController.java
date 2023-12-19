package com.ak.mathoperations;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/operations")
@CrossOrigin(origins = "https://localhost:3000")
public class MathoperationsController {
    private static final DAO dao = new DAO();
    private MathOperation operation;
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/add/name={name}/exp={expression}")
    public Long addOperation(@PathVariable String name,@PathVariable String expression) {
        long i=-1;
        operation = new MathOperation(name, expression);
        i = dao.addOperation(operation);
        return i;
    }

    @PostMapping("/del/{name}")
    public Long deleteOperation(@PathVariable String name) {
        long i=-1;
        i = dao.deleteOperation(name);
        return i;
    }
    
    @GetMapping("/all")
    public List<MathOperation> getOperations() {
        List<MathOperation> list = dao.getOperations();
        return list;
    }

    @GetMapping("/names")
    public List<String> getOperationNames() {
        List<String> names = new ArrayList<>();
        List<MathOperation> operations = dao.getOperations();
        for(MathOperation op:operations) {
            names.add(op.getName());
        }
        return names;
    }

    @GetMapping("/exp/{name}")
    public String getOperationExpression(@PathVariable String name) {
        String expression = "";
        for(MathOperation op:dao.getOperations()) {
            if (op.getName().equals(name)) {
                expression = op.getExpression() +"\t"+ op.getTimestamp();
            }
        }
        return expression.toString();
    }

    
}
