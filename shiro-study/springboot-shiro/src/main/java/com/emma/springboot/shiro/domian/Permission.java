package com.emma.springboot.shiro.domian;

import javax.persistence.*;

import lombok.Data;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Entity
@Data
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String perssion;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
}
