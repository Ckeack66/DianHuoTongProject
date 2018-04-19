package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class AllGoodsInfo implements Serializable {
    /**
     * id : 1
     * name : 中西医药
     * sort : 0
     * children : [{"id":9,"name":"儿科用药","sort":0,"children":[{"id":24,"name":"儿童生长发育","sort":0,"children":[],"picture":null,"new":false},{"id":25,"name":"小儿腹泻","sort":1,"children":[],"picture":null,"new":false},{"id":26,"name":"小儿感冒发热","sort":2,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":10,"name":"风湿跌打","sort":1,"children":[{"id":27,"name":"跌打损伤内服","sort":0,"children":[],"picture":null,"new":false},{"id":28,"name":"跌打损伤外用","sort":1,"children":[],"picture":null,"new":false},{"id":29,"name":"风湿骨痛内服","sort":2,"children":[],"picture":null,"new":false},{"id":30,"name":"风湿骨痛外用","sort":3,"children":[],"picture":null,"new":false},{"id":31,"name":"风湿性关节炎","sort":4,"children":[],"picture":null,"new":false},{"id":32,"name":"痛风","sort":5,"children":[],"picture":null,"new":false},{"id":33,"name":"类风湿","sort":6,"children":[],"picture":null,"new":false},{"id":34,"name":"骨质疏松","sort":7,"children":[],"picture":null,"new":false},{"id":35,"name":"颈椎腰椎病","sort":8,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":11,"name":"妇科用药","sort":2,"children":[{"id":36,"name":"乳腺疾病","sort":0,"children":[],"picture":null,"new":false},{"id":37,"name":"月经不调","sort":1,"children":[],"picture":null,"new":false},{"id":38,"name":"保胎促孕","sort":2,"children":[],"picture":null,"new":false},{"id":39,"name":"更年期综合症","sort":3,"children":[],"picture":null,"new":false},{"id":40,"name":"除湿止带","sort":4,"children":[],"picture":null,"new":false},{"id":41,"name":"贫血类","sort":5,"children":[],"picture":null,"new":false},{"id":42,"name":"妇科洗液","sort":6,"children":[],"picture":null,"new":false},{"id":43,"name":"产后疾病","sort":7,"children":[],"picture":null,"new":false},{"id":44,"name":"卵巢疾病","sort":8,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":12,"name":"肝胆胰类","sort":3,"children":[{"id":45,"name":"肝纤维肝硬化","sort":0,"children":[],"picture":null,"new":false},{"id":46,"name":"乙型肝炎","sort":1,"children":[],"picture":null,"new":false},{"id":47,"name":"肝胆保健","sort":2,"children":[],"picture":null,"new":false},{"id":48,"name":"保肝护肝","sort":3,"children":[],"picture":null,"new":false},{"id":49,"name":"退黄降酶","sort":4,"children":[],"picture":null,"new":false},{"id":50,"name":"脂肪肝酒精肝","sort":5,"children":[],"picture":null,"new":false},{"id":51,"name":"免疫调节","sort":6,"children":[],"picture":null,"new":false},{"id":52,"name":"胆道系统","sort":7,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":13,"name":"呼吸系统","sort":4,"children":[{"id":53,"name":"化痰止咳","sort":0,"children":[],"picture":null,"new":false},{"id":54,"name":"呼吸道感染","sort":1,"children":[],"picture":null,"new":false},{"id":55,"name":"感冒咳嗽","sort":2,"children":[],"picture":null,"new":false},{"id":56,"name":"抗结核","sort":3,"children":[],"picture":null,"new":false},{"id":57,"name":"气管炎","sort":4,"children":[],"picture":null,"new":false},{"id":58,"name":"哮喘用药","sort":5,"children":[],"picture":null,"new":false},{"id":59,"name":"鼻炎","sort":6,"children":[],"picture":null,"new":false},{"id":60,"name":"急慢性咽炎","sort":7,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":14,"name":"解热镇痛","sort":5,"children":[{"id":61,"name":"感冒发热","sort":0,"children":[],"picture":null,"new":false},{"id":62,"name":"其他用药","sort":1,"children":[],"picture":null,"new":false},{"id":63,"name":"肌肉关节","sort":2,"children":[],"picture":null,"new":false},{"id":64,"name":"头痛牙痛","sort":3,"children":[],"picture":null,"new":false},{"id":65,"name":"风湿","sort":4,"children":[],"picture":null,"new":false},{"id":66,"name":"癌症疼痛","sort":5,"children":[],"picture":null,"new":false},{"id":67,"name":"创伤疼痛","sort":6,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":15,"name":"抗肿瘤药","sort":6,"children":[{"id":68,"name":"淋巴癌","sort":0,"children":[],"picture":null,"new":false},{"id":69,"name":"宫颈癌","sort":1,"children":[],"picture":null,"new":false},{"id":70,"name":"胃肠癌","sort":2,"children":[],"picture":null,"new":false},{"id":71,"name":"肺癌","sort":3,"children":[],"picture":null,"new":false},{"id":72,"name":"鼻咽癌","sort":4,"children":[],"picture":null,"new":false},{"id":73,"name":"其他","sort":5,"children":[],"picture":null,"new":false},{"id":74,"name":"肝癌","sort":6,"children":[],"picture":null,"new":false},{"id":75,"name":"营养支持","sort":7,"children":[],"picture":null,"new":false},{"id":76,"name":"白血病","sort":8,"children":[],"picture":null,"new":false},{"id":77,"name":"器官移植","sort":9,"children":[],"picture":null,"new":false},{"id":78,"name":"前列腺癌","sort":10,"children":[],"picture":null,"new":false},{"id":79,"name":"乳腺癌","sort":11,"children":[],"picture":null,"new":false},{"id":80,"name":"卵巢癌","sort":12,"children":[],"picture":null,"new":false},{"id":81,"name":"癌症止痛","sort":13,"children":[],"picture":null,"new":false},{"id":82,"name":"化疗辅助","sort":14,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":16,"name":"泌尿系统","sort":7,"children":[{"id":83,"name":"尿路结石","sort":0,"children":[],"picture":null,"new":false},{"id":84,"name":"尿路感染","sort":1,"children":[],"picture":null,"new":false},{"id":85,"name":"肾炎","sort":2,"children":[],"picture":null,"new":false},{"id":86,"name":"肾病综合征","sort":3,"children":[],"picture":null,"new":false},{"id":87,"name":"尿毒症","sort":4,"children":[],"picture":null,"new":false},{"id":88,"name":"尿崩症","sort":5,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":17,"name":"男科用药","sort":8,"children":[{"id":89,"name":"补肾壮阳","sort":0,"children":[],"picture":null,"new":false},{"id":90,"name":"性功能障碍","sort":1,"children":[],"picture":null,"new":false},{"id":91,"name":"滋阴补肾","sort":2,"children":[],"picture":null,"new":false},{"id":92,"name":"前列腺类","sort":3,"children":[],"picture":null,"new":false},{"id":93,"name":"男性不育","sort":4,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":18,"name":"皮肤用药","sort":9,"children":[{"id":94,"name":"皮肤感染","sort":0,"children":[],"picture":null,"new":false},{"id":95,"name":"皮肤瘙痒","sort":1,"children":[],"picture":null,"new":false},{"id":96,"name":"其他用药","sort":2,"children":[],"picture":null,"new":false},{"id":97,"name":"皮炎湿疹","sort":3,"children":[],"picture":null,"new":false},{"id":98,"name":"皮肤过敏","sort":4,"children":[],"picture":null,"new":false},{"id":99,"name":"手足癣病","sort":5,"children":[],"picture":null,"new":false},{"id":100,"name":"治裂防冻","sort":6,"children":[],"picture":null,"new":false},{"id":101,"name":"白癜风","sort":7,"children":[],"picture":null,"new":false},{"id":102,"name":"花斑癣","sort":8,"children":[],"picture":null,"new":false},{"id":103,"name":"扁平苔癣","sort":9,"children":[],"picture":null,"new":false},{"id":104,"name":"银屑病","sort":10,"children":[],"picture":null,"new":false},{"id":105,"name":"带状疱疹","sort":11,"children":[],"picture":null,"new":false},{"id":106,"name":"痤疮","sort":12,"children":[],"picture":null,"new":false},{"id":107,"name":"生发乌发","sort":13,"children":[],"picture":null,"new":false},{"id":108,"name":"祛斑类","sort":14,"children":[],"picture":null,"new":false},{"id":109,"name":"烧烫创伤","sort":15,"children":[],"picture":null,"new":false},{"id":110,"name":"蚊虫叮咬","sort":16,"children":[],"picture":null,"new":false},{"id":111,"name":"尖锐湿疣","sort":17,"children":[],"picture":null,"new":false},{"id":112,"name":"鸡眼","sort":18,"children":[],"picture":null,"new":false},{"id":113,"name":"痱子晒伤","sort":19,"children":[],"picture":null,"new":false},{"id":114,"name":"疥疮","sort":20,"children":[],"picture":null,"new":false},{"id":115,"name":"头皮糠疹","sort":21,"children":[],"picture":null,"new":false},{"id":116,"name":"褥疮溃烂","sort":22,"children":[],"picture":null,"new":false},{"id":117,"name":"酒糟鼻","sort":23,"children":[],"picture":null,"new":false},{"id":118,"name":"多汗腋臭","sort":24,"children":[],"picture":null,"new":false},{"id":119,"name":"麻风病","sort":25,"children":[],"picture":null,"new":false},{"id":120,"name":"寻常疣扁平疣","sort":26,"children":[],"picture":null,"new":false},{"id":121,"name":"艾滋病","sort":27,"children":[],"picture":null,"new":false},{"id":122,"name":"疱疹","sort":28,"children":[],"picture":null,"new":false},{"id":123,"name":"痤疮粉刺","sort":29,"children":[],"picture":null,"new":false},{"id":124,"name":"红斑狼疮","sort":30,"children":[],"picture":null,"new":false},{"id":125,"name":"各种癣","sort":31,"children":[],"picture":null,"new":false},{"id":126,"name":"脱发","sort":32,"children":[],"picture":null,"new":false},{"id":127,"name":"丘疹脓疱","sort":33,"children":[],"picture":null,"new":false},{"id":128,"name":"祛除斑点","sort":34,"children":[],"picture":null,"new":false},{"id":129,"name":"淡化疤痕","sort":35,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":19,"name":"日常用药","sort":10,"children":[{"id":130,"name":"口腔疾病","sort":0,"children":[],"picture":null,"new":false},{"id":131,"name":"眼部用药","sort":1,"children":[],"picture":null,"new":false},{"id":132,"name":"清热解毒","sort":2,"children":[],"picture":null,"new":false},{"id":133,"name":"抗菌消炎","sort":3,"children":[],"picture":null,"new":false},{"id":134,"name":"感冒发烧","sort":4,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":20,"name":"神经系统","sort":11,"children":[{"id":135,"name":"抑郁症类","sort":0,"children":[],"picture":null,"new":false},{"id":136,"name":"精神分裂","sort":1,"children":[],"picture":null,"new":false},{"id":137,"name":"面瘫癫痫","sort":2,"children":[],"picture":null,"new":false},{"id":138,"name":"神经衰弱","sort":3,"children":[],"picture":null,"new":false},{"id":139,"name":"其他用药","sort":4,"children":[],"picture":null,"new":false},{"id":140,"name":"偏头疼类","sort":5,"children":[],"picture":null,"new":false},{"id":141,"name":"老年痴呆","sort":6,"children":[],"picture":null,"new":false},{"id":142,"name":"记忆减退","sort":7,"children":[],"picture":null,"new":false},{"id":143,"name":"眩晕失眠","sort":8,"children":[],"picture":null,"new":false},{"id":144,"name":"晕车晕船","sort":9,"children":[],"picture":null,"new":false},{"id":145,"name":"帕金森类","sort":10,"children":[],"picture":null,"new":false},{"id":146,"name":"焦虑症","sort":11,"children":[],"picture":null,"new":false},{"id":147,"name":"肌无力类","sort":12,"children":[],"picture":null,"new":false},{"id":148,"name":"抑郁症","sort":13,"children":[],"picture":null,"new":false},{"id":149,"name":"甲亢甲减","sort":14,"children":[],"picture":null,"new":false},{"id":150,"name":"戒烟戒毒","sort":15,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":21,"name":"糖尿病类","sort":12,"children":[{"id":151,"name":"西药降糖","sort":0,"children":[],"picture":null,"new":false},{"id":152,"name":"中药降糖","sort":1,"children":[],"picture":null,"new":false},{"id":153,"name":"营养调节","sort":2,"children":[],"picture":null,"new":false},{"id":154,"name":"预防病变","sort":3,"children":[],"picture":null,"new":false},{"id":155,"name":"Ⅱ型糖尿病中成药","sort":4,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":22,"name":"胃肠疾病","sort":13,"children":[{"id":156,"name":"泛酸打嗝","sort":0,"children":[],"picture":null,"new":false},{"id":157,"name":"消化不良","sort":1,"children":[],"picture":null,"new":false},{"id":158,"name":"腹痛腹泻","sort":2,"children":[],"picture":null,"new":false},{"id":159,"name":"胃及十二指肠溃疡","sort":3,"children":[],"picture":null,"new":false},{"id":160,"name":"解暑化湿","sort":4,"children":[],"picture":null,"new":false},{"id":161,"name":"胃炎用药","sort":5,"children":[],"picture":null,"new":false},{"id":162,"name":"便秘","sort":6,"children":[],"picture":null,"new":false},{"id":163,"name":"恶心呕吐","sort":7,"children":[],"picture":null,"new":false},{"id":164,"name":"胃痛胀气","sort":8,"children":[],"picture":null,"new":false},{"id":165,"name":"其他用药","sort":9,"children":[],"picture":null,"new":false},{"id":166,"name":"痔疮用药","sort":10,"children":[],"picture":null,"new":false},{"id":167,"name":"驱虫类药","sort":11,"children":[],"picture":null,"new":false},{"id":168,"name":"胃肠道感染","sort":12,"children":[],"picture":null,"new":false},{"id":169,"name":"肠道菌群紊乱","sort":13,"children":[],"picture":null,"new":false},{"id":170,"name":"消化道肿瘤","sort":14,"children":[],"picture":null,"new":false},{"id":171,"name":"小儿肠胃病","sort":15,"children":[],"picture":null,"new":false},{"id":172,"name":"肠道营养剂","sort":16,"children":[],"picture":null,"new":false},{"id":173,"name":"菌群紊乱","sort":17,"children":[],"picture":null,"new":false}],"picture":null,"new":false},{"id":23,"name":"心脑血管","sort":14,"children":[{"id":174,"name":"高血脂类","sort":0,"children":[],"picture":null,"new":false},{"id":175,"name":"高血压类","sort":1,"children":[],"picture":null,"new":false},{"id":176,"name":"冠心病","sort":2,"children":[],"picture":null,"new":false},{"id":177,"name":"中风偏瘫","sort":3,"children":[],"picture":null,"new":false},{"id":178,"name":"其他用药","sort":4,"children":[],"picture":null,"new":false},{"id":179,"name":"动脉硬化","sort":5,"children":[],"picture":null,"new":false},{"id":180,"name":"心力衰竭","sort":6,"children":[],"picture":null,"new":false},{"id":181,"name":"外周血管疾病","sort":7,"children":[],"picture":null,"new":false},{"id":182,"name":"心肌病","sort":8,"children":[],"picture":null,"new":false},{"id":183,"name":"低血压","sort":9,"children":[],"picture":null,"new":false},{"id":184,"name":"心律不齐","sort":10,"children":[],"picture":null,"new":false},{"id":185,"name":"肺动脉高压","sort":11,"children":[],"picture":null,"new":false},{"id":186,"name":"脉管炎","sort":12,"children":[],"picture":null,"new":false},{"id":187,"name":"心绞痛","sort":13,"children":[],"picture":null,"new":false},{"id":188,"name":"心律失常","sort":14,"children":[],"picture":null,"new":false},{"id":189,"name":"高血压","sort":15,"children":[],"picture":null,"new":false},{"id":190,"name":"高血脂","sort":16,"children":[],"picture":null,"new":false}],"picture":null,"new":false}]
     * picture : null
     */

    private int id;
    private String name;
    private int sort;
    private Object picture;
    private List<ChildrenBeanX> children;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX implements Serializable {
        /**
         * id : 9
         * name : 儿科用药
         * sort : 0
         * children : [{"id":24,"name":"儿童生长发育","sort":0,"children":[],"picture":null,"new":false},{"id":25,"name":"小儿腹泻","sort":1,"children":[],"picture":null,"new":false},{"id":26,"name":"小儿感冒发热","sort":2,"children":[],"picture":null,"new":false}]
         * picture : null
         * new : false
         */

        private int id;
        private String name;
        private int sort;
        private Object picture;
        private boolean newX;
        private List<ChildrenBean> children;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements Serializable {
            /**
             * id : 24
             * name : 儿童生长发育
             * sort : 0
             * children : []
             * picture : null
             * new : false
             */

            private int id;
            private String name;
            private int sort;
            private Object picture;
            private boolean newX;
            private List<?> children;

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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public Object getPicture() {
                return picture;
            }

            public void setPicture(Object picture) {
                this.picture = picture;
            }

            public boolean isNewX() {
                return newX;
            }

            public void setNewX(boolean newX) {
                this.newX = newX;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
