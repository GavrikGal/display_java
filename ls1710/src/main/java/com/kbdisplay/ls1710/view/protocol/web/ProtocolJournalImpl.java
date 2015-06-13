package com.kbdisplay.ls1710.view.protocol.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kbdisplay.ls1710.domain.Protocol;
import com.kbdisplay.ls1710.view.protocol.ProtocolJournal;

@ManagedBean(name = "protocolJournal")
@ViewScoped
public class ProtocolJournalImpl implements ProtocolJournal, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 557946504055150952L;

	private List<Protocol> protocols;

	private Protocol selectedProtocol;

	@Override
	public List<Protocol> getProtocols() {
		return protocols;
	}

	@Override
	public void setProtocols(List<Protocol> protocols) {
		this.protocols = protocols;
	}

	@Override
	public Protocol getSelectedProtocol() {
		return selectedProtocol;
	}

	@Override
	public void setSelectedProtocol(Protocol selectedProtocol) {
		this.selectedProtocol = selectedProtocol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
