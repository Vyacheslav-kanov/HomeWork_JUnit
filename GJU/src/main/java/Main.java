import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static StringBuilder log = new StringBuilder();
    private static List<GameProgress> gp = new ArrayList<>();
    private static List<String> travelFileList= new ArrayList<>();

    public static void main(String[] args) throws IOException {
        executor();
    }

    public static void executor(){
        gp.add(new GameProgress(100, 2, 10, 100.20));
        gp.add(new GameProgress(50, 4, 20, 500.54));
        gp.add(new GameProgress(75, 10, 50, 3000.99));

        File games = new File("D:/Games");

        //Folder D:/Games/
        if(games.isDirectory()){
            File games_src = createFolder("D:/Games/src");
            File games_res = createFolder("D:/Games/res");
            File games_savegames = createFolder("D:/Games/savegames");
            File games_tmp = createFolder("D:/Games/tmp");

            //Folder D:/Games/src/
            if(games_src.isDirectory()){
                File games_src_main = createFolder("D:/Games/src/main");
                File games_src_test = createFolder("D:/Games/src/test");

                //Folder D:/Games/src/main/
                if(games_src_main.isDirectory()){
                    File gamesSrcMain_main_java =  createFile("D:/Games/src/main/Main.java");
                    File gamesSrcMain_utils_java = createFile("D:/Games/src/main/Utils.java");
                }
            }

            //Folder D:/Games/games_savegames/
            if(games_savegames.isDirectory()){

                //Читаем список обьектов и передаем каждый обьект в метод
                //который создаст файлы сохранения и сразу записывает путь созданного файла в travelFileList
                for (int i = 0; i < gp.size(); i++) {
                    String travel = "D:/Games/savegames/save" + i + ".txt";
                    saveGame(travel , gp.get(i));
                    travelFileList.add(travel);
                }

                //Создаем архив и указываем где его создать и список путей файлов
                //которые будут добавленны в архив, потом удалены в папке
                zipFiles("D:/Games/savegames/zip.zip", travelFileList);
            }

            //Folder D:/Games/tmp/
            //Записываю в текстовый файл tmp весь текст который накопился в StringBuilder log.
            if(games_tmp.isDirectory()){
                File gamesTmp_temp_txt = createFile("D:/Games/tmp/temp.txt");
                try {
                    FileWriter writer = new FileWriter(gamesTmp_temp_txt);
                    writer.write(log.toString());
                    writer.flush();
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static File createFile(String travel)  {
        try {
            File file_java = new File(travel);
            if(file_java.createNewFile()) addInLog(travel, "File created!");
            else addInLog(travel, "Crashed create!");
            return file_java;
        }catch (IOException e){
            System.out.println("File crashed!: " + travel);
        }
        return null;
    }

    private static File createFolder(String travel){
        File folder = new File(travel);
        if(folder.mkdir()) addInLog(travel, "Folder created!");
        else addInLog(travel, "Crashed create!");
        return folder;
    }

    //Метод создан для добавления в StringBuilder имени файла, и коментирующего сообщения.
    private static void addInLog(String travel, String message){
        String nameFile[] = travel.split("/");
        log.append(nameFile[nameFile.length -1] + " " + message + "\r\n");
    }

    private static void saveGame(String travel, GameProgress save){
        try(FileOutputStream fos = new FileOutputStream(travel);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipFiles(String travelCreateZip, List<String> fileList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(travelCreateZip))) {

            addInLog(travelCreateZip, "Zip archive created!");
            log.append("В архиве: " + " { \r\n");

            for (String e: fileList) {
                FileInputStream fis = new FileInputStream(e);

                String nameFile[] = e.split("/");
                ZipEntry ze = new ZipEntry(nameFile[nameFile.length -1]);

                zout.putNextEntry(ze);
                byte[] buffer = new byte[fis.available()];

                fis.read(buffer);
                fis.close();
                zout.write(buffer);
                zout.closeEntry();

                addInLog(e , "Created inside zip archive!");

                //После добавления файла в архив подчищает его в папке
                File delGarbage = new File(e);
                if(delGarbage.delete()) addInLog(e, "Clone deleted!");
                else addInLog(e, "Crash deleted clone!");
            }
            log.append("} \r\n");
        }catch (IOException e){ e.printStackTrace();}
    }
}
