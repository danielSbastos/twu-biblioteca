package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private List<IOption> options;

    public Menu(List<IOption> options) {
        this.options = options;
    }

    public List<IOption> getOptions() {
        return this.options;
    }

    //TODO: make it private
    public IOption getOptionById(int id) throws IndexOutOfBoundsException {
        return this.options
                   .stream()
                   .filter(option -> option.getId() == id)
                   .collect(Collectors.toList())
                   .get(0);
    }

    public String executeOption(int optionId) {
        IOption currentOption = this.getOptionById(optionId);
        return currentOption.action();
    }

    public void closeCurrentOption() {
    }
}
