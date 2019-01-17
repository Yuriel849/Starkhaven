package com.dto;

import java.util.List;

public class ListView {
	private int messageTotalCount;
	private int currentPageNumber;
	private List list;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int lastRow;
	
	public ListView(List list, int messageTotalCount, int currentPageNumber, int messageCountPerPage, int startRow, int lastRow) {
		this.list = list;
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		calculatePageTotalCount();
	}
	
	private void calculatePageTotalCount() {
		if(messageTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;
			if(messageTotalCount % messageCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
	
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
