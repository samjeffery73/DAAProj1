package projectthree;

import java.io.*;
import java.util.*;




public class Sudoku {
    final long startTime = System.nanoTime();

    List<Variable> list = new ArrayList<>();

    int rows;

    int gridSize;

    int columns;

    int value;

    int variablesNeeded;

    Scanner fileScan;

    Scanner intScan;
    String line;








    public Sudoku(String filename) throws FileNotFoundException {

        // HARDCODED FILEPATH!! TODO
        File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/src/main/java/projectthree/" + filename);


        try {
            fileScan = new Scanner(readFile);
            intScan = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        gridSize = fileScan.nextInt(); // let gridsize be i

        rows = gridSize * gridSize; // rows be j

        columns = rows; // columns be k

        variablesNeeded = rows * rows * rows;

        System.out.println("Sudoku is size: " + rows + " x " + columns);

        toString();




        // skipping initial parameters
        for (int i = 0; i < 2; i++) {

            value = intScan.nextInt();
        }



        while (intScan.hasNext()) {

            value = intScan.nextInt();



        }




    }

    /**
     * Create a SAT from this Sudoku puzzle.
     */

    private void toSAT(Variable v) {

        rowClauses(v);
        colClauses(v);
        boxClauses(v);

    }

    /**
     * Create clauses based on row restrictions.
     */
    private void rowClauses(Variable v) {
// TODO build this algo
        // For each number 1-9, we will have clauses that make sure that number is included.
        // For each number 1-9, we will have clauses that assures there is only one present.


    }

    /**
     * Create clauses based on column restrictions.
     */
    private void colClauses(Variable v) {

        // For each number 1-9, we will make sure that each number is included.
        // For each number 1-9, we will make sure that there is only one present.

    }

    /**
     * Create clauses based on box restrictions.
     *
     */
    private void boxClauses(Variable v) {

        // For each number 1-9 we will make sure each number is included.

        // For each number 1-9, we will make sure that there is only one present in each box.


    }




    public String toString() {


        for (int i = 0; i < 2; i++) {

            line = fileScan.nextLine();
        }

        System.out.println("Full Sudoku:");

        while (fileScan.hasNext()) {


            line = fileScan.nextLine();


            System.out.println(line);


        }

        fileScan.close();

        return "";


    }




}





