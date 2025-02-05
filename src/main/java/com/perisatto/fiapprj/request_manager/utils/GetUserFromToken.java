package com.perisatto.fiapprj.request_manager.utils;

import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GetUserFromToken {

	public static String getUser(String token) throws JsonMappingException, JsonProcessingException {		
		String[] chunks = token.split("\\.");
		
		Base64.Decoder decoder = Base64.getUrlDecoder();
		
		String payload = new String(decoder.decode(chunks[1]));
		
		ObjectNode node = new ObjectMapper().readValue(payload, ObjectNode.class);
		
		JsonNode userIdNode = node.get("custom:id");
		
		return userIdNode.asText();
	}

}
