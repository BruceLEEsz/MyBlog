package service;

import domain.Comment;

/**
 *���۹���ҵ���߼��ӿ� 
 */
public interface CommentManageService {
	void post(Comment comment, Long blog_id);

	void delete(Comment comment, Long blog_id);
}
