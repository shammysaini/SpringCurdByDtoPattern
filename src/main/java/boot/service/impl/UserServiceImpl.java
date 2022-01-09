package boot.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import boot.controller.dto.UserRequest;
import boot.controller.model.User;
import boot.repository.UserRepository;
import boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Value("${image.upload.file}")
	private String FOLDER_PATH;
	@Autowired ServletContext context;

	@Override
	public String saveUser(UserRequest userRequest) {
		String message = "";
		String language = "";

		// String languageValue =userRequest.getLanguage();
		// System.out.println("......"+languageValue);
		User user = null;

		try {

			if (!StringUtils.isEmpty(userRequest.getId())) {
				Optional<User> optUser = userRepository.findById(Long.parseLong(userRequest.getId()));

				if (optUser.isPresent()) {
					user = optUser.get();
					message = "user update successfully";
				} else {

					System.out.println("......");

				}

			} else {
				user = new User();

				// String value[]= userRequest.getLanguage();
				String value[] = userRequest.getLanguage().split(",");
				for (int i = 0; i < value.length; i++) {

					language += value[i] + " ";

				}
				
				//String pat = context.getRealPath("resourceFiles/image/gift-img/");
			//	System.out.println("hero"+pat);
				//String fileImagePath = new ClassPathResource("static/images").getFile().getAbsolutePath();
				
				String fileImagePath = StringUtils.cleanPath(userRequest.file.getOriginalFilename());
				
			String uploadPath =	System.getProperty("user.dir")+"/src/main/resources/static/images/"+fileImagePath;
			Path path = Paths.get(uploadPath);
			byte[] bytes = userRequest.getFile().getBytes();
			Files.write(path, bytes);
				
				// final String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/downloadFile/")
	                  //   .path(userRequest.file.getOriginalFilename()).toUriString();

				//String fileImagePath = StringUtils.cleanPath(FOLDER_PATH + userRequest.file.getOriginalFilename());
			//String fileImagePathcom = StringUtils.cleanPath(fileImagePath + userRequest.file.getOriginalFilename());
				 
//				 final File convertFile = new File(fileImagePath );
//					convertFile.getParentFile().mkdirs();
//					convertFile.createNewFile();
//					final FileOutputStream fout = new FileOutputStream(convertFile, false);
//					fout.write(userRequest.getFile().getBytes());
//					fout.close();
			
			
//				byte[] bytes = userRequest.getFile().getBytes();
//				Path path = Paths.get(uri);
//				Files.write(path, bytes);

				user.setUserEmail(userRequest.getUserEmail());
				user.setUserName(userRequest.getUserName());
				user.setPassword(userRequest.getPassword());
				user.setCountry(userRequest.getCountry());
				user.setGender(userRequest.getGender());
				user.setLanguage(language);
				user.setImage(uploadPath);
				user.setImgName(fileImagePath);

				userRepository.save(user);
				message = "data has been saved successfull";
				return message;
			}

		} catch (Exception exp) {
			System.out.println("Exception is " + exp);
		}

		return message;

	}

	@Override
	public List<UserRequest> getUser() {
		List<User> user = userRepository.findAll();
		 List<UserRequest> userRequestList = new ArrayList<>();
		
		for(User u :user)
		{
			UserRequest userRequest = new UserRequest();
			userRequest.setId(String.valueOf(u.getId()));
			userRequest.setUserEmail(u.getUserEmail());
			userRequest.setUserName(u.getUserName());
			userRequest.setPassword(u.getPassword());
			userRequest.setLanguage(u.getLanguage());
			userRequest.setGender(u.getGender());
			userRequest.setCountry(u.getCountry());
			userRequest.setImage(u.getImage());
			userRequest.setImgName(u.getImgName());
		
			userRequestList.add(userRequest);
			
		}
		return userRequestList;
	}

}
