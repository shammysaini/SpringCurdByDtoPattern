package boot.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
	
	private String id;
	private String userName;
	private String password;
	private String userEmail;
	private String country;
	private String gender;
	private String language;
	public MultipartFile file;
	private String image;
	private String imgName;

}
