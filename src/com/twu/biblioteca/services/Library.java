package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<IItem> items;

    public Library(List<IItem> items) {
        this.items = items;
    }

    public List<IItem> listBooks() {
        return this.items;
    }

    public String checkoutBook(int itemId) {
        IItem item = findBook(itemId);

        if (alreadyBooked(item)) {
            return "Item already checked out.";
        }

        item.setStatus("booked");
        return "Successfully checked out item.";
    }

    public String returnBook(int itemId) {
        IItem item = findBook(itemId);

        if (alreadyReturned(item)) {
            return "Item already returned.";
        }

        item.setStatus("available");
        return "Successfully returned item.";
    }

    private IItem findBook(int itemId) {
        return this.items
               .stream()
               .filter(book -> book.getId() == itemId)
               .collect(Collectors.toList())
               .get(0);
    }

    private boolean alreadyBooked(IItem item) {
        return item.getStatus() == "booked";
    }

    private boolean alreadyReturned(IItem item) {
        return item.getStatus() == "available";
    }
}
