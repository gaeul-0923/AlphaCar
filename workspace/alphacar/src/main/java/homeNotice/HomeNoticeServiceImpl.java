package homeNotice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeNoticeServiceImpl implements HomeNoticeService {
	
	@Autowired private HomeNoticeDAO dao;

	@Override
	public void notice_insert(HomeNoticeVO vo) {
		dao.notice_insert(vo);	
	}

	@Override
	public HomeNoticePage notice_list(HomeNoticePage page) {
		// TODO Auto-generated method stub
		return dao.notice_list(page);
	}


	@Override
	public List<HomeNoticeVO> notice_list() {
		return dao.notice_list();
	}

	@Override
	public HomeNoticeVO notice_detail(int id) {
		return dao.notice_detail(id);
	}

	@Override
	public void notice_update(HomeNoticeVO vo) {
		dao.notice_update(vo);
		
	}

	@Override
	public void notice_delete(int id) {
		dao.notice_delete(id);
		
	}

	@Override
	public void notice_read(int id) {
		dao.notice_read(id);
	}

	@Override
	public int board_comment_insert(HomeNoticeCommentVO vo) {
		return dao.board_comment_insert(vo);
	}

	@Override
	public int board_comment_update(HomeNoticeCommentVO vo) {
		return dao.board_comment_update(vo);
	}

	@Override
	public int board_comment_delete(int id) {
		return dao.board_comment_delete(id);
	}

	@Override
	public List<HomeNoticeCommentVO> board_comment_list(int notice_id) {
		return dao.board_comment_list(notice_id);
	}
	
	@Override
	public List<HomeNoticeVO> an_notice_list() {
		// TODO Auto-generated method stub
		return dao.an_notice_list();
	}
	

}
