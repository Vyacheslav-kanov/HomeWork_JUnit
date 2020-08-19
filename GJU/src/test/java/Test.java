import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        Main.executor();
        Assertions.assertTrue(availbleFile("D:\\Games"));
        Assertions.assertTrue(availbleFile("D:\\Games\\res"));
        Assertions.assertTrue(availbleFile("D:\\Games\\savegames"));
        Assertions.assertTrue(availbleFile("D:\\Games\\savegames\\zip.zip"));
        Assertions.assertTrue(availbleFile("D:\\Games\\src"));
        Assertions.assertTrue(availbleFile("D:\\Games\\src\\main"));
        Assertions.assertTrue(availbleFile("D:\\Games\\src\\main\\Main.java"));
        Assertions.assertTrue(availbleFile("D:\\Games\\src\\main\\Utils.java"));
        Assertions.assertTrue(availbleFile("D:\\Games\\tmp"));
        Assertions.assertTrue(availbleFile("D:\\Games\\tmp\\temp.txt"));
    }

    private static Boolean availbleFile(String pathname){
        File file = new File(pathname);
        return file.exists();
    }

    @org.junit.jupiter.api.Test
    public void testZip(){
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream("D:\\Games\\savegames\\zip.zip"))){

            ZipEntry entry = zis.getNextEntry();
            Assertions.assertTrue(entry.getName().equals("save0.txt"));

            entry = zis.getNextEntry();
            Assertions.assertTrue(entry.getName().equals("save1.txt"));

            entry = zis.getNextEntry();
            Assertions.assertTrue(entry.getName().equals("save2.txt"));

            zis.closeEntry();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
