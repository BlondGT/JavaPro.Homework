package lesson7;

import java.util.Objects;

public class FileData {

    private final String name;
    private final int size;
    private final String path;


    public FileData(int size, String name, String path) {
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return size == fileData.size && name.equals(fileData.name) && path.equals(fileData.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, path);
    }

    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}

