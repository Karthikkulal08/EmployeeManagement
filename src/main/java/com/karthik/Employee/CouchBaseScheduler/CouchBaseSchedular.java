package com.karthik.Employee.CouchBaseScheduler;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Collection;
import com.karthik.Employee.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Component
public class CouchBaseSchedular {
	@Autowired
	private Collection collection;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String QUERY="SELECT * FROM app_users";
	@Scheduled(cron = "0 0 */4 * * *")
	public void updateCouchBase(){
		String id="1";
		List<User> users = new ArrayList<>();
		try{
			 users = namedParameterJdbcTemplate.query(QUERY,new BeanPropertyRowMapper<>(User.class));
			 System.out.println(users);
			 log.info("successfully fetched DB data");
		}catch(Exception e){
			log.info("Error getting data from db with error {}",e.getMessage());
		}

		for(User user : users){
			try{
				String couchbaseKey = "user:" + id + ":" + user.getId();
				collection.upsert(couchbaseKey,user);
				log.info("Successfully updated Object {} ",couchbaseKey);
			}catch(Exception e){
				log.info("Exception in updating object {} got Exception {}",user.getId(),e.getMessage());
			}
		}
	}
}
