import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTester {

    BSTree<Integer> bst;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bst = new BSTree<>();
    }

    @org.junit.jupiter.api.Test
    void getRoot() {
        assertNull(bst.getRoot());
    }

    @org.junit.jupiter.api.Test
    void getSize() {
        assertEquals(bst.getSize(), 0);
    }

    @org.junit.jupiter.api.Test
    void insert() {
        assertTrue(bst.insert(10));
        assertFalse(bst.insert(10));
        assertTrue(bst.insert(3));
        assertEquals(bst.getSize(), 2);
        boolean throwsException = false;
        try{
            bst.insert(null);
        }
        catch (NullPointerException e){
            throwsException = true;
        }
        assertTrue(throwsException);
    }

    @org.junit.jupiter.api.Test
    void findKey() {
        bst.insert(10);
        bst.insert(3);
        bst.insert(5);
        bst.insert(20);
        assertTrue(bst.findKey(5));
        assertFalse(bst.findKey(15));
        assertFalse(bst.findKey(21));
        boolean throwsException = false;
        try{
            bst.findKey(null);
        }
        catch (NullPointerException e){
            throwsException = true;
        }
        assertTrue(throwsException);
    }

    @org.junit.jupiter.api.Test
    void insertData() {

    }

    @org.junit.jupiter.api.Test
    void findDataList() {
    }

    @org.junit.jupiter.api.Test
    void findHeight() {
        for(int x : new int[]{100, 10, 50, 140, 5, 25, 1, 160, 125})
            bst.insert(x);//working with a BST that we know the shape of
        assertEquals(bst.findHeight(), 3);

        bst = new BSTree<>();

        for(int x : new int[]{50, 40, 60, 30, 45, 35, 55, 80, 75, 90})
            bst.insert(x);
        assertEquals(bst.findHeight(), 3);

        bst = new BSTree<>();

        for(int x : new int[]{1,2,3,4,5,0})
            bst.insert(x);
        assertEquals(bst.findHeight(), 4);
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        //First case
        for(int x : new int[]{100, 10, 50, 140, 5})
            bst.insert(x);
        Iterator<Integer> iter = bst.iterator();
        //assertTrue(iter.hasNext());
        assertEquals(iter.next(), 5);
        assertEquals(iter.next(), 10);
        for(int i = 0; i < 3; i++)
            iter.next();
        assertFalse(iter.hasNext());

        bst = new BSTree<>();
        //Second case
        for(int x : new int[]{50, 40, 60, 30, 45, 35, 55, 80, 75, 90})
            bst.insert(x);
        iter = bst.iterator();
        assertEquals(iter.next(), 30);
        iter.next();
        assertEquals(iter.next(), 40);
        iter.next();
        iter.next();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 55);
    }
}