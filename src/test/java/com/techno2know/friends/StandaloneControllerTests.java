package com.techno2know.friends;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.techno2know.friends.controller.FriendController;
import com.techno2know.friends.model.Friend;
import com.techno2know.friends.service.FriendService;


@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class StandaloneControllerTests {


	@MockBean
	FriendService friendService;

	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testCreateReadDelete() throws Exception{
		
		
		List<Friend> friends = new ArrayList<>(); 
		
		
		Friend friend = new Friend(9,"Karthik", "Sid", 36, true);

		friends.add(friend);


		Mockito.when(friendService.findAll()).thenReturn( friends);
		
		mockMvc.perform(MockMvcRequestBuilders
			      .get("/friend"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Karthik")));
		
		
		Assertions.assertThat(friends).last().hasFieldOrPropertyWithValue("firstName", "Karthik");
		

		
	}
}