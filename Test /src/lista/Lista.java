package lista;


//Esiste già una classe lista in Java, questa riprende alcuni concetti 

//In Java esiste sia una linked list sia un array list 

public class Lista {

    private /*static: classe annidata */ class Elemento{ //inner class, quando vado acreare un nuovo oggetto elemento, questo ha un riferimento alla classe lista che l'ha creato e quindi può usare attributi e metodi di lista
        public Elemento(int i) {
            valore = i;
                //completare!!
            Elemento next 
        }
        int valore;
        Elemento next;
        
    }

    private Elemento head;

    private int dimensione; //contatore elementi della lista 

    private Elemento current;


    public int dimensione(){
        return dimensione;
    }

    public void aggiungi(int i) {
        head = new Elemento(i);
    }

    public int getFirst() {
        this.current = head;
        return head.valore;
    }

    public int getNext() {
        current = current.next;
        return current.valore;
    }

    public boolean fine() {
        return current.next = null;
    }

    public class Iteratore{
            
    }
}
