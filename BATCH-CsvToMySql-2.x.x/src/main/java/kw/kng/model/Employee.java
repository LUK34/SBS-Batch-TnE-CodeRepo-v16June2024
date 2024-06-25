package kw.kng.model;

import lombok.Data;

@Data
public class Employee 
{
	private Integer empno;
	private String ename;
	private String eadd;
	private Float salary;
	private Float grossSalary;
	private Float netSalary;

}

/*
CREATE TABLE BATCH_EMPLOYEEINFO (
    EMPNO BIGINT,
    ENAME VARCHAR(20),
    EADD VARCHAR(200),
    SALARY DOUBLE,
    GROSSSALARY DOUBLE,
    NETSALARY DOUBLE
);
*/