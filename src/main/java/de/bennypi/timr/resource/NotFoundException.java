package de.bennypi.timr.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}
*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
		super();
	}

	public NotFoundException(String id) {
		super("could not find entity with id: '" + id + "'.");
	}
}
