package inhatc.testing.capstone.Login.dto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inhatc.testing.capstone.Login.Service.LoginService;

@Repository 
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String ns = "inhatc.testing.capstone.Login.dto.LoginDAO";
	
	// 로그인
	@Override
	public boolean userLogin(LoginDto dto) {
		String name = sqlSession.selectOne(ns + ".userLogin", dto);
		return (name == null) ? false : true;
	}

	// 회원가입
	@Override
	public void userJoin(LoginDto dto) {
		sqlSession.insert(ns + ".userJoin", dto);
	}

	// 중복 검사
	@Override
	public int idCheck(String id) {
		return sqlSession.selectOne(ns + ".idCheck", id);
	}
	
	// 카메라 검사
	@Override
	public int cameraCheck(String id) {
		return sqlSession.selectOne(ns + ".cameraCheck", id);
	}
	
	// 카메라 추가
	@Override
	public void IPCameraInsert(IPCameraList ipCameraList) {
		sqlSession.insert(ns + ".IPCameraInsert", ipCameraList);
	}
	
	// 카메라 리스트 호출
	@Override
	public List<IPCameraList> ipCameraList(IPCameraList camL) {
		return sqlSession.selectList(ns + ".ipCameraList", camL);
	}
	
	// PORT 확인
	@Override
	public int portcheck() {
		return sqlSession.selectOne(ns + ".portCheck");
	}
	
	// PORT 지정
	@Override
	public void UsingPort(int port) {
		sqlSession.insert(ns + ".UsingPort", port);
	}
	
	// 카메라 삭제
	@Override
	public void deleteCamera(String id) {
		sqlSession.delete(ns + ".deleteCamera", id);
	}
	
	// 미사용 PORT 제거
	@Override
	public void deletePort(int port) {
		sqlSession.delete(ns + ".deletePort", port);
	}
	
	// ID 찾기
	@Override
	public String idFind(LoginDto dto) {
		return sqlSession.selectOne(ns + ".idFind", dto);
	}
	
	// PW 확인
	@Override
	public int pwFind(LoginDto dto) {
		return sqlSession.selectOne(ns + ".pwFind", dto);
	}
	
	// PW 변경
	@Override
	public void pwChange(LoginDto dto) {
		sqlSession.update(ns + ".pwChange", dto);
	}

	// PW 중복 확인
	@Override
	public String pwCheck(String id) {
		return sqlSession.selectOne(ns + ".pwCheck", id);
	}
	
}
