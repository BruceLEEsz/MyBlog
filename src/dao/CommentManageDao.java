package dao;

import java.util.List;
import java.util.Set;

import domain.Comment;

/**
 * 评论数据管理接口
 */
public interface CommentManageDao {

	void add(Comment comment, Long blog_id);

	void del(Comment comment, Long blog_id);

	Comment get_by_comment_id(Long comment_id);

	Set<Comment> get_by_blog_id(Long blog_id);

	int getAllRowCount(Long blog_id);

	List<Comment> get_by_page(Long blog_id, int offset, int pagesize);
}
