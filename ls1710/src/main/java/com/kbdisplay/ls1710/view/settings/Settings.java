package com.kbdisplay.ls1710.view.settings;

import org.primefaces.event.TabChangeEvent;

public interface Settings {


	public int getTabIndex();

	public void setTabIndex(int tabIndex);

	public String onChangeTab(TabChangeEvent event);
}
