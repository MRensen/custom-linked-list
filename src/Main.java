import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("string1");
        arrayList.add("string2");
        arrayList.add("string3");
        arrayList.add("string4");
        arrayList.add("string5");
        arrayList.add("string6");
//        arrayList.set(3, "3");

        //De linkedList kunnen we als een List opslaan, omdat we in LinkedList de List interface hebben geimplementeerd
        List<String> linkedList = new LinkedList<>();
        System.out.println("Is de lijst empty? " + linkedList.isEmpty());
        System.out.println("De size van de lijst met 0 is: "+linkedList.size());
        linkedList.add("item 1");
        System.out.println("De size van de lijst met 1 is: "+linkedList.size());
        System.out.println("Is de lijst nu empty? " + linkedList.isEmpty());
        System.out.println("Zit item 1 nu in de lijst? "+ linkedList.contains("item 1"));
//        System.out.println(linkedList.get(0));
        linkedList.add("item 2");
        linkedList.add("item 3");
//        System.out.println(linkedList.get(2));
        System.out.println("De size van de lijst met 3 is: "+linkedList.size());
        System.out.println("De index van item 3 moet 2 zijn en is: " + linkedList.indexOf("item 3"));
        System.out.println("De index van item 1 moet 0 zijn en is: " + linkedList.indexOf("item 1"));
        System.out.println("Zitten alle items in de lijst? " + linkedList.containsAll(List.of("item 1", "item 2", "item 3")));
        System.out.println("Zitten er ook niet bestaande items in de lijst? " + linkedList.containsAll(List.of("item 1", "item 2", "item 4")));
        System.out.println("Zit item 3 in de lijst? " + linkedList.contains("item 3"));
        System.out.println("Zit item 4 in de lijst? " + linkedList.contains("item 4"));

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
        System.out.println("Zit item 3 nu in de lijst? " + linkedList.contains("item 3"));
        System.out.println("Is item 2 ook verwijderd? " + linkedList.remove("item 2"));
        System.out.println("Is de remove van item 2 nog een keer succesvol? " + linkedList.remove("item 2"));

        System.out.println();
        linkedList.add("item 2");
        linkedList.add("item 3");

        System.out.println("Item 2 en 3 staan weer in de lijst. " + linkedList.containsAll(List.of("item 3", "item 2")));
        System.out.println("Kunnen we ze ook weer in bulk verwijderen? " + linkedList.removeAll(List.of("item 3", "item 2")));

        System.out.println();
        linkedList.add("item 2");
        linkedList.add("item 3");
        linkedList.add("item 2");
        linkedList.add("item 3");

        System.out.println("Item 2 en 3 staan er nu dubbel in. Wat is de index van de eerste item 2? " + linkedList.indexOf("item 2"));
        System.out.println("Wat is de index van de laatse item 2? " + linkedList.lastIndexOf("item 2"));

        linkedList.clear();
        System.out.println();
        System.out.println("Print de hele lijst");
        System.out.println(linkedList);

        System.out.println();
        System.out.println("Oeps! De lijst is leeg. We proberen het nog eens");

        for(int i = 1; i <= 6; i++) {
            linkedList.add("item " + i);
        }
        System.out.println("Print de hele lijst");
        System.out.println(linkedList);

        List<String> subList = linkedList.subList(2, 4);

        System.out.println("Print de sublist van index 2 tot 4");
        System.out.println(subList);

    }

}