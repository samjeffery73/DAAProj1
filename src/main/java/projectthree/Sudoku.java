package projectthree;

import java.io.*;
import java.util.*;




public class Sudoku {
    final long startTime = System.nanoTime();
    int clauses;

    int rows;

    int gridSize;

    int columns;

    int value;

    int variablesNeeded;

    Scanner fileScan;

    Scanner intScan;








    public Sudoku(String filename) throws FileNotFoundException {

        // HARDCODED FILEPATH!! TODO
       //  LAPTOP!!!!  File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/out/" + filename);
        File readFile = new File("C:/Users/njhdt/Desktop/ROWAN CS 1 BOOKS/sem3/daa_inputs/inputs/" + filename);


        try {
            fileScan = new Scanner(readFile);
            intScan = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        gridSize = fileScan.nextInt(); // let gridsize be h

        rows = gridSize * gridSize; // rows be i

        columns = rows; // columns be j

        variablesNeeded = rows * rows * rows;
        

        System.out.println("Sudoku is size: " + rows + " x " + columns);

        toString();

        try {

            File cnfFile = new File("sudokuCNF.cnf");

            cnfFile.createNewFile();


        }



        catch(IOException e) {
            System.err.println("Error.");
        }

        toSAT();














    }

    /**
     * Create a CNF SAT formula from this Sudoku puzzle.
     */

    private void toSAT() {

        try {

            File cnfFile = new File("sudokuCNF.cnf");

            cnfFile.createNewFile();

            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);

           // cnfWriter.write("c This is the cnf file.");
          //  cnfWriter.write("\np cnf " + variablesNeeded + "\n");

            cnfWriter.append("c LITERALS\n");


            cnfWriter.close();
            cnfWriter.flush();


        }


        catch(IOException e) {
            System.err.println("Error.");
        }

        // skipping initial parameters
        for (int i = 0; i < 2; i++) {

            value = intScan.nextInt();
        }



        int r = 1; // starting row number
        int c = 1; // starting column number

        while (intScan.hasNext()) {

            value = intScan.nextInt();

            if (c > columns) {

                r++;
                c=1;
            }

            Variable v = new Variable(r, c, value);

            if (v.value > 0) {

                literalClause(v);

            }

            c++;

        }

        setConstraints();


        System.out.println("Done");





    }

    /** Create clauses for positive literals.
     * I.e.
     * Given row 1 column 1, value 5
     *
     * Add a singular clause "115 0"
     *
     *
     * @param  v (a positive literal) ( a true variable )
     */
    private void literalClause(Variable v) {


        // Creating single clauses from each variable given.

        try {

            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);



            cnfWriter.append(v.ijk + " 0\n");



            negate(v);

            clauses++;

            cnfWriter.flush();

            cnfWriter.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

    /**
     * Given one POSITVE LITERAL variable x<i,j,k>,
     *
     * Negate variables that are NOT POSSIBLE.
     *
     * @param v
     */

    private void negate(Variable v) {

        int rowLoc = v.rowLoc;
        int kValue = v.value;


        String s = "";




        try {

            FileWriter cnfWriter = new FileWriter ("sudokuCNF.cnf", true);



            for (int j = 1; j <= columns; j++) {

                if (j == v.colLoc) {

                    j++;

                }


                cnfWriter.append("-" + ((rowLoc * 100) + (j *10) + kValue + " 0\n"));

                cnfWriter.flush();

            }

            cnfWriter.close();


        }

        catch (IOException e) {

        }
    }

    /**
     *
     * Set up sudoku constraints by creating clauses
     * Each method has a sub-method for AT-MOST clauses.
     */
    private void setConstraints() {

        rowClauses();

        cellClauses();

        colClauses();

        gridClauses();


    }

    /**
     * Create clauses based on Row Restrictions.
     * AT LEAST one value in each cell.
     *
     *
     */
    private void rowClauses() {

        String s = "";

        try {


            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);
            cnfWriter.append("c ROW CLAUSES\n");


            for (int k = 1; k <= columns; k++) {

                for (int i = 1; i<=rows; i++) {

                    for (int j = 1; j <= columns; j++) {

                        s = i + "" + j + "" +  k + "" + " ";
                        cnfWriter.append(s);
                        cnfWriter.flush();

                    }

                    cnfWriter.append("0\n");
                    cnfWriter.flush();

                    clauses++;

                }


            }


            cnfWriter.close();


        }


        catch (IOException e) {


        }





        atMostRow();




    }
    /**
     * Create clauses based on Row Restrictions.
     *
     * AT MOST one of each value in a row.
     *
     *
     */

