package service;

/**
 * ���̿���ҵ���߼��ӿ�
 */
public interface ProcessManageService {

	public void showblog(Long blog_id, Integer page);

	public void showmain(String user_name,Integer page);

	public void showindex();

}
