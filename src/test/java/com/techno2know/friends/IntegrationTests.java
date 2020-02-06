package com.techno2know.friends;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.techno2know.friends.controller.FriendController;
import com.techno2know.friends.model.Friend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	FriendController friendController;

	@Test
	public void testCreateReadDelete() {
		
		Friend friend = new Friend(9,"Karthik", "Sid", 36, true);
		
		Friend friendRes = friendController.addFriend(friend);
		
		Iterable<Friend> friends = friendController.getAllFriends();
		
		Assertions.assertThat(friends).last().hasFieldOrPropertyWithValue("firstName", "Karthik");
		

		
	}
}
