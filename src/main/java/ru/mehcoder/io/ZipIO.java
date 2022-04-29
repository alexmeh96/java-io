package ru.mehcoder.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipIO {

    public static void main(String[] args) {


        writeZip("test.zip");
        readZip("test.zip");

        createZip("test.zip", ".");
    }

    public static void readZip(String filename) {

        try (ZipFile zf = new ZipFile(filename)) {
            for (var iter = zf.entries(); iter.hasMoreElements(); ) {
                ZipEntry ze = iter.nextElement();
                System.out.println("File: " + ze.getName());
                if (!ze.isDirectory()) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));

                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                System.out.println("------------------>>>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeZip(String fileName) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName))) {

            ZipEntry ze1 = new ZipEntry("name1.txt");
            zos.putNextEntry(ze1);
            zos.write("SUPER1".getBytes());

            ZipEntry ze2 = new ZipEntry("name2.txt");
            zos.putNextEntry(ze2);
            zos.write("SUPER2".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createZip(String filename, String path) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filename))) {
            File[] files = new File(path).listFiles();
            createZipDir(zos, files, "", filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void createZipDir(ZipOutputStream zos, File[] files, String path, String zipName) throws IOException {
        if (files == null) return;

        for (File f : files) {
            if (f.isDirectory()) {
                createZipDir(zos, f.listFiles(), path + f.getName() + "/", zipName);
            } else if (!f.getName().equals(zipName)) {
                ZipEntry ze = new ZipEntry(path + f.getName());
                zos.putNextEntry(ze);

                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                int size = -1;
                while ((size = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, size);
                }
            }
        }
    }
}