package projectthree;

import java.io.*;
import java.util.*;




public class Sudoku {
    final long startTime = System.nanoTime();

    ArrayList<String> clauseList = new ArrayList();

    int clauses;

    int[][] sudArray;

    int rows;

    int gridSize;

    int columns;

    int value;

    int variablesNeeded;

    Scanner fileScan;


    String data = "";



    public Sudoku(String filename) throws FileNotFoundException {

        // HARDCODED FILEPATH!! TODO
         File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/DAAProjects/out/" + filename);
        //  LAPTOP!!!! File readFile = new File("C:/Users/njhdt/Desktop/ROWAN CS 1 BOOKS/sem3/daa_inputs/inputs/" + filename);


        try {
            fileScan = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        gridSize = fileScan.nextInt(); // let gridsize be h

        fileScan.nextInt();

        rows = gridSize * gridSize; // rows be i

        columns = rows; // columns be j

        variablesNeeded = rows * rows * rows;

        sudArray = new int [rows][columns];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j< columns; j++) {

                sudArray[i][j] = fileScan.nextInt();

            }

        }




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



        }


        catch(IOException e) {
            System.err.println("Error.");
        }


        setConstraints();





        try {

            FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf");

            BufferedWriter bf = new BufferedWriter((cnfWriter));

            bf.write("p cnf " + variablesNeeded + " " + clauseList.size() + "\n");

            bf.flush();

            for (int i = 0; i< clauseList.size(); i++) {

                bf.append(clauseList.get(i) + "\n");

            }
            bf.flush();
            cnfWriter.close();
            bf.close();

        }

        catch (IOException e) {

        }







    }

    /** Create clauses for positive literals.
     * I.e.
     * Given row 1 column 1, value 5
     *
     * Add a singular clause "115 0"
     *
     *
     *
     */
    private void literalClause() {


        // Creating single clauses from each variable given.

            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < rows; j++) {


                    if (sudArray[i][j] != 0) {

                        String tempClause = encode(i+1,j+1,sudArray[i][j]) + " 0";

                        clauseList.add(tempClause);

                    }

                }

            }




    }


    /**
     *
     * Set up sudoku constraints by creating clauses
     * Each method has a sub-method for AT-MOST clauses.
     */
    private void setConstraints() {

        literalClause();

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



            for (int i = 1; i <= rows; i++) {

                for (int k = 1; k<=columns; k++) {

                    String tempclause = "";


                    for (int j = 1; j <= columns; j++) {

                        tempclause  += encode(i,j,k) + " ";


                    }

                    tempclause += "0";

                    clauseList.add(tempclause);

                }


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



            for (int i = 1; i<= rows; i++) {

                for (int k = 1; k<= columns; k++) {

                    for (int j = 1; j<= columns; j++) {

                        String tempclause = "";

                        for (int z = j+1; z<= columns; z++) {


                            tempclause = (-1 * encode(i,j,k)) + " " + (-1 * encode(i,z,k)) + " 0";

                            clauseList.add(tempclause);


                            }


                        }

                    }

                }


            }



    /**
     * Create clauses based on CELL restrictions.
     *
     * AT LEAST one value in each cell.
     *
     */
    private void cellClauses() {



            for (int i = 1; i <= rows; i++) {

                for (int j = 1; j<=columns; j++) {

                    String tempclause = "";

                    for (int k = 1; k <= columns; k++) {

                        tempclause += encode(i,j,k) + " ";


                    }

                    tempclause += "0";

                    clauseList.add(tempclause);

                }






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



            for (int i = 1; i<= rows; i++) {

                for (int j = 1; j<= columns; j++) {

                    for (int k = 1; k<= columns; k++) {

                        int start = encode(i,j,k) * -1; // start = 111;



                        for (int z = 1; z<= columns; z++) {



                            next = (encode(i,j,z)) * -1;




                                data = data + (start + " " + next + " 0\n");

                                clauses++;






                        }

                    }

                }


            }





        }


    /**
     * Create clauses based on column restrictions.
     * AT LEAST one value in each column.
     */
    private void colClauses() {

        String tempclause;

            // Each variable must be present at least once in each column
            for (int j = 1; j <= columns; j++) {

                for (int k = 1; k<=rows; k++) {


                    tempclause = "";

                    for (int i = 1; i <= columns; i++) {

                        tempclause += encode(i,j,k) + " ";

                    }

                        tempclause += "0";

                    clauseList.add(tempclause);

                }


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

        String tempclause;

            for (int j = 1; j<= rows; j++) {

                for (int k = 1; k<= columns; k++) {

                    for (int i = 1; i<= columns; i++) {


                        for (int z = i + 1; z<= columns; z++) {

                            tempclause = "";


                            tempclause += ((encode(i,j,k) * -1) + " " + (encode(z,j,k) * -1) + " 0");

                            clauseList.add(tempclause);



                            }

                        }

                    }

                }


            }


    /**
     * Create clauses based on box restrictions.
     *
     */
    private void gridClauses() {

        int subGridI;

        int subGridJ;

        String tempclause;

            // For each number 1-9, we will make sure that there is at least one present in each box.

            // The sub grid -- Horizontal movement
            for (int h = 1; h <= rows; h += gridSize) {

                subGridI = (h + (gridSize - 1)) / gridSize;

                // Sub grid -- Vertical Movement
                for (int g = 1; g <= rows; g++) {

                    subGridJ = (g + (gridSize - 1)) / gridSize;

                    for (int k = 1; k <= columns; k++) {

                        tempclause = "";

                        for (int i = h; i <= rows * subGridI; i++) {

                            for (int j = g; j <= gridSize * subGridJ;  j++) {


                               tempclause += encode(i,j,k) + " ";


                            }


                        }

                        tempclause+= "0";
                        clauseList.add(tempclause);




                    }

                }

            }


           // atMostGrid();

        }








        public void atMostGrid() {

           int start;

           int next;


            String tempclause;


            try {


                FileWriter cnfWriter = new FileWriter("sudokuCNF.cnf", true);


                for (int h = 0; h < gridSize; h++) {

                    // Sub grid -- Vertical Movement
                    for (int g = 0; g < gridSize; g++) {

                        for (int k = 1; k <= rows; k++) {

                            for (int i = gridSize * h + 1; i <= gridSize * h + gridSize; i++) {

                                for (int j = gridSize * g + 1; j <= gridSize * g + gridSize; j++) {


                                    tempclause = "";

                                    start = encode(i,j,k);


                                    for (int z = gridSize * h + 1; z <= gridSize * h + gridSize; z++) {


                                        for (int x = gridSize * g + 1;  x <= gridSize * g + gridSize; x++) {


                                            if (x <= j && z <= i) {

                                                x = j + 1;


                                                if (z < i ) {

                                                    z = i;

                                                }

                                                if (x > gridSize * g + gridSize) {

                                                    break;

                                                }

                                            }


                                            next = encode(z,h,k) * -1;


                                            tempclause+= (start + " " + next + " 0");
                                            clauseList.add(tempclause);
                                            clauses++;



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

        System.out.println("Full Sudoku:");

        String s = "";

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                System.out.print(sudArray[i][j] + "  ");

            }

            System.out.print("\n");


        }






        fileScan.close();

        return s;


    }


    private int encode(int i, int j, int k) {

        // return (i * (columns * columns) +( j * columns) + (k + 1));

        return (i * (100) +( j * 10) + (k));

    }




}





