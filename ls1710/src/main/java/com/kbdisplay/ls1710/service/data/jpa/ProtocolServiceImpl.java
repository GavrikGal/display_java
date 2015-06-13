package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;
import com.kbdisplay.ls1710.repository.ProtocolRepository;
import com.kbdisplay.ls1710.service.data.ProtocolService;

@Service("protocolService")
@Repository
@Transactional
public class ProtocolServiceImpl implements ProtocolService {

	@Autowired
	private ProtocolRepository protocolRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Protocol> findAll() {
		return Lists.newArrayList(protocolRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Protocol findById(Long id) {
		return protocolRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Protocol findByNumberAndPostfix(Long number, String postfix) {
		return protocolRepository.findByNumberAndPostfix(number, postfix);
	}

	@Override
	public Protocol save(Protocol protocol) {
		return protocolRepository.save(protocol);
	}

	public void delete(Protocol protocol) {
		protocolRepository.delete(protocol.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Protocol> findByMeasurements(List<Measurement> measurements) {
		return protocolRepository.findByMeasurements(measurements);
	}

}
