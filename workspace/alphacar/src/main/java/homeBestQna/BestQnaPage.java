package homeBestQna;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class BestQnaPage extends PageVO{
	private List<BestQnaVO> list;
	
	public List<BestQnaVO> getList(){
		return list;
	}
	
	public void setList(List<BestQnaVO> list) {
		this.list = list;
	}
} 
