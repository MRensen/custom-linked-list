import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.ListIterator;

//We implementeren hier de List interface
//Tot nu toe zijn alleen de add() en de get() methodes geimplementeerd, de rest returned nog 0, false of null

//Voel je vrij om de isEmpty(), clear(), size(), remove(), addAll(), removeAll() en set() zelf te implementeren. Ik zie graag jou uitwerking tegemoet.
//De andere methoden mag je natuurlijk ook implementeren, maar die zijn erg moeilijk. (alsof het nog niet lastig moeilijk was...)
//Ik heb er regelmatig voor moeten spieken bij de java.util.LinkedList implementatie en de java.util.List documentatie (groene comments).

//De Iterator en ListIterator methodes mag je uberhaupt laten voor wat het is. Kort gezegd zijn dat alternatieven voor een for-loop.
//Maar we zijn met deze oefening al ver genoeg buiten de scope van de cursus gegaan.

//Mijn uitwerkingen kun je vinden in de "full-implementation" branch

public class LinkedList<T> implements List<T> {
    private Node<T> node;

    public LinkedList() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
        return false;
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
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
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
