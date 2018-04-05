package com.imbirev.nikolay.singletons;

import com.imbirev.nikolay.beans.Client;

import java.util.ArrayList;
import java.util.List;


/**
 * здесь соответственно все сервисы по работе с данными клиента (сортировка, поиск, хранение информации в runtime
 */
public class ClientSingleton extends SortClass {

    /**
     * классический паттерн синглетона
     */
    private static ClientSingleton sClientSingleton;
    private List<Client> clients;


    public static ClientSingleton getClientSingleton() {
        if (sClientSingleton == null) {
            sClientSingleton = new ClientSingleton();
        }
        return sClientSingleton;
    }

    /**
     * почему ArrayList, потому что удаление клиентов происходит все же намного реже чем вставка новых и работа
     * с выдергиванием их данных, так что мы добъемся чуть большей производительности
     */
    private ClientSingleton() {
        clients = new ArrayList<>();
    }

}