package homeChart;


import java.util.List;


public interface HomeChartService {
	//월별 이용자 수 조회
	List<ChartVO> month_list(int store_number);
	
	//해당 달 몰리는 시간대 랭크
	List<ChartVO> time_rank(int store_number);
	
	//해당 주 요일별 이용자 수 조회
	List<ChartVO> week_cnt(int store_number);
	
	List<ChartVO> dcode_cnt();
}
