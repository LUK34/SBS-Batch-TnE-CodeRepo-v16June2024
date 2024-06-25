package kw.kng.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import kw.kng.listener.JobMonitoringListener;
import kw.kng.model.Employee;
import kw.kng.processor.EmployeeInfoItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig	//SpringBoot version: 2.7.6
{
	/*
	 NOTE: JobBuilderFactory and StepBuilderFactory are deprecated from springboot version 3.x
	 and marked for removal in Spring boot 5.x. So use JobBuilder, StepBuilder 
	 directly to create Job, Step objects.
	 */
	@Autowired
	private JobBuilderFactory jobBuilder;
	@Autowired
	private StepBuilderFactory stepBuilder;
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private EmployeeInfoItemProcessor processor;
	@Autowired
	private DataSource dataSource;
	
	//------------------------------------------------------------------------------------------------------------------------------------------
														//FLAT FILE ITEM READER
	
	//1. Traditional Approach
	/*
	@Bean(name="ffiReader")
	public FlatFileItemReader<Employee> createFFIReader()
	{
		System.out.println("FlatFileItemReader:: BEGIN");
		//create Reader Object
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
		
		//set csv file as resource
		reader.setResource(new ClassPathResource("EmployeeInfo.csv"));
		
		//Skip the header row of the csv file
		reader.setLinesToSkip(1);
		
		System.out.println("Reader:: "+reader);
		
		//Create LineMapper obj ( To get each line from CSV file)
		DefaultLineMapper<Employee> lineMapper= new DefaultLineMapper<Employee>();
		
		//create LineTokenizer (To get Tokens from lines)
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames("empno","ename","eadd","salary");
		
		//create FieldSetMapper (To set the tokens to Model class object properties)
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<Employee>();
		fieldSetMapper.setTargetType(Employee.class);
		
		//set Tokenizer, fieldSetMapper objects to LineMapper
		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.setLineTokenizer(tokenizer);
		
		System.out.println("Line Mapper:: "+lineMapper);
		
		//set LineMapper to Reader object
		reader.setLineMapper(lineMapper);

		System.out.println("FlatFileItemReader:: END");
		
		return reader;
	}
	*/
	
	
	// 2. Builder Design Pattern with Method Chaining -> FlatFileItemReader -> BEST
	@Bean(name="ffiReader")
	public FlatFileItemReader<Employee> createFFIReader()
	{
		System.out.println("BatchConfig.createFFIReader():: START");
		
		FlatFileItemReader<Employee> reader = new FlatFileItemReaderBuilder<Employee>()
	            .name("file-Reader") // Set reader name
	            .resource(new ClassPathResource("EmployeeInfo.csv")) // Set CSV file as resource
	            .linesToSkip(1) // Skip the header row of the CSV file
	            .delimited() // Define the format of the file as delimited
	            .names("empno", "ename", "eadd", "salary") // Define the column names
	            .targetType(Employee.class)
	            .build(); // Build the reader
		
		System.out.println("BatchConfig.createFFIReader():: END");
		
		return reader;

	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------
													
													//JDBC ITEM WRITER
	
	//1. Traditional Approach
	/*
	@Bean(name="jbiw")
	public JdbcBatchItemWriter<Employee> createJBIWriter()
	{
		System.out.println("JdbcBatchItemWriter:: BEGIN");
		
		//create JdbcBatchItemWriter
		JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<Employee>();
		
		System.out.println("Data Source "+ dataSource);
		
		//set Datasource
		writer.setDataSource(dataSource);
		
		//set INSERT SQL QUERY with named params
		writer.setSql("INSERT INTO BATCH_EMPLOYEEINFO VALUES(:empno,:ename,:eadd,:salary,:grossSalary,:netSalary)");
		
		//create BeanPropertyItemSqlParameterSourceProvider Object
		BeanPropertyItemSqlParameterSourceProvider<Employee> sourceProvider = new BeanPropertyItemSqlParameterSourceProvider<Employee>();
		
		//set SourceProvider to writer object
		writer.setItemSqlParameterSourceProvider(sourceProvider);
		
		System.out.println("JdbcBatchItemWriter:: END");
		
		return writer;
	}
	*/
	
	// 2. Builder Design Pattern with Method Chaining -> JdbcBatchItemWriter ->BEST
	@Bean(name="jbiw")
	public JdbcBatchItemWriter<Employee> createJBIWriter()
	{
	   System.out.println("BatchConfig.createJBIWriter() :: START");

	    JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriterBuilder<Employee>()
	            .dataSource(dataSource) // Set the DataSource
	            .sql("INSERT INTO BATCH_EMPLOYEEINFO VALUES(:empno,:ename,:eadd,:salary,:grossSalary,:netSalary)") // Set the SQL query with named params
	            .beanMapped() // Create and use a BeanPropertyItemSqlParameterSourceProvider
	            .build(); // Build the writer

	    System.out.println("BatchConfig.createJBIWriter() :: END");

	    return writer;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	
	

	@Bean(name="step1")
	public Step createStep1()
	{
		
		return stepBuilder.get("step1")
						.<Employee,Employee> chunk(5)
						.reader(createFFIReader())
						.processor(processor)
						.writer(createJBIWriter())
						.build();
	}
	
	@Bean(name="job1")
	public Job createJob()
	{
		return jobBuilder.get("job1")
						 .listener(listener)
						 .incrementer(new RunIdIncrementer())
						 .start(createStep1())
						 .build();
	}
	
	
	
	
	
	
	
	
	


}
