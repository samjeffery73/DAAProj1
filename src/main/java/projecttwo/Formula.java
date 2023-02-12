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




public class Formula {

    int vars;
    int statements;
    int next;
    String filename;
    Scanner fileScan = null;
    String line;

    List<Integer> intList = new ArrayList<Integer>();
    List<ArrayList<Clause>> list = new ArrayList<ArrayList<Clause>>();

    // The file with the names of the e


    public Formula(String filename) throws FileNotFoundException {

     checkFile(filename);

    }

    public void checkFile(String filename) throws FileNotFoundException {

        File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/src/main/java/projecttwo/" + filename + ".cnf");



        try {
            fileScan = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        line = fileScan.nextLine();



        if (line.contains("c ")) {
            line = fileScan.nextLine();
        }

            if (line.contains("p cnf")) {

                line = line.replaceAll("[^0-9]"," ");

                line = line.replaceAll(" + ", " ");

                setParams();

                next = fileScan.nextInt();


        }

            while (fileScan.hasNext()) {

                intList.add(next);

                next = fileScan.nextInt();


            }



        System.out.println(intList);
            System.out.println("Making clauses...");
            makeClauses();


    }

    public void setParams() {

        String[] nums = line.replaceAll("^\\D+","").split("\\D+");

        vars = Integer.parseInt(nums[0]);

        statements = Integer.parseInt(nums[1]);

        System.out.println("Number of variables: " + vars);
        System.out.println("Number of statements: " + statements);


    }

    public void makeClauses() {

        Clause c = new Clause(intList);




        }


    }






