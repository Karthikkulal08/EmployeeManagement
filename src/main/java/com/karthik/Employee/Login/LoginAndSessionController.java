package com.karthik.Employee.Login;

import com.karthik.Employee.Security.JwUtil;
import com.karthik.Employee.Service.ProfileService;
import com.karthik.Employee.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class LoginAndSessionController {


	private final ProfileService profileService;
	private final JwUtil jwUtil;



	@GetMapping("/login")
	public String login(HttpSession session,@RequestParam String name){
		session.setAttribute("username",name);
		String token = jwUtil.generateToken(name, session.getId());
		return "Logged in as: " + name + "| session ID: " + session.getId() + "Token "+token;
	}

	@GetMapping("/profile")
	public String profile(HttpSession session){
		String user = (String) session.getAttribute("username");
		if(user==null){
			return "No active session.Please login first";
		}
		log.info("Hello");
		log.info("Controller recieved request for /profile");

		String rank = profileService.fetchUserProfileData(user);

		return "Welcome back" + user+"ID"+session.getId()+"Rank "+ rank;
	}

	@GetMapping("/check")
	public String checkMethod(HttpSession session){
		String user = (String) session.getAttribute("username");
		return "Working";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "Logged out successfully";
	}
}
