package ПотокиВводаВывода.Загрузка;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static String prefix = "d:" + File.separator + "Games" + File.separator + "savegames" + File.separator;

    public static void main(String[] args) {

        openZip(prefix + "gameProgress.zip", prefix);
        System.out.println(openProgress(prefix + "2 packed_save.dat"));
    }

    public static void openZip(String zipFile, String unzipFilesFolder) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(zipFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(unzipFilesFolder + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String save) {
        GameProgress game = null;
        try (FileInputStream fis = new FileInputStream(save);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            game = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return game;
    }

}

