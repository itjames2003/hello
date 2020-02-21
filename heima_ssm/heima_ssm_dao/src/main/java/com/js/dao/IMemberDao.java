package com.js.dao;

import com.js.domain.Member;
import org.apache.ibatis.annotations.Select;
//会员
public interface IMemberDao {
	@Select("select * from member where id=#{id}")
	public Member findById(String id);
}
