package com.techno2know.friends;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.techno2know.friends.model.Friend;
import com.techno2know.friends.service.FriendService;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataJPAServiceTests {
	

		@Autowired
		FriendService friendService;

		@Test
		public void testCreateReadDelete() {
			
			Friend friend = new Friend(20,"Karthik", "Sid", 36, true);
			friendService.save(friend);
									
			Iterable<Friend> friends = friendService.findAll();
			Assertions.assertThat(friends).extracting(Friend:: getFirstName).containsAnyOf("Karthik");
			

			friendService.delete(friend);
			
			Assertions.assertThat(friendService.findById(20)).isEmpty();
			
		}
	}
