package inhatc.testing.capstone.Login.Controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import inhatc.testing.capstone.Login.Service.LoginService;
import inhatc.testing.capstone.Login.dto.IPCameraList;
import inhatc.testing.capstone.Login.dto.LoginDAO;
import inhatc.testing.capstone.Login.dto.LoginDto;
import inhatc.testing.capstone.Login.dto.PortDto;

@Controller
public class LoginController {

	@Autowired
	private LoginService LoginService;

	@Autowired
	private LoginDAO LoginDAO;

	// 로그인 페이지
	@RequestMapping("/")
	public String userLoginPage() {
		return "/Login/UserLogin";
	}

	// 로그인
	@PostMapping(value = "/login")
	@ResponseBody
	public void userLogin(@RequestBody LoginDto dto, HttpServletResponse res, HttpSession session) throws Exception {
		int check = 0;
		if(LoginService.userLogin(dto, session)) {
			check = 1;
		} else {
			LoginService.userLogout(session);
		}
		res.getWriter().print(check);
	}

	// 메인 화면
	@RequestMapping("/main")
	public String goHome(HttpSession session) throws Exception {
		return "Login/Success";
	}

	// 회원가입 페이지
	@RequestMapping("/joinPage")
	public String Insert() {
		return "/Login/Insert";
	}

	// 회원가입
	@PostMapping(value = "/join")
	@ResponseBody
	public void UserInsert(@RequestBody LoginDto dto) {
		LoginService.userJoin(dto);
		return;
	}

	// 아이디 중복 검사
	@RequestMapping("/idCheck")
	public void idCheck(@RequestParam String id, HttpServletResponse res) throws Exception {
		int result = 0;
		if (LoginService.idCheck(id) != 0) {
			result = 1;
		}
		res.getWriter().print(result);
	}

	// PW 변경
	@PostMapping(value = "/pwChange")
	@ResponseBody
	public void pwChange(@RequestBody LoginDto dto) throws Exception {
		LoginService.pwChange(dto);
		return;
	}

	// PW 중복 확인
	@RequestMapping("/pwCheck")
	public void pwCheck(@RequestParam String id, HttpServletResponse res) throws Exception {
		res.getWriter().print(LoginService.pwCheck(id));
	}

	// 아이디 찾기
	@PostMapping(value = "/idFind")
	@ResponseBody
	public void idFind(@RequestBody LoginDto dto, HttpServletResponse res) throws Exception {
		String result = LoginService.idFind(dto);
		res.getWriter().print(result);
	}

	// PW 확인
	@PostMapping(value = "/pwFind")
	@ResponseBody
	public void pwFind(@RequestBody LoginDto dto, HttpServletResponse res) throws Exception {
		int result = 0;
		if (LoginService.pwFind(dto) != 0) {
			result = 1;
		}
		res.getWriter().print(result);
	}

	// 카메라 유무 체크
	@RequestMapping("/cameraCheck")
	public void cameraCheck(HttpSession session, HttpServletResponse res) throws Exception {
		int result = 1;
		if (LoginService.cameraCheck(session.getAttribute("id").toString()) == 0) {
			result = 0;
		}
		res.getWriter().print(result);
	}
	
	// 세션 ID 받기
	@RequestMapping("/getID")
	public void getID(HttpSession session, HttpServletResponse res) throws Exception {
		res.getWriter().print(session.getAttribute("id").toString());
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String userLogout(HttpSession session) throws Exception {
		LoginService.userLogout(session);
		return "redirect:/";
	}

	// IP카메라 등록창 호출
	@RequestMapping("/member/IPCameraInsert")
	public String IPCameraInsert(Model model, IPCameraList camL, HttpSession session) {
		List<IPCameraList> list = LoginService.ipCameraList(camL);
		model.addAttribute("list", list);
		return "Login/IPCameraInsert";
	}

	// IP카메라 DB 등록
	@PostMapping(value = "/member/IPCameraIn")
	@ResponseBody
	public void IPCameraIn(@RequestBody IPCameraList[] ipCameraList, HttpSession session) throws Exception {
		String id = session.getAttribute("id").toString();
		LoginDAO.deleteCamera(id);
		for (int i = 0; i < ipCameraList.length; i++) {
			if (ipCameraList[i].getPort() == 0) {
				int port = LoginDAO.portcheck();
				LoginDAO.UsingPort(port);
				ipCameraList[i].setPort(port);
			}
			ipCameraList[i].setUserid(id);
			LoginDAO.IPCameraInsert(ipCameraList[i]);
			System.out.println(ipCameraList[i]);
		}
	}

	// Port 삭제
	@PostMapping(value = "/member/deletePort")
	@ResponseBody
	public void deleteusePort(@RequestBody PortDto[] portDto) throws Exception {
		for (int i = 0; i < portDto.length; i++) {
			LoginDAO.deletePort(portDto[i].getPort());
		}
	}

	// ID,PW 찾기
	@RequestMapping("/member/find")
	public String findIDSPW() {
		return "Login/FindIDPW";
	}
}