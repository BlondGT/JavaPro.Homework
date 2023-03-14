package hm12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericArrayTest {

    @Test
    void shouldFindIndexOf() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");

        assertEquals(-1, genericArray.indexOf("fd"));
        assertEquals(1, genericArray.indexOf("it"));

    }

    @Test
    void shouldRemoveElement() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");

        assertEquals("it", genericArray.remove(1));
        assertArrayEquals(new String[] {"gh"}, genericArray.toArray());
    }

    @Test
    void shouldAddElement() {

        GenericArray<String> genericArray = new GenericArray<>();
        genericArray.add("gh");

        assertArrayEquals(new String[] {"gh"}, genericArray.toArray());
    }

    @Test
    void shouldArrayContainsElement() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");
        genericArray.add("mj");
        genericArray.add("po");

        assertTrue(genericArray.contains("mj"));
        assertFalse(genericArray.contains("1"));
    }

    @Test
    void shouldArrayHasSize() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");
        genericArray.add("mj");
        genericArray.add("po");

        assertEquals(4, genericArray.size());
        assertNotEquals(3, genericArray.size());
    }

    @Test
    void shouldGetIndexOfElement() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");
        genericArray.add("mj");
        genericArray.add("po");

        assertEquals("gh", genericArray.get(0));

    }

    @Test
    void shouldShowedGetAll() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");

        assertArrayEquals(new String[] {"gh", "it"}, genericArray.toArray());
    }

    @Test
    void shouldShowedGetAllTypeT() {

        GenericArray<String> genericArray = new GenericArray<>();

        genericArray.add("gh");
        genericArray.add("it");

        assertArrayEquals(new String[] {"gh", "it"}, genericArray.toArray(new String[0]));
    }

}