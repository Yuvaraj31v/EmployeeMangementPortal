package com.ideas2it.entity;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table (name ="Trainees")
public class Trainee extends Employee {

    
    @Id   
    @GeneratedValue(strategy=GenerationType.TABLE, generator="trainees")
    @SequenceGenerator(
	    name = "trainees",
	    sequenceName = "trainee_sequence")	    
    private long id;    
  
    private String employeeId;

    private String employeeFirstName;

    private String employeeLastName;

    private long employeePhoneNumber;

    private LocalDate employeeDateOfBirth;

    private LocalDate employeeDateOfJoin;

    private String employeeEmailId;

    private int deleteStatus = 1; 
        
    
    @ManyToMany(cascade = CascadeType.ALL,mappedBy="trainees") 
    private List<Trainer> trainers;

    public List<Trainer> getTrainer(){
	return trainers;
    }
        
    public void setDeleteStatus(int value) {
	this.deleteStatus = value;
    }
                                
    public void setTrainer(List<Trainer> trainer) {
	this.trainers = trainer;
    }


    public void setId(String id) {
	this.employeeId = id;
    }   

    public String getId() {
	return employeeId;
    }

    public void setFirstName(String firstName) {
	this.employeeFirstName = firstName;
    }

    public String getFirstName() {
	return employeeFirstName;
    }

    public void setLastName(String lastName) {
	this.employeeLastName = lastName;
    }

    public String getLastName() {
	return employeeLastName;
    }

    public void setPhoneNumber(long phoneNumber) {
	this.employeePhoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
	return employeePhoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.employeeDateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
	return  employeeDateOfBirth ;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
	this.employeeDateOfJoin = dateOfJoin;
    }

     public LocalDate getDateOfJoin() {
	return  employeeDateOfJoin;
    }

    public void setEmailId(String emailId) {
	this.employeeEmailId = emailId;
    }

    public String getEmailId() {
	return  employeeEmailId;
    }
	
}