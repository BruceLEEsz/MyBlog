package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Comment;
import service.CommentManageService;
import service.impl.CommentManageServiceImpl;

/**
 * 评论管理Action类，用于处理评论评论相关请求
 */
public class CommentManageAction extends ActionSupport implements ModelDriven<Comment> {

	private static final long serialVersionUID = 1L;
	// 评论实体对象，用于接收页面传入参数
	Comment comment = new Comment();
	// 评论管理service对象
	private CommentManageService cms = new CommentManageServiceImpl();

	private Long blog_id;

	// 评论发表
	public String post() {
		// 取得参数
		Long blog_id = new Long(ServletActionContext.getRequest().getParameter("blog_id"));
		// 调用service层
		cms.post(comment, blog_id);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("page", 1);
		this.blog_id = blog_id;
		return "chainblog";
	}

	// 评论删除
	public String delete() {
		// 调用service层
		cms.delete(comment, blog_id);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainblog";
	}

	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return comment;
	}

	public Long getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}

}
