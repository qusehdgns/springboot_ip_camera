package inhatc.testing.capstone.Login.Service;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import inhatc.testing.capstone.Login.dto.IPCameraList;
import inhatc.testing.capstone.Login.dto.LoginDAO;
import inhatc.testing.capstone.Login.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO dao;
	
	@Autowired
	private static Hashtable<String, String> loginUsers = new Hashtable<String, String>();

	String id;
	
	// 로그인
	@Override
	public boolean userLogin(LoginDto login, HttpSession session)  throws Exception {
		boolean isLogin = isLogin(login.getId());
		if(!isLogin) {
			boolean result = dao.userLogin(login);
			if (result) {
				setSession(session, login);
				id = login.getId();
			}
			return result;
		}
		return !isLogin;
	}
	
	// 회원가입
	@Override
	public void userJoin(LoginDto login) {
		login.setId(login.getId());
		login.setPw(login.getPw());
		login.setName(login.getName());
		login.setPhone(login.getPhone());
		dao.userJoin(login);		
	}

	// 세션
	public void setSession(HttpSession session, LoginDto login) {
		loginUsers.put(session.getId(), login.getId());
		session.setAttribute("id", login.getId());
	}

	// 로그인이 되어있는지 확인
	private boolean isLogin(String id) {
		boolean isLogin = false;
		
		Enumeration<String> e = loginUsers.keys();
		String key = "";
		
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (id.equals(loginUsers.get(key)))
				isLogin = true;
		}
		return isLogin;
	}
	
	// 중복 검사
	@Override
	public int idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}
	
	@Override
	public int cameraCheck(String id) throws Exception {
		return dao.cameraCheck(id);
	}
	
	// 로그아웃
	@Override
	public void userLogout(HttpSession session) throws Exception {
		loginUsers.remove(session.getId());
		session.invalidate();
	}
	
	// 카메라 리스트 호출
	@Override
	public List<IPCameraList> ipCameraList(IPCameraList camL) {
		camL.setUserid(id);
		System.out.println("--------------------------------------------------------->"+camL.getUserid());
		return dao.ipCameraList(camL);
	}

	// ID 찾기
	@Override
	public String idFind(LoginDto dto) {
		String id = dao.idFind(dto);
		return id;
	}

	// PW 확인
	@Override
	public int pwFind(LoginDto dto) throws Exception {
		return dao.pwFind(dto);
	}
	
	// PW 변경
	@Override
	public void pwChange(LoginDto dto) {
		dao.pwChange(dto);
	}

	// PW 중복 확인
	@Override
	public String pwCheck(String id) {
		return dao.pwCheck(id);
	}
	
}
