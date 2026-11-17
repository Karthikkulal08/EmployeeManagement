package com.karthik.Employee.Service;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.UpsertOptions;
import com.karthik.Employee.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CouchBaseService {
	@Autowired
	private  Collection collection;
	private final String id = "1";


	public void saveToCouchBase(List<User> users){
		for(User user : users){
			String couchbaseKey = "user:" + id + ":" + user.getId();
			UpsertOptions options =UpsertOptions.upsertOptions();
			options.expiry(Duration.ofMinutes(60*4));
			collection.upsert(couchbaseKey,user,options);
			log.info("User {} saved to CouchBase in key {}",user,couchbaseKey);
		}
	}


		public User getCouchBaseObject(String key){
		try{
			GetResult result = collection.get(key);
			return result.contentAs(User.class);
		}catch(DocumentNotFoundException e){
			log.info("Error in fetching Document {} with Exception {}",key,e.getMessage());
		}
			return null;
	}
}
