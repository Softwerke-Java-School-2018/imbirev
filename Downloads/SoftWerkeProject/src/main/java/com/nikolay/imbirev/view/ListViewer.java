package com.nikolay.imbirev.view;

import com.nikolay.imbirev.model.entities.BaseEntity;

import java.util.List;

public class ListViewer<T extends BaseEntity> {

    public void listView(List<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }
}