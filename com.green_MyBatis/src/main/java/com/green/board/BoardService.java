package com.green.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface BoardService {
	// 하나의 게시글 추가
   public void addBoard(BoardDTO bdto);
    
    // 전체 게시글 출력
   public List<BoardDTO> allBoard();
    
    // 하나의 게시글 상세 조회
   public BoardDTO oneBoard(int num);
   
   // 게시글 클릭할때 마다 조회수 1씩 증가하는 메소드 작성
   public int updateReadCount(int num);
    
    // 게시글 수정
   public boolean modifyBoard(BoardDTO bdto);
    
    // 게시글 삭제
   public boolean removeBoard(int num, String writerPw);
    
    // 게시글 검색
   public List<BoardDTO> searchBoard(String searchType, String searchKeyword);
   
   // 게시글 전체 개수
   public int getAllcount();
   
   // 게시글 시작 1 ~ 10개씩 limit로 
   public List<BoardDTO> getPageBoard(int offset, int limit);
   
	// BoardService.java 인터페이스에 추가할 메서드
	public List<BoardDTO> getPageList2(int page, int pageSize);
	
	public List<BoardDTO> getPageList(int startRow, int pageSize);
	
	// 검색조건에 만족하는 전체 개수
	public int getSearchCount(String searchType, String searchKeyword);

	// 검색한 후의 페이징
	public List<BoardDTO> getSearchPageList(String searchType,
	                                 String searchKeyword,
	                                 int startRow,
                                 int pageSize);
	
	public List<BoardDTO> getMyBoardList(String loginId, int startRow, int pageSize);
	
	public int getMyBoardCount(String loginId);
}
