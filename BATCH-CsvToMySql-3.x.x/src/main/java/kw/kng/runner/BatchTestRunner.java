package kw.kng.runner;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchTestRunner implements CommandLineRunner 
{
	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception 
	{
		/*
		 //I commented this code because this was again executing the job which will create duplicate record in db.
		  
		System.out.println(" --------------------------------------------------------------------------------------------");
		System.out.println("Inside BatchTestRunner Class:: START");
		//Create Job Parameters
		JobParameters params = new JobParametersBuilder().addLong("run.id", new Random().nextLong(1000)).toJobParameters();
		
		//run the job
		JobExecution execution = launcher.run(job, params);
		
		System.out.println("Job Execution status::"+execution.getExitStatus());
		System.out.println("Inside BatchTestRunner Class:: END");
		System.out.println(" --------------------------------------------------------------------------------------------");
		
		*/
		
	}

}

/*

 JobParametersBuilder:
 ----------------------- 
 A builder for JobParameters that can be used to build JobParameters in a fluent style.

.addLong("run.id", new Random().nextLong(1000)): Adds a parameter named run.id with a random long value. 
This ensures that each job instance has a unique parameter, preventing collisions and allowing the job to be executed multiple times.

.toJobParameters(): Converts the builder to an instance of JobParameters.


Running the Job:
-------------------
launcher.run(job, params): Executes the job with the specified job parameters. 
This returns a JobExecution object which provides information about the job's execution.


*/