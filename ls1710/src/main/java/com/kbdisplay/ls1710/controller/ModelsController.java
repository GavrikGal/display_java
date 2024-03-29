package com.kbdisplay.ls1710.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.service.data.ModelService;


@RequestMapping("/models")
@Component("modelsController")
public class ModelsController {
	
	final Logger			logger	= LoggerFactory.getLogger(ModelsController.class);
	
	@Autowired
	private ModelService modelsService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		ModelOfEquipment model = modelsService.findById(id);

		if (model.getPhoto() != null) {
			logger.info("Downloading photo for id: {} with size {}", model.getId(),
					model.getPhoto().length);
		}
		return model.getPhoto();
	}


}
