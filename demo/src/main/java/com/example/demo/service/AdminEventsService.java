package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Events;

public interface AdminEventsService {
	
List<Events> findAllEventList(Events events);

Events editEvents(Events events);

Integer delEvents(Long id);

}
