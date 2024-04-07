package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@DataJpaTest(showSql = true)
//@AutoConfigureTestDatabase(replace  = AutoConfigureTestDatabase.Replace.NONE)
public class LocalDateTest {
    @BeforeEach
    void setUp() throws Exception {

    }
    @Test
    public void instanceTest(){
        //convert LocalDate to long
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startOfDay = currentDate.atStartOfDay();
        long datetime = startOfDay.toEpochSecond(ZoneOffset.UTC);
        System.out.println("datetime "+datetime);
        Long datetoInstantTime = startOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println("datetoInstantTime "+datetoInstantTime);

    }
}
