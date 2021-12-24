package iot;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class IotDAO implements IotService {
	
	@Autowired private SqlSession sql;

	@Override
	public void state_update(IotVO vo) {
		// TODO Auto-generated method stub
		sql.update("iot.mapper.state_update", vo);
		
	}

	
	
	
	

}
