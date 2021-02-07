package inhatc.testing.capstone.Login.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import inhatc.testing.capstone.Login.dto.IPCameraList;
import inhatc.testing.capstone.Login.dto.LoginDto;

public interface LoginService {

	// 로그인
	boolean userLogin(LoginDto login, HttpSession session) throws Exception;
	
	// 회원가입
	void userJoin(LoginDto login);

	// 중복 검사
	int idCheck(String id) throws Exception;

	// 로그아웃
	void userLogout(HttpSession session) throws Exception;

	// List<LoginDto> ipCameraList(LoginDto dto);
	List<IPCameraList> ipCameraList(IPCameraList camL);
	
	// 카메라 개수 체크
	int cameraCheck(String id) throws Exception;
	
	// ID 찾기
	String idFind(LoginDto dto);
	
	// PW 확인
	int pwFind(LoginDto dto) throws Exception;
	
	// PW 변경
	void pwChange(LoginDto dto);
	
	// PW 중복 확인
	String pwCheck(String id);
}
