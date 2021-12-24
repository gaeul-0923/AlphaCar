package review;

import java.util.List;

public interface ReviewService {
	
	int review_insert(ReviewVO vo);					// 리뷰 등록			
	
	ReviewVO review_detail(int review_id);			//업체에 달린 리뷰 전부
	
	List<ReviewVO> review_list(int store_number);	// 리뷰 상세 정보

	
	
}
