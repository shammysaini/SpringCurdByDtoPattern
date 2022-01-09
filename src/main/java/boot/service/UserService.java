package boot.service;

import java.util.List;

import boot.controller.dto.UserRequest;

public interface UserService {
	
	public String saveUser(UserRequest userRequest);
	public List<UserRequest>getUser();

}
