package com.example.jacktest.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writer")
    private String writer;

    @Column(name = "viewcnt")
    private Integer viewcnt;

    @Column(name = "replycnt")
    private Integer replycnt;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

    @Column(name = "credate")
    private Timestamp credate;

    @Column(name = "moddate")
    private Timestamp moddate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "noticeid")
    private Set<Reply> replies;

    public Notice() {
        
    }

    public Notice(String title, String contents, String writer, Integer viewcnt, Integer replycnt, String filename, String filepath, Timestamp credate, Timestamp moddate, Set<Reply> replies) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.viewcnt = viewcnt;
        this.replycnt = replycnt;
        this.filename = filename;
        this.filepath = filepath;
        this.credate = credate;
        this.moddate = moddate;
        this.replies = replies;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents(){
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter(){
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getViewcnt(){
        return viewcnt;
    }

    public void setViewcnt(Integer viewcnt) {
        this.viewcnt = viewcnt;
    }

    public Integer getReplycnt(){
        return replycnt;
    }

    public void setReplycnt(Integer replycnt){
        this.replycnt = replycnt;
    }

    public String getFilename(){
        return filename;
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getFilepath(){
        return filepath;
    }

    public void setFilepath(String filepath){
        this.filepath = filepath;
    }

    public Timestamp getCredate(){
        return credate;
    }

    public void setCredate(Timestamp credate) {
        this.credate = credate;
    }

    public Timestamp getModdate(){
        return moddate;
    }

    public void setModdate(Timestamp moddate) {
        this.moddate = moddate;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies){
        this.replies = replies;
    }
}
