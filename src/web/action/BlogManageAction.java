package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Blog;
import service.BlogManageService;
import service.impl.BlogManageServiceImpl;

/**
 * ���͹���Action�࣬���ڴ������������
 */
public class BlogManageAction extends ActionSupport implements ModelDriven<Blog> {

	private static final long serialVersionUID = 1L;
	// ����ʵ����󣬽���ҳ���ύ����
	Blog blog = new Blog();
	// ���͹���service����
	private BlogManageService bms = new BlogManageServiceImpl();

	private Long blog_id;

	// �����²���
	public String post() {
		// ����service��
		bms.post(blog);
		return "chainindex";

	}

	// �����޸�
	public String modify() {
		// ȡ�ò���
		Long blog_id = (Long) ServletActionContext.getRequest().getAttribute("blog_id");
		// ����service��
		bms.modify(blog, blog_id);
		// ���÷��ز���
		this.blog_id = blog_id;
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainblog";

	}

	// ����ɾ��
	public String delete() {
		// ȡ�ò���������service��
		String user_nickname = bms.delete(new Long(ServletActionContext.getRequest().getParameter("blog_id")));
		// ���÷��ز���
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";

	}

	// ���Ͳ�ѯ
	public String query() {
		// ȡ�ò���������service��
		Boolean modify = new Boolean(ServletActionContext.getRequest().getParameter("modify"));
		bms.qurey_by_id(new Long(ServletActionContext.getRequest().getParameter("blog_id")));
		// �����ǡ���ʾ�����ǡ��޸ġ����ز�ͬ���ַ���
		if (modify == null || !modify) {
			return "toblog";
		} else {
			ServletActionContext.getRequest().setAttribute("modify", true);
			return "topostblog";
		}

	}

	@Override
	public Blog getModel() {
		// TODO Auto-generated method stub
		return blog;
	}

	public Long getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}

}
