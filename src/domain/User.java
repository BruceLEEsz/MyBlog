package domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表实体类
 */
public class User {
	// 注册基本属性
	private Long user_id;
	private String user_name;
	private String user_loginname;
	private String user_email;
	private String user_password;
	private String user_password2;
	private Boolean confirmed;
	// 关系维护属性
	private Role role;
	private Set<Blog> blogs = new HashSet<Blog>();
	private Set<Comment> comments = new HashSet<Comment>();
	private Set<User> friends = new HashSet<User>();
	// 个人基本信息属性
	private String user_nickname;
	private String user_gender;
	private String user_address;
	private String user_hobby;
	private Integer user_age;
	private Date user_birthday;
	private Date add_time;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_loginname() {
		return user_loginname;
	}

	public void setUser_loginname(String user_loginname) {
		this.user_loginname = user_loginname;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_password2() {
		return user_password2;
	}

	public void setUser_password2(String user_password2) {
		this.user_password2 = user_password2;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Role getRole() {
		return role;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_hobby() {
		return user_hobby;
	}

	public void setUser_hobby(String user_hobby) {
		this.user_hobby = user_hobby;
	}

	public Integer getUser_age() {
		return user_age;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

}
