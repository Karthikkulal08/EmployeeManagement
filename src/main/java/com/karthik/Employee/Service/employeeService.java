package com.karthik.Employee.Service;

import com.karthik.Employee.dtos.DepartmentDTO;
import com.karthik.Employee.dtos.EmployeeDTO;
import com.karthik.Employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class employeeService {


    List<Employee> empList = new ArrayList<>();

    public String addEmpDetails(Employee employee) {
        Employee emp = new Employee();
        DepartmentDTO ddto = new DepartmentDTO();

            emp.setEmpId(employee.getEmpId());
            emp.setEmpName(employee.getEmpName());
            emp.setEmpRole(employee.getEmpRole());
            emp.setEmpShift(employee.getEmpShift());
            emp.setEmpSalary(employee.getEmpSalary());
            emp.setEmpAge(employee.getEmpAge());
            emp.setDepartment(employee.getDepartment());
            //gives null pointer exception if not ised.
            //emp.getDepartment().getDepartmentId();
            empList.add(emp);
            return "Success";


    }

//    public List<Employee> allEmployees(String sort) {
//        String str = sort.toUpperCase();
//        Employee temp;
//        List<Employee>sortedEmployeeList = new ArrayList<>();
//        if(str.equals("ASC")){
//            for(int i=0;i<allEmployee.size()-2;i++){
//                int min=i;
//                for(int j=i+1;j<allEmployee.size()-1;j++){
//                    if(allEmployee.get(j).getEmpId() < allEmployee.get(min).getEmpId()){
//                        min=j;
//                    }
//                    temp = allEmployee.get(i);
//                    allEmployee.add(i,allEmployee.get(j));
//                    allEmployee.add(j,temp);
//                }
//            }
//        }
//        return allEmployee;
//    }


    public Employee deleteEmployee(Employee employee) {
//        int empId = employee.getEmpId();
//        Iterator itr = empList.iterator();
//        while(itr.hasNext()){
//            Employee employee2 =(Employee) itr.next();
//            if(employee2.getEmpId() == employee.getEmpId()){
//                itr.remove();
//                return employee2;
//            }
//        }
//        return null;

        for(int i=0;i<empList.size();i++){
            if(employee.getEmpId()==empList.get(i).getEmpId()){
                empList.remove(empList.get(i));
                return employee;
            }
        }
        return null;
    }
    List<Employee>allEmployee = new ArrayList<>();
    public List<Employee> addAllEmployees(List<Employee> employees) {

		for (Employee employee : employees) {

			Employee emps = new Employee();
			emps.setEmpId(employee.getEmpId());
			emps.setEmpName(employee.getEmpName());
			emps.setEmpRole(employee.getEmpRole());
			emps.setEmpShift(employee.getEmpShift());
			emps.setEmpSalary(employee.getEmpSalary());
			emps.setEmpAge(employee.getEmpAge());
			allEmployee.add(emps);

		}
        return allEmployee;
    }

    public Employee updateEmployee(Employee employee) {
        Employee newEmployee = new Employee();
        if(employee!=null){
            for(int i=0;i<allEmployee.size();i++){
                if(allEmployee.get(i).getEmpId() == employee.getEmpId()){
                    newEmployee.setEmpId(employee.getEmpId());
                    newEmployee.setEmpName(employee.getEmpName());
                    newEmployee.setEmpRole(employee.getEmpRole());
                    newEmployee.setEmpShift(employee.getEmpShift());
                    newEmployee.setEmpSalary(employee.getEmpSalary());
                    newEmployee.setEmpAge(employee.getEmpAge());
                    allEmployee.set(i,newEmployee);
                    break;
                }
            }
        }
        return newEmployee;
    }

    public Employee findEmployee(int id) {
        return allEmployee.get(id-1);
    }

    public Employee findEmployeeByName(String name) {
        int id=0;
        for(int i=0;i<allEmployee.size();i++){
            if(allEmployee.get(i).getEmpName().equals(name)){
                id = i;
            }
        }
        return allEmployee.get(id);
    }

    public List<Employee> findEmployeeBySalaryRange(int salary1, int salary2) {
        List<Employee> salaryRangeEmployees = new ArrayList<>();
        for(int i=0;i<allEmployee.size();i++){
            if(allEmployee.get(i).getEmpSalary() >= salary1 && allEmployee.get(i).getEmpSalary() <= salary2){
                salaryRangeEmployees.add(allEmployee.get(i));
            }
        }
        return salaryRangeEmployees;
    }

    public List<EmployeeDTO> getEmployeeIdNameRole(List<Employee> employee) {
        List<EmployeeDTO>empDTO = new ArrayList<>();
        for(int i=0;i<employee.size();i++){
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmpId(employee.get(i).getEmpId());
            dto.setEmpName(employee.get(i).getEmpName());
            dto.setEmpRole(employee.get(i).getEmpRole());
            empDTO.add(dto);
        }
        return empDTO;
    }


    public List<Employee> sortedEmployees(String sort) {
        String str = sort.toUpperCase();

        if(str == "ASC"){
            Comparator<Employee> emp = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if(o1.getEmpId() <= o2.getEmpId())
                        return -1;

                    return 1;
                }
            };
            Collections.sort(allEmployee,emp);
        }else if(str == "DSC"){
            Comparator<Employee> emp = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if(o1.getEmpId() >= o2.getEmpId())
                        return -1;

                    return 1;
                }
            };
            Collections.sort(allEmployee,emp);
        }
        return allEmployee;
    }


    public List<Employee> getAutoSuggestion(String str) {
        List<Employee>tempList = new ArrayList<>();
        for(int i=0;i<allEmployee.size();i++){
            if(allEmployee.get(i).getEmpName().toLowerCase().contains(str.toLowerCase()) || allEmployee.get(i).getEmpRole().toLowerCase().contains(str.toLowerCase()) || allEmployee.get(i).getEmpShift().toLowerCase().contains(str.toLowerCase())){
                tempList.add(allEmployee.get(i));
            }
        }
        return tempList;
    }

    public List<Employee> getALlEmployeeResponseInThisApi() {
        RestTemplate rs = new RestTemplate();
        String url = "/employee/{name}";
        String str = "Keeeeeeeeeeee";
        HttpEntity ht = new HttpEntity(str);
        List employees = (List)rs.exchange("/employee/{name}", HttpMethod.GET,ht,String.class);

        return employees;
    }


    public Employee updateUsingPutMapping(Employee employee) {
        Employee newEmployee = new Employee();
        if(employee!=null){
            for(int i=0;i<allEmployee.size();i++){
                if(allEmployee.get(i).getEmpId() == employee.getEmpId()){
                    newEmployee.setEmpId(employee.getEmpId());
                    newEmployee.setEmpName(employee.getEmpName());
                    newEmployee.setEmpRole(employee.getEmpRole());
                    newEmployee.setEmpShift(employee.getEmpShift());
                    newEmployee.setEmpSalary(employee.getEmpSalary());
                    newEmployee.setEmpAge(employee.getEmpAge());
                    allEmployee.set(i,newEmployee);
                    break;
                }
            }
        }return newEmployee;
    }
}
