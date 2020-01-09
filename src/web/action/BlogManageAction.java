package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Blog;
import service.BlogManageService;
import service.impl.BlogManageServiceImpl;

/**
 * 博客管理Action类，用于处理博客相关请求
 */
public class BlogManageAction extends ActionSupport implements ModelDriven<Blog> {

	private static final long serialVersionUID = 1L;
	// 博客实体对象，接收页面提交参数
	Blog blog = new Blog();
	// 博客管理service对象
	private BlogManageService bms = new BlogManageServiceImpl();

	private Long blog_id;

	// 发表新博客
	public String post() {
		// 调用service层
		bms.post(blog);
		return "chainindex";

	}

	// 博客修改
	public String modify() {
		// 取得参数
		Long blog_id = (Long) ServletActionContext.getRequest().getAttribute("blog_id");
		// 调用service层
		bms.modify(blog, blog_id);
		// 设置返回参数
		this.blog_id = blog_id;
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainblog";

	}

	// 博客删除
	public String delete() {
		// 取得参数，调用service层
		String user_nickname = bms.delete(new Long(ServletActionContext.getRequest().getParameter("blog_id")));
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";

	}

	// 博客查询
	public String query() {
		// 取得参数，调用service层
		Boolean modify = new Boolean(ServletActionContext.getRequest().getParameter("modify"));
		bms.qurey_by_id(new Long(ServletActionContext.getRequest().getParameter("blog_id")));
		// 根据是“显示”还是“修改”返回不同的字符集
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