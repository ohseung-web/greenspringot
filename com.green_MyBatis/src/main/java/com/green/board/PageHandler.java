package com.green.board;

public class PageHandler {
	// PageHandler는 페이지 계산기지 게시판 전용 클래스가 아니다
	 /* ===============================
		    1. 기본 입력값
		 =============================== */
		 private int totalCnt;   // 전체 게시글 수
		 private int pageNum;    // 현재 페이지 번호
		 private int pageSize ;   // 한 페이지에 보여줄 게시글 수
		 private int pageBlock = 3; // 페이지 번호 묶음 (1~3)
		
		 /* ===============================
		    2. DB 조회용 값
		 =============================== */
		 private int startRow;   // DB에서 시작 위치
		 private int endRow;     // 가져올 게시글 개수
		
		 /* ===============================
		    3. 페이지 네비게이션용 값
		 =============================== */
		 private int totalPage;  // 전체 페이지 수
		 private int startPage;  // 네비게이션 시작 번호
		 private int endPage;    // 네비게이션 끝 번호
		
		 private boolean prev;   // ◀ 이전
		 private boolean next;   // ▶ 다음
		
		 /* ===============================
		    생성자
		 =============================== */
		 public PageHandler(int totalCnt, int pageNum, int pageSize) {
		     this.totalCnt = totalCnt;
		     this.pageNum = pageNum;
		     this.pageSize = pageSize;
		
		     calcPaging();
		 }
		 /* ===============================
		    계산
		 =============================== */
		 private void calcPaging() {
		
		     // 1️ 전체 페이지 수
			 // double 해야하는 이유 : 23 / 10 = 2.3 → 올림해서 3페이지 필요하니까
		     totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		
		     // 2️ DB 조회 범위
		     // 1페이지 → 0부터 10개
			 //	2페이지 → 10부터 10개
		     //	3페이지 → 20부터 10개
		     startRow = (pageNum - 1) * pageSize;
		     endRow = pageSize;
		
		     // 3️ 페이지 네비게이션 시작 / 끝
		     startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		     endPage = startPage + pageBlock - 1;
		
		  // endPage는 "페이지 번호를 몇 번까지 보여줄지"를 의미한다.
		  // 하지만 계산식대로 두면, 실제 존재하지 않는 페이지 번호가
		  // 화면에 출력될 수 있다.
		  //
		  // 예) 전체 페이지는 3페이지뿐인데
          //    1 2 3 4 5 처럼 나오면 안 되기 때문에
		  //
		  // endPage가 전체 페이지 수(totalPage)를 넘어가면
		  // 가장 마지막 페이지 번호로 강제로 맞춰준다.
		     if(endPage > totalPage) {
		         endPage = totalPage;
		     }
		
		     // 4️ 이전 / 다음 버튼 여부
		     prev = startPage > 1;
		     next = endPage < totalPage;
		 }

		 
		 
		 public int getTotalCnt() {
			 return totalCnt;
		 }

		 public void setTotalCnt(int totalCnt) {
			 this.totalCnt = totalCnt;
		 }

		 public int getPageNum() {
			 return pageNum;
		 }

		 public void setPageNum(int pageNum) {
			 this.pageNum = pageNum;
		 }

		 public int getPageSize() {
			 return pageSize;
		 }

		 public void setPageSize(int pageSize) {
			 this.pageSize = pageSize;
		 }

		 public int getPageBlock() {
			 return pageBlock;
		 }

		 public void setPageBlock(int pageBlock) {
			 this.pageBlock = pageBlock;
		 }

		 public int getStartRow() {
			 return startRow;
		 }

		 public void setStartRow(int startRow) {
			 this.startRow = startRow;
		 }

		 public int getEndRow() {
			 return endRow;
		 }

		 public void setEndRow(int endRow) {
			 this.endRow = endRow;
		 }

		 public int getTotalPage() {
			 return totalPage;
		 }

		 public void setTotalPage(int totalPage) {
			 this.totalPage = totalPage;
		 }

		 public int getStartPage() {
			 return startPage;
		 }

		 public void setStartPage(int startPage) {
			 this.startPage = startPage;
		 }

		 public int getEndPage() {
			 return endPage;
		 }

