package kw.kng.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult 
{
	@Id
    private Long id;    
    private Date dob;
    private Integer semester;
    private Float percentage;
}



/*

 create table ntrjbatch.EXAM_RESULT
(
	id bigint(20) NOT NULL auto_increment,
    dob timestamp not null default current_timestamp,
    semester int(11) default null,
    percentage float default null,
    primary key(id)
);

DELIMITER $$
CREATE PROCEDURE generate_EXAM_RESULT()
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 500 DO
        INSERT INTO ntrjbatch.EXAM_RESULT (dob, percentage, semester)
        VALUES (
            FROM_UNIXTIME(UNIX_TIMESTAMP('2000-01-01 01:00:00') + FLOOR(RAND() * 31536000)),
            ROUND(RAND() * 100, 2),
            1
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

call generate_EXAM_RESULT();
 
select * from exam_result;

 */










