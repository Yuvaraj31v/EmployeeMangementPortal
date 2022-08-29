package com.ideas2it.converter;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.entity.Trainee;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import java.util.List;
import java.util.ArrayList;


public class Converter {

    public static Trainer convertTrainerDtoToTrainer(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
	trainer.setId(trainerDto.getEmployeeId());
	trainer.setFirstName(trainerDto.getEmployeeFirstName());
	trainer.setLastName(trainerDto.getEmployeeLastName());
	trainer.setPhoneNumber(trainerDto.getEmployeePhoneNumber());
	trainer.setDateOfBirth(trainerDto.getEmployeeDateOfBirth());
	trainer.setDateOfJoin(trainerDto.getEmployeeDateOfJoin());
	trainer.setEmailId(trainerDto.getEmployeeEmailId());
        return trainer;
    }


    public static TrainerDto convertTrainerToTrainerDto(Trainer trainer) {
	TrainerDto trainerDto =  new TrainerDto(trainer.getId(),trainer.getFirstName(),trainer.getLastName(),trainer.getPhoneNumber(),trainer.getDateOfBirth(),trainer.getDateOfJoin(),trainer.getEmailId());
        return trainerDto;
    }

    public static Trainee convertTraineeDtoToTrainee(TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
	trainee.setId(traineeDto.getEmployeeId());
	trainee.setFirstName(traineeDto.getEmployeeFirstName());
	trainee.setLastName(traineeDto.getEmployeeLastName());
	trainee.setPhoneNumber(traineeDto.getEmployeePhoneNumber());
	trainee.setDateOfBirth(traineeDto.getEmployeeDateOfBirth());
	trainee.setDateOfJoin(traineeDto.getEmployeeDateOfJoin());
	trainee.setEmailId(traineeDto.getEmployeeEmailId());
        return trainee;
    }

    public static TraineeDto convertTraineeToTraineeDto(Trainee trainee) {
	TraineeDto traineeDto =  new TraineeDto(trainee.getId(),trainee.getFirstName(),trainee.getLastName(),trainee.getPhoneNumber(),trainee.getDateOfBirth(),trainee.getDateOfJoin(),trainee.getEmailId());
        return traineeDto;
    }

    public static List<Trainee> convertTraineeDtoToModel(List<TraineeDto> traineeDtos) {
	
	List<Trainee> trainees = new ArrayList<>();
	for (TraineeDto traineeDto : traineeDtos) {
	    Trainee trainee = convertTraineeDtoToTrainee(traineeDto);
	    trainees.add(trainee);
	}
	return trainees;
    }

     public static List<Trainer> convertTrainerDtoToModel(List<TrainerDto> trainerDtos) {
	
	List<Trainer> trainers = new ArrayList<>();
	for (TrainerDto trainerDto : trainerDtos) {
	    Trainer trainer = convertTrainerDtoToTrainer(trainerDto);
	    trainers.add(trainer);
	}
	return trainers;
    }

	     

    public static List<TraineeDto> convertTraineeList(List<Trainee> trainees) {
	List<TraineeDto> traineeDtos = new ArrayList<>();
	
	TraineeDto traineeDto = null; 
	for (Trainee trainee : trainees) {
	    traineeDto = convertTraineeToTraineeDto(trainee);
	    traineeDtos.add(traineeDto);
	}
	return traineeDtos;
    }

    public static Trainer convertEmployeeDtoToTrainer(EmployeeDto employeeDto) {
	
	Trainer trainer = new Trainer();
	trainer.setId(employeeDto.getEmployeeId());
	trainer.setFirstName(employeeDto.getEmployeeFirstName());
	trainer.setLastName(employeeDto.getEmployeeLastName());
	trainer.setPhoneNumber(employeeDto.getEmployeePhoneNumber());
	trainer.setDateOfBirth(employeeDto.getEmployeeDateOfBirth());
	trainer.setDateOfJoin(employeeDto.getEmployeeDateOfJoin());
	trainer.setEmailId(employeeDto.getEmployeeEmailId());
	return trainer;
    }

    public static Trainee convertEmployeeDtoToTrainee(EmployeeDto employeeDto) {
	Trainee trainee = new Trainee();
	trainee.setId(employeeDto.getEmployeeId());
	trainee.setFirstName(employeeDto.getEmployeeFirstName());
	trainee.setLastName(employeeDto.getEmployeeLastName());
	trainee.setPhoneNumber(employeeDto.getEmployeePhoneNumber());
	trainee.setDateOfBirth(employeeDto.getEmployeeDateOfBirth());
	trainee.setDateOfJoin(employeeDto.getEmployeeDateOfJoin());
	trainee.setEmailId(employeeDto.getEmployeeEmailId());
	return trainee;
    }
		

    public static List<TrainerDto> convertTrainerList(List<Trainer> trainers) {
	List<TrainerDto> trainerDtos = new ArrayList<>();
	
	TrainerDto trainerDto = null;
	
	for (Trainer trainer : trainers) {
	    trainerDto = convertTrainerToTrainerDto(trainer);
	    trainerDtos.add(trainerDto);
	  
	}
	return trainerDtos;
    }

    public static List<Trainee> convertEmployeeDtoListtoTrainee(List<EmployeeDto> employees) {
	List<Trainee> trainees = new ArrayList<>();
	for(EmployeeDto employeeDto : employees) {
	    Trainee trainee = new Trainee();
	    trainee.setId(employeeDto.getEmployeeId());
	    trainee.setFirstName(employeeDto.getEmployeeFirstName());
	    trainee.setLastName(employeeDto.getEmployeeLastName());
	    trainee.setPhoneNumber(employeeDto.getEmployeePhoneNumber());
	    trainee.setDateOfBirth(employeeDto.getEmployeeDateOfBirth());
	    trainee.setDateOfJoin(employeeDto.getEmployeeDateOfJoin());
	    trainee.setEmailId(employeeDto.getEmployeeEmailId());
	    trainee.setDeleteStatus(1);
	    trainees.add(trainee);
	}
	return trainees;
    }

    public static List<Trainer> convertEmployeeDtoListtoTrainer(List<EmployeeDto> employees) {
	List<Trainer> trainers = new ArrayList<>();
	for(EmployeeDto employeeDto : employees) {
	    Trainer trainee = new Trainer();
	    trainee.setId(employeeDto.getEmployeeId());
	    trainee.setFirstName(employeeDto.getEmployeeFirstName());
	    trainee.setLastName(employeeDto.getEmployeeLastName());
	    trainee.setPhoneNumber(employeeDto.getEmployeePhoneNumber());
	    trainee.setDateOfBirth(employeeDto.getEmployeeDateOfBirth());
	    trainee.setDateOfJoin(employeeDto.getEmployeeDateOfJoin());
	    trainee.setEmailId(employeeDto.getEmployeeEmailId());
	    trainee.setDeleteStatus(1);
	    trainers.add(trainee);
	}
	return trainers;
    }
}
