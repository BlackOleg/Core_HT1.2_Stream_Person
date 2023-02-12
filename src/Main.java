import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> under18 = persons.stream()
                .filter(x -> x.getAge() < 18);
        System.out.println("Количество несовершеннолетних: " + under18.count());
        List<String> mobList = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .map(Person::getFamily).toList();
        System.out.println(mobList);
        List<String> highList = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 65)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).toList();
        System.out.println(highList);
    }
}