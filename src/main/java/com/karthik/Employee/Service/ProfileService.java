package com.karthik.Employee.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

	private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

	public String fetchUserProfileData(String username){
		log.info("Step 1: Fetching database records for user: {}",username);

		boolean isVip = username.equalsIgnoreCase("admin");

		log.info("Step 2: Business logic complete user is VIP: {}",isVip);
		return isVip ? "VIP Gold Member" : "Standard Member";
	}
}
