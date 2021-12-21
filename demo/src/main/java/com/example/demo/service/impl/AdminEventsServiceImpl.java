package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Events;
import com.example.demo.repository.EventsRepository;
import com.example.demo.service.AdminEventsService;

@Service
public class AdminEventsServiceImpl implements AdminEventsService {
	
	@Autowired
	private EventsRepository eventsRepository;

	@Override
	public List<Events> findAllEventList(Events events) {
		return eventsRepository.findAll();
	}

	@Override
	public Events editEvents(Events events) {
		Events event = new Events();
		BeanUtils.copyProperties(events, event);
		return eventsRepository.save(event);
	}

	@Override
	public Integer delEvents(Long id) {
		//Optional為一個封裝物件,提供一些方法驗證物件的型態等等
		Optional<Events> entity = eventsRepository.findById(id);
		if(entity.isEmpty()) {
			return 0;
		}
		eventsRepository.delete(entity.get());
		return 1;
	}

}
