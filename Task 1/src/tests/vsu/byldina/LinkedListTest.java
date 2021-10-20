package tests.vsu.byldina;

import org.junit.jupiter.api.Test;
import ru.vsu.byldina.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void sizeIsZeroTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.clear();

        // assert
        assertEquals(list.size(), 0);
    }

    @Test
    void sizeIsNonZeroTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(0);

        // assert
        assertNotEquals(list.size(), 0);
    }

    @Test
    void isEmptyTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.clear();

        // assert
        assertTrue(list.isEmpty());
    }

    @Test
    void isNotEmptyTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(0);

        // assert
        assertFalse(list.isEmpty());
    }

    @Test
    void containsNullTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // assert
        assertDoesNotThrow(() -> list.contains(null));
    }

    @Test
    void emptyIteratorTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.clear();

        // assert
        assertFalse(list.iterator().hasNext());
    }

    @Test
    void notEmptyIteratorTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(0);

        // assert
        assertTrue(list.iterator().hasNext());
    }

    @Test
    void toEmptyArrayTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.clear();

        // assert
        assertNotNull(list.toArray());
    }

    @Test
    void toArrayWithCollectionSizeGreaterArrayLengthTest() {
        // arrange
        var list = new LinkedList<Integer>();
        var arr = new Integer[1];

        // act
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // assert
        assertNotNull(list.toArray(arr)[list.size() - 1]);
    }

    @Test
    void toArrayWithCollectionSizeLessArrayLengthTest() {
        // arrange
        var list = new LinkedList<Integer>();
        var arr = new Integer[10];

        // act
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // assert
        assertNull(list.toArray(arr)[list.size()]);
    }

    @Test
    void addNullElementTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(null);

        // assert
        assertEquals(list.size(), 1);
    }

    @Test
    void removeNullElementTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        boolean status = list.remove(null);

        // assert
        assertFalse(status);
    }

    @Test
    void containsElementTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        boolean status = list.contains(2);

        // assert
        assertTrue(status);
    }

    @Test
    void notContainsElementTest() {
        // arrange
        var list = new LinkedList<Integer>();

        // act
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        boolean status = list.contains(6);

        // assert
        assertFalse(status);
    }
}