package Utils.Mail;

/**
 * 邮件模版接口
 */
public interface Mail {

	StringBuffer SUBJECT = new StringBuffer();
	StringBuffer CONTEXT = new StringBuffer();

	StringBuffer getSubjet();

	StringBuffer getContext();

	void clean();
}
