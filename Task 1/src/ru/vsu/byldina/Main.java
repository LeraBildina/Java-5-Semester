package ru.vsu.byldina;

public class Main {

    public static void main(String[] args) {
        var list = new LinkedList<Integer>();

        list.add(1);
        list.add(2);

        list.clear();

        list.add(2);
        list.add(1);

        list.add(3);
        list.add(4);
        list.add(5);
        
        list.remove(2);
        list.remove(5);
        list.add(6);

        for (var obj : list) {
            System.out.println(obj); 
        }
    }
}
