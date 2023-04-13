package projectthree;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SudokuSolver {


    public static void main (String args[]) throws FileNotFoundException {

        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(3600); // 1 hour timeout

        Reader reader = new DimacsReader(solver);

        Sudoku sudOne = new Sudoku("puz1");

        int[][] completedSudoku;



            try {


                IProblem problem = reader.parseInstance("sudokuCNF.cnf");

                if (problem.isSatisfiable()) {

                    System.out.println("Satisfiable !");

                    System.out.println(getPositives(problem.model()));

                    decode(getPositives(problem.model()),sudOne);


                    }


                 else {
                    System.out.println("Unsatisfiable !");
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            } catch (ParseFormatException e) {
                System.out.println("Parse format exception");
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
            } catch (ContradictionException e) {
                System.out.println("Unsatisfiable (trivial)!");

            } catch (TimeoutException e) {
                System.out.println("Timeout, sorry!");
            }




    }


    /**
     *  getPositives
     *
     *  Iterate through the .model() array to find all positive values, (positive variables).
     *
     * @param values
     * @return a List of ONLY positive values.
     */
  private static ArrayList<Integer> getPositives(int[] values) {

      ArrayList<Integer> valueList = new ArrayList<>();

      for (int i = 0; i < values.length; i++) {

          int val = values[i];

          if (val > 0) {

              valueList.add(val);


          }


      }


      return valueList;


  }


    /**
     * decode
     *
     * Given the list of positive variables and the starting sudoku,
     * create a SOLVED sudoku board of size (sudokuSize), using coded i,j,k values,
     * which are added to the list.
     *
     * @param list
     * @param sud
     */
  private static void decode(ArrayList<Integer> list, Sudoku sud) {

        int[][] fullSud = new int[sud.rows][sud.rows];

        while (!list.isEmpty()) {

            int code = list.get(0);
            int i = code / (sud.rows * sud.rows);

            i--;

           int j = (code / sud.rows) % sud.rows;

           j--;

           int k = code -  (((i+1) * (sud.rows * sud.rows)) + ((j+1) * sud.rows));


            fullSud[i][j] = k;

            list.remove(0);

        }


      System.out.println("Solved sudoku: ");

      String s = "";

      for (int i = 0; i < sud.rows; i++) {

          for (int j = 0; j < sud.columns; j++) {

              System.out.print(fullSud[i][j] + "  ");

          }

          System.out.print("\n");


      }

      long endTime = System.currentTimeMillis() - sud.startTime;
        System.out.println("Done in " +  (endTime - sud.startTime));




    }

}


