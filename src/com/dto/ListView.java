package com.dto;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ListView {
	private int messageTotalCount;
	private int currentPageNumber;
	private List list;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int lastRow;
	
	public ListView(List list, int messageTotalCount, int currentPageNumber, int messageCountPerPage, int pageTotalCount, int firstRow, int lastRow) {
		this.list = list;
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
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
	public List getList() {
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
	public int getLastRow() {
		return lastRow;
	}
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
}