import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import com.ideas2it.service.IEmployeeService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.util.Utility;
import com.ideas2it.exception.EmptyListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;


public class EmployeeController {

    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    private IEmployeeService<TrainerDto> trainerDtoEmployeeService = new EmployeeServiceImpl(new TrainerDto());
    private IEmployeeService<TraineeDto> traineeDtoEmployeeService = new EmployeeServiceImpl(new TraineeDto());

    public static void main( String[] args) {
        EmployeeController controller = new EmployeeController ();
	System.out.println("Welcome to Ideas2IT Employee management portal!!\n");
        controller.init();
    }

     /**
     * used to interact with user
     * @return {@link void}
     */
    public void init() {
	boolean isContinue = true;
        while (isContinue) {
	    try {		
                logger.info("Enter 1 to add Employee");
            	logger.info("Enter 2 to display Employe ");
	    	logger.info("Enter 3 to asociate Employee");
	    	logger.info("Enter 4 to display Trainers and Trainees");
	    	logger.info("Press 5 to update Employee Phone Number");
	    	logger.info("Press 6 to delete Employee records");	    
            	logger.info("Enter any other to exit");
	    	int userChoice = scanner.nextInt();	
	    	switch (userChoice) {

            	case 1: 
	            createEmployee();
	            break;
    
	        case 2: 
	            logger.info("Press 1 to display trainer details");
	            logger.info("Press 2 to display trainee details");
	            logger.info("Press 3 to dispaly Trainer or Trainee by entering their EmployeId"); 
	            int usersChoice = scanner.nextInt();
	            if (usersChoice == 1 ) {
			try {
	                    displayAllTrainers();
			}
			catch (EmptyListException  e ) {
			    logger.info(e.getMessage());
			}
		    } else if (usersChoice == 2) {
	                displayAllTrainees();
		    } 
		    else if(usersChoice == 3) {
	                displayEmployeById();
		    }
                    break;
	    
	        case 3:
                    associateTrainersAndTrainees();
	            break;

                case 4:
		    displayTrainersAndTrainees();
		    break;
		
	        case 5:
		    updateEmployeePhoneNumber();
		    break;

	        case 6 :
		    deleteEmployeeById ();
		    break;
		
	        default:
	            isContinue = false;
	      
       	        }
	    }
	    catch (InputMismatchException ex) {
		scanner.next();
		logger.info("Invalid Input please Enter again");
	    }
        }
    }

    /**
     * used to gather and set employee details
     * @param userInput {@link int} input from user  
     * @return {@link void} 
     */     
	
    public void createEmployee() throws InputMismatchException{
	String employeeFirstName,employeeLastName,employeeDateOfBirth,employeeDateOfJoin;
	long employeePhoneNumber;	       
	logger.info("Press 1 to add Trainer details");
	logger.info("Press 2 to add Trainees details");
        int userChoice = scanner.nextInt();
	if (userChoice == 1 || userChoice == 2 ) {
	    logger.info("Enter Number of detail to add");
        int employeesToAdd = scanner.nextInt();
	        
            for (int i = 0; i < employeesToAdd; i++) {
 	        logger.info("Enter First name");
	        employeeFirstName = scanner.next();
		scanner.next();
	        logger.info("Enter Last name");
	        employeeLastName = scanner.next();
	        logger.info("Enter DOB in YYYY-MM-DD format");
	        employeeDateOfBirth= scanner.next();
	        LocalDate dateOfBirth = LocalDate.parse(employeeDateOfBirth);
	        logger.info("Enter PhoneNumber");
	        employeePhoneNumber = scanner.nextLong();	    	    	               
	        logger.info("Enter DateOfJoin");
	        employeeDateOfJoin = scanner.next();	    
	        LocalDate dateOfJoin = LocalDate.parse(employeeDateOfJoin);
	        int yearOfJoin = Utility.extractYearFromDate(employeeDateOfJoin);
	        String employeeId = Utility.generateEmployeeId(yearOfJoin);
	        String emailId = Utility.generateMailId(employeeFirstName,employeeLastName);
	        logger.info("Your employee id is "+ employeeId+"\n"+" Email id is"+ emailId);
	        logger.info("Kindly take a note of it");
	 
	        if (userChoice == 1) {			

		    TrainerDto trainerDto = new TrainerDto(employeeId,employeeFirstName ,employeeLastName, employeePhoneNumber,dateOfBirth,dateOfJoin,emailId);
		    trainerDtoEmployeeService.addEmployee(trainerDto);

	        } else if ( userChoice == 2 ) {

		    TraineeDto traineeDto = new TraineeDto(employeeId,employeeFirstName ,employeeLastName, employeePhoneNumber,dateOfBirth,dateOfJoin,emailId);
		    traineeDtoEmployeeService.addEmployee(traineeDto); 
	        }
	    }
        }
	else {
	    logger.info("Invalid input ");
	}
    } 

    /**
     * used to display trainers details
     * @return {@link void} 
     */    

     public void displayAllTrainers() throws EmptyListException{

	if (trainerDtoEmployeeService.getAllEmployees().size() == 0) {
	    throw new EmptyListException("List is Empty");
	}
	else {
	    for (TrainerDto trainerDto : trainerDtoEmployeeService.getAllEmployees() ) {
	        logger.info(trainerDto.toString());
            }
	}    	
    }

    public void displayAllTrainees() {

	for (TraineeDto traineeDto : traineeDtoEmployeeService.getAllEmployees() ) {
	    logger.info(traineeDto.toString());
        }
    } 

