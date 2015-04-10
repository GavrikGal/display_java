package com.kbdisplay.ls1710.view.dataJournal;

import org.primefaces.event.SelectEvent;

import com.kbdisplay.ls1710.domain.Measurement;

public interface EditForm {

	void init(final Measurement lastMeasurement);

	void onRowDbSelect(final SelectEvent event);

	void edit(final Measurement selected);

	EditData getData();

	void setData(EditData data);

	// TODO что-то надо с этим сделать, нужно ли оно тут, или переместить
	// куда-то
	boolean isShowNewModelDialog();

	void setShowNewModelDialog(final boolean showNewModelDialog);



}
