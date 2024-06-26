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

    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public boolean contains(Object o) {
        // Deze heb ik moeten afkijken bij de java.util implementatie.
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
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

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

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
                    if(next.value.equals(0)){
                        return index;
                    }
                    next = next.next;
                }
                return index;

            }
        }
        //Wanneer het object neit gevonden wordt, returnen we -1. Dit is conventie. Zie ook ArrayList.indexOf
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
