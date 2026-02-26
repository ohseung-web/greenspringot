package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper
public interface MemberMapper {
	// MemberDAO의 메소드들을 모두 추상메소드로 작성한다.
	// 이렇게 설정된 객체는 IoC 스프링 컨테이너에 탑재가 된다.
	
	   // 회원 개인을 추가하는 메소드
	   public int insertMember(MemberDTO mdto); 
	   //id 없음 => flase
	   public boolean isMember(String id); 
	   // 회원 전체 목록 검색 쿼리
	   public List<MemberDTO> allSelectMember(); 
	   // 개인 한 사람의 정보를 검색하는 메소드
	   public MemberDTO oneSelectMember(String id); 
	   // 개인 한사람의 정보를 수정하는 쿼리
	   public int updateMember(MemberDTO mdto); 
	   // 개인 한사람의 패스워드 리턴하는 쿼리
	   public String getPass(String id); 
	   // 한사람 개인의 정보를 삭제하는 메소드 작성
	   public int deleteMember(String id);
}

