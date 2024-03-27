package test;

import lista.Lista; //import Lista dal package lista 
import lista.Lista.Iteratore;

import static org.junit.Assert.assertEquals;
//poichè mi interessa solo di importare il metodo assertEquals di quella classe
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
                                                
//import org.junit.Assert; import semplice

import org.junit.Before;
import org.junit.Test;

//import calc.Calculator;

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
        assertEquals( 0, l.dimension()); //con import static (preferibile!!)

        //Assert.assertEquals(0, l.dimensione()); //import semplice 
    }

    @Test
    public void testInserimento(){
        //Lista l = new Lista();

        l.add( 3 );

        assertEquals(1, l.dimension()); //mi aspetto che l abbia dimensione 1

        l.add(3);

        assertEquals(2, l.dimension()); //mi aspetto che l abbia dimensione 1

    }

    /*     @Test
    public void testScansione(){
        l.add( 5 );
        // tradotto con autoboxing in:
        l.add( Integer.valueOf(5) );
        l.add( 4 );
        l.add( 3 );

        Number v = l.primo();
        assertEquals( Integer.valueOf(3), v);

        v = l.prossimo();
        assertEquals( Integer.valueOf(4), v);

        v = l.prossimo();
        assertEquals( Integer.valueOf(5), v);
    }

    @Test
    public void testScansioneLoop(){
        l.add( 5 );
        l.add( 4 );
        l.add( 3 );


        Number v = l.primo();
        assertEquals( 3, v);

        while( !l.fine() ){
            v = l.prossimo();
        }
    }*/

    @Test 
    public void testScansioneIteratore(){
        //Lista l = new Lista(); // poichè questo pezzo viene eseguito in tutti i test, è la FIXTURE, 
        //quindi la inserisco nel pre-test, perchè è un'operazione che deve essere eseguita prima di ogni test 
        
        l.add(5);
        l.add(4);
        l.add(3);

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
