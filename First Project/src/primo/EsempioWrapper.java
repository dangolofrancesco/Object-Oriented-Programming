package primo;

public class EsempioWrapper {

    public static void main(String[]args){
        {
            long t0 = System.currentTimeMillis();
            int acc = 0;
            for(int i=0; i< 1000000; ++i){
                acc += i;
            }
            System.out.println(acc);
            long t1 = System.currentTimeMillis();
            System.out.println("Elapsed int: " + (t1-t0));
        }
        {
            long t0 = System.currentTimeMillis();
            Integer acc = 0;
            for(Integer i=0; i< 1000000; ++i){
                acc += i;
            }
            System.out.println(acc);
            long t1 = System.currentTimeMillis();
            System.out.println("Elapsed Integer : " + (t1-t0));
    
        }
    }
}
