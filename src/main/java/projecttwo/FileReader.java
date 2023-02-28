/**
 * FileReader.java
 * @author SJ
 * @date 4 Feb. 2023
 *
 *
 *
 * The FileReader class reads the file, gets the parameters (# of variables and # of clauses)
 * And then creates a Formula.
 *
 *
 *
 */



package projecttwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;




public class FileReader {
    final long startTime = System.nanoTime();
    int variables;
     int clauses;
    int next;

    Scanner fileScan = null;
    String line;

    List<Variable> fullList = new ArrayList<Variable>();



    public FileReader(String filename) throws FileNotFoundException {

     checkFile(filename);


    }


    /**
     *
     * Checks a file to see if its valid. If it is,
     *
     * Iterate through the file, ignoring comment lines, gathering parameters, and putting all integers into a list.
     *
     * (I know that this is slower. My project would have greatly improved if I changed this, but due to time constraints and systems being built upon this,
     * I wasn't able to fix without restructuring entire project.)
     *
     * File path is hardcoded, and should be adjusted to the given path.
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public void checkFile(String filename) throws FileNotFoundException {

        // HARDCODED FILEPATH AND FILENAME!!!
       File readFile = new File("C:/Users/njhdt/Desktop/ROWAN CS 1 BOOKS/sem3/daa_inputs/" + filename + ".cnf");


        try {
            fileScan = new Scanner(readFile);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        line = fileScan.nextLine();


        while (fileScan.hasNext()) {


            // Checking to see if there are other comment lines in the input, not just at the start.
            if (line.contains("c ")) {

                line = fileScan.nextLine();

            }

            // getting all the values

            if (line.contains("p cnf")) {

                line = line.replaceAll("[^0-9]"," ");

                line = line.replaceAll(" + ", " ");

                setParams();

                next = fileScan.nextInt();

            }

            if (fileScan.hasNextInt()) {

                fullList.add(new Variable(next));

                next = fileScan.nextInt();

            }

            else
                line = fileScan.nextLine();


        }

        fileScan.close();

        System.out.println("Solving formula " + filename);
        Formula f = new Formula(fullList, variables, clauses);



    }

    /**
     *
     * pre: p cnf line
     *
     * post: returns the parameters of the given input file.
     *
     * # of variables, and # of clauses.
     *
     */

    public void setParams() {

        String[] nums = line.replaceAll("^\\D+","").split("\\D+");

        variables = Integer.parseInt(nums[0]);

        clauses = Integer.parseInt(nums[1]);


    }











}






