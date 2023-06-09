package com.example.demo.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DateEntity;

@Transactional
@Repository
public interface DateEntityRepository extends JpaRepository<DateEntity, String> {
	
	//localhost:8080/dateApi/updateBirthday?sqlDate=2023-02-03
	@Modifying
	@Query(value = "update DateEntity e set e.birthday='2023-08-08' where e.sqlDate=:sqlDate")
	void updateBirthday(@Param("sqlDate") Date sqlDate);
	

}
