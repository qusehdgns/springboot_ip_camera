package inhatc.testing.capstone.show.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inhatc.testing.capstone.show.dto.ShowDto;
import inhatc.testing.capstone.show.mapper.ShowMapper;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowMapper showMapper;
	
	@Override
	public List<ShowDto> ShowList(String id) {
		return showMapper.showList(id);
	}
}