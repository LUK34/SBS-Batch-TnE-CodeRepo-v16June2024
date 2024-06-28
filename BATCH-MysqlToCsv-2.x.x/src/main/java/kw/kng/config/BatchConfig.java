package kw.kng.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import kw.kng.listener.JobMonitoringListener;
import kw.kng.model.ExamResult;
import kw.kng.processor.ExamResultItemProcessor;


@Configuration
@EnableBatchProcessing //2.7.6
public class BatchConfig 
{
	@Autowired
	private JobBuilderFactory jobFactory;
	
	@Autowired
	private StepBuilderFactory stepFactory;
	
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
	public Step createStep1()
	{
		
		return stepFactory.get("step1")
						.<ExamResult,ExamResult> chunk(5)
						.reader(createReader())
						.processor(processor)
						.writer(createWriter())
						.build();
	}
	
	
	
	@Bean(name="job1")
	public Job createJob1()
	{
		return jobFactory.get("job1")
						 .listener(listener)
						 .incrementer(new RunIdIncrementer())
						 .start(createStep1())
						 .build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
