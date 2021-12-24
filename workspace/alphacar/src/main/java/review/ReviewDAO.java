package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAO implements ReviewService {
	@Autowired private SqlSession sql;
	
	DataSource dataSource;
	
	@Override
	public int review_insert(ReviewVO vo) {
		
		return sql.insert("review.mapper.insert", vo);
	}

	@Override
	public ReviewVO review_detail(int review_id) {
		return (ReviewVO) sql.selectOne("review.mapper.detail", review_id);
	}
	

	@Override
	public List<ReviewVO> review_list(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("review.mapper.list", store_number);
	}
	

}
