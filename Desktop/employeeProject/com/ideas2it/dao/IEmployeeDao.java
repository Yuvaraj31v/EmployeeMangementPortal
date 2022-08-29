package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;

public interface IEmployeeDao<T extends Employee> {

    public void insertEmployee(T employee);

    public List<T> retrieveAllEmployees();

    public T retrieveEmployeeById(String employeeId);

    public void updateEmployee(T employee);

}  
