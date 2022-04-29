package ru.mehcoder.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileIO {

    public static void main(String[] args) {

        readWriteFileByByteBuffer("test.zip", "test2.zip");
        readWriteFileByCharBuffer("test.xml", "test2.xml");

        readWriteFileByOwnByteBuffer("test.zip", "test2.zip");
        readWriteFileByOwnCharBuffer("test.xml", "test2.xml");

        pipelineReader("test.xml");

//        readWriteFileByByte("test.zip", "test2.zip");
//        readWriteFileByChar("test.xml", "test2.xml");
    }

    // читает и записывает в файл по буфферу байтов. Отличный вариант!
    public static void readWriteFileByByteBuffer(String readFilename, String writeFilename) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(readFilename));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(writeFilename))) {

            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читает и записывает в файл по заданному собственному буферу байтов. Хороший вариант!
    public static void readWriteFileByOwnByteBuffer(String readFilename, String writeFilename) {
        try (InputStream is = new FileInputStream(readFilename);
             OutputStream os = new FileOutputStream(writeFilename)) {

            byte[] buffer = new byte[4096];
            int r;
            while ((r = is.read(buffer)) != -1) {
                os.write(buffer, 0, r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читает и записывает в файл по одному байту. Плохой вариант!
    public static void readWriteFileByByte(String readFilename, String writeFilename) {
        try (InputStream is = new FileInputStream(readFilename);
             OutputStream os = new FileOutputStream(writeFilename)) {

            int r;
            while ((r = is.read()) != -1) {
                os.write(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читает и записывает в файл по буфферу символов. Отличный вариант!
    public static void readWriteFileByCharBuffer(String readFilename, String writeFilename) {
        try (BufferedReader br = new BufferedReader(new FileReader(readFilename));
             BufferedWriter bw = new BufferedWriter(new FileWriter(writeFilename))) {

            int b;
            while ((b = br.read()) != -1) {
                bw.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читает и записывает в файл по заданному собственному буфферу символов. Хороший вариант!
    public static void readWriteFileByOwnCharBuffer(String readFilename, String writeFilename) {
        try (Reader r = new FileReader(readFilename);
             Writer w = new FileWriter(writeFilename)) {

            char[] buffer = new char[4096];
            int c;
            while ((c = r.read(buffer)) != -1) {
                w.write(buffer, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // читает и записывает в файл по одному символу. Плохой вариант!
    public static void readWriteFileByChar(String readFilename, String writeFilename) {
        try (Reader r = new FileReader(readFilename);
             Writer w = new FileWriter(writeFilename)) {

            int c;
            while ((c = r.read()) != -1) {
                w.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // преобразование чтение из байтового > символьный > символьный буфер
    public static void pipelineReader(String readFilename) {
        try (FileInputStream fis = new FileInputStream(readFilename);
             Reader r = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(r)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
