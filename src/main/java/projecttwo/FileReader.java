/**
 * Formula.java
 * @author SJ
 * @date 4 Feb. 2023
 *
 * A formula is a list of clauses. This is currently NOT what it is in my project.
 *
 * The Formula class reads the file, gets the parameters (# of variables and # of statements)
 * And then creates a Clause object, which is actually just a FORMULA. Which I have come to realize. This project is
 * a mess at the moment.
 *
 * This class will likely be renamed.
 *
 *
 */


package projecttwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;




public class FileReader {

    int vars;
    int statements;
    int next;
    String filename;
    Scanner fileScan = null;
    String line;

    boolean truth = false;

    List<Variable> fullList = new ArrayList<Variable>();



    public FileReader(String filename) throws FileNotFoundException {

     checkFile(filename);
     Formula f = new Formula(null);



    }

    public void checkFile(String filename) throws FileNotFoundException {

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


        System.out.println("List of ALL values in file " + filename + ".cnf " + fullList);


        System.out.println("Making clauses...");
        Formula f = new Formula(fullList);


    }

    public void setParams() {

        String[] nums = line.replaceAll("^\\D+","").split("\\D+");

        vars = Integer.parseInt(nums[0]);

        statements = Integer.parseInt(nums[1]);

        System.out.println("Number of variables: " + vars);
        System.out.println("Number of statements: " + statements);


    }











}






