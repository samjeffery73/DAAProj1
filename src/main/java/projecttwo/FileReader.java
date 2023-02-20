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
    String filename;
    Scanner fileScan = null;
    String line;

    boolean truth = false;

    List<Variable> fullList = new ArrayList<Variable>();



    public FileReader(String filename) throws FileNotFoundException {

     checkFile(filename);


    }

    public void checkFile(String filename) throws FileNotFoundException {

        // hardcoded Filename, needs revision
       //File readFile = new File("C:/Users/njhdt/Desktop/ROWAN CS 1 BOOKS/sem3/daa_inputs/" + filename + ".cnf");
       File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/src/main/java/projecttwo/" + filename + ".cnf");

        try {
            fileScan = new Scanner(readFile);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        line = fileScan.nextLine();


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

        while (fileScan.hasNext()) {

            fullList.add(new Variable(next));
            next = fileScan.nextInt();

        }


        System.out.println("Generating formula...");
        Formula f = new Formula(fullList, variables, clauses);



    }

    public void setParams() {

        String[] nums = line.replaceAll("^\\D+","").split("\\D+");

        variables = Integer.parseInt(nums[0]);

        clauses = Integer.parseInt(nums[1]);


    }











}






