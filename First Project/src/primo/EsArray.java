package primo;

import java.util.Arrays;

public class EsArray {
  public static void main(String[] args) {
    
    int[] v = new int[10]; // tutti inizializzati a 0

    int i = v[0];

    for(int j=0; j<v.length; ++j){
        v[j] = j;
    }

    System.out.println(v); // [I@7344699f

    System.out.println(Arrays.toString(v));

    Arrays.sort(v);

    //i = v[-1];

    double[][] matrice; // array-of array-of double

    matrice = new double[2][2]; // matrice quadrata
    matrice[0][0] = 3.1415;
    double[] riga = matrice[1];

    matrice = new double[2][];
    // +------+
    //0| null |
    // +------+
    //1| null |
    // +------+
   
    matrice[0] = new double[10];
    matrice[1] = new double[2];

    // +------+
    //0| o----+----> | | | ...| |
    // +------+
    //1| o----+----> | 0.0 | 0.0 |
    // +------+


  }
}
