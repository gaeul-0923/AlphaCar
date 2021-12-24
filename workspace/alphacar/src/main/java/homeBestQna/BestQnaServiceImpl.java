package homeBestQna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestQnaServiceImpl implements BestQnaService {
	
	@Autowired private BestQnaDAO dao;

	@Override
	public void faq_insert(BestQnaVO vo) {
		dao.faq_insert(vo);
		
	}

	@Override
	public BestQnaPage faq_list(BestQnaPage page) {
		// TODO Auto-generated method stub
		return dao.faq_list(page);
	}

	@Override
	public List<BestQnaVO> faq_list() {
		// TODO Auto-generated method stub
		return dao.faq_list();
	}

	@Override
	public BestQnaVO faq_detail(int best_qna_id) {
		// TODO Auto-generated method stub
		return dao.faq_detail(best_qna_id);
	}

	@Override
	public void faq_update(BestQnaVO vo) {
		dao.faq_update(vo);
		
	}

	@Override
	public void faq_delete(int best_qna_id) {
		dao.faq_delete(best_qna_id);
		
	}

	@Override
	public void faq_read(int best_qna_id) {
		// TODO Auto-generated method stub
		dao.faq_read(best_qna_id);
	}

	
	
	

}
