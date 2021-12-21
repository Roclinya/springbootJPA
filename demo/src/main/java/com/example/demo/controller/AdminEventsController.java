package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Events;
import com.example.demo.service.AdminEventsService;

@RestController
@RequestMapping("app")
public class AdminEventsController {
	
	@Autowired
	AdminEventsService adminEventsService;
	
	@PostMapping("/findAllEventList")
	ResponseEntity<List<Events>> findAllEventList(@RequestBody Events events){
		return ResponseEntity.ok(adminEventsService.findAllEventList(events));
		}
	@PostMapping("/addEvents")
	ResponseEntity<Events> addEvents(@RequestBody Events events){
		return ResponseEntity.ok(adminEventsService.editEvents(events));
	}
	@PostMapping("/modifyEvents")
	ResponseEntity<Events> modifyEvents(@RequestBody Events events){
		return ResponseEntity.ok(adminEventsService.editEvents(events));
	}
	@PostMapping("/delEvents")
	ResponseEntity<Integer> delEvents(@RequestBody Events events){
		return ResponseEntity.ok(events.getId());
	}

}
