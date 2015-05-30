package com.kbdisplay.ls1710.view.common.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tabMenuManagedBean")
@SessionScoped
public class TabMenuManagedBean {
    private int index = 0;

    public int getIndex() {
    	System.out.println(index);
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

			return "/app/dataJournal";

		case 2:

			return "/app/settings";

		default:
			return "/app/dataJournal";
		}


    }
}
