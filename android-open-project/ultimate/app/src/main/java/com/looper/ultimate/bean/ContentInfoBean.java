package com.looper.ultimate.bean;

import java.util.List;

public class ContentInfoBean extends BaseBean {

	/**
	 * 
	 */
	private String contentId;
	private String contentName;
	private String isHaveLive;
	private String isHaveVideo;
	private String description;
	private String mcpId;
	private String mcp;
	private String mcpShortName;
	private String chargeFlag;
	private String chargeMode;
	private String isPromotion;
	private String smallLogo;
	private String middleLogo;
	private String bigLogo;
	private String totalTime;
	private String goals;
	private String audiences;
	private List<TeacherInfoBean> teacherList;
	private String status;
	private ContentExtInfoBean contentExtInfo;
	
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getIsHaveLive() {
		return isHaveLive;
	}
	public void setIsHaveLive(String isHaveLive) {
		this.isHaveLive = isHaveLive;
	}
	public String getIsHaveVideo() {
		return isHaveVideo;
	}
	public void setIsHaveVideo(String isHaveVideo) {
		this.isHaveVideo = isHaveVideo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMcpId() {
		return mcpId;
	}
	public void setMcpId(String mcpId) {
		this.mcpId = mcpId;
	}
	public String getMcp() {
		return mcp;
	}
	public void setMcp(String mcp) {
		this.mcp = mcp;
	}
	public String getMcpShortName() {
		return mcpShortName;
	}
	public void setMcpShortName(String mcpShortName) {
		this.mcpShortName = mcpShortName;
	}
	public String getChargeFlag() {
		return chargeFlag;
	}
	public void setChargeFlag(String chargeFlag) {
		this.chargeFlag = chargeFlag;
	}
	public String getChargeMode() {
		return chargeMode;
	}
	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}
	public String getIsPromotion() {
		return isPromotion;
	}
	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}
	public String getSmallLogo() {
		return smallLogo;
	}
	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}
	public String getMiddleLogo() {
		return middleLogo;
	}
	public void setMiddleLogo(String middleLogo) {
		this.middleLogo = middleLogo;
	}
	public String getBigLogo() {
		return bigLogo;
	}
	public void setBigLogo(String bigLogo) {
		this.bigLogo = bigLogo;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public ContentExtInfoBean getContentExtInfo() {
		return contentExtInfo;
	}
	public void setContentExtInfo(ContentExtInfoBean contentExtInfo) {
		this.contentExtInfo = contentExtInfo;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getAudiences() {
		return audiences;
	}
	public void setAudiences(String audiences) {
		this.audiences = audiences;
	}
	public List<TeacherInfoBean> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<TeacherInfoBean> teacherList) {
		this.teacherList = teacherList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}