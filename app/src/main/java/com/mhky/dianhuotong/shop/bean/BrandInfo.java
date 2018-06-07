package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

public class BrandInfo implements Serializable {
    /**
     * content : [{"id":1,"initial":"L","name":"丽倍乐","englishName":"lbl","picture":"http://192.168.2.158:9040/20180415\\GOODS\\15237815963826317.png"},{"id":2,"initial":"暂无","name":"乐孚亭","englishName":"lft","picture":"http://192.168.2.158:9040/20180415\\null\\15237619947665209.jpg"},{"id":3,"initial":"暂无","name":"泰白","englishName":"暂无","picture":"http://192.168.2.158:9040/20180415\\null\\15237619947665209.jpg"},{"id":4,"initial":"暂无","name":"其他","englishName":"暂无","picture":"http://192.168.2.158:9040/20180409\\COMPANY\\15232530033078941.png"},{"id":5,"initial":"暂无","name":"岭南万应","englishName":"暂无","picture":"http://192.168.2.158:9040/20180409\\COMPANY\\15232530033078941.png"},{"id":6,"initial":null,"name":"信谊","englishName":null,"picture":null},{"id":7,"initial":null,"name":"宁斯宝","englishName":null,"picture":null},{"id":8,"initial":null,"name":"神威","englishName":null,"picture":null},{"id":9,"initial":null,"name":"沙药","englishName":null,"picture":null},{"id":10,"initial":null,"name":"反映亭","englishName":null,"picture":null}]
     * last : false
     * totalElements : 5031
     * totalPages : 504
     * size : 10
     * number : 0
     * sort : null
     * first : true
     * numberOfElements : 10
     */

    private boolean last;
    private String totalElements;
    private String totalPages;
    private String size;
    private String number;
    private Object sort;
    private boolean first;
    private String numberOfElements;
    private List<ContentBean> content;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(String numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 1
         * initial : L
         * name : 丽倍乐
         * englishName : lbl
         * picture : http://192.168.2.158:9040/20180415\GOODS\15237815963826317.png
         */

        private String id;
        private String initial;
        private String name;
        private String englishName;
        private String picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
