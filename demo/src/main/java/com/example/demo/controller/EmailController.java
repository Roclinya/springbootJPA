package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Channel;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.service.BarHibernateDAO;
import com.example.demo.service.ReportRepository;



@Controller
@ResponseBody
@RequestMapping(value ="/email")
public class EmailController {

	@Autowired
	ReportRepository reportRepository;

	@GetMapping("/test")
	public Object test() {
		List<Object[]> reportDetails = reportRepository.find("user1@gmail.com");
		for (Object[] reportDetail : reportDetails) {
			Channel channel = (Channel) reportDetail[0];
			Subscription subscription = (Subscription) reportDetail[1];
			User user = (User) reportDetail[2];
			System.out.println(">>>>>> 結果" + channel + subscription + user);
			// do something with entities
		}
		return null;
	}
	
	@Autowired
	BarHibernateDAO barHibernateDAO;
	
	@GetMapping("/test2")
	public void test2() {
		
		barHibernateDAO.test2();
	}
	
	


}