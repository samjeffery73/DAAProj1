package projectthree;

import java.io.*;
import java.util.*;




public class FileReader {
    final long startTime = System.nanoTime();

    int rows;

    int columns;

    int next;

    Scanner fileScan = null;
    String line;

    int value;

    Map<Integer, ArrayList<Variable>> rowMap = new HashMap<>();
    Map<Integer, ArrayList<Variable>> colMap = new HashMap<>();







    public FileReader(String filename) throws FileNotFoundException {

        // HARDCODED FILEPATH AND FILENAME!!!
        File readFile = new File("C:/Users/njhdt/OneDrive/Desktop/Rowan Files/DAA/inputs/" + filename);


        try {
            fileScan = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        rows = fileScan.nextInt();

        rows = rows * rows;

        columns = rows;

        System.out.println("Sudoku is size: " + rows + " x " + columns);

        line = fileScan.nextLine();

        line = fileScan.nextLine();

        System.out.println("Full Sudoku:");


        int x = 1; // starting row position
        int y = 1; // starting col position

        for (int i = 0; i < rows*rows; i++) {
            

            value = fileScan.nextInt();

            Row r = new Row(2);




            System.out.println(line);


        }

        fileScan.close();







    }





    }





