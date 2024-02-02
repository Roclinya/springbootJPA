package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;

/**
 * Mock預設的回傳參數測試
 *
 */
class Test02DefaultReturnValues {

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
		
		
		// By default ,Mockito will returns nice mocks,meaning that they will return
		// values that actually make some sense.
		System.out.println("List returned " + roomServiceMock.getAvailableRooms());//the mock will return an empty list by default
		System.out.println("Object returned " + roomServiceMock.findAvailableRoomId(null));//the mock will return null by default
		System.out.println("Primitive returned " + roomServiceMock.getRoomCount());//the mock will return 0 by default
		
		/*
		 * By default, Mokito returns nice mocks. 預設這些Mock會回傳她原型該回傳的型態資料
		 * Nice Mocks default values:
		 * 1.empty list
		 * 2.null object
		 * 3. 0/false primitive
		 * */

	}

	@Test
	void should_CountAvailablePlaces() {
		// Behavior-driven development => BDD styles
		// given
		int expetcted = 0;
		// when
		int actual = bookingService.getAvailablePlaceCount(); // the mock will return 0 because of return type is int
		// then
		assertEquals(expetcted, actual);
	}

}
