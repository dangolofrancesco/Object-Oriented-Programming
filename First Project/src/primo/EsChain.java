package primo;

public class EsChain {

    public static void main(String[] args){

        // TDD: Test-Driven Development 
        //Faccio prima lo sviluppo del test e poi creo la classe
        // classe e metodi possono essere creati utilizzando quickfix
        /*
         * Test that suggests us how the program has to work properly
         * Then we will create the class with its methods and attributes
         */
        Contatore c = new Contatore();

        c.inc(); //increementa 

        if (c.valore() == 1){
            System.out.println("OK");
        }else{
            System.out.println("ERRORE");
        }

        //Altro modo per l'if !!
        //assert(c.valore == 1);

        verifica(c.valore()==1, "Mi aspettavo 1");

        c.dec(); //decrementa 

        verifica(c.valore()==0, "Mi aspettavo 0");

        // Method Chaining 

        c.incCont().decCont().incCont().incCont(); //per ottenere questo, i metodi inc e dec devono restituire contatore
        /*In questo modo, ogni chiamata a metodo crea un nuovo oggetto
        quindi, vengono creati 4 oggetti diversi e ciascuna chiamat 
        agisce sul proprio!!
        Per ovviare al problema, i metodi devono restituire "this", ossia 
        l'oggetto di quel metodo
        */ 

        verifica(c.valore()==2, "Mi aspettavo 2");


    }

    public static void verifica(boolean b, String msg){
        if (b){
            System.out.println(".");
        }else{
            System.out.println("Errore: " + msg);
        }
    }
}
