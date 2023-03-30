package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.*;

class Test04MultipleThenReturnCalls {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	// instaniate BookingService in setup method
	@BeforeEach
	void setup() {
		// creat mocks for these 4 dependencies of BookingService
		this.paymentServiceMock = mock(PaymentService.class); // create dummy object of PaymentService
		this.roomServiceMock = mock(RoomService.class); // create dummy object of RoomService
		this.bookingDAOMock = mock(BookingDAO.class); // create dummy object of BookingDAO
		this.mailSenderMock = mock(MailSender.class); // create dummy object of MailSender

		// instaniate BookingService by providing the mocks as the dependencies
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_CountAvailablePlaces_When_CalledMultipleTimes() {
		// given
		// #changed the default behavior of our mock, which was returning an empty list
		// ,into returning a single list
		when(this.roomServiceMock.getAvailableRooms())
		.thenReturn(Collections.singletonList(new Room("Room 1", 2))) //first call
		.thenReturn(Collections.emptyList());  //second call
		int expetctedFirstCall = 5;
		int expetctedFSecondCall = 0;
		// when
		int actualFirst = bookingService.getAvailablePlaceCount();
		int actualSecond = bookingService.getAvailablePlaceCount();
		// then
		// We want to check both calls at the same time,so instead of assertEquals,we use use assertAll
		assertAll(() -> assertEquals(expetctedFirstCall, actualFirst),
				() -> assertEquals(expetctedFSecondCall, actualSecond));
	}

}
