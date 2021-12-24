package homeChart;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class HomeChartDAO implements HomeChartService {

	@Autowired private SqlSession sql;

	@Override
	public List<ChartVO> month_list(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("homeChart.mapper.month_list", store_number);
	}

	@Override
	public List<ChartVO> time_rank(int store_number) {
		List<ChartVO> list = sql.selectList("homeChart.mapper.time_rank", store_number);
		return list;
	}

	@Override
	public List<ChartVO> week_cnt(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("homeChart.mapper.week_cnt", store_number);
	}

	@Override
	public List<ChartVO> dcode_cnt() {
		// TODO Auto-generated method stub
		return sql.selectList("homeChart.mapper.dcode_cnt");
	}

	


}
