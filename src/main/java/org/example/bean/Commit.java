package org.example.bean;

public class Commit {

    private String hash;
    private String tree;
    private String parents;
    private String author;
    private String committer;
    private String msg;

    public Commit(String hash, String tree, String parents, String author, String committer, String msg) {
        this.hash = hash;
        this.tree = tree;
        this.parents = parents;
        this.author = author;
        this.committer = committer;
        this.msg = msg;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "hash='" + hash + '\'' +
                ", tree='" + tree + '\'' +
                ", parents='" + parents + '\'' +
                ", author='" + author + '\'' +
                ", committer='" + committer + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
