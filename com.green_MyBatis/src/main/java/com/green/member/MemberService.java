package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;

// controller -> service : DAO 메소드 찾아있어 
// DAO야 메소드 있어 -> DB에서 찾아옴
// DB -> id, pw값들고  -> DAO로 보냄 -> service의 메소드로 보냄
// -> controller에게 id, pw 찾아서 보냄

@Service
public class MemberService {

	// id중복체크, 성공, 실패 상수변수 정의 
	// 회원가입의 중복을 확인하는 상수
	public final static int user_id_alreday_exit = 0;
	// 회원가입의 성공여부를 확인하는 상수
	public final static int user_id_success = 1;
	// 회원가입의 실패를 확인하는 상수
	public final static int user_id_fail = -1;
	
	// 1. MemberDAO 대신 MemberMapper를 주입받습니다.
    @Autowired
    private MemberMapper memberMapper; // 변수명을 관례에 맞게 memberMapper로 수정
	
	//암호화 하는 PasswordEncoder도 의존성 객체로 주입한다.
	@Autowired
	PasswordEncoder passwordencoder;
	
	//회원 전체 목록 출력하는 메소드
	public List<MemberDTO> allListMember(){
		return memberMapper.allSelectMember();
	}
	
	
	//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService signupConfirm()메소드 확인");
		
		//회원가입 중복체크
		//id 없음 => flase
		boolean isMember = memberMapper.isMember(mdto.getId());
		
		//회원가입 중복체크 통과했다면
		if(isMember == false) {
			// 중복된 아이디가 존재하지 않을 때 DB에 회원의 정보가 추가된다.
			// insertMember(mdto)부분에 암호화 하여 DAO로 넘긴다.
			// pw를 암호화화된 비밀번호로 반환해준다.
			String encodePw = passwordencoder.encode(mdto.getPw());
			
			// 암호화된 encodePw를 mdto.setPw()에 넣어준다.
			mdto.setPw(encodePw);
			
			// insertMember(mdto)의 pw는 암호화된 비밀번호가 추가된다.
			// 즉, MemberDAO의 insertMember(mdto)에 암호화된 비밀번호가 추가된다.
			int result = memberMapper.insertMember(mdto);
			if(result > 0 ) {
				return user_id_success; // result = 1
			}else {
				return user_id_fail; // result = -1
			}
		}else {
			// 중복된 아이디가 존재할 때
			System.out.println("중복된 아이디 발견"+mdto.getId());
			return user_id_alreday_exit; // result = 0;
		}
	}

	//----------------- 2026년 1월 27일 서비로직 작성 시작 부분 ------------------
	public MemberDTO oneSelect(String id) {
		System.out.println("MemberService oneSelect()메소드 확인");
		return memberMapper.oneSelectMember(id);
	}
	
	// 한 사람의 패스워드만 출력하는 메소드
	public String onePass(String id) {
		System.out.println("MemberService onePass()메소드 확인");
		// void가 아닌이상 데이터 타입이 존재하면 반드시 return 반환값이 필요
		return memberMapper.getPass(id);
	}
	
	// 개인 한사람의 정보를 수정하는 메소드
	// DB의 패스워드와 같은지 비교
	// DB의 패스워드와 내가 입력한 패스워드가 같을 때 실행문
	// DB의 패스워드와 내가 입력한 패스워드가 다를 때 실행문
	public boolean modifyMember(MemberDTO mdto) {
		System.out.println("MemberService modifyMember()메소드 확인");
		//1. DB조회
		String dbPass = memberMapper.getPass(mdto.getId());
		
		//2. if 비교
		if( dbPass != null && dbPass.equals(mdto.getPw())) {
			// 내가 입력한 DB의 패스워드가 존재할 때
			return memberMapper.updateMember(mdto) == 1;
		}else {
			// 내가 입력한 DB의 패스워드가 존재하지 않을 때
			return false;
		}
	}
	
	// 개인 한사람의 정보를 삭제하는 메소드
	public boolean oneDelete(String id) {
		System.out.println("MemberService oneDelete()메소드 확인");
		 // 현재 deleteMmeber() DAO의 결과 값이 result=0 또는 1
		// 삭제되면 1, 아니면 0
		// memberdao.deleteMember(id) => 1 == 1 => true
		// memberdao.deleteMember(id) => 0 == 1 => false
		return memberMapper.deleteMember(id) == 1;
	}
	
	//--------------------------- 2026년 01월 29일 추가 --------------------------
	
	public MemberDTO loginConfirm(MemberDTO mdto) {
		System.out.println("MemberService loginConfirm()메소드 확인");
		
		// 1. DB에서 해당정보의 Id 가져오기
		MemberDTO dbMember = memberMapper.oneSelectMember(mdto.getId());
		
		// 2. 사용자가 존재하고 비밀번호가 일치하는지 확인
		// passwordEncoder.matches(사용자가 입력한 평문, DB에 저장된 암호문)
		if(dbMember != null && dbMember.getPw() != null) {
			if(passwordencoder.matches(mdto.getPw(), dbMember.getPw())) {
				return dbMember; // 로그인 성공시 회원정보 반환
			}
		}
		return null; //로그인 실패시 null 반환
	}
	
}

