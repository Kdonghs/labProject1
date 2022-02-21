package com.example.labproject1.domain;

public class Books {
    private long id;
    private String name;
    private String writer;
    private int price;
    private Object buydate;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Object getBuydate() {
        return buydate;
    }

    public void setBuydate(Object buydate) {
        this.buydate = buydate;
    }
}
