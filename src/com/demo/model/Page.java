package com.demo.model;

import java.util.List;

/**
 * @author dragon
 * @create 2021-06-26 14:31
 */
public class Page {
    private int totalPage;
    private int pageSize=3;
    private int totalRecord;
    private int pageNum;
    private List list;
    private int startPage;
    private int endPage;
    private int startIndex;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public Page(int totalRecord, int pageNum) {
        this.totalRecord = totalRecord;
        this.pageNum = pageNum;
        this.totalPage=(this.totalRecord+pageSize-1)/this.pageSize;
        this.startIndex=(this.pageNum-1)*this.pageSize;
        if(this.totalPage<=3){
            this.startPage=1;
            this.endPage=this.totalPage;
        }else {
            this.startPage=pageNum-1;
            this.endPage=pageNum+1;
            if(this.startPage<1){
                this.startPage=1;
                this.endPage=3;
            }
            if(this.endPage>this.totalPage){
                this.endPage=this.totalPage;
                this.startPage=this.totalPage-2;
            }
        }

    }
}
