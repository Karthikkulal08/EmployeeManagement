package com.karthik.Employee.Controller;

import com.karthik.Employee.dtos.ModelMapperResponseDTO;
import com.karthik.Employee.dtos.ModelMapperSmallDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelMapperExample {
	@Autowired
	private ModelMapper modelmapper;

	@GetMapping("/mapperExample")
	public ModelMapperSmallDTO getModelMapperExample(){
		ModelMapperResponseDTO mmp = new ModelMapperResponseDTO();
		mmp.setId("#beast");
		mmp.setFirstName("Beast");
		mmp.setEmployeeCode(702581);
		mmp.setDepartment("IT");
		mmp.setLastName("life");

		return modelmapper.map(mmp,ModelMapperSmallDTO.class);
	}

}
