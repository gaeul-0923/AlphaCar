package homeQna;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired private QnaDAO dao;

	@Override
	public void qna_insert(QnaVO vo) {
		dao.qna_insert(vo);
		
	}

	@Override
	public QnaPage qna_list(QnaPage page) {
		// TODO Auto-generated method stub
		return dao.qna_list(page);
	}

	@Override
	public List<QnaVO> qna_list() {
		// TODO Auto-generated method stub
		return dao.qna_list();
	}

	@Override
	public QnaVO qna_detail(int qna_id) {
		// TODO Auto-generated method stub
		return dao.qna_detail(qna_id);
	}

	@Override
	public void qna_update(QnaVO vo) {
		dao.qna_update(vo);
		
	}

	@Override
	public void qna_delete(int qna_id) {
		dao.qna_delete(qna_id);
		
	}

	@Override
	public void qna_read(int qna_id) {
		// TODO Auto-generated method stub
		dao.qna_read(qna_id);
	}

	@Override
	public void qna_reply_insert(QnaVO vo) {
		dao.qna_reply_insert(vo);
		
	}

//	@Override
//	public QnaVO check_pw(HashMap<String, String> map) {
//		// TODO Auto-generated method stub
//		return dao.check_pw(map);
//	}

	@Override
	public QnaVO check_pw(int qna_id) {
		// TODO Auto-generated method stub
		return dao.check_pw(qna_id);
	}

	@Override
	public List<QnaVO> member_qna_list(String customer_email) {
		// TODO Auto-generated method stub
		return dao.member_qna_list(customer_email);
	}

	@Override
	public QnaPage member_qna_list(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.member_qna_list(map);
	}

	@Override
	public void reply_update(QnaVO vo) {
		dao.reply_update(vo);
		
	}


}
