package homeMypage;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;
import member.WebMemberVO;

@Component
public class CustomerPage extends PageVO{
	private List<WebMemberVO> list;
	
	public List<WebMemberVO> getList(){
		return list;
	}
	public void setList(List<WebMemberVO> list) {
		this.list = list;
	}
} 
