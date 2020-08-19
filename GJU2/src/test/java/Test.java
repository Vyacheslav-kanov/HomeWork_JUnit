import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;

public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
         Main.persons = Arrays.asList( //Samples
                new Person("Jack", "Evans", 16, Sex.MAN, Education.SECONDARY),
                new Person("Connor", "Young", 23, Sex.MAN, Education.FURTHER),
                new Person("Emily", "Harris", 42, Sex.WOMEN, Education.HIGHER),
                new Person("Harry", "Wilson", 69, Sex.MAN, Education.HIGHER),
                new Person("George", "Davies", 35, Sex.MAN, Education.FURTHER),
                new Person("Samuel", "Adamson", 40, Sex.MAN, Education.HIGHER),
                new Person("John", "Brown", 44, Sex.MAN, Education.HIGHER)
        );

        Main.executor();

        //Проверка списка не совершеннолетних
        Assertions.assertTrue(Main.countPerson == 1L);

        //Прорверка списка призывников
        Assertions.assertTrue(Main.listSummon.get(0).equals("Young"));
        Assertions.assertTrue(Main.listSummon.size() == 1);

        //Проверка списка работников подходящих по требованию
        Assertions.assertTrue(Main.listFitPeople.get(0).equals("Adamson"));
        Assertions.assertTrue(Main.listFitPeople.get(1).equals("Brown"));
        Assertions.assertTrue(Main.listFitPeople.get(2).equals("Harris"));
        Assertions.assertTrue(Main.listFitPeople.size() == 3);
    }
}
