/*
00 01 02 | 03 04 05 | 06 07 08 
10 11 12 | 13 14 15 | 16 17 18
20 21 22 | 23 24 25 | 26 27 28
------------------------------
30 31 32 | 33 34 35 | 36 37 38
40 41 42 | 43 44 45 | 46 47 48
50 51 52 | 53 54 55 | 56 57 58
------------------------------
60 61 62 | 63 64 65 | 66 67 68
70 71 72 | 73 74 75 | 76 77 78
80 81 82 | 83 84 85 | 86 87 88

1. check every subgrid 00 03 06 30 33 36 60 63 66's uniquness
   -> topx =  x // 3  *  3     topy = y//3 * 3
2. check every row's uniqueness
3. check every column's uniqueness

*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        //to save every grids
        Map<String, Set<Character>> map = new HashMap<>();
        map.put("00", new HashSet<Character>());
        map.put("03", new HashSet<Character>());
        map.put("06", new HashSet<Character>());
        map.put("30", new HashSet<Character>());
        map.put("33", new HashSet<Character>());
        map.put("36", new HashSet<Character>());
        map.put("60", new HashSet<Character>());
        map.put("63", new HashSet<Character>());
        map.put("66", new HashSet<Character>());
        
        //set up the col map
        Map<Integer, Set<Character>> colMap = new HashMap<>();
        for (int col = 0; col < 9; col++){
            colMap.put(col, new HashSet<Character>());
        }
        
        for (int i = 0; i < board.length; i++){
            char[] currentRow = board[i];
            //get the row map
            Set<Character> rowSet  = new HashSet<>();
            //get the current grid's location of X
            int topX = i / 3 * 3;
            for (int j = 0; j < currentRow.length; j++){
                char currentDigit = currentRow[j];
                if (currentDigit != '.'){
                    String whichGrid = Integer.toString(topX);
                    int topY = j / 3 * 3;     
                    //To get the location of the current grid 00 03 06 etc
                    whichGrid += Integer.toString(topY);

                    //check if the current row has the occurred digit, or the column has the occurred digit
                    //or the grid has the occurred digit
                    if (map.get(whichGrid).contains(currentDigit) || rowSet.contains(currentRow[j]) 
                        || colMap.get(j).contains(currentDigit)){
                        return false;
                    }
                    
                    //if not, we add the currentDigit to each set 
                    map.get(whichGrid).add(currentDigit);
                    rowSet.add(currentDigit);
                    colMap.get(j).add(currentDigit); 
                }

            }
        }
        return true;
    }
}