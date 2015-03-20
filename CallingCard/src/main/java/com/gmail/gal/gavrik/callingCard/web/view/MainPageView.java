package com.gmail.gal.gavrik.callingCard.web.view;

import java.io.Serializable;

import org.springframework.stereotype.Component;


/**
 * бин поддержки главной страницы.
 *
 * @author Gavrik
 *
 */
@Component("mainPageView")
public class MainPageView implements Serializable {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 13968292769953764L;



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MainPageView() {

	}



}
