package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MemberTemp;

@Repository
public interface MemberTempRepository extends JpaRepository<MemberTemp, Long> {

}
