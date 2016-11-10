package com.example.dllo.foodgroup.foodencyclopedia.compare;

import java.util.List;

/**
 * Created by dllo on 16/11/10.
 */
public class CompareBean {

    /**
     * code : mifanbing
     * name : 米饭饼
     * thumb_image_url : http://s.boohee.cn/house/upload_food/2008/12/4/129336_1228396070mid.jpg
     * large_image_url : http://s.boohee.cn/house/upload_food/2008/12/4/129336_1228396070.jpg
     * nutrition : [{"prop":"calory","name":"热量","value":"210.1","unit":"kcal","remark":""},{"prop":"protein","name":"蛋白质","value":"6.4","unit":"克","remark":""},{"prop":"fat","name":"脂肪","value":"0.5","unit":"克","remark":"低脂肪"},{"prop":"carbohydrate","name":"碳水化合物","value":"45.4","unit":"克","remark":""},{"prop":"fiber_dietary","name":"膳食纤维","value":"0.3","unit":"克","remark":""},{"prop":"vitamin_e","name":"维生素E","value":"2.9","unit":"mgRE"},{"prop":"thiamine","name":"维生素B1(硫胺素)","value":"0.0","unit":"毫克"},{"prop":"lactoflavin","name":"维生素B2(核黄素)","value":"0.1","unit":"毫克"},{"prop":"niacin","name":"烟酸(烟酰胺/尼克酸)","value":"1.3","unit":"毫克"},{"prop":"magnesium","name":"镁","value":"6.8","unit":"毫克"},{"prop":"calcium","name":"钙","value":"5.9","unit":"毫克"},{"prop":"iron","name":"铁","value":"2.7","unit":"毫克"},{"prop":"zinc","name":"锌","value":"0.4","unit":"毫克"},{"prop":"copper","name":"铜","value":"0.5","unit":"毫克"},{"prop":"kalium","name":"钾","value":"29.4","unit":"毫克"},{"prop":"phosphor","name":"磷","value":"57.8","unit":"毫克"},{"prop":"natrium","name":"钠","value":"10.9","unit":"毫克"},{"prop":"selenium","name":"硒","value":"2.3","unit":"微克"}]
     * type : food
     */

    private String code;
    private String name;
    private String thumb_image_url;
    private String large_image_url;
    private String type;
    /**
     * prop : calory
     * name : 热量
     * value : 210.1
     * unit : kcal
     * remark :
     */

    private List<NutritionBean> nutrition;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb_image_url() {
        return thumb_image_url;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
    }

    public String getLarge_image_url() {
        return large_image_url;
    }

    public void setLarge_image_url(String large_image_url) {
        this.large_image_url = large_image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NutritionBean> getNutrition() {
        return nutrition;
    }

    public void setNutrition(List<NutritionBean> nutrition) {
        this.nutrition = nutrition;
    }

    public static class NutritionBean {
        private String prop;
        private String name;
        private String value;
        private String unit;
        private String remark;

        public String getProp() {
            return prop;
        }

        public void setProp(String prop) {
            this.prop = prop;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
