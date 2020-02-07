package com.techno2know.friends.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techno2know.friends.model.Friend;
import com.techno2know.friends.service.FriendService;
import com.techno2know.friends.utils.FieldErrorMessage;

@RestController
public class FriendController {

	@Autowired
	FriendService friendService;

	@PostMapping("/friend")
	public Friend addFriend(@Valid @RequestBody Friend friend) {
		return friendService.save(friend);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

		List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream()
				.map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		return fieldErrorMessages;

	}

	@PutMapping("/friend")
	public ResponseEntity<Friend> updateFriend(@RequestBody Friend friend) {

		if (friendService.findById(friend.getId()).isPresent())
			return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
		else
			return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/friend")
	public Iterable<Friend> getAllFriends() {
		return friendService.findAll();
	}

	@GetMapping("/friend/{id}")
	public Optional<Friend> findByFriendId(@PathVariable Integer id) {
		return friendService.findById(id);
	}

	@GetMapping("/friend/search")
	public Iterable<Friend> getFriends(@RequestParam(value = "first", required = false) String firstName,
			@RequestParam(value = "last", required = false) String lastName) {

		if (firstName != null && lastName != null)
			return friendService.findByFirstNameAndLastName(firstName, lastName);
		else if (firstName != null && lastName == null)
			return friendService.findByFirstName(firstName);
		else if (firstName == null && lastName != null)
			return friendService.findByLastName(lastName);
		else
			return friendService.findAll();
	}

	/*
	 * @GetMapping("/friend/search") public Iterable<Friend>
	 * findByFirst(@RequestParam("first") String firstName) { return
	 * friendService.findByFirstName(firstName); }
	 * 
	 * @GetMapping("/friend/search") public Iterable<Friend>
	 * findByLast(@RequestParam("last") String lastName) { return
	 * friendService.findByLastName(lastName); }
	 */

	@DeleteMapping("/friend/{id}")
	public void deleteFriends(@PathVariable Integer id) {
		friendService.deleteById(id);
	}
	
	@GetMapping("/wrong")
	public Friend SomethingIsWrong() {
		throw new ValidationException("Something is worng");
	}

}
