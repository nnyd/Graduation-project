package com.cgn.bbs.beans;

public class Files {
	private Integer fileId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private String filePath;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	//===============================
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
