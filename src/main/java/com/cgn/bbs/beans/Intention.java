package com.cgn.bbs.beans;

/**
 * 求职意向 
 */
public class Intention {
	private Integer intentionId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer modelId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	//===========================
	public Integer getIntentionId() {
		return intentionId;
	}
	public void setIntentionId(Integer intentionId) {
		this.intentionId = intentionId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	
}
