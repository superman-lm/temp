package com.tool.test;


public class AAA {

	public static void main(String[] args) {
//		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
//				"org.apache.commons.logging.impl.NoOpLog");
//		TestDome testDome = new TestDome();
//		EastAirServiceImpl eastAirServiceImpl = new EastAirServiceImpl();
		
		//��ȡ������Ϣ
//		List<ODSearch> ods = new ArrayList<ODSearch>();
//		ODSearch odSearch1 = new ODSearch();
//		odSearch1.setDeparture("nay");
//		odSearch1.setArrive("pvg");
//		odSearch1.setPeriod(2);
//		ods.add(odSearch1);
//
//		ODSearch odSearch2 = new ODSearch();
//		odSearch2.setDeparture("nay");
//		odSearch2.setArrive("nkg");
//		odSearch2.setPeriod(1);
//		ods.add(odSearch2);

//		ODSearch odSearch3 = new ODSearch();
//		odSearch3.setDeparture("nay");
//		odSearch3.setArrive("can");
//		odSearch3.setPeriod(3);
//		ods.add(odSearch3);
//		eastAirServiceImpl.flight(ods);
		
		//ģ�����
//		Flight flight = new Flight();
//		flight.setPrice("1240");
//		flight.setCabin("��׼���ò�");
//		flight.setAirLine("MU3178");
//		flight.setDepCity("nay");
//		flight.setArrCity("pvg");
//		flight.setDepDate("150127");
//		System.out.println(eastAirServiceImpl.pricing(flight));
		
//		System.out.println("��׼���òգ�Y�գ�".indexOf("��׼���ò�")>-1);do
		
		
		//ģ��Ԥ��
//		Flight flight = new Flight();
//		flight.setPrice("1240");
//		flight.setCabin("��׼���ò�");
//		flight.setAirLine("MU3178");
//		flight.setDepCity("nay");
//		flight.setArrCity("pvg");
//		flight.setDepDate("150131");
//		List<Passage> passages = new ArrayList<Passage>();
//		Passage adt = new Passage("adt", "����", "18312345678", "987654321", null, null);
//		Passage chn = new Passage("chn", "����", "18387654321", "123456789", null, null);
//		passages.add(adt);
//		passages.add(chn);
//		eastAirServiceImpl.booking(flight,passages);
		
		Test a = new Test();
		aa(a.getClass());
		
	}

	public static void aa(Class<?> clasz){
		try {
			System.out.println(clasz);
			Class<?> obj = Class.forName(clasz.getName());
			System.out.println(obj.getName());
		} catch (Exception e) {
			
		}
		
	}
}
