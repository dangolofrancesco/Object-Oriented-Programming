package primo;

public class Contatore {

    private int valore = 0;


    public Contatore inc() {
        valore++;
        return this;
    }

    public int valore() {
        return valore;
    }

    public Contatore dec() {
        valore--;
        return this;
    }

}
