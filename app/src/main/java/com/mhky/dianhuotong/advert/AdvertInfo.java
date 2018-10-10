package com.mhky.dianhuotong.advert;

import java.io.Serializable;

/**
 * 轮播图类
 */

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

    private int id;                                        //id
    private String name;                                   //banner   name
    private String key;                                    //key
    private String link;                                   //点击链接
    private String type;                                   //？？
    private String image;                                  //图片URL
    private int sort;                                      //banner序号
    private String createTime;                             //创建时间
    private String lastModifyTime;                         //最后一次修改时间

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
