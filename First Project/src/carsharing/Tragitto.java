package carsharing;

import carsharing.Posto.Lato;

public class Tragitto {

    private String da; // = null
    private String a;
    private Ora oraPartenza;
    private Ora oraArrivo;

    //Posto posti[]; // C-like
    Posto[] posti={ // static initializer (WITH declaration)
        new Posto('A', Lato.SINISTRO, true),
        new Posto('A', Lato.DX ),
        new Posto('P', Lato.SINISTRO ),
        new Posto('P', Lato.CENTRO ),
        new Posto('P', Lato.DX ),
    };

    // def Tragitto(_self, da, a) 7/ python
    public Tragitto(String da, String a, int h0, int m0, int h1, int m1){
        this.da = da;
        this.a = a;
        oraPartenza = new Ora(h0,m0);
        oraArrivo = new Ora(h1, m1);

        posti = new Posto[5]; // array di 5 reference
                              // inizializzati a null
        char[] posizioni = {'A', 'A', 'P', 'P', 'P'}; // static initializer
        Lato[] lati = {Lato.SINISTRO,Lato.DX,Lato.SINISTRO,Lato.CENTRO, Lato.DX};
        boolean[] cond = {true, false, false, false, false};
        for(int i=0; i<posti.length; ++i){
            posti[i] = new Posto(posizioni[i], lati[i], cond[i]);
        }
        // IN ALTERNATIVA
        posti = new Posto[]{ // static initializer (not with declaration)
            new Posto('A', Lato.SINISTRO, true),
            new Posto('A', Lato.DX ),
            new Posto('P', Lato.SINISTRO ),
            new Posto('P', Lato.CENTRO ),
            new Posto('P', Lato.DX ),
        };
    }

    public int durata(){
        return oraArrivo.differenzaMinuti(oraPartenza);
    }
    /**
     * Il passeggero viene prenotato sul primo posto
     * disponibile per il tragitto (ammesso che ci sia)
     * @param nomePasseggero
     * @return se è riuscito a prenotare un posto
     */
    public boolean prenota(String nomePasseggero){
        for(int i=0; i<posti.length; ++i){
            Posto p = posti[i];
            if( ! p.eOccupato() ){
                p.prenota(nomePasseggero);
                return true;
            }
        }
        // IN ALTERNATIVA con la sintassi for-each
        for(Posto p : posti){
            if( ! p.eOccupato()){
                p.prenota(nomePasseggero);
                return true;
            }
        }
        return false;
    }

    //------------------------

    //class Ora {  // Inner class (ha il "riferimento" all'oggetto contenitore)
    static class Ora { // Nested class (non ha legami con l'oggetto contenitore)
        int ore;
        int minuti;
        public Ora(int h, int m){
            ore = h;
            minuti = m;
        }
        public String toString(){
            return ore+":"+minuti;
        }

        /**
         * 
         * Esempio:
         * Ora p = new Ora(10,20);
         * Ora a = new Ora(11:30);
         * int durata = a.differenzaMinuti(p);
         * 
         * @param inizio
         * @return
         */
        public int differenzaMinuti(Ora inizio){
            return this.ore*60+this.minuti - inizio.ore*60 - inizio.minuti;
        }

        // public int durata(){
        //     return this.ore*60+this.minuti - 
        //            (oraPartenza.ore*60 + oraPartenza.minuti);
        // }
    }
}
