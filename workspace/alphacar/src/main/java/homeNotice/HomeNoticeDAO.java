package homeNotice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeNoticeDAO implements HomeNoticeService {
	
	@Autowired private SqlSession sql;

	@Override
	public void notice_insert(HomeNoticeVO vo) {
		sql.insert("homeNotice.mapper.homeNoticeInsert", vo);
	}

	@Override
	public HomeNoticePage notice_list(HomeNoticePage page) {
		//총 글의 개수를 조회(totalList)
			int pagecnt = sql.selectOne("homeNotice.mapper.hometotalList", page);
			page.setTotallist(pagecnt); //총 글의 수
			
			//전체 글을 조회하여 List 
			List<HomeNoticeVO> list = sql.selectList("homeNotice.mapper.homeNoticeList", page);
			page.setList(list);
			return page;
	}

	@Override
	public List<HomeNoticeVO> notice_list() {
		return sql.selectList("homeNotice.mapper.homeNoticeList");
	}

	@Override
	public HomeNoticeVO notice_detail(int id) {
		return sql.selectOne("homeNotice.mapper.homeNoticeDetail", id);
	}

	@Override
	public void notice_update(HomeNoticeVO vo) {
		sql.update("homeNotice.mapper.homeNoticeUpdate", vo);
	}

	@Override
	public void notice_delete(int id) {
		sql.delete("homeNotice.mapper.homeNoticeDelete", id);
		
	}

	@Override
	public void notice_read(int id) {
		sql.update("homeNotice.mapper.homeNoticeRead", id);
	}

	@Override
	public int board_comment_insert(HomeNoticeCommentVO vo) {
		return sql.insert("homeNotice.mapper.commentInsert", vo);
	}

	@Override
	public int board_comment_update(HomeNoticeCommentVO vo) {
		return sql.update("homeNotice.mapper.commentUpdate", vo);
	}

	@Override
	public int board_comment_delete(int id) {
		return sql.delete("homeNotice.mapper.commentDelete", id);
	}

	@Override
	public List<HomeNoticeCommentVO> board_comment_list(int notice_id) {
		return sql.selectList("homeNotice.mapper.commentList", notice_id);
	}
	
	@Override
	public List<HomeNoticeVO> an_notice_list() {
		// TODO Auto-generated method stub
		return sql.selectList("homeNotice.mapper.an_notice_select");
	}
	

	
	

}
