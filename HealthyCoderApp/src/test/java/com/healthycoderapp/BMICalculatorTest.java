package com.healthycoderapp;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class BMICalculatorTest {

	private String environment = "dev";

	// it is usually applied to operations that are too expensive to be run before
	// each unit test such as
	// setting up database connections or starting servers.
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all unit tests.");
	}

	// This will be typically used to close database connections or stopping
	// servers.
	@AfterAll
	static void afterAll() {
		System.out.println("After all unit tests.");
	}

	@Nested
	class IsDietRecommendedTest {
		@ParameterizedTest(name = "weight={0},height={1}") // 加入name屬性可以讓測試結果的顯示知道對應的key名稱為何
//		@ValueSource(doubles= {70.0,89.0,95.0,110.0}) //單一參數測試,僅傳參數coderWeight  => 第一個失敗,其他成功
//		@CsvSource(value= {"89.0,1.72","95.0,1C.75","110.0,1.78"}) //3 test here with 2 parameters
		@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1) // skip第一行為header
		void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
			// given
			double weight = coderWeight;
			double height = coderHeight;
			// when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			// then
			assertTrue(recommended);
		}

		@Test
		void should_ReturnFalse_When_DietNotRecommended() {
			// given
			double weight = 50.0;
			double height = 1.92;
			// when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			// then
			assertFalse(recommended);
		}

		// test exceptions
		@Test
		void should_ThrowArithemeticException_When_HeightIsZero() {
			// given
			double weight = 50.0;
			double height = 0.0;
			// when
			// lamda expression here,use executable means it will not be executed
			// immediately
			Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

			// then
			assertThrows(ArithmeticException.class, executable);
		}
	}

	@Nested
	//@DisplayName("sample inner class display name")
	class FindCoderWithWorstBMITests {
		
		// test with multiple assertions
		@Test
		@DisplayName(" [ should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty  ]")
//		@Disabled
		@DisabledOnOs(OS.MAC)
		void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {

			// given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));
			// when
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
			// then
			// 如果第一個assertEquals失敗了就不會執行第二個assertEquals,因此使用assertAll
//			assertAll(() -> assertEquals(1.85, coderWorstBMI.getHeight()),
//					() -> assertEquals(98.5, coderWorstBMI.getWeight()));

			assertAll(() -> assertEquals(1.82, coderWorstBMI.getHeight()),
					() -> assertEquals(98.0, coderWorstBMI.getWeight()));
		}

		@Test
		void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements() {
			// given
			//this clearly references the this.environment from the outer class
			assumeTrue(BMICalculatorTest.this.environment.equals("prod")); 
			List<Coder> coders = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}
			// when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
			// then
			assertTimeout(Duration.ofMillis(500), executable);
		}

		// test null values
		@Test
		void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {

			// given
			List<Coder> coders = new ArrayList<>();
			// when
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
			// then
			assertNull(coderWorstBMI);
		}
	}

	@Nested
	class GetBMIScoresTests {
		@Test
		void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
			// given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));
			double[] expected = { 18.52, 29.59, 19.53 };
			// when
			double[] bmiScores = BMICalculator.getBMIScores(coders);
			// then
			// assertEquals(expected,bmiScores);
			// //這裡使用這個會失敗,因為他會去比對兩個Array物件是否也是同一個實例,因此改用assertArrayEquals
			assertArrayEquals(expected, bmiScores);

		}
	}

}
