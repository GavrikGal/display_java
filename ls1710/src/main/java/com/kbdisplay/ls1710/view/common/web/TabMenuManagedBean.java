package com.kbdisplay.ls1710.view.common.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "tabMenu")
@SessionScoped
public class TabMenuManagedBean {
    private int index=3;

    public int getIndex() {
        return index;

    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String goTo(int index){
        // Do some work
        // Change the index that TabMenu refers as activated tab
        this.index = index;
        switch (index) {
		case 0:
			return "dataJournal";
		case 1:
			return "protocol";
		case 2:
			return "settings";

		default:
			return "dataJournal";
		}


    }

    public String onChangeTab(TabChangeEvent event) {

		String viewName = "";

		if (event.getTab().getTitle().equals("Журнал измерений")) {
			viewName = "/dataJournal";
			this.index = 0;
		}
		if (event.getTab().getTitle().equals("Протоколы")) {
			viewName = "/protocol";
			this.index = 1;
		}
		if (event.getTab().getTitle().equals("Настройки")) {
			viewName = "/settings";
			this.index = 2;
		}

		return viewName;
    }
}
