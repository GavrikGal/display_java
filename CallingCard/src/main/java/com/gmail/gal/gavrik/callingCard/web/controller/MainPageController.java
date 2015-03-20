package com.gmail.gal.gavrik.callingCard.web.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.gal.gavrik.callingCard.service.file.FileFinderService;
import com.gmail.gal.gavrik.callingCard.web.view.MainPageView;

/**
 * контроллер отвечающий за обработку представления главной страницы.
 *
 * @author Gavrik
 * @version 1.0
 */
@Component("mainPageController")
public class MainPageController {

	/**
	 * логгер класса.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(MainPageController.class);

	@Autowired
	private FileFinderService fileFinderService;


	/**
	 * создает новую бин для страницы визитки.
	 *
	 * @return бин-поддержки страницы визитки.
	 */
	public final MainPageView newMainPageView() {

		logger.info("Creating new main page");

		MainPageView mainPagelView = new MainPageView();

		System.out.println("TyT");
		List<File> folders = null;
		String rootPath = "D:\\TestPath";
		try {
			folders = fileFinderService.findDirectories(rootPath);
		} catch (Exception e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
		System.out.println("uJlu TyT");
		if (folders != null) {
			for (File file : folders) {
				System.out.println(file.getName());
			}
		} else {
			System.out.println("Bce");
		}

		return mainPagelView;
	}


}
