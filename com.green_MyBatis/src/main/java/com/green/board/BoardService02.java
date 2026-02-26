package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.member.MemberDTO;

@Service
public class BoardService02 {

	@Autowired
	BoardDAO boardDao;
	
	//하나의 게시글 추가하는 메소드
	public void addBoard(BoardDTO bdto) {
		boardDao.insertBoard(bdto);
	}
	
	//전체 게시글 출력하는 메소드
	public List<BoardDTO> allBoard(){
		return boardDao.getAllBoard();
	}
	
	//하나의 게시글 출력 메소드
	public BoardDTO oneBoard(int num) {
		System.out.println("BoardService oneBoard()메소드 확인");
		return boardDao.getOneBoard(num);
	}
	
	//하나의 게시글을 수정하는 메소드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("BoardService modifyBoard()메소드 확인");
		
		 int result = boardDao.updateBoard(bdto);
		
		 if(result > 0) {
             System.out.println("게시글 수정 성공");
             return true;
         } else {
             System.out.println("게시글 수정 실패 (비밀번호 불일치 등)");
             return false;
         }
	}
	
	// 하나의 게시글을 삭제하는 메소드
	public boolean removeBoard(int num, String writerPw) {
		System.out.println("BoardService removeBoard()메소드 확인");
		
		 int result = boardDao.deleteBoard(num, writerPw);
		
		 if(result > 0) {
             System.out.println("게시글 삭제 성공");
             return true;
         } else {
             System.out.println("게시글 삭제 실패 (비밀번호 불일치 등)");
             return false;
         }
	}
	
	// 게시글 검색하는 메소드
		public List<BoardDTO> searchBoard(String searchType, String searchKeyword){
			System.out.println("3)BoardService searchBoard()메소드 호출 ");
			System.out.println("3)searchType : "+searchType);
			System.out.println("3)searchKeyword : "+searchKeyword);
			return boardDao.getSearchBoard(searchType, searchKeyword);
		}
}
