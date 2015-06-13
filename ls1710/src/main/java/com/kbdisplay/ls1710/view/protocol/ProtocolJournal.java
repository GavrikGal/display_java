package com.kbdisplay.ls1710.view.protocol;

import java.util.List;

import com.kbdisplay.ls1710.domain.Protocol;

public interface ProtocolJournal {

	public List<Protocol> getProtocols();

	public void setProtocols(List<Protocol> protocols);

	public Protocol getSelectedProtocol();

	public void setSelectedProtocol(Protocol selectedProtocol);

}
