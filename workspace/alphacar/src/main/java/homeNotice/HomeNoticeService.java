package homeNotice;

import java.util.List;

public interface HomeNoticeService {
	//공지글 신규 저장
	void notice_insert(HomeNoticeVO vo);
	
	//공지글 목록 조회 - 페이지 처리된
	HomeNoticePage notice_list(HomeNoticePage page);
	
	//공지글 목록 조회
	List<HomeNoticeVO> notice_list();
	
	//공지글 상세 조회
	HomeNoticeVO notice_detail(int id);
	
	//공지글 변경 저장
	void notice_update(HomeNoticeVO vo);
	
	//공지글 삭제
	void notice_delete(int id);
	
	//공지글 조회시 조회수 증가 처리
	void notice_read(int id);
	
	//댓글 신규 저장 처리
	int board_comment_insert(HomeNoticeCommentVO vo); 
	
	//댓글 수정
	int board_comment_update(HomeNoticeCommentVO vo); 
	
	//댓글 삭제
	int board_comment_delete(int id); 
	
	//댓글 목록 조회
	List<HomeNoticeCommentVO> board_comment_list(int pid);

	//안드 공지사항 조회
	List<HomeNoticeVO> an_notice_list();
	
	
	
	
	
	
	
	
	
	
}
