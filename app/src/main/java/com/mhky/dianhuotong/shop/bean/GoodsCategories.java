package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by God_K  on  2018/9/5
 * Describe：商品类目类
 */
public class GoodsCategories implements Serializable{

    /**
     * id : 9
     * name : 中西医药
     * icon : null
     * menuData : []
     * menuNav : []
     * menuAd : [{"type":"none","image":"http://116.255.155.156:9040/20180904/COMPANY/15360322353751433.png","link":"","title":"中西医药"}]
     * sort : 1
     * type : MOBILE
     * items : [{"id":8,"menuId":9,"name":"儿科用药","itemData":{"type":"category","name":"儿科用药","data":"9","hl":false},"itemMore":{"type":"none","name":"更多>>","data":"24,25,26,","hl":false},"subitemData":[{"image":"http://116.255.155.156:9040/20180529/GOODS/15275588107035207.jpg","link":"24","title":"儿童生长发育","type":"category"}],"itemType":"IMAGE","sort":0}]
     */

    private int id;
    private String name;
    private Object icon;
    private int sort;
    private String type;
    private List<?> menuData;
    private List<?> menuNav;
    private List<MenuAdBean> menuAd;
    private List<ItemsBean> items;

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

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<?> getMenuData() {
        return menuData;
    }

    public void setMenuData(List<?> menuData) {
        this.menuData = menuData;
    }

    public List<?> getMenuNav() {
        return menuNav;
    }

    public void setMenuNav(List<?> menuNav) {
        this.menuNav = menuNav;
    }

    public List<MenuAdBean> getMenuAd() {
        return menuAd;
    }

    public void setMenuAd(List<MenuAdBean> menuAd) {
        this.menuAd = menuAd;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class MenuAdBean implements Serializable{
        /**
         * type : none
         * image : http://116.255.155.156:9040/20180904/COMPANY/15360322353751433.png
         * link :
         * title : 中西医药
         */

        private String type;
        private String image;
        private String link;
        private String title;

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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ItemsBean implements Serializable{
        /**
         * id : 8
         * menuId : 9
         * name : 儿科用药
         * itemData : {"type":"category","name":"儿科用药","data":"9","hl":false}
         * itemMore : {"type":"none","name":"更多>>","data":"24,25,26,","hl":false}
         * subitemData : [{"image":"http://116.255.155.156:9040/20180529/GOODS/15275588107035207.jpg","link":"24","title":"儿童生长发育","type":"category"}]
         * itemType : IMAGE
         * sort : 0
         */

        private int id;
        private int menuId;
        private String name;
        private ItemDataBean itemData;
        private ItemMoreBean itemMore;
        private String itemType;
        private int sort;
        private List<SubitemDataBean> subitemData;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ItemDataBean getItemData() {
            return itemData;
        }

        public void setItemData(ItemDataBean itemData) {
            this.itemData = itemData;
        }

        public ItemMoreBean getItemMore() {
            return itemMore;
        }

        public void setItemMore(ItemMoreBean itemMore) {
            this.itemMore = itemMore;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<SubitemDataBean> getSubitemData() {
            return subitemData;
        }

        public void setSubitemData(List<SubitemDataBean> subitemData) {
            this.subitemData = subitemData;
        }

        public static class ItemDataBean implements Serializable{
            /**
             * type : category
             * name : 儿科用药
             * data : 9
             * hl : false
             */

            private String type;
            private String name;
            private String data;
            private boolean hl;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public boolean isHl() {
                return hl;
            }

            public void setHl(boolean hl) {
                this.hl = hl;
            }
        }

        public static class ItemMoreBean implements Serializable{
            /**
             * type : none
             * name : 更多>>
             * data : 24,25,26,
             * hl : false
             */

            private String type;
            private String name;
            private String data;
            private boolean hl;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public boolean isHl() {
                return hl;
            }

            public void setHl(boolean hl) {
                this.hl = hl;
            }
        }

        public static class SubitemDataBean implements Serializable{
            /**
             * image : http://116.255.155.156:9040/20180529/GOODS/15275588107035207.jpg
             * link : 24
             * title : 儿童生长发育
             * type : category
             */

            private String image;
            private String link;
            private String title;
            private String type;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
