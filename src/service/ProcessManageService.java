package service;

/**
 * 流程控制业务逻辑接口
 */
public interface ProcessManageService {

	public void showblog(Long blog_id, Integer page);

	public void showmain(String user_name,Integer page);

	public void showindex();

}
