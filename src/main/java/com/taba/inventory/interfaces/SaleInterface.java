package com.taba.inventory.interfaces;

import com.taba.inventory.entity.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface SaleInterface {

    public ObservableList<Sale> SALELIST = FXCollections.observableArrayList();
}
