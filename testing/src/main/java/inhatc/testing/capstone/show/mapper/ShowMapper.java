package inhatc.testing.capstone.show.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import inhatc.testing.capstone.show.dto.ShowDto;

@Mapper
public interface ShowMapper {
	
	List<ShowDto> showList(String id);
	
}
