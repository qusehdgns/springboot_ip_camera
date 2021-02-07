package inhatc.testing.capstone.show.dto;

import lombok.Data;

@Data
public class ShowDto {
	
	private String userid;
	private String ip;
	private int rtspport;
	private int httpport;
	private String camid;
	private String campw;
	private int port;
}
