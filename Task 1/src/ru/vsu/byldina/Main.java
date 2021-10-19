package ru.vsu.byldina;

public class Main {
    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        var manager = new ConsoleCollectionManager<>(list, x -> Integer.parseInt(x));
        try {
            while (true) {
                ConsoleCollectionManager.printMenu();
                int index = manager.readIndex();

                switch (index) {
                    case 1:
                        manager.addElement();
                        break;
                    case 2:
                        manager.removeFirstElement();
                        break;
                    case 3:
                        manager.clear();
                        break;
                    case 4:
                        manager.printElements();
                        break;
                    case 5:
                        return;
                }
            }
        } finally {
            manager.close();
        }
    }
}
