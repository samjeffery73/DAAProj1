/**
 * Clause.java
 * @author SJ
 * @date 4 Feb. 2023
 *
 * This is the algorithm to solve project two. This algo uses stacks, and will scan each piece of the equation given,
 * determine if it is true or false, and then return if the result is SAT or NOT SAT.
 *
 * A clause, for this problem, will be a list of integers. For each clause in the statement,
 * The program will identify each one using a loop, determine if they are sat, and then move to the next clause in the file.
 *
 *
 *
 */


package projecttwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Clause {

    int vars;
    int clauses;
    String filename;
    List<Integer> list = new ArrayList<Integer>();

    // The file with the names of the e


    public Clause(String filename) throws FileNotFoundException {

     checkFile(filename);




    }

    public void checkFile(String filename) throws FileNotFoundException {

        File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/src/main/java/projecttwo/" + filename + ".cnf");

        Scanner fileScan = null;



            try {
                fileScan = new Scanner(readFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        String line = fileScan.nextLine();
            
        while (fileScan.hasNext()) {


            if (line.contains("c")) {

                line = fileScan.nextLine();
            }


                vars = fileScan.nextInt();

                clauses = fileScan.nextInt();

                System.out.print(vars);

                System.out.print(clauses);


        }
    }

    public void scanFile() {


    }







}
