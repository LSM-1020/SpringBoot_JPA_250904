package com.LSM.jpatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.LSM.jpatest.dto.QuestionDto;
import com.LSM.jpatest.entity.Questiontbl;
import com.LSM.jpatest.repository.QuestionRepository;

@SpringBootTest
public class TestQuestion {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	@DisplayName("질문 등록 테스트")
	public void writeQuestion() {
//		Questiontbl question = new Questiontbl();
//		question.setQtitle("두번째 질문");
//		question.setQcontent("내일은 금요일이 맞나요?");
		
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQtitle("홍길동");
		questionDto.setQcontent("홍길동입니다");
		Questiontbl question = questionDto.getEntity(); //dto객체-> entity객체로 변환 questiontbl이 엔티티클래스
		
		//JPA 메소드는 엔티티 객체만 인자값으로 받을수 있다
		questionRepository.save(question); //save=insert문 쿼리 실행 , 일반 클래스는 못쓰고 엔티티클래스만 쓸수있음
		
	}
	
	@Test
	@DisplayName("질문 삭제 테스트")
	public void deleteQuestion() {
		
		//questionRepository.deleteById(3L); //3번째 레코드+Long=3L
		//기본키 qunu=3인 레코드를 찾아서 삭제
		
//		List<Questiontbl> quList = new ArrayList<>();
//		questionRepository.deleteAll(quList); //엔티티 리스트 삭제(원하는 레코드들만 삭제)
		
		questionRepository.deleteAllByQtitle("강감찬"); //질문 제목으로 삭제
	}
	
	@Test
	@DisplayName("질문 조회 테스트")
	public void searchQuestion() {
		
		List<Questiontbl> questionAllList = questionRepository.findAll();
		//findAll() -> 모든 레코드 가져오기 (select * from jpaquestiontbl)
				for(Questiontbl question :questionAllList) {
					System.out.println(question.getQnum());
					System.out.println(question.getQcontent());
					System.out.println(question.getQcontent());
					System.out.println(question.getQdate());
					System.out.println(question.getUdate());
				}
				//가장 최근 질문이 위로 오도록 정렬하여 출력
				List<Questiontbl> questionOrderList = questionRepository.findAllByOrderByQdateDesc();
				for(Questiontbl question :questionOrderList) {
					System.out.println(question.getQnum());
					System.out.println(question.getQcontent());
					System.out.println(question.getQcontent());
					System.out.println(question.getQdate());
					System.out.println(question.getUdate());
	}
	}
	
	@Test
	@DisplayName("특정 질문 검색")
	public void searchQuestionByField() {
		Optional<Questiontbl> questionOption = questionRepository.findById(4L);
		//select * from jpaquestiontbl where qnum=4
		
		//기본키로 검색했을경우 레코드가 1개 또는 존재하지 않는 경우 발생
		//Option<Questiontbl>형태로 반환타입 정해야한다
		//isPresent()를 활용하면 Option<Questiontbl>내에 객체의 존재여부를 알수있다
		if(questionOption.isPresent()) { //참이면 해당 기본키를 가진 레코드가 존재 ->조회성공
			Questiontbl question = questionOption.get();
			System.out.println(question.getQtitle());
		} else { //해당 기본키를 가진 레코드가 존재하지 않음-> 조회실패
			 System.out.println("해당 질문이 존재하지 않습니다.");
		}
		
		//select * from jpaquestiontbl where qnum=1 and qtitle=홍길동
		Questiontbl qOptional = questionRepository.findByQnumAndQtitle(13L,"홍길동");
		
		//질문 제목이 정확히 일치하는 조건으로 조회
		 List<Questiontbl> questions = questionRepository.findAllByQtitle("홍길동");
		for(Questiontbl question:questions) {
			 System.out.println(question.getQtitle());
			 System.out.println(question.getQnum());
			 System.out.println("========================");
		}
	
		//질문 제목에 특정문자 포함하면 찾는 조건으로 조화 ->like
		 List<Questiontbl> likeQuestions = questionRepository.findAllByQtitleLikeOrderByQdateDesc("%질문%");
		 for(Questiontbl question:likeQuestions) {
			 System.out.println(question.getQtitle());
			 System.out.println(question.getQnum());
			 System.out.println("========================");
		}
		//직접 쓴 sql문 (@Query사용) 으로 조회
		 Questiontbl questionSql = questionRepository.findQuestionByQnum(4L);
		 System.out.println(questionSql.getQnum());
		 System.out.println(questionSql.getQtitle());	 
	}
}
