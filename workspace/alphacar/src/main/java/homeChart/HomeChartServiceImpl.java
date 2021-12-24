package homeChart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HomeChartServiceImpl implements HomeChartService {

	@Autowired private HomeChartDAO dao;

	@Override
	public List<ChartVO> month_list(int store_number) {
		// TODO Auto-generated method stub
		return dao.month_list(store_number);
	}

	@Override
	public List<ChartVO> time_rank(int store_number) {
		// TODO Auto-generated method stub
		return dao.time_rank(store_number);
	}

	@Override
	public List<ChartVO> week_cnt(int store_number) {
		// TODO Auto-generated method stub
		return dao.week_cnt(store_number);
	}

	@Override
	public List<ChartVO> dcode_cnt() {
		// TODO Auto-generated method stub
		return dao.dcode_cnt();
	}

	

}
