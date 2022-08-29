package com.ideas2it.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.criterion.Restrictions;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Criteria;
import com.ideas2it.singleton.ConnectionEstablishment;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dao.IEmployeeDao;

public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> { 
  
    private T value;
   
    public EmployeeDaoImpl(T value) {
	this.value = value;
    }
    
    public void insertEmployee(T employee) {

        try (Session session  = ConnectionEstablishment.getConnection();) {	    
    	    Transaction transaction =session.beginTransaction();
             if (employee instanceof Trainer)  {
	         Trainer trainer = (Trainer)employee;	   
	         session.persist(trainer);
	     }	  	              		    	        	            
	     else {
        		     
	         Trainee trainee = (Trainee)employee;
	         session.persist(trainee);
	      }
	      transaction.commit();
	 }
	 catch(Exception e ) {
	     e.printStackTrace();
	}			    		 	
    }
    
    public List<T> retrieveAllEmployees() {

	List<T> employees = new ArrayList<>();	     
        try (Session session  = ConnectionEstablishment.getConnection();) {
            Transaction transaction =session.beginTransaction();	
                if (value instanceof Trainer) {	    		        	      		
	            Criteria criteria = session.createCriteria(Trainer.class);
	            criteria.add(Restrictions.eq("deleteStatus",1));
	            employees = criteria.list();  
	            transaction.commit();  
	        } else {	    
	            Criteria criteria = session.createCriteria(Trainee.class);
	            criteria.add(Restrictions.eq("deleteStatus",1));
	            employees = criteria.list();
	            transaction.commit();
	        } 	
	}
	catch(Exception e ) {
	    e.printStackTrace();
	}
	return (List<T>)employees;
    }

    public T retrieveEmployeeById(String employeeId) {

	T employee = null;
	
        try (Session session  = ConnectionEstablishment.getConnection();) {
    	    Transaction transaction =session.beginTransaction(); 
	    if (value instanceof Trainer) {   
		List<Trainer> trainers = new ArrayList<>();
                Criteria criteria = session.createCriteria(Trainer.class);
	        criteria.add(Restrictions.eq("employeeId",employeeId)).add(Restrictions.eq("deleteStatus",1));
	        trainers = criteria.list();
		if  (trainers.size() >0 ) { 
		    employee = (T)trainers.get(0);
		}
		transaction.commit();
		
	    } else {	        	    	       
	         List<Trainee> trainees = new ArrayList<>();
	         Criteria criteria = session.createCriteria(Trainee.class);
	         criteria.add(Restrictions.eq("employeeId",employeeId));
	         trainees = criteria.list(); 
		 if  (trainees.size() >0 ) { 
		    employee = (T)trainees.get(0);
		 }
		 transaction.commit();
	    } 
	}
	catch(Exception e ) {
	    e.printStackTrace();
	}	   	   
	return (T)employee;
    }

    public void updateEmployee(T employee) {
	try (Session session  = ConnectionEstablishment.getConnection();) {
	    Transaction transaction =session.beginTransaction();
            if (value instanceof Trainer) {	    
                session.update((Trainer)employee);
	    }
	    else {
               session.update((Trainee)employee);	    
	    }
	    transaction.commit();
	}
	catch(Exception e ) {
            e.printStackTrace();	   
	}       
    }
}	
      
    

    
   