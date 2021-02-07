package inhatc.testing.capstone.Login.dto;

import lombok.Data;

@Data
public class IPCameraList {
	private String userid;
	private String camname;
	private String ip;
	private int rtspport;
	private int httpport;
	private String camid;
	private String campw;
	private int port;
}
