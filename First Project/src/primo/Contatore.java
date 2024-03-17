package primo;

public class Contatore {

    private int valore = 0;

    public void inc() {
        valore++;
    }

    public int valore() {
        return valore;
    }

    public void dec() {
       valore--;
    }

    public Contatore incCont(){
        valore++;
        //return null; // because it has to return a Contatore value!!
        return this; // restituisce se stesso, in modo che metodi concatenati lavorano sullo stesso oggetto!!
    }

    public Contatore decCont(){
        valore--;
        //return null; // because it has to return a Contatore value!!
        return this;
    }

    // Use null to specify the reference to no object!! It generates an exeption
    // but it doesn't block the program
}
