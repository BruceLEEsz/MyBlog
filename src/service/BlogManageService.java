package service;

import domain.Blog;
/**
 * 博客管理业务逻辑接口
 */
public interface BlogManageService {

	void post(Blog blog);

	void qurey_by_id(Long blog_id);

	void modify(Blog blog, Long blog_id);

	String delete(Long blog_id);

}
