package com.mhky.dianhuotong.advert;

import java.io.Serializable;

public class AdvertInfo implements Serializable {
    /**
     * id : 4
     * name :
     * key : APP_INDEX_SLIDER
     * link : 123
     * type : url
     * image : false
     * sort : 0
     * createTime : 2018-05-23 07:14:42
     * lastModifyTime : 2018-05-23 07:14:42
     */

    private int id;
    private String name;
    private String key;
    private String link;
    private String type;
    private String image;
    private int sort;
    private String createTime;
    private String lastModifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
