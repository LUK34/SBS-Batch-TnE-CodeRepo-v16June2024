package kw.kng.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMonitoringListener implements JobExecutionListener
{
	private long start, end;
	
	@Override
	public void beforeJob(JobExecution jobExecution) 
	{
		start=System.currentTimeMillis();
		System.out.println("Job ia about to start @"+new Date());
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) 
	{
		end= System.currentTimeMillis();
		System.out.println("Job is completed at:: "+new Date());
		System.out.println("Job Execution time:: "+(end-start)+" ms");
		System.out.println("Job is Execution Status:: "+ jobExecution.getStatus());
	}

	
	
	
	
}