    private void atMostRow() {

        int next;


        try {


            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);


            for (int i = 1; i<= rows; i++) {

                for (int k = 1; k<= columns; k++) {

                    for (int j = 1; j<= columns; j++) {

                        int start = ((i * 100) + (j * 10) + k) * -1; // start = 111;



                        for (int z = 1; z<= columns; z++) {

                            if (z <= j) {

                                z = j+1;

                                if (z == 10) {

                                    break;
                                }

                            }


                            next = ((i * 100) + (z * 10) + k) * -1;



                            for (int x = 0; x < 1; x++) {

                                if (next == -999 && start == -999) {

                                    break;
                                }

                                cnfWriter.append(start + " " + next + " 0\n");
                                cnfWriter.flush();
                                clauses++;




                            }

                        }

                    }

                }


            }





        }

        catch (IOException e) {

        }


    }

    /**
     * Create clauses based on CELL restrictions.
     *
     * AT LEAST one value in each cell.
     *
     */
    private void cellClauses() {

        try {

            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);
            cnfWriter.append("c CELL CLAUSES\n");


            for (int i = 1; i <= rows; i++) {

                for (int j = 1; j<=columns; j++) {

                    for (int k = 1; k <= columns; k++) {

                        String s = i + "" + j + "" +  k + "" + " ";
                        cnfWriter.append(s);
                        cnfWriter.flush();
                    }

                    cnfWriter.append("0");
                    cnfWriter.append("\n");

                    clauses++;

                }


            }


            cnfWriter.close();




        }


        catch (IOException e) {


        }

        atMostCell();






    }


    /**
     * Create clauses based on CELL restrictions.
     *
     * AT MOST one value in each cell.
     *
     */

    private void atMostCell() {

        int next;


        try {


            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);


            for (int i = 1; i<= rows; i++) {

                for (int j = 1; j<= columns; j++) {

                    for (int k = 1; k<= columns; k++) {

                        int start = ((i * 100) + (j * 10) + k) * -1; // start = 111;



                        for (int z = 1; z<= columns; z++) {

                            if (z <= k) {

                                z = k+1;

                                if (z == 10) {

                                    break;
                                }

                            }


                            next = ((i * 100) + (j * 10) + z) * -1;



                            for (int x = 0; x < 1; x++) {

                                if (next == -999 && start == -999) {

                                    break;
                                }

                                cnfWriter.append(start + " " + next + " 0\n");
                                cnfWriter.flush();
                                clauses++;




                            }

                        }

                    }

                }


            }





        }

        catch (IOException e) {

        }



    }

    /**
     * Create clauses based on column restrictions.
     * AT LEAST one value in each column.
     */
    private void colClauses() {

        String s = "";



        try {


            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);
            cnfWriter.append("c COLUMN CLAUSES\n");

            // Each variable must be present at least once in each column
            for (int k = 1; k <= columns; k++) {

                for (int j = 1; j<=rows; j++) {

                    for (int i = 1; i <= columns; i++) {

                        s = i + "" + j + "" +  k + "" + " ";
                        cnfWriter.append(s);
                        cnfWriter.flush();

                    }

                        cnfWriter.append("0");
                        cnfWriter.append("\n");
                        cnfWriter.flush();

                        clauses++;

                }


            }


            cnfWriter.close();


        }


        catch (IOException e) {


        }

        atMostCol();

    }

    /**
     *
     * Create clauses based on column restrictions.
     *
     * AT MOST one value in each column.
     *
     */
    private void atMostCol() {


        int next;


        try {


            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);


            for (int k = 1; k<= rows; k++) {

                for (int j = 1; j<= columns; j++) {

                    for (int i = 1; i<= columns; i++) {

                        int start = ((i * 100) + (j * 10) + k) * -1; // start = 111;



                        for (int z = 1; z<= columns; z++) {

                             if (z <= i) {

                                z = i + 1;
                            }

                                if (z == 10) {

                                    break;
                                }




                            next = ((z * 100) + (j * 10) + k) * -1;




                            for (int x = 0; x < 1; x++) {

                                if (next == -999 && start == -999) {

                                    break;
                                }

                                cnfWriter.append(start + " " + next + " 0\n");
                                cnfWriter.flush();
                                clauses++;




                            }

                        }

                    }

                }


            }





        }

        catch (IOException e) {

        }





    }

    /**
     * Create clauses based on box restrictions.
     *
     */
    private void gridClauses() {

        String s = "";

        try {

            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);

            cnfWriter.append("c GRID CLAUSES\n");

            // For each number 1-9, we will make sure that there is at least one present in each box.

            // The sub grid -- Horizontal movement
            for (int h = 0; h < gridSize; h++) {

                // Sub grid -- Vertical Movement
                for (int g = 0; g < gridSize; g++) {

                    for (int k = 1; k <= columns; k++) {

                        for (int i = gridSize * h + 1; i <= gridSize * h + gridSize; i++) {

                            for (int j = gridSize * g + 1; j <= gridSize * g + 3; j++) {


                                s = i + "" + j + "" + k + "";
                                cnfWriter.append(s + " ");
                                cnfWriter.flush();



                            }

                            cnfWriter.append("0");

                            clauses++;


                            cnfWriter.append("\n");
                            cnfWriter.flush();




                        }




                    }

                }

            }

            cnfWriter.flush();

            atMostGrid();

        }

        catch(IOException e){


            }

        }







        public void atMostGrid() {

            int next;


            try {


                FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);


                for (int o = 0; o < gridSize; o++) {

                    // Sub grid -- Vertical Movement
                    for (int d = 0; d < gridSize; d++) {

                        for (int k = 1; k <= rows; k++) {

                            for (int i = gridSize * o + 1; i <= gridSize * o + gridSize; i++) {

                                for (int j = gridSize * d + 1; j <= gridSize * d + 3; j++) {

                                    int start = ((i * 100) + (j * 10) + k) * -1; // start = 111;


                                    for (int z = gridSize * o + 1; z <= gridSize * o + gridSize; z++) {


                                        for (int h = gridSize * d + 1;  h <= gridSize * d + gridSize; h++) {


                                            if (h <= j && z <= i) {

                                                h = j + 1;

                                                if (h > gridSize * d + gridSize) {

                                                    break;

                                                }

                                            }




                                            next = ((z * 100) + (h * 10) + k) * -1;


                                            for (int x = 0; x < 1; x++) {

                                                if (next == -999 && start == -999) {

                                                    break;
                                                }

                                                cnfWriter.append(start + " " + next + " 0\n");
                                                cnfWriter.flush();
                                                clauses++;


                                            }

                                        }

                                    }

                                }


                            }


                        }
                    }
                }
            }

            catch (IOException e) {

            }










        }

@Override
    public String toString() {


        String s = "";

        for (int i = 0; i < 2; i++) {

            fileScan.nextLine();
        }

        System.out.println("Full Sudoku:");

        while (fileScan.hasNext()) {


            System.out.println(fileScan.nextLine());


        }


        fileScan.close();

        return s;


    }




}





