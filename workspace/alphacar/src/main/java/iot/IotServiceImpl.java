package iot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IotServiceImpl implements IotService {
	
	@Autowired private IotDAO dao;

	@Override
	public void state_update(IotVO vo) {
		// TODO Auto-generated method stub
		dao.state_update(vo);
	}


}
