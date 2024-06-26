public class Node<T> {
    Node<T> next;
    T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node(){}

    //We gebruiken hier een recursieve methode (een methode die zichzelf aanroept).
    //Bij recursie is het heel belangrijk om wel een "terminating condition" te hebben, want anders loopt het oneindig door.
    //In deze methode is de null check de terminating condition. Er komt altijd een punt waar er geen null is en dan roep je niet meer next.add() aan, maar voeg je een nieuwe Node toe met de juiste waarde (T)
    public boolean add(T b){
        if(next == null){
           next = new Node(null, b);
            return true;
        } else {
            return next.add(b);
        }
    }

    //De terminating condition in deze methode is wanneer de index 0 is.
    //We zorgen dat we de terminating condition uiteindelijk altijd bereiken door de next.get met (index -1) aan te roepen.
    //Hoewel... wat gebeurt er als we in main "linkedList.get(-1)" doen?
    public T get(int index) {
        if(index == 0){
            return this.value;
        } else {
            return next.get(index - 1);
        }
    }

    public int size() {
        if(next == null){
            return 1;
        } else {
            return 1 + next.size();
        }
    }

    public T set(T t) {
        this.value = t;
        return value;
    }

//    public T add(int index, T t) {
//
//    }
}
