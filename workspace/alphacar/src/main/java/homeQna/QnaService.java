package homeQna;

import java.util.HashMap;
import java.util.List;

public interface QnaService {
	//qna 신규 저장
	void qna_insert(QnaVO vo);
	
	//qna 목록 조회 - 페이지 처리된
	QnaPage qna_list(QnaPage page);
	
	//qna 목록 조회
	List<QnaVO> qna_list();
	
	//qna 상세 조회
	QnaVO qna_detail(int qna_id);
	
	//qna 변경 저장
	void qna_update(QnaVO vo);
	
	//qna 삭제
	void qna_delete(int qna_id);
	
	//qna 조회시 조회수 증가 처리
	void qna_read(int qna_id);
	
	//qna 답글 저장
	void qna_reply_insert(QnaVO vo);
	
	//qna 답글 변경 저장
	void reply_update(QnaVO vo);
	
	//qna 비밀번호 일치 확인
	QnaVO check_pw(int qna_id);
	
	//고객과 점주일 경우 qna 목록 조회
	List<QnaVO> member_qna_list(String customer_email);
//	List<QnaVO> member_qna_list(String customer_email);
		
	//고객과 점주일 경우 qna 목록조회 - 페이지 처리된
	QnaPage member_qna_list(HashMap<String, Object> map);
	
	
}


