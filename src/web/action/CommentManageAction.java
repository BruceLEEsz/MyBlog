package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Comment;
import service.CommentManageService;
import service.impl.CommentManageServiceImpl;

/**
 * ���۹���Action�࣬���ڴ������������������
 */
public class CommentManageAction extends ActionSupport implements ModelDriven<Comment> {

	private static final long serialVersionUID = 1L;
	// ����ʵ��������ڽ���ҳ�洫�����
	Comment comment = new Comment();
	// ���۹���service����
	private CommentManageService cms = new CommentManageServiceImpl();

	private Long blog_id;

	// ���۷���
	public String post() {
		// ȡ�ò���
		Long blog_id = new Long(ServletActionContext.getRequest().getParameter("blog_id"));
		// ����service��
		cms.post(comment, blog_id);
		// ���÷��ز���
		ServletActionContext.getRequest().setAttribute("page", 1);
		this.blog_id = blog_id;
		return "chainblog";
	}

	// ����ɾ��
	public String delete() {
		// ����service��
		cms.delete(comment, blog_id);
		// ���÷��ز���
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
