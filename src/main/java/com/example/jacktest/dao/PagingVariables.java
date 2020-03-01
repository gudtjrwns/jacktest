package com.example.jacktest.dao;

public class PagingVariables {

    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private int pageNumber;
    private long pageableOffset;
    private int startPage;
    private int endPage;

    public PagingVariables() {
        
    }


    public PagingVariables(int totalPages, boolean hasNext, boolean hasPrevious, int pageNumber, long pageableOffset, int endPage, int startPage) {
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.pageNumber = pageNumber;
        this.pageableOffset = pageableOffset;
        this.endPage = endPage;
        this.startPage = startPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageableOffset() {
        return pageableOffset;
    }

    public void setPageableOffset(long pageableOffset) {
        this.pageableOffset = pageableOffset;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}