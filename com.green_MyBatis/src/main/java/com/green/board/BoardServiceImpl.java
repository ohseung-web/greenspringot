package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
   
	@Autowired
	private BoardMapper boardMapper; // MyBatis 주입
	
    @Override
    public void addBoard(BoardDTO bdto) {
    	boardMapper.insertBoard(bdto);
    }
    
    @Override
    public List<BoardDTO> allBoard() {
        return boardMapper.getAllBoard();
    }
    
    @Override
    public BoardDTO oneBoard(int num) {
        System.out.println("BoardServiceImpl oneBoard() 메소드 확인");
        
        // 1. 먼저 조회수를 1 증가시킵니다.
        boardMapper.updateReadCount(num);
        // 게시글 상세 정보로 넘긴다.
        return boardMapper.getOneBoard(num);
    }
    
    @Override
    public boolean modifyBoard(BoardDTO bdto) {
        System.out.println("BoardServiceImpl modifyBoard() 메소드 확인");
        int result = boardMapper.updateBoard(bdto);
        
        if(result > 0) {
            System.out.println("게시글 수정 성공");
            return true;
        } else {
            System.out.println("게시글 수정 실패");
            return false;
        }
    }
    
    @Override
    public boolean removeBoard(int num, String writerPw) {
        System.out.println("BoardServiceImpl removeBoard() 메소드 확인");
        int result = boardMapper.deleteBoard(num, writerPw);
        
        if(result > 0) {
            System.out.println("게시글 삭제 성공");
            return true;
        } else {
            System.out.println("게시글 삭제 실패");
            return false;
        }
    }
    
    @Override
    public List<BoardDTO> searchBoard(String searchType, String searchKeyword) {
        System.out.println("BoardServiceImpl searchBoard() 호출: " + searchType + " / " + searchKeyword);
        return boardMapper.getSearchBoard(searchType, searchKeyword);
    }

	@Override
	public int updateReadCount(int num) {
		System.out.println("BoardServiceImpl updateReadCount() 메소드 확인");
		return boardMapper.updateReadCount(num);
	}

	@Override
	public int getAllcount() {
		System.out.println("BoardServiceImpl getAllcount() 메소드 확인");
		return boardMapper.getAllcount();
	}

	@Override
	public List<BoardDTO> getPageList(int startRow, int pageSize) {
			System.out.println("BoardServiceImpl getPageList() 메소드 확인");
		    return boardMapper.getPageList(startRow, pageSize);
	}
	
	@Override
	public List<BoardDTO> getPageBoard(int offset, int limit) {
		System.out.println("BoardServiceImpl getPageBoard() 메소드 확인");
		return boardMapper.getPageBoard(offset, limit);
	}
	
	
	
	@Override
	public List<BoardDTO> getPageList2(int page, int pageSize) {
		System.out.println("BoardServiceImpl getPageList() 메소드 확인");
	    // MySQL의 LIMIT은 0부터 시작하므로 (현재페이지 - 1) * 페이지크기 로 계산합니다.
	    int offset = (page - 1) * pageSize;
	    return boardMapper.getPageBoard(offset, pageSize);
	}

	@Override
	public int getSearchCount(String searchType, String searchKeyword) {
	    return boardMapper.getSearchCount(searchType, searchKeyword);
	}

	@Override
	public List<BoardDTO> getSearchPageList(String searchType,
	                                        String searchKeyword,
	                                        int startRow,
	                                        int pageSize) {
	    return boardMapper.getSearchPageList(searchType, searchKeyword, startRow, pageSize);
	}
	
	// ServiceImpl 부분만 예시로 작성합니다.
	@Override
	public List<BoardDTO> getMyBoardList(String loginId, int startRow, int pageSize) {
	    return boardMapper.getMyBoardList(loginId, startRow, pageSize);
	}

	@Override
	public int getMyBoardCount(String loginId) {
	    return boardMapper.getMyBoardCount(loginId);
	}
}
