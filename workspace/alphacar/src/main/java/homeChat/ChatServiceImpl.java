package homeChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl{
	
	@Autowired private ChatDAO dao;

	public void insertChat(ChatVO vo) {
		dao.insertChat(vo);	
	}
}
