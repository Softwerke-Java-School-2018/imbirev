package com.nikolay.imbirev.view;

import com.nikolay.imbirev.model.entities.BaseEntity;
import lombok.extern.log4j.Log4j;
import java.util.List;

@Log4j
public class ListViewer<T extends BaseEntity> {
    public void listView(List<T> list) {
        list.forEach(log::info);
    }
}