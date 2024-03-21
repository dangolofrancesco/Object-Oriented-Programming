package lista.test;

import lista.Lista; //import Lista dal package lista 

import static org.junit.Assert.assertEquals; //import static serve per importare un elemento statico di una classe, 
                                                //poichè mi interessa solo di importare il metodo assertEquals di quella classe

//import org.junit.Assert; import semplice

import org.junit.Before;
import org.junit.Test;

import calc.Calculator;

public class TestLista {  // cliccando sulla doppia freccia qui esegue tutti i test della classe

    private Lista l; // la definisco esterna ai test in modo che tutti i test la vedono

    @Before
    public void setUp(){ // setUp viene utilizzato solitamente come nome per i Before
        //FIXTURE pre-test
        l = new Lista();
    }

    @Test 
    public void testNuovaLista(){
        //Lista l = new Lista();
        assertEquals( 0, l.dimensione()); //con import static (preferibile!!)

        //Assert.assertEquals(0, l.dimensione()); //import semplice 
    }

    @Test
    public void testInserimento(){
        //Lista l = new Lista();

        l.aggiungi( 3 );

        assertEquals(1, l.dimensione()); //mi aspetto che l abbia dimensione 1

        l.aggiungi(3);

        assertEquals(2, l.dimensione()); //mi aspetto che l abbia dimensione 1

    }

    @Test 
    public void testScansioneIteratore(){
        //Lista l = new Lista(); // poichè questo pezzo viene eseguito in tutti i test, è la FIXTURE, 
        //quindi la inserisco nel pre-test, perchè è un'operazione che deve essere eseguita prima di ogni test 
        
        l.aggiungi(5);
        l.aggiungi(4);
        l.aggiungi(3);

        /*int v = l.getFirst();
        assertEquals(3,v);

        int v = l.getNext();
        assertEquals(4,v);

        int v = l.getNext();
        assertEquals(5,v);
        */

        Iteratore it1 = l.iteratore();

        while (it1.esisteProssimo() ){
            Iteratore it2 = l.iteratore();
            while (it2.esisteProssimo()){
                primo = it1.prossimo;
            }
        }
        //finire di vedere!!
    }
}
