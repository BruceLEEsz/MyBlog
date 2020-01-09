package Utils;

/**
 * 分页用页面信息工具类
 */
public class Page {
	// 当前页面
	private Integer currentPage;
	// 记录总条数
	private Integer allRows;
	// 总页数
	private Integer totalpage;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getAllRows() {
		return allRows;
	}

	public void setAllRows(Integer allRows) {
		this.allRows = allRows;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	// 计算总页数方法
	public int getTotalPages(int pageSize, int allRows) {
		int totalPage = (allRows % pageSize == 0) ? (allRows / pageSize) : (allRows / pageSize) + 1;

		return totalPage;
	}

	// 计算偏移量
	public int getCurrentPageOffset(int pageSize, int currentPage) {
		int offset = pageSize * (currentPage - 1);

		return offset;
	}

	public int getCurPage(int page) {
		int currentPage = (page <= 0) ? 1 : page;

		return currentPage;
	}

}
