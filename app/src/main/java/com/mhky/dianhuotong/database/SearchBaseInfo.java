package com.mhky.dianhuotong.database;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchBaseInfo {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Id
    private Long id;
    private int type;
    private String body;
    @Generated(hash = 1257474625)
    public SearchBaseInfo(Long id, int type, String body) {
        this.id = id;
        this.type = type;
        this.body = body;
    }

    @Generated(hash = 707661715)
    public SearchBaseInfo() {
    }
}
