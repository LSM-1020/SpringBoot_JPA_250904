package com.LSM.jpatest.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //엔티티 클래스로 설정
@Table(name = "jpaquestiontbl") //실제로 매핑될 데이터베이스의 테이블 이름 설정
@SequenceGenerator(
			name = "QUESTION_SEQ_GENERATOR",//JPA 내부 시퀀스 이름
			sequenceName = "QUESTION_SEQ", //실제 DB 시퀀스 이름
			initialValue = 1, //시퀀스 시작값
			allocationSize = 1 //시퀀스 증가치
			)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questiontbl {
	
	@Id //해당 테이블의해당 필드를 기본키 필드로 설정
	@Column(name="qnum") //실제로 DB테이블의 만들어질 필드 이름을 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ_GENERATOR")
	private Long qnum; //질문번호 (기본키-자동증가,wrapper class로 설정,integer,Long 보통은 Long)
	
	@Column(name="qtitle",length = 20, nullable = false) //실제로 DB테이블의 만들어질 필드 이름을 설정
	private String qtitle; //질문 제목
	
	@Column(name="qcontent",length = 200, nullable = false) //실제로 DB테이블의 만들어질 필드 이름을 설정
	private String qcontent; //질문 내용
	
	@CreationTimestamp //자동으로 입력(sysdate)
	private LocalDateTime qdate; //질문 등록일
	
	@UpdateTimestamp
	private LocalDateTime udate; //레코드(질문글)가 수정된 시간 자동입력
}
