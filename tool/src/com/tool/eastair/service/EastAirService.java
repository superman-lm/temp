package com.tool.eastair.service;

import java.util.List;

import com.tool.eastair.entity.Flight;
import com.tool.eastair.entity.ODSearch;
import com.tool.eastair.entity.Passage;

public interface EastAirService {
	
	/***
	 * 获取航班数据
	 * @param ods
	 */
	void flight(List<ODSearch> ods);
	
	/***
	 * 模拟预定
	 * @param odSearch
	 */
	void booking(Flight flight,List<Passage> passages);
	
	/***
	 * 模拟验价
	 * @return
	 */
	boolean pricing(Flight flight);
	
}
