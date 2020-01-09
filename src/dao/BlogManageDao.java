package dao;

import java.util.List;

import domain.Blog;


/**
 * 博客数据管理接口
 */
public interface BlogManageDao {

	void add(Blog blog);

	Blog get_by_id(Long blog_id);

	void alter(Blog blog);

	void delete(Blog blog);

	List<Blog> query();

	List<Blog> query_by_page(String user_nickname, int offset, int pageSize);

	int getAllRowCount(String hql);
}
