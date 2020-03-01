package com.example.jacktest.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "noticeid")
    private long noticeid;

    @Column(name = "writer")
    private String writer;

    @Column(name = "contents")
    private String contents;

    @Column(name = "credate")
    private Timestamp credate; 
    
    @Column(name = "moddate")
    private Timestamp moddate;

    public Reply() {

    }

    public Reply(Long noticeid, String writer, String contents, Timestamp credate, Timestamp moddate) {
        this.noticeid = noticeid;
        this.writer = writer;
        this.contents = contents;
        this.credate = credate;
        this.moddate = moddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Long noticeid){
        this.noticeid = noticeid;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Timestamp getCredate() {
        return credate;
    }

    public void setCredate(Timestamp credate) {
        this.credate = credate;
    }

    public Timestamp getModdate() {
        return moddate;
    }

    public void setModdate(Timestamp moddate) {
        this.moddate = moddate;
    }
}