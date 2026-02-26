package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	//MemberService 클래스를 DI로 의존객체화 해야한다.
	@Autowired
	MemberService meberservice;

	// 회원가입 양식 폼
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("MemberController signup()메소드 확인");
		String nextPage = "member/signup_form";
		return nextPage;
	}
	
	// 회원가입 확인
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto, Model model) {
		System.out.println("MemberController signupConfirm()메소드 확인");
		String nextPage = "member/signup_result";
		//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
		int result = meberservice.signupConfirm(mdto);
		model.addAttribute("result",result);
		
		// 회원가입이 성공하였을 경우 => 회원 목록인 새로운 주소로 이동하는 Redirect
		if(result == meberservice.user_id_success) {
			//return "redirect:/member/signup_result";
			return nextPage;
		}else {
			// 회원가입이 실패한 경우
			return nextPage;
		}
	}
	
	// 회원 전체 목록화면 호출
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// MemberService의 allListMember()
		System.out.println("MemberController memberList()메소드 호출");
		List<MemberDTO> memberlist = meberservice.allListMember();
		model.addAttribute("list",memberlist);
		
		String nextPage = "member/memberList";
		return nextPage;
		
	}
	
	// ------------------------- 2026년 1월 27일 Controller 로직 부분 ---------------
	
	// 한 개인의 정보를 상세보기하는 핸들러 
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model, @RequestParam("id") String id) {
		System.out.println("MemberController memberInfo()메소드 호출"+ id);
		MemberDTO onememberInfo = meberservice.oneSelect(id);
		//MemberDTO onememberInfo = meberservice.oneSelect(mdto.getId());
		
		model.addAttribute("onelist",onememberInfo);
		String nextPage = "member/memberInfo";
		return nextPage;
	}
	
	// 개인 정보를 수정하는 화면으로 이동하는 핸들러
	@GetMapping("/member/modify")
	public String modifyForm(MemberDTO mdto, Model model) {
		System.out.println("MemberController memberInfo()메소드 호출"+ mdto.getId() );
		
		// 개인수정 화면 그릴때 필요한 정보 : 한사람의 데이터
		// oneSelect(String id) : memberservice의 메소드
		MemberDTO oneModify = meberservice.oneSelect(mdto.getId());
		model.addAttribute("member",oneModify);
		String nextPage ="member/member_modify";
		return nextPage;
		
	}
	
	// 개인정보를 수정을 처리 하는 핸들러
	// 비밀번호가 일치하는지의 비교에 관련된 핸들러
	// redirect 사용 해볼것
	// modifyMember() 메소드 이용해볼것 => service
	
	@PostMapping("/member/modify")
	public String modifySubmit(MemberDTO mdto, RedirectAttributes re) {
		System.out.println("MemberController modifySubmit()메소드 호출");
		boolean result = meberservice.modifyMember(mdto);
		// 지금 현재 result의 결과 값 true또는 false
		if(result) {
			// update 성공
			// RedirectAttributes 단, 한번만 데이터를 넘길 수 있다.
			re.addFlashAttribute("msg","회원정보 수정완료");
			// 수정이 완료되면 list 의 url => /member/list
			return "redirect:/member/list";
		}else {
			// 비밀번호가 틀림
			re.addFlashAttribute("msg","비밀번호가 틀립니다.");
			// 비밀번호가 틀리면 현재 화면인 modify 그대로 존재햐야함
			// http://localhost:8090/member/modify?id=ot1045
			// 비밀번호가 틀릴경우 위 주소랑 같은 곳으로 돌아가야함
			return "redirect:/member/modify?id="+mdto.getId();
		}
		
	}
	
	// 개인 한 사람의 정보를 삭제하는 핸들러
	@GetMapping("/member/delete")
	public String deleteMember(
			@RequestParam("id") String id,
			RedirectAttributes re
			) {
		System.out.println("MemberController deleteMember()메소드 호출");
		boolean result = meberservice.oneDelete(id);
		// result 삭제가 된경우 true, 삭제가 되지 않은 경우 false
		if(result) {
			// 입력된 id가 존재하는 그래서 삭제된 경우
			re.addFlashAttribute("msg","회원이 삭제되었습니다.");
			// 삭제된 경우 List url => /member/list
			return "redirect:/member/list";
		}else {
			// 삭제가 진행되지 않은 경우
			re.addFlashAttribute("msg","삭제 실패");
			// 삭제가 실패된 경우 =>
			// http://localhost:8090/member/memberInfo?id=kkk 주소에 머물러야함
			return "redirect:/member/memberInfo?id="+id;
		}
		
	}
	
	
	// 로그인 양식 폼 이동
    @GetMapping("/member/login")
    public String loginFrom() {
        System.out.println("MemberController loginFrom() 폼 이동");
        return "member/login_form";
    }
	
 // 로그인 확인 처리
    @PostMapping("/member/loginPro")
    public String loginPro(MemberDTO mdto, RedirectAttributes re, HttpSession session) {
        System.out.println("MemberController loginPro() 처리");
        
        MemberDTO loginedMember = meberservice.loginConfirm(mdto);
        
        if (loginedMember != null) {
            // 로그인 성공 시 세션에 회원 정보 저장
            session.setAttribute("loginedMember", loginedMember);
            re.addFlashAttribute("msg", loginedMember.getId() + "님 환영합니다!");
            return "redirect:/";
        } else {
            // 로그인 실패 시
            re.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/member/login";
        }
    }
    
    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session, RedirectAttributes re) {
    	
    	// 1. 세션 무효화 (세션에 담긴 모든 데이터 삭제)
        session.invalidate(); 
        
        // 2. 로그아웃 완료 메시지 전달 (선택사항)
        re.addFlashAttribute("msg", "로그아웃 되었습니다.");
        
        // 3. 홈 화면으로 리다이렉트 (url: /)
        return "redirect:/";
       
    }
	
	
	
}

