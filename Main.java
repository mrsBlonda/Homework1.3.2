import java.io.*;
import java.util.*;
import java.util.zip.*;


public class Main {
    public static void saveGame(GameProgress game, String address) {
        try(FileOutputStream fos = new FileOutputStream(address);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delete (File file) {
        if (file.delete()) {
            System.out.println("Файл " + file +  " удалён");
        }
    }

    public static void zipFiles(String addres, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(addres))) {
            for (String file : list) {
                File fileToZip = new File(file);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry entry = new ZipEntry(fileToZip.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(80, 3, 3, 56);
        GameProgress game2 = new GameProgress(95, 6, 2, 39);
        GameProgress game3 = new GameProgress(100, 2, 1, 20);
        String separator = File.separator;
        String userDir = System.getProperty("user.dir");
        String filename1 = "save1.dat";
        String filename2 = "save2.dat";
        String filename3 = "save3.dat";
        String filepath = userDir + separator + "Games" + separator + "savegames" + separator;
        String filepath1 = filepath + filename1;
        String filepath2 = filepath + filename2;
        String filepath3 = filepath + filename3;

        saveGame(game1, filepath1);
        saveGame(game2, filepath2);
        saveGame(game3, filepath3);
        ArrayList<String> list = new ArrayList<>();
        list.add(filepath1);
        list.add(filepath2);
        list.add(filepath3);
        zipFiles(filepath + separator + "zip_output.zip",list);
        File file1 = new File(filepath1);
        File file2 = new File(filepath2);
        File file3 = new File(filepath3);
        delete(file1);
        delete(file2);
        delete(file3);
        System.out.println("Пользовательская папка: " + filepath);




    }
}
