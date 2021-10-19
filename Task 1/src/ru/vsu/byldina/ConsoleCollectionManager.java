package ru.vsu.byldina;

import java.io.Closeable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleCollectionManager<E> implements Closeable {
    private final Scanner ioScanner;
    private final ElementParser<E> elementParser;
    private final Collection<E> collection;
    private static final String[] actions;

    static {
        actions = new String[] {
                "Добавить элемент.",
                "Удалить элемент.",
                "Очистить список.",
                "Распечатать список.",
                "Выход"
        };
    }

    public ConsoleCollectionManager(Collection<E> collection, ElementParser<E> elementParser) {
        if (collection == null)
            throw new NullPointerException();

        if (elementParser == null)
            throw new NullPointerException();

        this.collection = collection;
        this.elementParser = elementParser;
        this.ioScanner = new Scanner(System.in);
    }

    public static void printMenu() {
        for (var i = 1; i <= actions.length; ++i)
            System.out.printf("%d. %s\n", i, actions[i - 1]);
    }

    public int readIndex() {
        while (true) {

            try {
                System.out.print("Введите номер: ");
                int v = Integer.parseInt(this.ioScanner.next().trim());
                if (v < 1 || v > actions.length)
                    throw new IndexOutOfBoundsException();
                return v;
            } catch (Exception ex) {
                System.out.println("Не удалось распознать номер.");
                safeSleep(1000);
            }
        }
    }

    public void addElement() {
        E e = readElement();
        if (this.collection.add(e))
            System.out.println("Элемент успешно добавлен.");
        else
            System.out.println("Не удалось добавить элемент.");
    }

    public void removeFirstElement() {
        E e = readElement();
        if (this.collection.remove(e))
            System.out.println("Элемент успешно удален.");
        else
            System.out.println("Не удалось удалить элемент.");
    }

    public void clear() {
        this.collection.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void hasElement() {
        E e = readElement();
        if (this.collection.contains(e))
            System.out.println("Элемент содержится в коллекции.");
        else
            System.out.println("Элемент не содержится в коллекции.");
    }

    public void printElements() {
        if (this.collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Содержимое коллекции: ");

        Iterator<E> it = this.collection.iterator();

        if (it.hasNext()) {
            System.out.print(it.next());
        }

        while (it.hasNext()) {
            System.out.print(", ");
            System.out.print(it.next());
        }

        System.out.println();
    }

    private E readElement() {
        while (true) {
            try {
                System.out.print("Введите элемент: ");
                return this.elementParser.parse(this.ioScanner.next().trim());
            } catch (Exception e) {
                System.out.println("Не удалось распознать элемент.");
                safeSleep(1000);
            }
        }
    }

    private static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void close() {
        this.ioScanner.close();
    }
}
