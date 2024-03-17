package carsharing;

// #define ANTERIORE 'A'
// #define POSTERIORE 'P'

public class Posto {
    static final char ANTERIORE = 'A';
    static final char POSTERIORE = 'P';

    enum Lato {
        SINISTRO, CENTRO, DX
    }
    // static final int SX = -1;
    // static final int CENTRO = 0;
    // static final int DX = 1;

    // field / attribute
    private char posizione; // 'A' | 'P'
    private Lato lato; // SX | CENTRO | DX
    private boolean conducente = false;

    private String nomePasseggero;
    private boolean occupato;

    private static int numeroPosti = 0;

    public static int quantiPosti(){  // metodo di classe, senza 'this'
        return numeroPosti;
    }

    // public Posto(){} // C-tor di default

    /**
     * Costruttore di posto
     * 
     * @param posizione posizione ANTERIORE o POSTERIORE
     * @param lato SX DX o CENTRALE
     * @param conducente se è un il posto del conducente
     */
    public /* niente ritorno */ Posto(char posizione, Lato lato, boolean conducente){
        this.conducente = conducente;
        this.lato = lato;
        this.posizione = posizione;

        numeroPosti += 1;
        System.out.println("Creato Posto #" + numeroPosti);
    }

    // OVERLOADING
    public Posto(char posizione, Lato lato){
        this.conducente = false;
        this.lato = lato;
        this.posizione = posizione;

        numeroPosti += 1;
        System.out.println("Creato Posto #" + numeroPosti);
    }

    // method / operation
    /**
     * Serve per prenotare il posto an nome di un passeggero
     * 
     * @param passeggero il nome del passeggero
     */
    public void prenota(String passeggero){
        // + parametro implicito "speciale": this

        this.nomePasseggero = passeggero;
        this.occupato = true;
    }
    
    public boolean eOccupato(){
        return occupato;
    }

    // GETTER
    public Lato getLato(){
        return lato;
    }

    public char getPosizione(){
        return posizione;
    }

    public boolean getConducente(){
        return conducente;
    }

    /*
     * @deprecated
     */
    public String descrivi(){
        String desc = new String();  // equivale a = "";

        desc = (posizione==ANTERIORE?"Anteriore":"Posteriore");

        desc += " ";
        switch(lato){
            case SINISTRO: desc+="sinistro";
                     break;
            case DX: desc+="destro";
                     break;
            case CENTRO: desc+="centrale";
                     break;

        }

        if(conducente){
            desc += " (conducente)";
        }

        return desc;
    }

    public boolean uguale(Posto altro){
        return this.lato == altro.lato &&
                this.posizione == altro.posizione &&
                this.conducente == altro.conducente;
    }
}
