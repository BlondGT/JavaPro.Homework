package hm7;

import java.util.*;

public class FileNavigator {

    private final Map<String, List<FileData>> files = new HashMap<>();


//2. Реалізувати метод add у класі FileNavigator. Цей метод додає файл за вказаним шляхом.
// Якщо шлях вже існує, новий файл необхідно додати до списку, вже пов'язаному з відповідним шляхом.
// ВАЖЛИВО: Шлях – унікальне значення і не повинно повторюватися.

    public void add(String path, FileData file) throws InconsistentPathException{

        if(files.containsKey(path)) {
            if (!path.equals(file.getPath())) throw new InconsistentPathException("Wrong path!!!");
            List<FileData> existingFile = files.get(path);
            List<FileData> newFile = new ArrayList<>(existingFile);
            newFile.add(file);
            files.put(path, newFile);
        } else {
            if (!path.equals(file.getPath())) throw new InconsistentPathException("Wrong path!!!");
            files.put(path, List.of(file));
        }
    }

//   3. Реалізувати метод find у класі FileNavigator. Метод повертає список файлів,
//   пов'язаних з шляхом переданим як параметр.

    public List<FileData> find(String path) {
        return files.get(path);
    }

//    4. Реалізувати метод filterBySize у класі FileNavigator. Метод повертає список файлів,
//    розмір (в байтах) яких не перевищує значення, передане як параметр.

    public List<FileData> filterBySize (int maxSize) {

        List<FileData> filterFile = new ArrayList<>();

        for (List<FileData> file : files.values()) {
            for (FileData fileData : file) {
                if (fileData.getSize() <= maxSize) {
                    filterFile.add(fileData);
                }
            }
        }
        return filterFile;
    }

//   5. Реалізувати метод remove у класі FileNavigator. Метод видаляє шлях і пов'язані з ним файли,
//   виходячи з значення шляху, переданого як параметр.

    public void remove(String path) {
        files.remove(path);
    }

//    6. * Реалізувати метод sortBySize у класі FileNavigator. Метод сортує всі наявні файли за розміром
//    (за зростанням), потім повертає список відсортованих файлів.

    public List<FileData> sortBySize() {

        List<FileData> allFiles = new ArrayList<>();

        for(List<FileData> fileData : files.values()) {
            allFiles.addAll(fileData);
        }
        allFiles.sort(Comparator.comparingLong(FileData::getSize));
        return allFiles;
    }

//    7. ** Реалізувати перевірку консистентності у методі add у класі FileNavigator.
//    Не дозволяти додавати значення і повідомити про це через свій unchecked exception,
//    при спробі додати значення FileData значення шляху якого не відповідає шляху-ключу,
//    що веде до списку файлів.
//            Наприклад:
//    Має бути помилка, оскільки шлях-ключ і шлях до файлу не збігатися
//    Шлях: /path/to/file
//    FileData: {name: ..., size: ..., path: /another/path/}


}


