package Utils.Mail;

/**
 * �ʼ�ģ��ӿ�
 */
public interface Mail {

	StringBuffer SUBJECT = new StringBuffer();
	StringBuffer CONTEXT = new StringBuffer();

	StringBuffer getSubjet();

	StringBuffer getContext();

	void clean();
}
