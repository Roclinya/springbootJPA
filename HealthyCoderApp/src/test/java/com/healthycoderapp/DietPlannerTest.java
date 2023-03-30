package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class DietPlannerTest {

	// it's not a good idea to initialize it inside unit test because then you would
	// have
	// to initialize it seperately in each unit test.
	// On the other hand we don't want to initialize it directly here
	// because unit test should be independent of each other and we want to have a
	// brand new instance of dietplanner
	// each time we run a test.
	private DietPlanner dietPlanner;

	@BeforeEach
	void setup() {
		this.dietPlanner = new DietPlanner(20, 30, 50);

	}
	@AfterEach
	void afterEach() {
		System.out.println("Unit test is finished!");
	}

//	@Test
	@RepeatedTest(value=5,name =RepeatedTest.LONG_DISPLAY_NAME)
	void should_ReturnCorrectDietPlan_When_CorrectCoder() {
		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);
		// when
		DietPlan actual = dietPlanner.calculateDiet(coder);
		// then
		//這裡使用這個會失敗,因為他會去比對兩個DietPlan物件是否也是同一個實例,因此改用assertAll各別比對內容
		//assertEquals(coder, actual);
		
		assertAll(() -> assertEquals(expected.getCalories(),actual.getCalories()),
				() -> assertEquals(expected.getProtein(),actual.getProtein()),
				() -> assertEquals(expected.getFat(),actual.getFat()),
				() -> assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate())
					);
	}

}
