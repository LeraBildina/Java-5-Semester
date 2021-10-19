package ru.vsu.byldina;

public interface ElementParser<E> {
    E parse(String str);
}
