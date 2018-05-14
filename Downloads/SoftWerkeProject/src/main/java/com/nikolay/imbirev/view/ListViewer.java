package com.nikolay.imbirev.view;

import com.nikolay.imbirev.model.entities.BaseEntity;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class ListViewer<T extends BaseEntity> {

    public void listView(List<T> list) {
        for (T t : list) {
            log.info(t.toString());
        }
    }
}