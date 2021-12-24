package common;

public class PageVO {
	// 총 글의건수, 총 페이지수, 총 블록 수
	int totallist, totalPage, totalBlock, root, step, indent;
	//페이지 당 보여질 목록의 수, 블록당 보여질 페이지의 수
	int pageList = 10;
	int blockPage = 10;
	int curPage; //현재 페이지
	int beginList;// 각 페이지에 보여질 시작 목록 번호
	int endList; // 끝 목록 번호
	int curBlock; // 현재 블록
	int beginPage; // 각 블록에 보여질 시작 페이지
	int endPage; // 각 블록에 보여질 끝 페이지
	
	String search, keyword;
	
	
	private String viewType="list"; // 게시판 형태 (기본은 목록 형태, 
	
	
	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotallist() {
		return totallist;
	}
	
	public void setTotallist(int totallist) {
		this.totallist = totallist; //db에서 받아온 총 글의 건수 (19)
		totalPage = totallist / pageList; //총 페이지 수 = 총 건수 / 페이지당 보여질 글 수
		//19 / 5 = 3.8
		if (totallist % pageList > 0) ++totalPage;
		totalBlock = totalPage / blockPage; //총 블록수 = 총 페이지수 / 블록당 보여질 페이지 수
		//4 / 3 = 1.33333
		if(totalPage % blockPage > 0) ++totalBlock; //총 블록수 : 2
		
		//현재 페이지에 보여질 글의 시작 / 끝 목록(글)
		endList = totallist - (curPage - 1) * pageList;
		beginList = endList - (pageList - 1); 
		
		//블록 번호
		curBlock = curPage / blockPage;
		if(curPage % blockPage > 0) ++curBlock;
		
		//각 블록의 끝 페이지 번호 : 블록변호 * 블록당 보여질 페이지
		endPage = curBlock * blockPage;
		
		//각 블록의 시작 페이지 번호 : 끝 페이지번호 - (블럭당 보여질 페이지수 -1)
		beginPage = endPage - (blockPage - 1);
		
		//총 페이지 수
		if(endPage > totalPage) endPage = totalPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginList() {
		return beginList;
	}
	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}
	public int getEndList() {
		return endList;
	}
	public void setEndList(int endList) {
		this.endList = endList;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
