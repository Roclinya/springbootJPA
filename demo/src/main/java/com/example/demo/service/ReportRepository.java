package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class ReportRepository {

	// Container-Managed EntityManager 受到容器托管的EntityManager可以直接通过注解@PersistenceContext注入的方式来获得
	// Persistence Context預設為Transaction-scoped，也就是在方法開始前會啟始交易，結束後停止交易
	@PersistenceContext
	// EntityManager不是Thread-safe
	private EntityManager entityManager;

//    public ReportRepository() {
//        // create an instance of entity manager factory
//    }

	public List<Object[]> find(String email) {

		Query query = entityManager.createQuery("SELECT c, s, u FROM  Channel c, Subscription s, User u"
				+ " WHERE c.subscriptionId = s.id AND s.id = u.subscriptionId AND u.email=:email");
		query.setParameter("email", email);

		return query.getResultList();
	}
}
