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
CREATE TABLE ntrjbatch.BATCH_EMPLOYEEINFO (
    EMPNO BIGINT,
    ENAME VARCHAR(200),
    EADD VARCHAR(200),
    SALARY DOUBLE,
    GROSSSALARY DOUBLE,
    NETSALARY DOUBLE
);
*/


/*
 SELECT COUNT(*) FROM ntrjbatch.batch_employeeinfo;
 
select a.empno, COUNT(a.empno)
from ntrjbatch.batch_employeeinfo a
group by a.empno
having count(a.empno)>1

//Mockeroo - I did not include unique data.

 */

