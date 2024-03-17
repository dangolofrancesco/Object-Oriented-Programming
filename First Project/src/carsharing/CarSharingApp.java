package carsharing;

public class CarSharingApp {

    public static void main(String[] args){

        int i = 0;

        i+=5;


        Posto p = new Posto(Posto.ANTERIORE, Posto.Lato.SINISTRO, true);
        // il REFERENCE "p" punta all'oggeto di tipo "Posto"
        // che è stato creato dall'operartore "new"

        // inzializzare gli attributi
        // p.lato = Posto.SX;
        // p.posizione = Posto.ANTERIORE;
        // p.conducente = true;
        // // Pro/ contro
        // p.init(Posto.ANTERIORE, Posto.SX, true);
        
        System.out.println("Occupato: " + p.eOccupato());

        p.prenota("Marco");

        System.out.println("Occupato: " + p.eOccupato());

        Posto q = new Posto(Posto.ANTERIORE, Posto.Lato.SINISTRO, true);

        System.out.println("Occupato: " + q.eOccupato());

        q.prenota("Mario");

        System.out.println("Occupato: " + q.eOccupato());

        System.out.println(p.descrivi());

        // usando i metodi getter

        System.out.println("Lato: " + p.getLato());
        System.out.println("Posizione: " + p.getPosizione());

        Posto.quantiPosti();

        Math.sqrt(4);


        Posto r = new Posto(Posto.ANTERIORE, Posto.Lato.CENTRO);

        // ---
        Posto alias = p; // copia il riferimento: ALIASING

        if( p == alias ){ // confronta i riferimenti
            System.out.println("SI");
        }else{
            System.out.println("NO");
        }

        Integer ig = new Integer(42);
        Integer j = Integer.valueOf(42);
    }
}
