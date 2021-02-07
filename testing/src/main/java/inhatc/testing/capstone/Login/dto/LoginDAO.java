package inhatc.testing.capstone.Login.dto;

import java.util.List;

public interface LoginDAO {

	// 로그인
	public boolean userLogin(LoginDto logindto);

	// 회원가입
	public void userJoin(LoginDto loginDto);

	// 중복 검사
	public int idCheck(String id);

	// port 체크
	public int portcheck();

	// IP카메라 추가
	public void IPCameraInsert(IPCameraList ipCameraList);

	// 사용불가포트 기록
	public void UsingPort(int port);

	// IP카메라 리스트 호출
	public List<IPCameraList> ipCameraList(IPCameraList camL);

	// IP카메라 저장정보 초기화
	public void deleteCamera(String id);

	// 포트 삭제
	public void deletePort(int port);

	// 카메라 개수 체크
	public int cameraCheck(String id);

	// ID 찾기
	public String idFind(LoginDto dto);
	
	// PW 확인
	public int pwFind(LoginDto dto);
	
	// PW 변경
	public void pwChange(LoginDto dto);
	
	// PW 중복 확인
	public String pwCheck(String id);
}
