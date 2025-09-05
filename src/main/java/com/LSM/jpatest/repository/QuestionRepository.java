package com.LSM.jpatest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.LSM.jpatest.entity.Questiontbl;



public interface QuestionRepository extends JpaRepository<Questiontbl, Long> { //dao클래스의 역활 <엔티티타입 클래스이름,기본키의 타입>
	
	@Transactional
	public void deleteAllByQtitle(String qtitle); //질문 제목으로 삭제, deleteAllBy 까지는 고정이름, deleteall는 transctional 써주기
	
	//최근 질문글이 가장 위로 오도록 정렬하여 모든반환
	public List<Questiontbl> findAllByOrderByQdateDesc(); //대소문자 고정

	//두개의 조건이 일치하는 질문글 조회하기
	public Questiontbl findByQnumAndQtitle(Long qnum, String qtitle);
	
	//제목이 정확히 일치하는 질문글 조회하기
	public List<Questiontbl> findAllByQtitle(String qtitle); //대소문자 고정
	
	//제목에 특정문자가 포함되면 조회하기 ->like , 최근글이 위로오도록 정렬
	public List<Questiontbl> findAllByQtitleLikeOrderByQdateDesc(String keyword); 
	
	//SQL문 직접 쓰기
	@Query("SELECT q FROM Questiontbl q WHERE q.qnum=:qnum") //엔티티 이름을 넣어주기 , *는 적용이 안되서 엔티티를 넣기
	public Questiontbl findQuestionByQnum(@Param("qnum") Long qnum); //위 qnum을 자동인식하지 않기때문에 @param으로 위 qnum을 지정해줌
}
