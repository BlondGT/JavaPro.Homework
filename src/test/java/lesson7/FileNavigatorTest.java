package lesson7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileNavigatorTest {

    @Test
    void shouldAdd() {

        FileNavigator fileNavigator = new FileNavigator();

        assertTrue(fileNavigator.add("path/to/file",
                new FileData(32, "list.txt", "path/to/file")));
    }

    @Test
    void shouldFind() {

        FileNavigator fileNavigator = new FileNavigator();
        fileNavigator.add("path/to/file", new FileData(32, "list.txt", "path/to/file"));

        assertEquals(List.of(new FileData(32, "list.txt", "path/to/file")),
                fileNavigator.find("path/to/file"));
    }

    @Test
    void shouldFilterBySize() {

        FileNavigator fileNavigator = new FileNavigator();
        fileNavigator.add("path/to/file", new FileData(32, "list.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(16, "app.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(24, "email.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(16, "array.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(8, "name.txt", "path/to/file"));

        assertEquals(List.of(
                        new FileData(16, "app.txt", "path/to/file"),
                        new FileData(16, "array.txt", "path/to/file"),
                        new FileData(8, "name.txt", "path/to/file")
                ),
                fileNavigator.filterBySize(16));
    }

    @Test
    void shouldRemove() {

        FileNavigator fileNavigator = new FileNavigator();
        fileNavigator.add("path/to/file", new FileData(32, "list.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(16, "app.txt", "path/to/file"));

        assertTrue(fileNavigator.remove("path/to/file"));
    }

    @Test
    void shouldSortBySizeAscending() {

        FileNavigator fileNavigator = new FileNavigator();
        fileNavigator.add("path/to/file", new FileData(32, "list.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(16, "app.txt", "path/to/file"));
        fileNavigator.add("path/to/file", new FileData(24, "email.txt", "path/to/file"));

        assertEquals(List.of(
                        new FileData(16, "app.txt", "path/to/file"),
                        new FileData(24, "email.txt", "path/to/file"),
                        new FileData(32, "list.txt", "path/to/file")),
                fileNavigator.sortBySize());
    }

}