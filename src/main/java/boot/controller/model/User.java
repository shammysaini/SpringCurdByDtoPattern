package boot.controller.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="myuser")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3157438413216346830L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userName;
	private String password;
	private String userEmail;
	private String country;
	private String gender;
	private String language;
	//@Lob
	//@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	private String imgName;


}
