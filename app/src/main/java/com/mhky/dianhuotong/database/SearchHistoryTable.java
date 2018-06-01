package com.mhky.dianhuotong.database;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class SearchHistoryTable extends DataSupport {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Column(unique = true)
    private int id;
    private int type;
    private String body;
}
