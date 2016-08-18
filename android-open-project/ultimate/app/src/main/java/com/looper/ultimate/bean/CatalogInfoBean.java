package com.looper.ultimate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CatalogInfoBean extends BaseBean{

	public ResultInfoBean getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfoBean resultInfo) {
		this.resultInfo = resultInfo;
	}

	public CatalogInfo getCatalogInfo() {
		return catalogInfo;
	}

	public void setCatalogInfo(CatalogInfo catalogInfo) {
		this.catalogInfo = catalogInfo;
	}

	private ResultInfoBean resultInfo;
	private CatalogInfo catalogInfo;
	
	public static class CatalogInfo extends BaseBean implements Parcelable{
		
		private String catalogId;
		private String catalogName;
		private String description;
		private List<CatalogInfo> children;
		private List<CoverImageInfoBean> coverImages;
		public String getCatalogId() {
			return catalogId;
		}
		public void setCatalogId(String catalogId) {
			this.catalogId = catalogId;
		}
		public String getCatalogName() {
			return catalogName;
		}
		public void setCatalogName(String catalogName) {
			this.catalogName = catalogName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<CatalogInfo> getChildren() {
			return children;
		}
		public void setChildren(List<CatalogInfo> children) {
			this.children = children;
		}
		public List<CoverImageInfoBean> getCoverImages() {
			return coverImages;
		}
		public void setCoverImages(List<CoverImageInfoBean> coverImages) {
			this.coverImages = coverImages;
		}
	
		@Override
		public int describeContents() {
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(catalogId);
			dest.writeString(catalogName);
			dest.writeString(description);
			
		}
		
		public static final Creator<CatalogInfo> CREATOR = new Creator<CatalogInfo>()
			     {
			         public CatalogInfo createFromParcel(Parcel in) 
			         {
			        	CatalogInfo infoBean = new CatalogInfo();
			        	infoBean.catalogId=in.readString();
			        	infoBean.catalogName=in.readString();
			        	infoBean.description=in.readString();
			        	return infoBean;
			         }

			         public CatalogInfo[] newArray(int size) 
			         {
			             return new CatalogInfo[size];
			         }
			     };
		
		
	}
}
