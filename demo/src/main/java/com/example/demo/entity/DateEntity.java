package com.example.demo.entity;

import java.sql.Date;
import java.time.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Table(name = "DateEntity")
@Data
public class DateEntity {
    @Id
    private String dateNumber;

    @ColumnDefault("2023-01-01")
	@Column
    private LocalDate birthday;
    
	
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
	@Column
    private Date sqlDate;

    
    
    // other fields, getters and setters
}

//insert  into DATEENTITY(DATENUMBER,BIRTHDAY,SQLDATE,UPDATED_ON) values('1','2023-02-02','2023-02-03', '2023-02-04'); 