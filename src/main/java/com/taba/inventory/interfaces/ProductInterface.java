package com.taba.inventory.interfaces;

import com.taba.inventory.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ProductInterface {
    
    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();   
}
