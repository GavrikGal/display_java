package com.kbdisplay.ls1710.view.settings.web;

import java.io.Serializable;

import org.primefaces.event.TabChangeEvent;

import com.kbdisplay.ls1710.view.settings.Settings;

public class SettingsImpl implements Settings, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4450392946098868228L;
	private int tabIndex = 0;


	@Override
	public int getTabIndex() {
		return tabIndex;
	}

	@Override
	public void setTabIndex(int tabIndex) {

		System.out.println(tabIndex);

		this.tabIndex = tabIndex;
	}

	@Override
	public String onChangeTab(TabChangeEvent event) {

		String viewName = "";

		if (event.getTab().getTitle().equals("Нормы")) {
			viewName = "/normsSetting";
			this.tabIndex = 0;
			System.out.println("Нормы");
		}
		if (event.getTab().getTitle().equals("Личные данные")) {
			viewName = "/userDetailsSetting";
			this.tabIndex = 1;
			System.out.println("Личные данные");
		}
		if (event.getTab().getTitle().equals("Пользователи")) {
			viewName = "/usersSetting";
			this.tabIndex = 2;
			System.out.println("Пользователи");
		}

		return viewName;
	}
}
