package ru.mehcoder.io;

import java.io.File;

public class FileSystem {

    // получить список файлов оканчивающихся на .xml
    public static void main(String[] args) {
        File file = new File(".");
        String[] l = file.list((dir, name) -> name.endsWith(".xml"));

        if (l == null) return;

        for (String name : l) {
            System.out.println(name);
        }
    }
}
