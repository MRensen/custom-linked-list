import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.ListIterator;

//We implementeren hier de List interface
//Tot nu toe zijn alleen de add() en de get() methodes geimplementeerd, de rest returned nog 0, false of null
//Voel je vrij om de isEmpty(), size(), contains(), remove() en set() zelf te implementeren. Ik zie graag jou uitwerking tegemoet.
//De andere methoden mag je natuurlijk ook implementeren, maar die zijn nog wat lastiger.
//De Iterator en ListIterator methodes mag je laten voor wat het is. Kort gezegd zijn dat alternatieven voor een for-loop.
public class LinkedList<T> implements List<T> {
    private Node<T> node;

    public LinkedList() {

    }

    private LinkedList(Node<T> start) {
        this.node = start;
    }

    @Override
    public int size() {
        // iteratieve implementatie:
        int counter = 0;
        //Als node null is, dan is de list leeg. -> return 0
        if(node != null) {
            counter++; //Als de node niet null is, dan is de lijst minstens 1 lang -> ++
            Node next = node.next;
            while (next != null) { //Kijk vervolgens net zo vaak naar de volgende node van de volgende node, totdat er geen volgende node meer is. Elke keer verhoog je de counter met 1.
                next = next.next;
                counter++;
            }
        }
        return counter;

//        // recursieve oplossing
//        if(node != null) {
//            return node.size();
//        } else {
//            return 0;
//        }
    }

    //Als er geen Node is, dan is de lijst leeg.
    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public boolean contains(Object o) {
        // Deze heb ik moeten afkijken bij de java.util implementatie.
        return indexOf(o) >= 0;
    }

