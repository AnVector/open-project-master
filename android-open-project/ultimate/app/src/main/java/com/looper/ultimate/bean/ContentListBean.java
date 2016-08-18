package com.looper.ultimate.bean;

import java.util.List;

public class ContentListBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResultInfoBean resultInfo;
	private String totalCount;
	private String pageCount;
	private List<ContentInfoBean> contentList;
	
	public ResultInfoBean getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfoBean resultInfo) {
		this.resultInfo = resultInfo;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public List<ContentInfoBean> getContentList() {
		return contentList;
	}
	public void setContentList(List<ContentInfoBean> contentList) {
		this.contentList = contentList;
	}
}
