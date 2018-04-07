package com.imbirev.nikolay.singletons;

public class ClientSingleton {

    /**
     * standart singleton pattern
     */
    private static ClientSingleton sClientSingleton;


    public static ClientSingleton getClientSingleton() {
        if (sClientSingleton == null) {
            sClientSingleton = new ClientSingleton();
        }
        return sClientSingleton;
    }

    private ClientSingleton() {

    }

}