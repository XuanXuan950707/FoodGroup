package com.example.dllo.foodgroup.letorm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/11/12.
 */
public class SearchHistory {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String searchName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