    public void  displayEmployeById() {
	
	    logger.info("Press 1 to display respective Trainer by entering their ");
	    logger.info("Press 2 to display respective Trainee by entering their");
	    int choiceOfUser = scanner.nextInt();
	    if (choiceOfUser == 1) {
		String employeeId= getEmployeeIdFromUser();
	        if (trainerDtoEmployeeService.getEmployeeById(employeeId) != null) {
	            TrainerDto trainerDto = trainerDtoEmployeeService.getEmployeeById(employeeId);
	            logger.info(trainerDto.toString());
	        }
	        else {
		     logger.info("NO SUCH ID");
	        }
	    } 
	    else if (choiceOfUser == 2) {
		String employeeId= getEmployeeIdFromUser();
	        if (traineeDtoEmployeeService.getEmployeeById(employeeId) != null) {
	            TraineeDto traineeDto = traineeDtoEmployeeService.getEmployeeById(employeeId);
	            logger.info(traineeDto.toString());
	        }
	        else {
		    logger.info("NO SUCH ID");
	        }
	    }
    }

   public String getEmployeeIdFromUser() {
	logger.info("Enter Employee Id");
	String employeeId = scanner.next();
	return employeeId;
    }


  public void associateTrainersAndTrainees() throws InputMismatchException {
	logger.info("Enter 2 to add trainees to trainer");
	logger.info("Enter 1 to add trainers to trainee");
	
	int choiceOfUser = scanner.nextInt();	
	if (choiceOfUser == 1) {	
	    List<String> trainersDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            String traineeId = scanner.nextLine();

            logger.info("Enter the Trainers Id you want to add");
            
            String[] trainerIds = scanner.nextLine().split(",");
            for (int i = 0; i<trainerIds.length; i++) {
                trainersDto.add(trainerIds[i]);
            }
            traineeDtoEmployeeService.association(trainersDto, traineeId);
   	}

	else if (choiceOfUser  == 2 ) {
	    List<String> traineesDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            String trainerId = scanner.nextLine();

            logger.info("Enter the Trainees Id you want to add");
            
            String[] traineeIds = scanner.nextLine().split(",");

            for (int i = 0; i<traineeIds.length; i++) {
                traineesDto.add(traineeIds[i]);
            }
	    trainerDtoEmployeeService.association(traineesDto, trainerId);
         }	    
     }

     public void displayTrainersAndTrainees() throws InputMismatchException {

	
	    logger.info("Enter 1 to get details by trainer Id ");
	    logger.info("Enter 2 to get details by Trainee Id ");
	    int choice = scanner.nextInt();
	    if (choice == 1) {
		logger.info("Enter Trainer Id");
	        String employeeId = scanner.next();
		TrainerDto trainerDto = trainerDtoEmployeeService.displayTrainerAndTrainee(employeeId);
		logger.info("Trainer details "+ trainerDto.toString());
		for (TraineeDto traineeDto : trainerDto.getTraineeDto()) {
		    logger.info(traineeDto.toString());
		}    
                
            }
	    else if (choice == 2 ) {
		logger.info("Enter Trainee Id");
	        String employeeId = scanner.next();
                TraineeDto traineeDto = traineeDtoEmployeeService.displayTrainerAndTrainee(employeeId);
		logger.info("Trainee details "+traineeDto.toString());
		for (TrainerDto trainerDto : traineeDto.getTrainerDto()) {
		    logger.info("Trainer details"+trainerDto.toString());
		    }    
                
	    }       
    }
	    
    public void updateEmployeePhoneNumber() throws InputMismatchException {
	logger.info("Enter 1 to update Trainer phoneNumber");
	logger.info("Enter 2 to update Trainee phoneNumber");
	int choice = scanner.nextInt();
	
	if (choice == 1) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (TrainerDto trainerDto: trainerDtoEmployeeService.getAllEmployees()) {
	        if ((trainerDto.getEmployeeId()).equals(employeeId)) {
		    logger.info("Enter phoneNumber to update"); 
	            long employeePhoneNumber = scanner.nextLong();
	            trainerDtoEmployeeService.updateEmployeePhoneNumber(employeeId,employeePhoneNumber);
		    flag =false;
		    break;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	    
	}
	else if(choice == 2) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (TraineeDto traineeDto: traineeDtoEmployeeService.getAllEmployees()) {
	        if ((traineeDto.getEmployeeId()).equals(employeeId)) {
		    logger.info("Enter phoneNumber to update"); 
	            long employeePhoneNumber = scanner.nextLong();
	            traineeDtoEmployeeService.updateEmployeePhoneNumber(employeeId,employeePhoneNumber);
		    flag =false;
		    break;
		}
	    }
	    if (flag == false) {
		logger.info("updated successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
    }

   public void deleteEmployeeById () throws InputMismatchException  {
	logger.info("Enter 1 to delete Trainer details");
	logger.info("Enter 2 to delete Trainee details");
	int choice = scanner.nextInt();
	if (choice == 1) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (TrainerDto trainerDto: trainerDtoEmployeeService.getAllEmployees()) {
	        if ((trainerDto.getEmployeeId()).equals(employeeId)) {
		    trainerDtoEmployeeService.deleteEmployeeById(employeeId);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("deleted successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
	else if(choice == 2) {
	    String employeeId= getEmployeeIdFromUser();
	    boolean flag = true;
	    for (TraineeDto traineeDto: traineeDtoEmployeeService.getAllEmployees()) {
	        if ((traineeDto.getEmployeeId()).equals(employeeId)) {
		    traineeDtoEmployeeService.deleteEmployeeById(employeeId);
		    flag =false;
		}
	    }
	    if (flag == false) {
		logger.info("deleted successfully");
	    } 
	    else {
		logger.info("There is no such Id ");
	    }
	}
    }
}
	    