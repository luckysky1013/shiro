package com.emma.springboot.shiro.domian;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String roleName;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	private List<Permission> permissions;

}
