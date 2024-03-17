package primo;

public class EsChain {
    public static void main(String[] args){

        // TDD: Test-Driven Development

        Contatore c = new Contatore();

        c.inc();

        verifica( c.valore() == 1 , "Mi aspettavo 1");

        c.dec();

        verifica( c.valore() == 0 , "Mi aspettavo 0");

        // method chaining

        c.inc().dec().inc().inc();

        verifica( c.valore() == 2 , "Mi aspettavo 2");

    }

    public static void verifica(boolean b, String msg){
        if(b){
            System.out.println(".");
        }else{
            System.out.println("Errore: " + msg);
        }
    }
}
