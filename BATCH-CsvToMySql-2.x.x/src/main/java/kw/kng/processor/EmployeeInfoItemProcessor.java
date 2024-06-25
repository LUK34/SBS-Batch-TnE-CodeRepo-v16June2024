package kw.kng.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import kw.kng.model.Employee;

@Component
public class EmployeeInfoItemProcessor implements ItemProcessor<Employee,Employee>
{
	
	@Override
	public Employee process(Employee emp) throws Exception 
	{
		//System.out.println("Item Processor:: START");
		
        if (emp.getSalary() >= 25000) 
        {
            emp.setGrossSalary(emp.getSalary() + emp.getSalary() * 0.4f);
            emp.setNetSalary(emp.getGrossSalary() - emp.getGrossSalary() * 0.2f);
            System.out.println("Data::" + emp.toString());
           // System.out.println("Item Processor:: END");
            return emp;
            
        } else 
        {
        	//System.out.println("Item Processor:: END");
            return null; // other employees will be filtered here.
        }
        
        
    }
	
	/*
	 NOTE: JobBuilderFactory and StepBuilderFactory are deprecated from springboot version 3.x
	 and marked for removal in Spring boot 5.x. So use JobBuilder, StepBuilder 
	 directly to create Job, Step objects.
	 */

}
