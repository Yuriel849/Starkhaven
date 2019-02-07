package com.yuriel.domain;

import java.util.List;

public class UserListVO {
	private int messageTotalCount;
	private int currentPageNumber;
	private List<UserVO> list;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	
	public UserListVO(List<UserVO> list, int messageTotalCount, int currentPageNumber, int messageCountPerPage, int pageTotalCount, int firstRow) {
		this.list = list;
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.pageTotalCount = pageTotalCount;
	}
	
	/* getters
	 * 	   (this class only ever needs to be able to get the values of the variables,
	 * 	   	setting values is only done via the constructor)
	 */
	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public List<UserVO> getList() {
		return list;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
}