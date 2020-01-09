package service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Utils.HibernateSessionFactory;
import dao.CommentManageDao;
import dao.impl.CommentManageDaoImpl;
import domain.Comment;
import service.CommentManageService;

/**
 * 评论管理业务逻辑实现类
 */
public class CommentManageServiceImpl implements CommentManageService {
	
	// 评论数据管理类对象
	private CommentManageDao cmd = new CommentManageDaoImpl();

	// 评论发表方法
	@Override
	public void post(Comment comment, Long blog_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 设置评论发表时间
		comment.setAdd_time(new Date());
		// 提交Dao
		cmd.add(comment, blog_id);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 评论删除方法
	@Override
	public void delete(Comment comment, Long blog_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用dao层
		cmd.del(comment, blog_id);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

}
