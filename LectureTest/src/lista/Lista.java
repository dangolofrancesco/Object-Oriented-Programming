package lista;

//Esiste già una classe lista in Java, questa riprende alcuni concetti 

//In Java esiste sia una linked list sia un array list 

public class Lista {

    private /*static: classe annidata */ class Elemento{ //inner class, quando vado acreare un nuovo oggetto elemento, questo ha un riferimento alla classe lista che l'ha creato e quindi può usare attributi e metodi di lista
        //Costruttore della lista 
        public Elemento(int i) {
            value = i;
            next = head;
        }
        int value;
        Elemento next;    
    }

    private Elemento head;
    private int dimension; //contatore elementi della lista 
    private Elemento current;

    public int dimension(){
        return dimension;
    }

    public void add(int i) {
        head = new Elemento(i);
    }

    public int getFirst() {
        this.current = head;
        return head.value;
    }

    public int getNext() {
        current = current.next;
        return current.value;
    }

    public boolean end() {
        return current.next==null;
    }

    public class Iteratore{ //Inner class
        private Elemento next = head;

        //Cosnstructor of the inner class Iteratore
        public Iteratore(){
            this.next = head;
        }

        public boolean existNext(){
            return next != null;
        }
        
        public int next(){
            int value = next.value;
            next = next.next;
            return value;
        }
    }

    public Iteratore iteratore(){
        return new Iteratore();
    }


}
