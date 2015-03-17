package com.tool.util.frequent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilForJson {

	private ObjectMapper objectMapper;

    public UtilForJson() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }
    
    public <T> T readeValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
    
    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
    
    
    /*List<Leg> legList = new ArrayList<>(); 
    Leg leg = new Leg();
	leg.setArrAirport("PEK"); legList.add(leg); 
	String json = jsonMapper.writeValueAsString(legList);
	System.out.println("turn before ---> "+json);
	JavaType javaType = getCollectionType(ArrayList.class, Leg.class);
	List<Leg> list = (List<Leg>)mapper.readValue(json, javaType);
	System.out.println("turn after ---> "+list.get(0).getArrAirport());*/
}
