package com.shms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/receive")
public class WearInfoController {
	
	@Autowired
	WearInfoServiceImpl wearInfoService;
	
	@PostMapping
	public void receiveWearInfo(@RequestBody StringBuilder json, Errors errors) {
		WearInfo jsonMap = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			jsonMap = mapper.readValue(json.toString(), WearInfo.class);
			
			new WearInfoValidator().validate(jsonMap, errors);
			
			if (errors.hasErrors()) {
				System.out.println("we got error");
			
//				return jsonMap;
			}
			System.out.println("wearInfoService start");
			wearInfoService.receiveWearInfo(jsonMap);
			
		} catch(Exception e) {
			e.printStackTrace();
			
//			return jsonMap;
		}
//		return jsonMap;
	}
}
