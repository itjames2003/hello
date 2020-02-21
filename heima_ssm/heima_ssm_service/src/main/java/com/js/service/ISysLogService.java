package com.js.service;

import com.js.domain.SysLog;

import java.util.List;

public interface ISysLogService {
	public void save(SysLog sysLog);

	List<SysLog> findAll();
}
