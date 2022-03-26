import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 50, 40, 10);
        GameProgress save2 = new GameProgress(87, 62, 31, 24);
        GameProgress save3 = new GameProgress(40, 120, 61, 15);

        File saveFile1 = new File("D:\\GAMES\\saveGames", "save1.dat");
        newFile(saveFile1);
        File saveFile2 = new File("D:\\GAMES\\saveGames", "save2.dat");
        newFile(saveFile2);
        File saveFile3 = new File("D:\\GAMES\\saveGames", "save3.dat");
        newFile(saveFile3);

        saveGame(save1, "D:\\GAMES\\saveGames\\save1.dat");
        saveGame(save2, "D:\\GAMES\\saveGames\\save2.dat");
        saveGame(save3, "D:\\GAMES\\saveGames\\save3.dat");

        String[] savesList = new String[3];
        savesList[0] = "D:\\GAMES\\saveGames\\save1.dat";
        savesList[1] = "D:\\GAMES\\saveGames\\save2.dat";
        savesList[2] = "D:\\GAMES\\saveGames\\save3.dat";

        zipFiles("D:\\GAMES\\saveGames\\save.zip", savesList);

        saveFile1.delete();
        saveFile2.delete();
        saveFile3.delete();
    }

    public static void saveGame(GameProgress save, String way) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void newFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Файл " + file.getName() + " создан");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String wayToZip, String[] arr) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(wayToZip))) {
            for (String wayToFile : arr) {
                try (FileInputStream fis = new FileInputStream(wayToFile)) {
                    ZipEntry entry = new ZipEntry(wayToFile);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
