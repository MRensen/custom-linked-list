import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("string");
//        arrayList.set(3, "3");

        //De linkedList kunnen we als een List opslaan, omdat we in LinkedList de List interface hebben geimplementeerd
        List<String> linkedList = new LinkedList<>();
        System.out.println("De size van de lijst met 0 is: "+linkedList.size());
        linkedList.add("item 1");
        System.out.println("De size van de lijst met 1 is: "+linkedList.size());
//        System.out.println(linkedList.get(0));
        linkedList.add("item 2");
        linkedList.add("item 3");
//        System.out.println(linkedList.get(2));
        System.out.println("De size van de lijst met 3 is: "+linkedList.size());
        System.out.println("De index van item 3 moet 2 zijn en is: " + linkedList.indexOf("item 3"));
        System.out.println("De index van item 1 moet 0 zijn en is: " + linkedList.indexOf("item 1"));

        System.out.println();
        System.out.println("Nu gaan we alle items uit de lijst uitprinten:");
        for(Object s : linkedList.toArray()){
            System.out.println((String)s);
        }
        System.out.println();

        System.out.println("Nu gaan we alle items printen met de andere toArray methode:");
        String[] arr = new String[4];
        for(String s : linkedList.toArray(arr)){
            System.out.println(s); //Omdat we de arr array al mee geven in de parameter, hoeven we s niet meer naar String te casten
        }
        System.out.println();

        System.out.println("item 3 moet verwijderd worden " + linkedList.remove(2));
        System.out.println("Is de remove succesvol? " + linkedList.remove("item 2"));
        System.out.println("Is de remove nog een keer succesvol? " + linkedList.remove("item 2"));

    }
}