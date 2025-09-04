package com.LSM.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LSM.jpatest.entity.Questiontbl;

public interface QuestionRepository extends JpaRepository<Questiontbl, Long> { //dao클래스의 역활 <엔티티타입 클래스이름,기본키의 타입>

}
