package com.taba.inventory.interfaces;

import com.taba.inventory.entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface SupplierInterface {
    
    public ObservableList<Supplier> SUPPLIERLIST = FXCollections.observableArrayList();
}
