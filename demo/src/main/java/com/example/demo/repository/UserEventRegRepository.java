package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Events;
import com.example.demo.entity.Member;

public interface UserEventRegRepository extends JpaRepository<Member, Events> {

}
