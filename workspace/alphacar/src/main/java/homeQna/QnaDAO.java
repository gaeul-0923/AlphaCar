package homeQna;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import homeNotice.HomeNoticeVO;

@Repository
public class QnaDAO implements QnaService {
	
	@Autowired private SqlSession sql;

	@Override
	public void qna_insert(QnaVO vo) {
		sql.insert("homeQna.mapper.homeInsert", vo);
		
	}

	@Override
	public QnaPage qna_list(QnaPage page) {
		//총 글의 개수를 조회(totalList)
		int pagecnt = sql.selectOne("homeQna.mapper.hometotalList", page);
		page.setTotallist(pagecnt); //총 글의 수
		
		//전체 글을 조회하여 List 
		List<QnaVO> list = sql.selectList("homeQna.mapper.homeQnaList", page);
		page.setList(list);
		return page;
	}

	@Override
	public List<QnaVO> qna_list() {
		// TODO Auto-generated method stub
		return sql.selectList("homeQna.mapper.homeQnaList");
	}

	@Override
	public QnaVO qna_detail(int qna_id) {
		// TODO Auto-generated method stub
		return sql.selectOne("homeQna.mapper.homeQnaDetail", qna_id);
	}

	@Override
	public void qna_update(QnaVO vo) {
		sql.update("homeQna.mapper.homeQnaUpdate", vo);
		
	}

	@Override
	public void qna_delete(int qna_id) {
		sql.delete("homeQna.mapper.homeQnaDelete", qna_id);
		
	}

	@Override
	public void qna_read(int qna_id) {
		sql.update("homeQna.mapper.homeQnaRead", qna_id);
		
	}

	@Override
	public void qna_reply_insert(QnaVO vo) {
		sql.insert("homeQna.mapper.homeQnaReply", vo);
	}

	@Override
	public QnaVO check_pw(int qna_id) {
		// TODO Auto-generated method stub
		return sql.selectOne("homeQna.mapper.homeQnaCheck", qna_id);
	}

	@Override
	public List<QnaVO> member_qna_list(String customer_email) {
		// TODO Auto-generated method stub
		return sql.selectList("homeQna.mapper.homeMemberQnaList", customer_email);
	}

	//로그인 된 경우 목록 조회
	@Override
	public QnaPage member_qna_list(HashMap<String, Object> map) {
			
		QnaPage page = (QnaPage)map.get("page");
		page.setTotallist( sql.selectOne("homeQna.mapper.homeMembertotalList", map) );
		map.put("page", page);
		page.setList( sql.selectList("homeQna.mapper.homeMemberQnaPageList", map) );
		return page;
			
		}

	@Override
	public void reply_update(QnaVO vo) {
		sql.update("homeQna.mapper.homeQnaReplyUpdate", vo);
		
	}
	
}
