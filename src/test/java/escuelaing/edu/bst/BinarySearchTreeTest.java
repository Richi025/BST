package escuelaing.edu.bst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void testAddAndSize() {
        
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        
        assertEquals(5, bst.size());
    }

    @Test
    public void testInOrderTraversal() {
        
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);

        assertEquals(20, bst.get(0)); 
        assertEquals(40, bst.get(2)); 
        assertEquals(70, bst.get(4)); 
    }

    @Test
    public void testContains() {
        bst.add(50);
        bst.add(30);
        bst.add(70);

        assertTrue(bst.contains(50));
        assertTrue(bst.contains(30));
        assertFalse(bst.contains(100));
    }

    @Test
    public void testRemove() {
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);

        
        assertTrue(bst.remove((Integer) 20));
        assertFalse(bst.contains(20));

        
        assertTrue(bst.remove((Integer) 30));
        assertFalse(bst.contains(30));

        
        assertTrue(bst.remove((Integer) 50));
        assertFalse(bst.contains(50));
    }

    @Test
    public void testClearAndIsEmpty() {
        bst.add(50);
        bst.add(30);
        bst.add(70);

        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testGetByIndex() {
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);

        assertEquals(20, bst.get(0)); 
        assertEquals(30, bst.get(1)); 
        assertEquals(50, bst.get(2)); 
        assertEquals(70, bst.get(3)); 
    }

    @Test
    public void testGetIndexOutOfBounds() {
        bst.add(50);
        bst.add(30);

        assertThrows(IndexOutOfBoundsException.class, () -> bst.get(5));
    }

    @Test
    public void testRemoveIndex() {
        bst.add(50);
        bst.add(30);
        bst.add(70);

        
        assertEquals(30, bst.remove(0)); 
        assertEquals(2, bst.size());    
        assertEquals(50, bst.get(0));  
    }
}
