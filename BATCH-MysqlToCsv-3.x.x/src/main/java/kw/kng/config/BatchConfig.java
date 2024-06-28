package kw.kng.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import kw.kng.listener.JobMonitoringListener;
import kw.kng.model.ExamResult;
import kw.kng.processor.ExamResultItemProcessor;


@Configuration
//@EnableBatchProcessing 					//3.3.1
public class BatchConfig 
{
	/* 
	  	NOTE: If the version of Spring boot is greater than 3.x.x than disable @EnableBatchProcessing 
	 
	 */
	
	@Autowired
	private JobMonitoringListener listener;
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private ExamResultItemProcessor processor;
	
	@Value("${file.loca}")
	private String fileLoca;
	
	
	//-------------------------------------------------------------------------------------------------
										// READER
	
	//Builder class method chaining approach ->BEST APPROACH
	@Bean(name="jcir") 
	public JdbcCursorItemReader<ExamResult> createReader()
	{
		System.out.println("BatchConfig.createReader() :: START");
		
		JdbcCursorItemReader<ExamResult> reader = new JdbcCursorItemReaderBuilder<ExamResult>()
					.name("reader")
					.dataSource(ds)
					.sql("SELECT ID,DOB,SEMESTER,PERCENTAGE FROM EXAM_RESULT")
					.beanRowMapper(ExamResult.class)
					.build();
		
		System.out.println("BatchConfig.createReader() :: END");
		
		return reader;
	}
	//-------------------------------------------------------------------------------------------------
									//WRITER
	
	//Builder class method chaining approach ->BEST APPROACH
	@Bean(name="ffiw")
	public FlatFileItemWriter<ExamResult> createWriter()
	{
		System.out.println("BatchConfig.createWriter() :: START");
		
		System.out.println("File location:: " + fileLoca);
		
		FlatFileItemWriter<ExamResult> writer= new FlatFileItemWriterBuilder<ExamResult>()
						.name("writer")
						.resource(new FileSystemResource(fileLoca))
						.lineSeparator("\r\n")
						.delimited()
						.delimiter(",")
						.names("id","dob","semester","percentage")
						.build();
		 
		 System.out.println("BatchConfig.createWriter() :: END");
		 
		 return writer;	 
	}
	
	//-------------------------------------------------------------------------------------------------
	
	

	@Bean(name="step1")
	public Step createStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager)
	{
		
		return new StepBuilder("step1", jobRepository)
						.<ExamResult,ExamResult> chunk(5, transactionManager)
						.reader(createReader())
						.processor(processor)
						.writer(createWriter())
						.build();
	}
	
	
	
	@Bean(name="job1")
	public Job createJob1(JobRepository jobRepository, Step step1)
	{
		return new JobBuilder("job1",jobRepository)
						 .listener(listener)
						 .incrementer(new RunIdIncrementer())
						 .start(step1)
						 .build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
