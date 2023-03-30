package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

class Test03ReturnCustomValues {

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
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		// given
		// #changed the default behavior of our mock, which was returning an empty list
		// ,into returning a single list
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 2)));
		int expetcted = 2;
		// when
		int actual = bookingService.getAvailablePlaceCount();
		// then
		assertEquals(expetcted, actual);
	}

	@Test
	void should_CountAvailablePlaces_When_MultipleRoomsAvailable() {
		// given
		List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5)); //room1兩人房,room2五人房
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
		int expetcted = 7;//可以容納七個人的房間容納量
		// when
		int actual = bookingService.getAvailablePlaceCount();
		// then
		assertEquals(expetcted, actual);
	}

}
