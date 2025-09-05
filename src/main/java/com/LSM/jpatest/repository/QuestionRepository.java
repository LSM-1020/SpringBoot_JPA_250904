package com.LSM.jpatest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	//SQL문 직접 쓰기 -> JPA SQL문 (JPQL)
	@Query("SELECT q FROM Questiontbl q WHERE q.qnum=:qnum") //엔티티 이름을 넣어주기 , *는 적용이 안되서 엔티티를 넣기
	public Questiontbl findQuestionByQnum(@Param("qnum") Long qnum); //위 qnum을 자동인식하지 않기때문에 @param으로 위 qnum을 지정해줌
	//SQL문 직접 쓰기->like문
	@Query("SELECT q FROM Questiontbl q WHERE q.qtitle LIKE %:qtitle%") //엔티티 이름을 넣어주기 , *는 적용이 안되서 엔티티를 넣기
	public Questiontbl findQuestionByQtitle(@Param("qtitle") String qtitle); //위 qnum을 자동인식하지 않기때문에 @param으로 위 qnum을 지정해줌
	
	//질문글 번호가 특정 값보다 큰 질문 글만 조회
	@Query("SELECT q FROM Questiontbl q WHERE q.qnum >= :number") //엔티티 이름을 넣어주기 , *는 적용이 안되서 엔티티를 넣기
	public Questiontbl findQuestionByQnumber(@Param("number") Long number); //위 qnum을 자동인식하지 않기때문에 @param으로 위 qnum을 지정해줌
	
	
	//Native SQL문 (오리지널 SQL문) 쓰기
	@Query(value = "SELECT * FROM jpaquestiontbl WHERE qnum=:qnum", nativeQuery = true)
	public Questiontbl findQuestionNativeByQnum(@Param("qnum") Long qnum);
	
	//기타 JPA문법
	public boolean existsByQnum(Long qnum); //qnum이 존재하는 번호면 true반환
	//질문글 번호가 특정 값 보다 큰 질문글만 조회
	public Questiontbl findByQnumGreaterThanEqual(Long qnum);
	//글 전체라 List+엔티티 한개면 그냥 엔티티만
	
	//질문 내용 업데이트
	@Modifying
	@Transactional
	@Query("UPDATE Questiontbl q SET q.qcontent = :qcontent WHERE q.qnum = :qnum")
	public int updateQcontentByQnum(@Param("qcontent") String qcontent, @Param("qnum")Long qnum);
	
	@Modifying
	@Query(value = "UPDATE Questiontbl SET qcontent = :qcontent WHERE qnum= :qnum", nativeQuery = true)
	public int updateNativeQcontentByQnum(@Param("qcontent") String qcontent, @Param("qnum") Long qnum);
}
