package com.ideas2it.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import com.ideas2it.dao.IEmployeeDao;
import com.ideas2it.converter.Converter;
import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.service.IEmployeeService;

public class EmployeeServiceImpl<T extends EmployeeDto> implements IEmployeeService<T> {   
	
    private static IEmployeeDao<Trainer> trainerEmployeeDao = new EmployeeDaoImpl(new Trainer());
    private static IEmployeeDao<Trainee> traineeEmployeeDao = new EmployeeDaoImpl(new Trainee());

    private T value;

    public EmployeeServiceImpl(T value) {
	this.value = value;
    }
    
    public void addEmployee(T employee) {
	if (employee instanceof TrainerDto) {
	    Trainer trainer = Converter.convertTrainerDtoToTrainer((TrainerDto)employee); 
	    trainerEmployeeDao.insertEmployee((Trainer)trainer);

        } else {
	    Trainee trainee = Converter.convertTraineeDtoToTrainee((TraineeDto)employee);
	    traineeEmployeeDao.insertEmployee((Trainee)trainee);
	}
    }
    
    public List<T> getAllEmployees() {
        if (value instanceof TrainerDto) {
	    List<TrainerDto> trainers = Converter.convertTrainerList((List<Trainer>)trainerEmployeeDao.retrieveAllEmployees());
	    return (List<T>)trainers ;
	
        } else {
	    List<TraineeDto> trainees = Converter.convertTraineeList((List<Trainee>)traineeEmployeeDao.retrieveAllEmployees());
            return (List<T>)trainees ;
        }
    }

    
    public T getEmployeeById(String employeeId) {
	if (value instanceof TrainerDto) {
	     TrainerDto trainerDto = Converter.convertTrainerToTrainerDto((Trainer)trainerEmployeeDao.retrieveEmployeeById(employeeId));
       	     return (T)trainerDto;

        } else {
	     TraineeDto traineeDto = Converter.convertTraineeToTraineeDto((Trainee)traineeEmployeeDao.retrieveEmployeeById(employeeId));
	     return (T)traineeDto;
	    
        }
    }

    public void association(List<String> employees, String employeeId) {
	
        if (value instanceof TrainerDto) {
	    System.out.println("Entered Service");
	    for (Trainer trainer : trainerEmployeeDao.retrieveAllEmployees()) {
		 System.out.println("Entered Service trainer loop");
		 if (employeeId.equals(trainer.getId())) {
		     System.out.println("Entered Service trainer loop if ");
		     List<Trainee> trainees = new ArrayList<>();
		     for (String id : employees ) {
			Trainee trainee = new Trainee();
			trainee = traineeEmployeeDao.retrieveEmployeeById(id);
			trainees.add(trainee);
		     }
                 trainer.setTrainee(trainees);
		 System.out.println("Entered Service added");
                 trainerEmployeeDao.updateEmployee(trainer);
		 System.out.println("break");
		 break;
		 }
	    
	    }
        } else {
	    for (Trainee trainee : traineeEmployeeDao.retrieveAllEmployees() ) {
	        if (employeeId.equals(trainee.getId())) {               
                    List<Trainer> trainers = new ArrayList<>();
		    for (String id : employees ) {
		        Trainer trainer = new Trainer();
			trainer = trainerEmployeeDao.retrieveEmployeeById(id);
			trainers.add(trainer);
		     }
                     trainee.setTrainer(trainers);
		     System.out.println("Entered Service added");
                     traineeEmployeeDao.updateEmployee(trainee);
		     System.out.println("break");
		     break;
		 }               
             } 
         }
     }
   
    public T displayTrainerAndTrainee(String employeeId) {
	
	if (value instanceof TrainerDto) {
	    Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
	    TrainerDto trainerDto = Converter.convertTrainerToTrainerDto(trainer);
	    List<TraineeDto> trainees = new ArrayList<>(); 
	    for (Trainee trainee : trainer.getTrainee()) {
		TraineeDto traineeDto = Converter.convertTraineeToTraineeDto(trainee);
		trainees.add(traineeDto);
	    }
	    trainerDto.setTraineeDto(trainees);
	    return (T)trainerDto;
	}
	else {
	    Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
	    TraineeDto traineeDto = Converter.convertTraineeToTraineeDto(trainee);
	    List<TrainerDto> trainers = new ArrayList<>();
	    for (Trainer trainer : trainee.getTrainer()) {
		TrainerDto trainerDto = Converter.convertTrainerToTrainerDto(trainer);
		trainers.add(trainerDto);
	    }
	    traineeDto.setTrainerDto(trainers);
	    return (T)traineeDto;
	}
    }
   

    public void  deleteEmployeeById(String employeeId) {
        if (value instanceof TrainerDto) {
	     Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
	     trainer.setDeleteStatus(0);
	     trainerEmployeeDao.updateEmployee(trainer); 
	}
	else {
	    Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
	    trainee.setDeleteStatus(0);
	    traineeEmployeeDao.updateEmployee(trainee);
	}
    }	    


    public void updateEmployeePhoneNumber(String employeeId,long phoneNumber) {

	if (value instanceof TrainerDto) {
	    Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
	    trainer.setPhoneNumber(phoneNumber);
	    trainerEmployeeDao.updateEmployee(trainer);
 	}
	else {
	    Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
	    trainee.setPhoneNumber(phoneNumber);
	    traineeEmployeeDao.updateEmployee(trainee);
	}
    }    	
}