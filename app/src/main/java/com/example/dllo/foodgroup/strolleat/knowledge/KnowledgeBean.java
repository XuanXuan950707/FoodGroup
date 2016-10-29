package com.example.dllo.foodgroup.strolleat.knowledge;

import java.util.List;

/**
 * Created by dllo on 16/10/29.
 */
public class KnowledgeBean {

    /**
     * page : 3
     * total_pages : 30
     * feeds : [{"source":"FitTime睿健时代","title":"这样吃沙拉不仅减肥还扛饿","link":"http://mp.weixin.qq.com/s?__biz=MjM5MjYxNzY2NA==&mid=2650437679&idx=1&sn=c1a5eecad234ed4de5eae97542824325&scene=21#wechat_redirect","tail":"5277","images":["http://up.boohee.cn/house/u/food_library/home_news/news_415.png"],"item_id":820,"type":"food_news","content_type":1},{"source":"丁香食堂","title":"螃蟹不能和什么一起吃？","link":"http://mp.weixin.qq.com/s?__biz=MzAwMTgxMjc1Mw==&mid=2670943295&idx=1&sn=2f42e455c9f17d23a63f2ea460759729&mpshare=1&scene=1&srcid=0928fhs9uJCF4ZRFQkqCSXr0#rd","tail":"1916","images":["http://up.boohee.cn/house/u/food_library/home_news/news_413.png"],"item_id":817,"type":"food_news","content_type":1},{"source":"NICE健康","title":"要变瘦，不要便秘！","link":"http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649418086&idx=1&sn=42d5d4386be0d488c9263ef6a1663a7a&chksm=f391fbdcc4e672ca1448e5b8ed4e097c99d89b078c156cda4b00d4fcde49ac2a0c6fc78d8333&scene=0#wechat_redirect","tail":"8059","images":["http://up.boohee.cn/house/u/food_library/home_news/news_412.png"],"item_id":816,"type":"food_news","content_type":1},{"source":"三九养生堂","title":"吃这种果养生功效翻倍","link":"http://weibo.com/ttarticle/p/show?id=2309614019301186201694","tail":"3214","images":["http://up.boohee.cn/house/u/food_library/home_news/news_410.png"],"item_id":814,"type":"food_news","content_type":1},{"source":"帝国烘培坊","title":"喝咖啡时为什么要配可颂","link":"http://mp.weixin.qq.com/s?__biz=MjM5NDIzNzAyNw==&mid=200547160&idx=1&sn=aaeefb2772b86c57cfa7028e8530be38","tail":"2969","images":["http://up.boohee.cn/house/u/food_library/home_news/news_409.jpg"],"item_id":810,"type":"food_news","content_type":1},{"source":"解放网","title":"早餐吃什么好?早餐不能缺3种食物","link":"http://m.jfdaily.com/jiankang/201509/t20150910_1807392.html","tail":"9926","images":["http://up.boohee.cn/house/u/food_library/home_news/news_407.jpg"],"item_id":805,"type":"food_news","content_type":1},{"source":"时尚COSMO","title":"乐活家丨为什么会下厨的人更值得交往、容易钓到小鲜肉？","link":"http://toutiao.com/group/6303688767707103490/","tail":"1814","images":["http://up.boohee.cn/house/u/food_library/home_news/news_406.jpg"],"item_id":806,"type":"food_news","content_type":1},{"source":"她的家","title":"怎样拍出一张好看的食物照片","link":"http://toutiao.com/group/6304062505644278017/","tail":"4000","images":["http://p3.pstatp.com/list/9de000618e1a7a47b44","http://p1.pstatp.com/list/9df000627e6ca4580ee","http://p3.pstatp.com/list/9df000627e38503cad8"],"item_id":808,"type":"food_news","content_type":2},{"source":"造洋饭书","title":"在这间餐厅吃饭，你会希望他们上菜不要太快","link":"http://mp.weixin.qq.com/s?__biz=MzA4MDQzNDk0MQ==&mid=2650428274&idx=1&sn=a284f82ce1ef98b1e7e922d51404b622&scene=0#wechat_redirect","tail":"2710","images":["http://up.boohee.cn/house/u/food_library/home_news/news_401.jpg"],"item_id":788,"type":"food_news","content_type":1},{"source":"素食星球","title":"听说不限制胆固醇就能随便吃肉和甜食了？","link":"http://mp.weixin.qq.com/s?__biz=MjM5ODk1NDE0MQ==&mid=2650630166&idx=1&sn=ed24b2af7dd91c156ce33c976aee7c01&scene=0#wechat_redirect","tail":"3091","images":["http://up.boohee.cn/house/u/food_library/home_news/news_403.jpg"],"item_id":796,"type":"food_news","content_type":1}]
     */

    private String page;
    private int total_pages;
    /**
     * source : FitTime睿健时代
     * title : 这样吃沙拉不仅减肥还扛饿
     * link : http://mp.weixin.qq.com/s?__biz=MjM5MjYxNzY2NA==&mid=2650437679&idx=1&sn=c1a5eecad234ed4de5eae97542824325&scene=21#wechat_redirect
     * tail : 5277
     * images : ["http://up.boohee.cn/house/u/food_library/home_news/news_415.png"]
     * item_id : 820
     * type : food_news
     * content_type : 1
     */

    private List<FeedsBean> feeds;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FeedsBean> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedsBean> feeds) {
        this.feeds = feeds;
    }

    public static class FeedsBean {
        private String source;
        private String title;
        private String link;
        private String tail;
        private int item_id;
        private String type;
        private int content_type;
        private List<String> images;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTail() {
            return tail;
        }

        public void setTail(String tail) {
            this.tail = tail;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
