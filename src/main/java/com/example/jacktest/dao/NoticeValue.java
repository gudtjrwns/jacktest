package com.example.jacktest.dao;

public class NoticeValue {

    private Long id;
    private String title;
    private String contents;
    private String writer;
    private Integer viewcnt;
    private Integer replycnt;
    private String filename;
    private String filepath;
    private String credate;
    private String moddate;

    public NoticeValue() {

    }

    public NoticeValue(Long id, String title, String contents, String writer, Integer viewcnt, Integer replycnt, String filename, String filepath, String credate, String moddate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.viewcnt = viewcnt;
        this.replycnt = replycnt;
        this.filename = filename;
        this.filepath = filepath;
        this.credate = credate;
        this.moddate = moddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getViewcnt() {
        return viewcnt;
    }

    public void setViewcnt(Integer viewcnt){
        this.viewcnt = viewcnt;
    }

    public Integer getReplycnt() {
        return replycnt;
    }

    public void setReplycnt(Integer replycnt) {
        this.replycnt = replycnt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }

    public String getModdate() {
        return moddate;
    }

    public void setModdate(String moddate) {
        this.moddate = moddate;
    }
}