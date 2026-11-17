package com.karthik.Employee.Controller;

import com.karthik.Employee.CustomException.CustomException;
import com.karthik.Employee.Dao.UserRepository;
import com.karthik.Employee.Service.CouchBaseService;
import com.karthik.Employee.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final CouchBaseService couchBaseService;

	// CREATE: Save a new user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addMultiple")
	public String addMultiple(@RequestBody List<User> users){
		List<User> userRes = new ArrayList<>();
		for(User user:users){
			userRes.add(userRepository.save(user));
		}
		couchBaseService.saveToCouchBase(userRes);
		return "successfull";
	}

	// READ: Get all users
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/finaAll/namedJDBC")
	public List<User> findAllUsers(){
		String query = "SELECT * FROM app_users";
		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("username",username);
		return namedParameterJdbcTemplate.query(query,new BeanPropertyRowMapper<>(User.class));
	}

	@GetMapping("findUser/{name}")
	public List<User> findUser(@PathVariable String name){
		String query = "SELECT * FROM app_users where name=:name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name",name);
		return namedParameterJdbcTemplate.query(query,params,new BeanPropertyRowMapper<>(User.class));
	}



	@GetMapping("/getCouchBaseObj/{key}")
	public Object getCouchBaseObj(@PathVariable String key){
		return couchBaseService.getCouchBaseObject(key)!=null ? couchBaseService.getCouchBaseObject(key) : "Not found";
	}

	@GetMapping("/number/{num}")
	public int getNumber(@PathVariable int num) throws CustomException {
		if(num<10){
			throw new CustomException("Number is less than 10");
		}
		return num;
	}

	// READ: Get one user by ID
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// DELETE: Remove a user
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
