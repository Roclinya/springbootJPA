package com.mockitotutorial.happyhotel.booking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class Test08Spies {

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
		// mock = dummy object with no real logic
		// spy(partial mock) = real object with real logic that we can modify
		this.bookingDAOMock = spy(BookingDAO.class); // create dummy object of BookingDAO
		this.mailSenderMock = mock(MailSender.class); // create dummy object of MailSender

		// instaniate BookingService by providing the mocks as the dependencies
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_MakeBooking_When_InputOk() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, true);// => isPrepaid=true execute paymentService.pay(bookingRequest, price);

		// when
		// we simply know the method was invoked,but we don't know what happened behind
		// the scenes.
		// we want to verify the paymentService mock was called.
		// In other words, we want to make sure that paymentService.pay() was executed
		// correctly in makeBooking method.
		String bookingId = bookingService.makeBooking(bookingRequest);
		// then
		verify(bookingDAOMock).save(bookingRequest);
		System.out.println("bookingId= " + bookingId);

	}
	
	//mocks: when(mock.method()).thenReturn()
	//spies: doReturn().when(spy).method())
	
	//mock(partial mock) = a real object that with real methods that we can modify 

	@Test
	void should_CancelBooking_When_InputOk() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, true);// => isPrepaid=true execute paymentService.pay(bookingRequest, price);
		bookingRequest.setRoomId("1.3");
		String bookingId = "1";
		
		doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);
		// when
		bookingService.cancelBooking(bookingId); //bookingDAOMock is spy ,thus it return actual value for actual bookingID,whis is null.
		// then

	}

}
