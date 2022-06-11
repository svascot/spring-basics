package com.spring.basics.springbasics.employee;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String employeeId) {
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String delete(String employeeId) {
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        dynamoDBMapper.delete(employee);
        return employeeId;
    }

    public Employee update(Employee employee) {
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(
                new AttributeValue().withS(employee.getId())
        );
        DynamoDBSaveExpression expression = new DynamoDBSaveExpression()
                .withExpectedEntry("id", expectedAttributeValue);
        dynamoDBMapper.save(employee, expression);
        return employee;
    }
}
