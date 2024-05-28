package com.taba.inventory.interfaces;

import com.taba.inventory.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CategoryInterface {
    
    public ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();   
}
