package inhatc.testing.capstone.show.service;

import java.util.List;

import inhatc.testing.capstone.show.dto.ShowDto;

public interface ShowService {
	
	List<ShowDto> ShowList(String id);
	
}
