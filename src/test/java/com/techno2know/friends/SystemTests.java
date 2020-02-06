package com.techno2know.friends;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techno2know.friends.model.Friend;

public class SystemTests {
	
	@Test
	public void testCreateReadDelete() {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8080/friend";
		
		Friend friend = new Friend(8,"Karthik", "Sid", 36, true);
		
		ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);
		Friend[] friends =  restTemplate.getForObject(url, Friend[].class);
		
		Assertions.assertThat(friends).extracting(Friend:: getFirstName).containsAnyOf("Karthik");
		
		restTemplate.delete(url+"/"+entity.getBody().getId());
		Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).doesNotContain(friend);
		
	}

}
