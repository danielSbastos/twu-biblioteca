package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;

import java.util.List;
import java.util.stream.Collectors;

public class Finder {
    private List<IItem> items;

    public Finder(List<IItem> items) {
        this.items = items;
    }

    public IItem execute(int itemId) {
        return this.items
                .stream()
                .filter(item -> item.getId() == itemId)
                .collect(Collectors.toList())
                .get(0);
    }
}
