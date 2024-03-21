package primo;

public class Contatore {

    private int valore = 0;


    public Contatore inc() {
        valore++;
        pushOp('+');
        return this;
    }

    public int valore() {
        return valore;
    }

    public Contatore dec() {
        valore--;
        pushOp('-');
        return this;
    }

    //Ritorna se stesso in modo che metodi concatenati lavorino sullo stesso oggetto
    //null specifica che non c'è riferimento ad alcun oggetto, questo genera un'eccezione senza bloccare il programma in esecuzione

    public void undo(){
      // pesca l'ultima operazione dalla cima dello stack delle operazioni passate
      // applica al contrario l'operazione
      ops[top].undo();
    }

    Operazione[] ops = new Operazione[100];
    int top = -1;

    void pushOp(char op){
        new Operazione(op);
    }

    class Operazione { // Inner class (con link al contenitore)
        final char op;
        Operazione(char op){
            this.op = op;
            ops[++top] = this;
        }
        void undo(){
            switch (op) {
                case '+': valore--;
                    break;
                case '-': valore++;
                    break;
            }
            top--;
        }
    }
}
