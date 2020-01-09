package domain;

import java.util.Date;

/**
 * 评论表实体类
 */

public class Comment {
	// 评论基本属性
	private Long comment_id;
	private String comment_content;
	private Date add_time;
	// 关系维护属性
	private Blog blog;
	private User user;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

}
