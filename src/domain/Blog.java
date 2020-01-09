package domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 博客表实体类
 */
public class Blog {
	// 博客基本属性
	private Long blog_id;
	private String title;
	private String blog_content;
	private Date add_time;
	// 关系维护属性
	private User user;
	private Set<Comment> comments = new HashSet<Comment>();

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBlog_content() {
		return blog_content;
	}

	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
