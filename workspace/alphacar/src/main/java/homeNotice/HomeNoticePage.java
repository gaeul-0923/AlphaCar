package homeNotice;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class HomeNoticePage extends PageVO{
	private List<HomeNoticeVO> list;
	
	public List<HomeNoticeVO> getList(){
		return list;
	}
	
	public void setList(List<HomeNoticeVO> list) {
		this.list = list;
	}
} 
