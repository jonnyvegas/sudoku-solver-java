/**
 * Name: Jonathan Villegas
 * Class: COMP282 T/TH 2-3:15
 * Assignment: Programming Assignment #1
 * 
 * Description: This program will take a sudoku board and solve it
 * based on trying to fill rows, columns, boxes, and spots. It
 * continues until it either cannot make any more changes, or completes
 * the sudoku board.
 */
package sudoku;



/**
 *
 * @author Jonny
 */
class sudoku 
{
    private int board[][];
    /**
     * Default constructor. Initializes a 9x9 blank sudoku board.
     */
    public sudoku() 
    {
        board = new int[9][9];
    }
    // Construct a new sudoku puzzle from a string
    // This piece of code might be useful to you:
    // (int) (s[row].charAt(col + col/3)) - 48
    /**
     * Constructor. Creates a sudoku puzzle out of an array of strings.
     * @param s The array of strings to use as a basis for the puzzle.
     */
    public sudoku(String s[]) 
    {
        //Initialize a 9x9 board.
        board = new int[9][9];
        //Fill the board from the array of strings, s[].
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                board[row][col] = (int)(s[row].charAt(col + col/3)) - 48;
            }
        }
    }
    /**
     * Copy constructor.
     * @param p A puzzle to be copied.
     */    
    public sudoku(sudoku p) 
    {
        this.board = new int[9][9];
        for(int row = 0; row < 9; row++)
        {
            System.arraycopy(p.board[row], 0, this.board[row], 0, 9);
        }
    }
    // Hint: use String.valueOf( i ) to convert an int to a String
    /**
     * Override of toString() method.
     * @return A string to print in the form of a Sudoku board.
     */
    @Override
    public String toString() 
    {
        int currentSquare = 0;
        String thePuzzle = new String();
        for(int row = 0; row < 9; row++)
        {
            for(int col = 0; col < 9; col++)
            {
                currentSquare++;
                thePuzzle = thePuzzle + String.valueOf(board[row][col]);
                if(currentSquare % 3 == 0 && currentSquare % 9 != 0)
                {
                    thePuzzle = thePuzzle + " | ";
                }
                if(currentSquare % 9 == 0)
                {
                    thePuzzle = thePuzzle + "\r\n";
                }
                if(currentSquare % 27 == 0)
                {
                    thePuzzle = thePuzzle + "---------------";
                    thePuzzle = thePuzzle + "\r\n";
                }
            }
        }
        return thePuzzle;
    }
    // for easy checking of your answers
    public String toString2() 
    {
        String result = new String();
        for (int row = 0; row < 9; row++) 
        {
            for (int col = 0; col < 9; col++) 
            {
                result = result + String.valueOf(board[row][col]);
            }
        }
        return result;
    }

    // create rotated sudoku puzzle – used by my test programs
    public void rotate() 
    {
        int[][] temp = new int[9][9];
        int row, col;
        for (row = 0; row < 9; row++) 
        {
            for (col = 0; col < 9; col++) 
            {
                temp[col][8-row] = board[row][col];
            }
        }
        for (row = 0; row < 9; row++) 
        {
            for (col = 0; col < 9; col++) 
            {
                board[row][col] = temp[row][col];
            }
        }
    }
    // Does the current board satisfy all the sudoku rules?
    /**
     * Check every row, column, and box to see if any values repeat.
     * If they do, it is not a valid sudoku.
     * @return If the puzzle is a valid sudoku after checking
     * rows, columns and boxes.
     */
    public boolean isValid() 
    {
        boolean valid = true, rowValid = true, colValid = true, boxValid = true;
        int theNums[] = new int[10];
        //Check every row.
        for(int row = 0; row < 9; row++)
        {
            for(int i = 0; i < 10; i++)
            {
                theNums[i] = 0;
            }
            for(int col = 0; col < 9; col++)
            {
                //If the value doesn't equal 0, check it.
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
                //If any of the values have more than 1 appearance
                for(int i = 0; i < 10; i++)
                {
                    if(theNums[i] > 1)
                    {
                        rowValid = false;
                    }
                }
            }
        }
        //Check every column.
        for(int col = 0; col < 9; col++)
        {
            for(int i = 0; i < 10; i++)
            {
                theNums[i] = 0;
            }
            for(int row = 0; row < 9; row++)
            {
                //If the value isn't 0, check it.
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
                //Do any have more than 1 appearance in col?
                for(int i = 0; i < 10; i++)
                {
                    if(theNums[i] > 1)
                    {
                        colValid = false;
                    }
                }
            }
        }
        //Check every box.
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box A.
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box B.
        for(int row = 0; row < 3; row++)
        {
            for(int col = 3; col < 6; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box C.
        for(int row = 0; row < 3; row++)
        {
            for(int col = 6; col < 9; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box D.
        for(int row = 3; row < 6; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box E.
        for(int row = 3; row < 6; row++)
        {
            for(int col = 3; col < 6; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box F.
        for(int row = 3; row < 6; row++)
        {
            for(int col = 6; col < 9; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box G.
        for(int row = 6; row < 9; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box H.
        for(int row = 6; row < 9; row++)
        {
            for(int col = 3; col < 6; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //Clear the values.
        for(int i = 0; i < 10; i++)
        {
            theNums[i] = 0;
        }
        //Box I.
        for(int row = 6; row < 9; row++)
        {
            for(int col = 6; col < 9; col++)
            {
                if(board[row][col] != 0)
                {
                    int val = board[row][col];
                    theNums[val]++;
                }
            }
            for(int i = 0; i < 10; i++)
            {
                if(theNums[i] > 1)
                {
                    boxValid = false;
                }
            }
        }
        //If any row, column, or box isn't valid,
        //the puzzle isn't valid.
        if(!rowValid || !colValid || !boxValid)
        {
            valid = false;
        }
        return valid;
    }
    // Is this a solved sudoku?
    /**
     * Is the puzzle complete?
     * @return true if the puzzle is complete, false if it isn't.
     */
    public boolean isComplete() 
    {
        boolean puzzleComplete;
        int numOfZeroes = 0;
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                //If we are at any 0.
                if(board[i][j] == 0)
                {
                    numOfZeroes++;
                }
                
            }
        }
        //puzzleComplete is true if numOfZeroes is 0.
        //else, it is false, and we are not done yet.
        puzzleComplete = numOfZeroes == 0;
        return puzzleComplete;
    }
    // return true if val appears in the row of the puzzle
    /**
     * Searches to see if a given row contains a value.
     * @param row the row to check
     * @param val the value to check
     * @return true if val is in the row, false if not.
     */
    private boolean doesRowContain(int row, int val) 
    {
        boolean valFound = false;
        for(int i = 0; i < 9; i++)
        {
            if(board[row][i] == val)
            {
                valFound = true;
            }
        }
        return valFound;
    }
    // return true if val appears in the col (column) of the puzzle
    /**
     * Searches to see if a given column contains a value.
     * @param col the column to check
     * @param val the value to check
     * @return true if val is in the row, false if not.
     */
    private boolean doesColContain(int col, int val) 
    {
        boolean valFound = false;
        for(int i = 0; i < 9; i++)
        {
            if(board[i][col] == val)
            {
                valFound = true;
            }
        }
        return valFound;
    }
    /**
     * Checks to see if a given box A-I contains a value.
     * @param row the row of the box to check
     * @param col the column of the box to check
     * @param val the value to check
     * @return true if the val is in the box, false if not.
     */
    private boolean doesBoxContain(int row, int col, int val)
    {
        boolean valFound = false;
        //Check first 3 squares 
        if(row >= 0 && row <= 2)
        {
            for(int i = 0; i <= 2; i++)
            {   
                //Box A.
                if(col >= 0 && col <= 2)
                {
                    for(int j = 0; j <= 2; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box B.
                else if(col >= 3 && col <= 5)
                {
                    for(int j = 3; j <= 5; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box C, col >= 6 && col <= 8
                else
                {
                    for(int j = 6; j <=8; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
            }
        }
        //Check next 3 boxes
        else if(row >= 3 && row <= 5)
        {
            for(int i = 3; i <= 5; i++)
            {
                //Box D.
                if(col >= 0 && col <= 2)
                {
                    for(int j = 0; j <= 2; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box E.
                else if(col >= 3 && col <= 5)
                {
                    for(int j = 3; j <= 5; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box F, col >=6 && col <= 8
                else
                {
                    for(int j = 6; j <=8; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
            }
        }
        //Check last 3 boxes.
        else
        {
            for(int i = 6; i <= 8; i++)
            {
                //Box G.
                if(col >= 0 && col <= 2)
                {
                    for(int j = 0; j <= 2; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box H.
                else if(col >= 3 && col <= 5)
                {
                    for(int j = 3; j <= 5; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
                //Box I, col >= 6 && col <= 8
                else
                {
                    for(int j = 6; j <=8; j++)
                    {
                        if(board[i][j] == val)
                        {
                            valFound = true;
                        }
                    }
                }
            }
        }
        return valFound;
    }

    /**
     * Checks to see if there is only one possible value
     * that can be put in the Spot sq.
     * @param sq the Spot to check
     * @return 0 if it can't be found, and the number, theVal,
     * if it there is only one possible value for this spot.
     */
    private int fillSpot(Spot sq) 
    {
        int theVal = 0;
        boolean possibilities[] = new boolean[9];
        int theRow = sq.getRow();
        int theCol = sq.getCol();
        int numberTrue = 0;
        //All 9 possibilities for ints set to true.
        //They are all possible at this point.
        for(int i = 0; i < 9; i++)
        {
            possibilities[i] = true;
            //If the row contains the value, it isn't possible.
            if(doesRowContain(theRow, i + 1))
            {
                possibilities[i] = false;
            }
            //If the column contains the value, it isn't possible.
            if(doesColContain(theCol, i + 1))
            {
                possibilities[i] = false;
            }
            //If the box contains the value, it isn't possible.
            if(doesBoxContain(theRow, theCol, i + 1))
            {
                possibilities[i] = false;
            }
        }
        //Check to see how many possibilities there are.
        for(int i = 0; i < 9; i++)
        {
            if(possibilities[i])
            {
                numberTrue++;
            }
        }
        //Only one choice if the numberTrue == 1.
        if(numberTrue == 1)
        {
            for(int i = 0; i < 9; i++)
            {
                if(possibilities[i])
                {
                    theVal = i + 1;
                }
            }
        }
        return theVal;
    }    
    /**
     * Checks the row to see if a val has only one place it can be
     * placed in the row.
     * @param row the row to check
     * @param val the value to check
     * @return the Spot in the row if there is only one possible
     * place in the row, or null otherwise.
     */
    private Spot rowFill(int row, int val) 
    {
        Spot theSpot;
        theSpot = null;
        int numOfPossibilities = 0, theRow = -1, theCol = -1;
        //Make sure the row doesn't ahve the value.
        if(!doesRowContain(row, val))
        {
            for(int col = 0; col < 9; col++)
            {
                //If at a 0, it is a possibility.
                if(board[row][col] == 0)
                {
                    //If the column or box don't contain the value, it
                    //is a possibility.
                    if(!doesColContain(col, val) && 
                            !doesBoxContain(row, col, val))
                    {
                        numOfPossibilities++;
                        theRow = row;
                        theCol = col;
                    }
                }
            }
            if(numOfPossibilities == 1)
            {
                theSpot = new Spot(theRow, theCol);
            }
        }
        return theSpot;
    }
    /**
     * Checks a column to see if there's only one spot in the column
     * the val can be placed.
     * @param col the column to check
     * @param val the value to check
     * @return A spot that the value can be placed or null if it can't
     * determine.
     */
    private Spot colFill(int col, int val) 
    {
        Spot theSpot;
        theSpot = null;
        int numOfPossibilities = 0, theRow = -1, theCol = -1;
        //If the column does not contain the number.
        if(!doesColContain(col, val))
        {
            for(int row = 0; row < 9; row++)
            {
                //If we are at 0, it is a possibility.
                if(board[row][col] == 0)
                {
                    //If the row or box don't contain the number
                    //it is a possibility.
                    if(!doesRowContain(row, val) && 
                            !doesBoxContain(row, col, val))
                    {
                        numOfPossibilities++;
                        theRow = row;
                        theCol = col;
                    }
                }
            }
            if(numOfPossibilities == 1)
            {
                theSpot = new Spot(theRow, theCol);
            }
        }
        return theSpot;
    }
    /**
     * Checks to see if there is only one value in a box A-I
     * that the value can be placed.
     * @param row The row of the box to check
     * @param col The column of the box to check
     * @param val the value to check
     * @return A spot if there is only one place in the box for the value
     * and null if not.
     */
    private Spot boxFill(int row, int col, int val) 
    {
        int numOfPossibilities = 0;
        Spot theSpot;
        theSpot = null;
        int theRow = -1, theCol = -1;
        //Make sure the box doesn't contain the value.
        if(!doesBoxContain(row, col, val))
        {
            if(row >= 0 && row <= 2)
            {
                //Box A.
                if(col >= 0 && col <= 2)
                {
                    for(int row1 = 0; row1 < 3; row1++)
                    {
                        for(int col1 = 0; col1 < 3; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    }
                }
                //Box B.
                else if(col >=3 && col <= 5)
                {
                    for(int row1 = 0; row1 < 3; row1++)
                    {
                        for(int col1 = 3; col1 < 6; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    }    
                }
                //Box C.
                else if(col >=6 && col<= 8)
                {
                    for(int row1 = 0; row1 < 3; row1++)
                    {
                        for(int col1 = 6; col1 < 9; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
            }
            else if(row >= 3 && row <= 5)
            {
                //Box D.
                if(col >= 0 && col <= 2)
                {
                    for(int row1 = 3; row1 < 6; row1++)
                    {
                        for(int col1 = 0; col1 < 3; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
                //Box E.
                else if(col >=3 && col <= 5)
                {
                    for(int row1 = 3; row1 < 6; row1++)
                    {
                        for(int col1 = 3; col1 < 6; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
                //Box F.
                else if(col >=6 && col<= 8)
                {
                    for(int row1 = 3; row1 < 6; row1++)
                    {
                        for(int col1 = 6; col1 < 9; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
            }
            else if(row >= 6 && row <= 8)
            {
                //Box G.
                if(col >= 0 && col <= 2)
                {
                    for(int row1 = 6; row1 < 9; row1++)
                    {
                        for(int col1 = 0; col1 < 3; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
                //Box H.
                else if(col >=3 && col <= 5)
                {
                    for(int row1 = 6; row1 < 9; row1++)
                    {
                        for(int col1 = 3; col1 < 6; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
                //Box I.
                else if(col >=6 && col<= 8)
                {
                    for(int row1 = 6; row1 < 9; row1++)
                    {
                        for(int col1 = 6; col1 < 9; col1++)
                        {
                            if(board[row1][col1] == 0)
                            {
                                //Make sure row/column don't contain
                                //the number.
                                if(!doesRowContain(row1, val) &&
                                        !doesColContain(col1,val))
                                {
                                    numOfPossibilities++;
                                    theRow = row1;
                                    theCol = col1;
                                }
                            }
                        }
                    } 
                }
            }
            if(numOfPossibilities == 1)
            {
                theSpot = new Spot(theRow, theCol);
            } 
        }
        return theSpot;
    }
    /**
     * Solves the sudoku puzzle until it is complete or there
     * are no more moves to be made.
     */
    public void solve() 
    {
        Spot theSpot = new Spot(-1, -1);
        boolean changeMade = true;
        int theRow = -1, theCol = -1;
        while(changeMade && !isComplete())
        {
            changeMade = false;
            for(int row = 0; row < 9; row++)
            {
                for(int col = 0; col < 9; col++)
                {
                    //This is a spot we need to fill.
                    if(board[row][col] == 0)
                    {
                        theSpot.setRow(row);
                        theSpot.setCol(col);
                        //If the spot returns anything but 0,
                        //the right number goes here.
                        if(fillSpot(theSpot) != 0)
                        {
                            board[row][col] = fillSpot(theSpot);
                            changeMade = true;
                        }
                        //fillSpot returns a 0.
                        else
                        {
                            //Check every number 1-9.
                            for(int theNum = 1; theNum < 10; theNum++)
                            {
                                //If we get a spot back, theNum goes there.
                                if(rowFill(row, theNum) != null)
                                {
                                    theSpot = rowFill(row, theNum);
                                    theRow = theSpot.getRow();
                                    theCol = theSpot.getCol();
                                    board[theRow][theCol] = theNum;
                                    changeMade = true;
                                }
                                else if(colFill(col, theNum) != null)
                                {
                                    theSpot = colFill(col, theNum);
                                    theRow = theSpot.getRow();
                                    theCol = theSpot.getCol();
                                    board[theRow][theCol] = theNum;
                                    changeMade = true;
                                }
                                else if(boxFill(row, col, theNum) != null)
                                {
                                    theSpot = boxFill(row, col, theNum);
                                    theRow = theSpot.getRow();
                                    theCol = theSpot.getCol();
                                    board[theRow][theCol] = theNum;
                                    changeMade = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * My name for the project.
     * @return a string with my name, Jonathan Villegas.
     */
    public static String myName() 
    {
        return "Jonathan Villegas";
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        String s[][] = {
				{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 300 400",
					"650 080 071",
					"004 003 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 200",
	
					"000 500 400",
					"650 080 071",
					"004 003 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"004 003 000",
	
					"092 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"004 003 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 044"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 200",
	
					"000 500 400",
					"650 080 071",
					"004 003 000",
	
					"002 000 000",
					"910 620 804",
					"780 000 004"
				},{
					"800 000 048",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"004 003 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"004 033 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"004 503 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
					"800 000 042",
					"007 059 063",
					"000 000 900",
	
					"000 500 400",
					"650 080 071",
					"084 003 000",
	
					"002 000 000",
					"910 620 800",
					"780 000 004"
				},{
                    "800 000 042",
                    "007 059 063",
                    "000 000 900",
                    
                    "000 900 400",
                    "650 080 071",
                    "004 003 000",
                    
                    "002 000 000",
                    "910 620 800",
                    "780 000 004"
                },{
                    "000 040 000",
                    "800 000 007",
                    "003 060 540",
                    
                    "090 100 800",
                    "050 304 020",
                    "007 008 060",
                    
                    "024 000 010",
                    "001 000 003",
                    "000 020 000"
                },{
                    "000 000 010",
                    "042 006 700",
                    "000 108 000",
                    
                    "005 000 406",
                    "600 307 009",
                    "209 000 107",
                    
                    "000 509 000",
                    "008 700 500",
                    "030 040 000"
                },{
                    "004 060 500",
                    "300 200 007",
                    "600 503 800",
                    
                    "908 000 300",
                    "000 010 000",
                    "007 000 401",
                    
                    "003 600 009",
                    "800 009 004",
                    "006 080 200"
                },{
                    "057 043 000",
                    "809 000 000",
                    "030 005 090",
                    
                    "002 071 000",
                    "004 000 200",
                    "008 500 900",
                    
                    "040 300 020",
                    "000 000 401",
                    "000 720 360"
                },{
                    "030 008 050",
                    "020 090 000",
                    "000 070 004",
                    
                    "190 002 067",
                    "280 000 035",
                    "640 100 098",
                    
                    "500 030 000",
                    "000 060 040",
                    "060 900 080"
                },{
                    "000 020 130",
                    "000 070 004",
                    "073 000 602",
                    
                    "009 687 000",
                    "000 000 000",
                    "000 542 700",
                    
                    "805 000 290",
                    "300 050 000",
                    "924 010 000"
                },{
                    "704 200 308",
                    "020 030 090",
                    "000 000 000",
                    
                    "900 000 600",
                    "030 060 010",
                    "008 000 007",
                    
                    "000 000 000",
                    "060 010 040",
                    "805 006 702"
                },{
                    "000 002 010",
                    "201 000 406",
                    "300 060 007",
                    
                    "830 001 000",
                    "000 426 000",
                    "000 700 045",
                    
                    "400 070 001",
                    "108 000 604",
                    "020 900 000"
                },{
                    "900 000 407",
                    "050 000 026",
                    "700 109 000",
                    
                    "030 408 000",
                    "008 090 700",
                    "000 501 080",
                    
                    "000 203 004",
                    "020 000 050",
                    "406 000 003"
                },{
                    "001 000 900",
                    "800 006 701",
                    "409 007 000",
                    
                    "000 620 300",
                    "000 470 000",
                    "003 095 000",
                    
                    "000 200 807",
                    "107 800 003",
                    "004 000 600"
                },{
                    "020 010 070",
                    "003 000 105",
                    "607 000 029",
                    
                    "000 024 000",
                    "000 305 000",
                    "000 890 000",
                    
                    "250 000 601",
                    "908 000 200",
                    "000 000 430"
                },{
                    "002 000 106",
                    "000 560 000",
                    "007 000 040",
                    
                    "000 001 060",
                    "103 002 007",
                    "006 008 090",
                    
                    "000 009 400",
                    "800 000 030",
                    "045 030 000"
                },{
                    "005 900 008",
                    "060 000 030",
                    "709 040 005",
                    
                    "000 003 000",
                    "900 000 001",
                    "000 500 000",
                    
                    "120 080 060",
                    "340 000 100",
                    "000 602 004"
                },{
                    "438 760 102",
                    "200 090 530",
                    "000 002 608",
                    
                    "004 023 050",
                    "300 000 800",
                    "600 000 000",
                    
                    "005 010 309",
                    "010 000 080",
                    "900 600 070"
                },{
                    "108 369 075",
                    "070 010 000",
                    "300 000 000",
                    
                    "007 002 109",
                    "000 000 050",
                    "000 901 040",
                    
                    "980 520 400",
                    "520 604 003",
                    "000 008 000"
                    
                },{
                    "002 600 700",
                    "800 004 005",
                    "007 005 320",
                    
                    "109 000 007",
                    "000 507 000",
                    "600 000 904",
                    
                    "058 700 600",
                    "200 300 009",
                    "003 006 800"
                },{
                    "080 306 270",
                    "000 000 006",
                    "507 001 009",
                    
                    "002 005 400",
                    "000 080 000",
                    "001 400 300",
                    
                    "700 100 605",
                    "200 000 000",
                    "035 802 010"
                }
        };
        
        String answers[] = {
        		"","","","","","","","","",
                "895316742427859163361247985138975426659482371274163598542738619913624857786591234",
                "275943186846512397913867542492176835658394721137258469724635918561489273389721654",
                "893274615142956783756138294375891426614327859289465137427589361968713542531642978",
                "004060503300200007670503802918000306000010008007000401003600089800009004096080205",
                "057943000809067000430085090002471000004090200008532900040310020003050401000724360",
                "736418952421593876859276314195382467287649135643157298518734629972861543364925781",
                "090020137000070904073090602009687300700931000030542709815764293367259000924010576",
                "704201368026030090080600270901000603030060010608103007000000106060010040815006732",
                "000042510201057406345169007834591762000426000002783045400678001108235604020914000",
                "900000417050000926700109030030408000008390740000501380000203004020000050406000203",
                "371582946852946731469317582715628394298473165643195278936254817127869453584731629",
                "020010070003000105617000029000024000000305000000890000254700691938000257000000438",
                "532984176914567823687123945758391264193642587426758391361279458879415632245836719",
                "215937648864125937739846215471293856953468721682571493127384569346759182598612374",
                "438765192261894537579132648184923756392576814657481923845217369716349285923658471",
                "148369275672815934395247681857432169419786352263951748986523417521674893734198526",
                "502630748836274195007005326129463587384597261675000934058700613261358479703006852",
                "489356271123978546567241839372615498946783152851429367798134625214567983635892714",
             };
        String rotateAnswers[] = {
        		"","","","","","","","","",
        		"795261348814753629632498175567149283923687451148325796286534917351972864479816532",
                "357164982862359147914782365746231859283597614195846723629478531571623498438915276",
                "594263718362817549187945623675438192418629357239571864953184276746352981821796435",
                "080009630900001700603708004006000520800010006090000300200403805008000000549186273",
                "000000480004000305030842097703504009251397864400201573340920000602000900010000000",
                "395621847671489523428375916987163254263548791514792638756214389842936175139857462",
                "938070000261300709475009300027596000156438972094217000502703691709000003603900247",
                "800609007160030820500801064000100602010060030600300001701006203340010796206703008",
                "014008320200003400080204510926745100137829654458361972060007045000406001041502760",
                "400000709020003050600080000002534100000090000003108900200370094050840321304000067",
                "519627483823491657476385921782146395365972148194538762648213579251769834937854216",
                "092000600035000102084000730007830000000902001000054000426000010359000207871000950",
                "283417695476295813591638742842763159317549268659821374764352981135986427928174536",
                "531694782942857361867231945673542819158769423294183657315478296786925134429316578",
                "978631524214598763365724918632459187541872396897163245423987651786215439159346872",
                "759248361328615974416397528165974283972583416843162759584731692291456837637829145",
                "720631085065782030318549762037054026050096073680073540846925317571368294293417658",
                "627893514319547628548162739851476293963281475274935186796314852182659347435728961",
            };
        String constructorAnswers[] = {
        		"002600700800004005007005320109000007000507000600000904058700600200300009003006800",
        		"502630748836274195007005326129463587384597261675000934058700613261358479703006852",
        		"080306270000000006507001009002005400000080000001400300700100605200000000035802010",
       	        "489356271123978546567241839372615498946783152851429367798134625214567983635892714",
        };
        sudoku p, p1;
        System.out.println("Author: " + sudoku.myName());
        for (int i = 0; i < 9; i++) {
			p = new sudoku(s[i]);
            System.out.print("#" + i + ": ");
			if (!p.isValid()) {
					System.out.print("Invalid board.");
	                System.out.println(" Answers match.   ");
			} else {
				System.out.println("   *** Board " + i + " is invalid.  ***");
			}
        }
		for (int i = 9; i < s.length; i++) {
			p = new sudoku(s[i]);
			System.out.print("#" + i + ": ");
			if (!p.isValid()) {
				System.out.println("   *** Board " + i + " IS valid.  ***");
			}
			else {
				p.solve();
				if (p.isComplete())
					System.out.print("Solution found.");
				else
					System.out.print("Not done yet.  ");
				if (p.toString2().compareTo(answers[i]) == 0)
					System.out.print(" Answers match.   ");
				else {
					System.out.print("   *** NO MATCH ***   ");
					System.out.println(p.toString2());
				}
				p = new sudoku(s[i]);
				p.rotate();
				p.solve();
				if (p.isComplete())
					System.out.print("Solution found.");
				else
					System.out.print("Not done yet.  ");
				if (p.toString2().compareTo(rotateAnswers[i]) == 0)
					System.out.print(" Answers match.");
				else {
					System.out.print("   *** NO MATCH ***   ");
					System.out.println(p.toString2());
				}
				System.out.println();
			}
		}
		System.out.println("Constructor check:");
        p = new sudoku(s[s.length-2]);
        p1 = new sudoku(p);
        p.solve();
        if (p1.toString2().compareTo(constructorAnswers[0]) == 0) 
            System.out.println(" Answers match.   ");
        else
            System.out.println("   *** Copy Constructor Problem with copied sudoku  ***   ");
        if (p.toString2().compareTo(constructorAnswers[1]) == 0) 
            System.out.println(" Answers match.   ");
        else
            System.out.println("   *** Copy Constructor Problem with original sudoku  ***   ");
        p = new sudoku(s[s.length-1]);
        p1 = new sudoku(p);
        p.solve();
        if (p1.toString2().compareTo(constructorAnswers[2]) == 0) 
            System.out.println(" Answers match.   ");
        else
            System.out.println("   *** Copy Constructor Problem with copied sudoku  ***   ");
        if (p.toString2().compareTo(constructorAnswers[3]) == 0) 
            System.out.println(" Answers match.   ");
        else
            System.out.println("   *** Copy Constructor Problem with original sudoku  ***   ");
        System.out.println("Author: " + sudoku.myName());
    }
}