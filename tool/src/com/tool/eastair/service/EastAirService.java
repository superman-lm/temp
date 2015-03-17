package com.tool.eastair.service;

import java.util.List;

import com.tool.eastair.entity.Flight;
import com.tool.eastair.entity.ODSearch;
import com.tool.eastair.entity.Passage;

public interface EastAirService {
	
	/***
	 * ��ȡ��������
	 * @param ods
	 */
	void flight(List<ODSearch> ods);
	
	/***
	 * ģ��Ԥ��
	 * @param odSearch
	 */
	void booking(Flight flight,List<Passage> passages);
	
	/***
	 * ģ�����
	 * @return
	 */
	boolean pricing(Flight flight);
	
}
