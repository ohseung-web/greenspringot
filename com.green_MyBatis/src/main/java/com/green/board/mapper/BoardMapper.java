package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	     //하나의 게시글을 추가하는 메소드----------------------------------
		public void insertBoard(BoardDTO bdto); 
			
		// 전체 게시글을 검색하는 메소드----------------------------
		public List<BoardDTO> getAllBoard();
		
		// 하나의 게시글을 리턴받는 메소드 작성
	    public BoardDTO getOneBoard(int num); 
	    
	    // 게시글 클릭할때 마다 조회수 1씩 증가하는 메소드 작성
	    public int updateReadCount(int num);
	    
	    // 하나의 게시글 수정하는 메소드 ---------------------------------------
	    public int updateBoard(BoardDTO bdto); 
	    
	    // 하나의 게시글을 삭제하는 메소드 ---------------------------------------
	    //public int deleteBoard(int num, String writerPw); 
	    // @Param을 통해 XML에서 사용할 이름을 명시적으로 지정합니다.
	    public int deleteBoard(@Param("num") int num, @Param("writerPw") String writerPw);
	    
	    // 내용또는 제목으로 게시글 검색하는 메소드
	 	// 검색메소드 반드시, searchType, searchKeyword매개변수 필요
	 	//public List<BoardDTO> getSearchBoard(String searchType, String searchKeyword);
	    // 검색 조건(타입)과 키워드를 전달받습니다.
	    public List<BoardDTO> getSearchBoard(@Param("searchType") String searchType, 
	                                  @Param("searchKeyword") String searchKeyword);
	    
	    // 전체 게시글의 개수 구하는 메소드
	    public int getAllcount();
	    
	    // 전체 게시글을 startRow부터 ~ paseSize 만큼 보기
	    public List<BoardDTO> getPageList(@Param("startRow") int startRow, 
	    		@Param("pageSize") int pageSize);
	    
	    
	    
	    // 전체 조회가 아닌, 페이징 처리를 위한 조회로 수정합니다.
	    public List<BoardDTO> getPageBoard(@Param("offset") int offset, @Param("limit") int limit);
	    
	   // 검색 결과 개수 => 페이징에서 사용함
	    int getSearchCount(@Param("searchType") String searchType,
	                       @Param("searchKeyword") String searchKeyword);

	    // 검색 + 페이징
	    List<BoardDTO> getSearchPageList(@Param("searchType") String searchType,
	                                     @Param("searchKeyword") String searchKeyword,
	                                     @Param("startRow") int startRow,
	                                     @Param("pageSize") int pageSize);
	    
	    public List<BoardDTO> getMyBoardList(@Param("loginId") String loginId, 
                @Param("startRow") int startRow, 
                @Param("pageSize") int pageSize);

	    public int getMyBoardCount(@Param("loginId") String loginId);
}


