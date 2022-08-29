package com.ideas2it.service;

import java.util.List; 

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dao.IEmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;


public interface IEmployeeService<T extends EmployeeDto> {

     public void addEmployee(T employee);

     public List<T> getAllEmployees(); 
   
     public T getEmployeeById(String employeeId);    

     public void updateEmployeePhoneNumber(String employeeId,long phoneNumber);

     public void association(List<String> employees, String employeeId);

     public T displayTrainerAndTrainee(String employeeId);

     public void deleteEmployeeById(String employeeId);
    
} 


   
		 
	