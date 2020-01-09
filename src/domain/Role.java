package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ��ɫ��ʵ����
 */

public class Role {
	// ��ɫ���������
	private Long role_id;
	private String role_name;
	private Integer role_authority;
	// ��ϵά������
	private Set<User> users = new HashSet<User>();

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getRole_authority() {
		return role_authority;
	}

	public void setRole_authority(Integer role_authority) {
		this.role_authority = role_authority;
	}

}