		 public void setEndPage(int endPage) {
			 this.endPage = endPage;
		 }

		 public boolean isPrev() {
			 return prev;
		 }

		 public void setPrev(boolean prev) {
			 this.prev = prev;
		 }

		 public boolean isNext() {
			 return next;
		 }

		 public void setNext(boolean next) {
			 this.next = next;
		 }
	
	
	
	
	
//	private int totalCnt; //총 게시물 갯수
//	  private int pageSize; // 한 페이지의 크기
//	  private int naviSize = 5; // 페이지 네비게이션의 크기
//	  private int totalPage; //전체 페이지의 갯수
//	  private int page; // 현재 페이지
//	  private int beginPage; // 네비게이션의 첫번째 페이지
//	  private int endPage; // 네비게이션의 마지막 페이지
//	  private  boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄것인지 여부
//	  private boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄겻인지 여부
//	   
//	   public PageHandler(int totalCnt, int page) {
//		 //  this(totalCnt,page, pageSize=10);
//		   this.totalCnt = 10;
//		   this.page = 5;
//	   }
//	   public PageHandler(int totalCnt, int page, int pageSize) {
//		   this.totalCnt = totalCnt;
//		   this.page = page;
//		   this.pageSize = pageSize;
//		   
//		   // 원인: totalCnt와 pageSize가 모두 정수(int)라 
//		   // 소수점이 버려진 채로 Math.ceil에 전달됩니다. (예: 25 / 10 = 2.0 → 올림해도 2가 됨)
//		   // totalPage =(int)Math.ceil( totalCnt / pageSize );
//		   
//		   totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
//		   beginPage = page / naviSize * naviSize + 1;
//		   endPage = Math.min(beginPage + naviSize - 1, totalPage) ; //둘 중에 작은 값을 출력한다.
//		   
//		   // 이전으로 이동하는 페이지는 beginPage가 1페이지만 아니면 됨
//		   showPrev = beginPage != 1;
//		   // 다음으로 이동하는 페이지는 endPage가 totalPage만 아니면 됨
//		   showNext = endPage != totalPage;
//	   }
//	   
//	  public void print() {
//		  System.out.println("page = " + page);
//		  System.out.println(showPrev ? "[PREV]": "");
//		  for(int i=beginPage; i<=endPage ; i++) {
//			  System.out.println(i+" ");
//		  }
//		  System.out.println(showNext ? "[NEXT]":"");
//	  }
//	  
//	  public int getTotalCnt() {
//		return totalCnt;
//		}
//		public void setTotalCnt(int totalCnt) {
//			this.totalCnt = totalCnt;
//		}
//		public int getPageSize() {
//			return pageSize;
//		}
//		public void setPageSize(int pageSize) {
//			this.pageSize = pageSize;
//		}
//		public int getNaviSize() {
//			return naviSize;
//		}
//		public void setNaviSize(int naviSize) {
//			this.naviSize = naviSize;
//		}
//		public int getTotalPage() {
//			return totalPage;
//		}
//		public void setTotalPage(int totalPage) {
//			this.totalPage = totalPage;
//		}
//		public int getPage() {
//			return page;
//		}
//		public void setPage(int page) {
//			this.page = page;
//		}
//		public int getBeginPage() {
//			return beginPage;
//		}
//		public void setBeginPage(int beginPage) {
//			this.beginPage = beginPage;
//		}
//		public int getEndPage() {
//			return endPage;
//		}
//		public void setEndPage(int endPage) {
//			this.endPage = endPage;
//		}
//		public boolean isShowPrev() {
//			return showPrev;
//		}
//		public void setShowPrev(boolean showPrev) {
//			this.showPrev = showPrev;
//		}
//		public boolean isShowNext() {
//			return showNext;
//		}
//		public void setShowNext(boolean showNext) {
//			this.showNext = showNext;
//		}
//	@Override
//	  public String toString() {
//		  return "PageHandler{" +
//	              ", totalCnt=" + totalCnt +
//	              ", showNext=" + showNext +
//	              ", beginPage=" + beginPage +
//	              ", naviSize=" + naviSize +
//	              ", totalPage=" + totalPage +
//	              ", endPage=" + endPage +
//	              ", showPrev=" + showPrev +
//	              '}';
//	  }
}
