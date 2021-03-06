package com.example.dllo.foodgroup.letorm;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.foodgroup.tools.MyApp;
import com.example.dllo.foodgroup.tools.WebCollectListener;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/11/12.
 */
public class DBTool {
    private static  DBTool sDBTool = new DBTool();

    private LiteOrm liteOrm;
    private Handler handler;
    private Executor threadPool;

    // 构造方法私有化
    public DBTool() {
        liteOrm = LiteOrm.newSingleInstance(MyApp.getsContext(), "food.db");
        // 可以在Handler的构造方法里传入
        // Looper.getMainLooper的参数
        // 来保证这个Handler一定属于主线程
        handler = new Handler(Looper.getMainLooper());
        int coreNum = Runtime.getRuntime().availableProcessors();
        threadPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    public static DBTool getInstance() {
        return sDBTool;
    }

    // 插入方法
    public void insertSearchHistory(final ArrayList<SearchHistory> searchHistories) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchHistories);
            }
        });
    }

    public void insertSearchHistory(final SearchHistory searchHistory) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchHistory);
            }
        });
    }

    public void insertWebCollect(final WebCollectBean webCollectBean){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(webCollectBean);
            }
        });
    }

    public void queryAllHistory(OnQueryListener onQueryListener) {
        threadPool.execute(new QueryRunnable(onQueryListener));
    }

    public void queryAllWebCollect(OnQueryCollectListener onQueryListener){
        threadPool.execute(new QueryCollectRunnable(onQueryListener));
    }

    public void deleteAllHistory() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.deleteAll(SearchHistory.class);
            }
        });
    }
    public void deleteWebCollect(final String url){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(new WhereBuilder(WebCollectBean.class)
                        .where("url = ?",url));
            }
        });
    }

    class QueryRunnable implements Runnable{

        private OnQueryListener onQueryListener;

        public QueryRunnable(OnQueryListener onQueryListener) {
            this.onQueryListener = onQueryListener;
        }

        @Override
        public void run() {
            ArrayList<SearchHistory> query = liteOrm.query(SearchHistory.class);
            handler.post(new CallBackRunnable(onQueryListener, query));
        }
    }

    class CallBackRunnable implements Runnable {

        private OnQueryListener onQueryListener;
        private ArrayList<SearchHistory> searchHistories;

        public CallBackRunnable(OnQueryListener onQueryListener, ArrayList<SearchHistory> searchHistories) {
            this.onQueryListener = onQueryListener;
            this.searchHistories = searchHistories;
        }

        @Override
        public void run() {
            onQueryListener.onQuery(searchHistories);
        }
    }


    public interface OnQueryListener {
        void onQuery(ArrayList<SearchHistory> histories);
    }

    class QueryCollectRunnable implements Runnable{

        private OnQueryCollectListener onQueryListener;

        public QueryCollectRunnable(OnQueryCollectListener onQueryListener) {
            this.onQueryListener = onQueryListener;
        }

        @Override
        public void run() {
            ArrayList<WebCollectBean> query = liteOrm.query(WebCollectBean.class);
            handler.post(new CallBackCollectRunnable(onQueryListener, query));
        }
    }

    class CallBackCollectRunnable implements Runnable {

        private OnQueryCollectListener onQueryCollectListener;
        private ArrayList<WebCollectBean> webCollectBeen;

        public CallBackCollectRunnable(OnQueryCollectListener onQueryListener, ArrayList<WebCollectBean> webCollectBeen) {
            this.onQueryCollectListener = onQueryListener;
            this.webCollectBeen = webCollectBeen;
        }

        @Override
        public void run() {
            onQueryCollectListener.onQuery(webCollectBeen);
        }
    }


    public interface OnQueryCollectListener {
        void onQuery(ArrayList<WebCollectBean> webCollectBeen);
    }


}
