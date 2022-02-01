package ПотокиВводаВывода.Загрузка;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {

        openZip("d:\\Games\\savegames\\gameProgress.zip", "d:\\Games\\savegames\\");
        System.out.println(openProgress("d:\\Games\\savegames\\2 packed_save.dat"));
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

