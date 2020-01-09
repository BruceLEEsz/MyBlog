package service;

import domain.Comment;

/**
 *评论管理业务逻辑接口 
 */
public interface CommentManageService {
	void post(Comment comment, Long blog_id);

	void delete(Comment comment, Long blog_id);
}
