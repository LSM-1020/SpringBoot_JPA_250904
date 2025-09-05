package com.LSM.jpatest.dto;

import java.time.LocalDateTime;

import com.LSM.jpatest.entity.Questiontbl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

	private Long qnum;
	private String qtitle;
	private String qcontent;
	private LocalDateTime qdate;
	private LocalDateTime udate;
	
	public Questiontbl getEntity() { //이름 작명, Dto객체에 들어온 값으로 엔티티 객체를 만들어서 반환(DTD->Entity), 엔티티로 만들어야해서 questiontbl로 타입 설정
		Questiontbl question = new Questiontbl();
		question.setQtitle(this.qtitle);
		question.setQcontent(this.qcontent);
				
		return question;
	}
	
}
