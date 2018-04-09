package com.imbirev.nikolay.controller.search;

import com.imbirev.nikolay.model.beans.Client;

import java.time.LocalDate;
import java.util.List;

public class ClientSearch implements SearchInterface<Client> {

    private List<Client> clientList;

    public ClientSearch(List<Client> clients) {
        this.clientList = clients;
    }
    // TODO добавить метод поиска по id
    /**
     *
     * @param param is the parameter of searh
     * @param keyOfSearch is the searching object
     * @return client from clients list or null
     */
    @Override
    public Client searchByParam(String param, String keyOfSearch) {
        switch (param) {
            case "First Name":
            {String[] array = new String[clientList.size()];
                for (int i = 0; i < array.length; i++) array[i] = clientList.get(i).getfName();
                int position = rank(keyOfSearch, array, 0, array.length);
                if (position >= 0) return clientList.get(position);
                else return null;}
            case "Last Name":
            {String[] array = new String[clientList.size()];
                for (int i = 0; i < array.length; i++) array[i] = clientList.get(i).getlName();
                int position = rank(keyOfSearch, array, 0, array.length);
                if (position >= 0) return clientList.get(position);
                else return null; }
            case "Client id":
            {int[] array = new int[clientList.size()];
                for (int i = 0; i < array.length; i++) array[i] = clientList.get(i).getClientId();
                int position = rank(Integer.valueOf(keyOfSearch), array, 0, array.length);
                if (position >= 0) return clientList.get(position);
                else return null; }
            default:return null;
        }
    }

    /**
     * method realize model of binary search
     * i have no time to realize smt more complicated and faster
     * @param key is a searching object
     * @param array is a array of object, where we will search
     * @param left is a initial border of array
     * @param right is a exit border of array
     * @return position if element is in the array else - return minus
     */
    protected static int rank(Comparable key, Comparable[] array, int left, int right) {

        int position = (left + right) /2; // обозначаем центр

        if (left >= right) return -(1+left); // останавливаем рекурсию

        if(array[left].compareTo(key) == 0) { // если слева
            return left;
        }

        if (array[position].compareTo(key) == 0) { // если в центре
            return position;
        }

        else if ((array[position].compareTo(key) > 0)) {
            return rank(key, array, left, position); // левая половина массива
        }
        else {
            return rank(key, array, position +1, right); // правая половина массива
        }
    }

    /**
     * overloading method
     * method realize model of binary search
     * i have no time to realize smt more complicated and faster
     * @param key is a searching object
     * @param array is a array of object, where we will search
     * @param left is a initial border of array
     * @param right is a exit border of array
     * @return position if element is in the array else - return minus
     */
    protected static int rank(int key, int[] array, int left, int right) {

        int position = (left + right) /2; // обозначаем центр

        if (left >= right) return -(1+left); // останавливаем рекурсию

        if(array[left] == key) { // если слева
            return left;
        }

        if (array[position] == key) { // если в центре
            return position;
        }

        else if (array[position] > key) {
            return rank(key, array, left, position); // левая половина массива
        }
        else {
            return rank(key, array, position +1, right); // правая половина массива
        }
    }
}
