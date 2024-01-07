package com.ak.mathoperations;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/operations")
@CrossOrigin(origins = {"https://localhost:3000","https://AkramMulani.github.io/Math-Operations-React"})
public class MathoperationsController {
    private static final DAO dao = new DAO();
    private MathOperation operation;
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/add")
    public Long addOperation(@RequestParam("name") String name,@RequestParam("exp") String expression) {
        long i=-1;
        operation = new MathOperation(name, expression);
        i = dao.addOperation(operation);
        return i;
    }

    @DeleteMapping("/del")
    public Long deleteOperation(@RequestParam("id") Long id) {
        Long i;
        i = dao.deleteOperation(id);
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

    @GetMapping("/exp")
    public String getOperationExpression(@RequestParam("name") String name) {
        String expression = "";
        for(MathOperation op:dao.getOperations()) {
            if (op.getName().equals(name)) {
                expression = op.getExpression() +"\t"+ op.getTimestamp();
            }
        }
        return expression.toString();
    }
}
