package com.emma.springboot.shiro.domian;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Role> roles;
}
