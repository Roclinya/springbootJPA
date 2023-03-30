package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test05ThrowingExceptions {

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
	void should_ThrowException_When_NoRoomAvailable() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, false);
		when(this.roomServiceMock.findAvailableRoomId(bookingRequest))
		.thenThrow(BusinessException.class);
		// when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		// then
		assertThrows(BusinessException.class, executable);

	}

}
