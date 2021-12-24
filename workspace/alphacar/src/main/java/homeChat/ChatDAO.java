package homeChat;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAO{

	@Autowired private SqlSession sql;
	
	public void insertChat(ChatVO vo) {
		sql.insert("homeChat.mapper.insert", vo);
	}
}
