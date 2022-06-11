package com.spring.basics.springbasics.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee getEmployeeById(String employeeId) {
        return repository.getEmployeeById(employeeId);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public String delete(String employeeId) {
        return repository.delete(employeeId);
    }

    public Employee update(Employee employee){
        return repository.update(employee);
    }
}
