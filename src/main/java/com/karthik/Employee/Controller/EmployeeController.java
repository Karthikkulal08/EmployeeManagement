package com.karthik.Employee.Controller;


import com.karthik.Employee.Service.employeeService;
import com.karthik.Employee.dtos.EmployeeDTO;
import com.karthik.Employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private employeeService employeeservice;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/employee/{name}")
    public String greet(@PathVariable String name){
        return "Hi Welcome "+name;
    }


    @PostMapping("/addEmpDetails")
    public String newEmployee(@RequestBody Employee employee){
        return employeeservice.addEmpDetails(employee);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addAllEmployees")
    public List<Employee> addAllEmployees(@RequestBody List<Employee> employees){
        return employeeservice.addAllEmployees(employees);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allEmployeesSorted")
    public List<Employee> allEmployees(@RequestParam("sort") String sort){
        return employeeservice.sortedEmployees(sort);
    }

    @GetMapping("/")
    public String HomePage(){
        return "<h1>Employee</h1>";
    }

    @DeleteMapping("/deleteEmployee")
    public Employee deleteEmployee(@RequestBody Employee employee){
        return employeeservice.deleteEmployee(employee);
    }

    @PostMapping("/updateDetails")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeservice.updateEmployee(employee);
    }

    @GetMapping("/findEmployee/{id}")
    public Employee getEmployee(@PathVariable int id){

        return employeeservice.findEmployee(id);
    }

    @GetMapping("/findByName")
    public Employee findEmployeeByName(@RequestParam("name") String name){
        return employeeservice.findEmployeeByName(name);
    }

    @GetMapping("/findBySalaryRange")
    public List<Employee> findByEmployeeSalaryRange(@RequestParam("salary1")int salary1,
                                                    @RequestParam("salary2")int salary2){
        return employeeservice.findEmployeeBySalaryRange(salary1,salary2);
    }

    @GetMapping("/getEmployeeIdNameRole")
    public List<EmployeeDTO> getEmployeeIdNameRole(@RequestBody List<Employee> employee){
        return employeeservice.getEmployeeIdNameRole(employee);
    }

    @GetMapping("/getAutoSuggestion/{str}")
    public List<Employee> getAutoSuggestion(@PathVariable String str){
        return employeeservice.getAutoSuggestion(str);
    }

    @GetMapping("/getALlEmployeeResponseInThisApi")
    public List<Employee> getALlEmployeeResponseInThisApi(){
        return employeeservice.getALlEmployeeResponseInThisApi();
    }

    @PutMapping("/updateUsingPutMapping")
    public Employee updateUsingPutMapping(@RequestBody Employee employee){
        return employeeservice.updateUsingPutMapping(employee);
    }



}