    //Niet geimplementeerd
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[node.size()];
        //Een magische loop die loopt door de nodes.
        //Weet je nog wat de definitie van een for loop is? for(startpunt; wanneer de loop stopt; hoe de loop verder gaat)
        for(Node<T> n = node; n != null; n = n.next){
            array[i] = n.value;
            i++;
        }
        return array;
    }

    //Deze methode maakt gebruik van de generic T1 ipv T.
    //Dat zijn twee verschillende variabalen, dus daar zullen we rekening mee moeten houden (ik heb hiervoor gespiekt bij de util.LinkedList implementatie)
    @Override
    public <T1> T1[] toArray(T1[] a) {
        int i = 0;
        Object[] myArray = a; //We maken hier een object array, zodat we daarin de values kunnen zeten
        //Een magische loop die loopt door de nodes.
        //Weet je nog wat de definitie van een for loop is? for(startpunt; wanneer de loop stopt; hoe de loop verder gaat)
        for(Node<T> n = node; n != null; n = n.next){
            myArray[i] = n.value;
            i++;
        }
        //Uiteindelijk wordt a weer gereturned en niet myArray, omdat a van type T1 is en myArray niet. De methode definitie zegt dat we een array van type T1 moeten returnen
        return a;
    }

    // Als er nog geen node is, dan wordt hier de eerste node gevuld
    // Als er al wel een node is, dan wordt hier de add methode van node aangeroepen.
    @Override
    public boolean add(T t) {
        if(node == null){
            node = new Node<>(null, t);
            return true;
        } else {
            return node.add(t);
        }
    }

    @Override
    public boolean remove(Object o) {
        //We loopen door de nodes heen, waarbij we de huidige en de vorige Node bijhouden.
        Node<T> toRemove = node;
        Node<T> previous = null;

        //We maken hier een while(true) loop, wat gevaarlijk is, dus daar moeten we heel bewust mee omgaan.
        while(true){
            //Dit eerste if-statement zorgt er voor dat we niet oneindig blijven loopen.
            if(toRemove == null){
                return false;
            }
            //Als het object uit de parameter is gevonden als value van de Node, dan willen we die Node verwijderen
            if(toRemove.value.equals(o)){
                if(previous == null){
                    node = node.next;
                    return true;
                }
                previous.next = toRemove.next; // zorg dat de next van de vorige wijst naar de next van de huidige. Daarmee haal je effectief de huidige Node tussen de lijst uit en is het verwijderd. (het maakt niet uit of de volgende node null is)
                return true;
            }
            //Als het object uit de parameter niet gevonden is, dan kijken we of het in de volgende Node wel gevonden wordt. (tot
            previous = toRemove;
            toRemove = toRemove.next;
        }
    }

    //Hier maken we simpelweg een for-loop waarin we de contains methode aanroepen voor elk item in de collectie van de parameter
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!this.contains(o)){
                return false;
            }
        }
        return true;
    }

    //Hier maken we een for loop waarin we de add methode aanroepen
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T t : c){
            this.add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int i = index;
        for(T t : c){
            add(i, t);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean allSucces = true;
        for(Object o : c){
            //Met dit if statment voer je sowieso de remove uit, maar als het false is, dan doe zet je ook de allSucces op false. Waarmee je aangeeft dat niet alles succesvol kon worden verwijderd.
            if(!this.remove(o)) {
                allSucces = false;
            }
        }
        return allSucces;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        node = null;
    }

    //Als er geen node is, kun je daar ook niet de get methode van aanroepen. Daarom returnen we dan null
    //Als er wel een node is, roepen we we daar de get methode van aan.
    @Override
    public T get(int index) {
        if(node == null){
            return null;
        }
        return node.get(index);
    }

    //Het verschil tussen set(index, element) en add(index, element) is dat set de waarde op de index positie vervangt en add de waarde op de index positie toeveogd en dus alles wat erachter komt opschuift.
    @Override
    public T set(int index, T element) {
        int i = index;
        Node<T> parent;
        Node<T> current = node;
        while(i>0){ //itereer door de nodes, tot je bij de juiste index bent
            parent = current;
            current = current.next;
        }
        //Doe dan add(element)
        return current.set(element);

    }

    @Override
    public void add(int index, T element) {
        int i = index;
        Node<T> parent;
        Node<T> current = node;
        while(i>0){ //itereer door de nodes, tot je bij de juiste index bent
            parent = current;
            current = current.next;
        }
        //Doe dan set(element)
        current.add(element);
    }

    @Override
    public T remove(int index) {
        Node<T> target = node;
        Node<T> previous = null;
        for(int i = index; i>0; i--){
            previous = target;
            target = target.next;
        }
        // verzamel eerst de informatie die bewaard moet blijven, voor je het verwijderd.
        T valueToReturn = target.value; //Deze moet uiteindelijk returned worden
        Node<T> nodeToDelete = target; //Deze gaan we verwijderen.
        previous.next = target.next; //Maar voordat we het verwijderen, moeten we eerst een de link door trekken naar de volgende (het maakt niet uit of dat null is).

        // nodeToDelete.delete() //We hoeven deze Node niet expliciet te verwijderen.
        // Als we zorgen dat deze instance niet meer gebruikt wordt en er niet meer naar gelinkt wordt, dan zal de JVM het zelfstandig opruimen met de "garbage collector"

        return valueToReturn;
    }

    @Override
    public int indexOf(Object o) {
        if(node != null) {
            int index = 0; //Hier houden we bij welke indexen we al gehad hebben.

            if (o.equals(node.value)) {
                return index;
            } else {
                Node<T> next = node.next;
                while (next != null){
                    index++;
                    if(next.value.equals(o)){
                        return index;
                    }
                    next = next.next;
                }
                return -1;

            }
        }
        //Wanneer het object neit gevonden wordt, returnen we -1. Dit is conventie. Zie ook ArrayList.indexOf
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int indexFound = -1; //Initieer de gevonden index als -1, voor het geval er niks gevonden wordt.
        for(Node<T> n = node; n != null; n = n.next){
            if(n.value.equals(o)){
                //Elke keer als je het object vind, overschrijf je de gevonden index met de huidige index.
                indexFound = index++;
            } else {
                index++;
            }
        }
        return indexFound; //returnt de laatst gevonden index, of -1 als er niks gevonden is.
    }

    //niet geimplementeerd
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    //niet geimplementeerd
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        //Hier houden we de nodes bij. Een start node, de end node, en het begin van de nieuwe sublist node.
        Node<T> start = node;
        Node<T> startCont;
        Node<T> end;

        if(node == null){
            return new LinkedList<>();
        }

        //Als eerst gaan we de start node op het begin van de sublist zetten.
        for(int i = fromIndex; i > 0; i--){
            start = start.next;
        }

        //Hier maken we een kopie van de Nodes tussen fromIndex en toIndex en returnen die kopie uiteindelijk als de sublist
        //Daarbij beginnen we bij de reeds gevonden start node en gaan we door tot we bij de toIndex zijn
        startCont = new Node<>(null, start.value);
        end = startCont;
        for(int i = fromIndex; i < toIndex-1; i++){ // toIndex -1, omdat het exclusief de toIndex is en inclusief de from Index is, volgens de documentatie.
            if(start.next != null) {
                start = start.next;
                startCont.next = new Node<>(null, start.value);
                startCont = startCont.next;
            }


            }

        return new LinkedList<T>(end);
    }

    //Als extra nog deze toString, om makkelijk de lijst te printen
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if(node == null){
            return "[]";
        }
        for(Node<T> n = node; n != null; n = n.next) {
            sb.append(n.value + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
