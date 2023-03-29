package projecttwo;

import java.io.File;
import java.io.IOException;

public class Driver {


    public static void main(String args[]) throws IOException {


        System.out.println("Satisfiables:" + "\n ============");

      FileReader f1 = new FileReader("s5");

      FileReader f2 = new FileReader("s15");

     FileReader f3 = new FileReader("s20");

     FileReader f4 = new FileReader("s28");

      System.out.println("Non Sats: " + "\n ==============");
     FileReader uf1 = new FileReader("u15");

     FileReader uf2 = new FileReader("u20");

      FileReader uf3 = new FileReader("u27");

      FileReader uf4 = new FileReader("u29");

      FileReader uf5 = new FileReader("u30");

      FileReader uf6 = new FileReader("u32");



    }


}
