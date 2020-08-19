import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static Collection<Person> persons = new ArrayList<>();
    public static List<String> listSummon = new ArrayList<>();
    public static List<String> listFitPeople = new ArrayList<>();

    public static long countPerson = 0L;

    public static void main(String[] args) {
        executor();
    }

    public static void executor(){
        Stream<Person> streamCount = persons.stream();
         countPerson = streamCount
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(countPerson);

        Stream<Person> streamSummon = persons.stream();
        listSummon = streamSummon
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map((x) -> x.getFamily() + "")
                .collect(Collectors.toList());
        System.out.println(listSummon.get(0));

        Stream<Person> streamFitPeople = persons.stream();
        listFitPeople = streamFitPeople
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 60)
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .sorted(new ComparatorPersonFamily())
                .map((x) -> x.getFamily() + "")
                .collect(Collectors.toList());
        for(String e: listFitPeople) System.out.println(e + " #");
    }
}
