package backend;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class Board {
    Tile[][] board;
    String[][] color;
    private static Board item;
    private static int numofwordsonboard;

    public Board() {
        numofwordsonboard=0;
        board = new Tile[15][15];
        color=new String[15][15];
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                this.color[i][j] = "";
            }
        this.color[7][7] = "Yellow";
        this.color[0][0] = "Red";
        this.color[7][0] = "Red";
        this.color[14][0] = "Red";
        this.color[7][0] = "Red";
        this.color[14][7] = "Red";
        this.color[14][0] = "Red";
        this.color[14][14] = "Red";
        this.color[1][1] = "Yellow";
        this.color[2][2] = "Yellow";
        this.color[3][3] = "Yellow";
        this.color[4][4] = "Yellow";
        this.color[13][1] = "Yellow";
        this.color[12][2] = "Yellow";
        this.color[11][3] = "Yellow";
        this.color[10][4] = "Yellow";
        this.color[10][10] = "Yellow";
        this.color[11][11] = "Yellow";
        this.color[12][12] = "Yellow";
        this.color[13][13] = "Yellow";
        this.color[4][10] = "Yellow";
        this.color[1][1] = "Yellow";
        this.color[3][11] = "Yellow";
        this.color[2][12] = "Yellow";
        this.color[1][13] = "Yellow";
        this.color[3][0] = "Blue";
        this.color[11][0] = "Blue";
        this.color[6][2] = "Blue";
        this.color[8][2] = "Blue";
        this.color[0][3] = "Blue";
        this.color[14][3] = "Blue";
        this.color[6][6] = "Blue";
        this.color[8][6] = "Blue";
        this.color[12][6] = "Blue";
        this.color[3][7] = "Blue";
        this.color[11][7] = "Blue";
        this.color[2][8] = "Blue";
        this.color[6][8] = "Blue";
        this.color[8][8] = "Blue";
        this.color[12][8] = "Blue";
        this.color[0][11] = "Blue";
        this.color[14][11] = "Blue";
        this.color[6][12] = "Blue";
        this.color[3][14] = "Blue";
        this.color[11][14] = "Blue";
        this.color[7][7] = "Star";
        this.color[5][1] = "Navy";
        this.color[9][1] = "Navy";
        this.color[5][1] = "Navy";
        this.color[1][5] = "Navy";
        this.color[5][5] = "Navy";
        this.color[9][5] = "Navy";
        this.color[13][5] = "Navy";
        this.color[1][9] = "Navy";
        this.color[5][9] = "Navy";
        this.color[9][9] = "Navy";
        this.color[13][9] = "Navy";
        this.color[5][13] = "Navy";
        this.color[9][13] = "Navy";
    }

    public static Board getBoard() {
        if (item == null)
            item = new Board();
        return item;
    }

    public Tile[][] getTiles() {
        return board.clone();
    }

    public boolean boardLegal(Word word){
        if(board[7][7]==null) {//ifboardempty
            if(Onboard(word) && Onstar(word))
                return true;
            return false;
        }
        else if(Onboard(word) && Overlap(word) && (Onside(word)||Cover(word)))
            return true;
        return false;
    }

    boolean Onboard(Word word){
        int len=word.tiles.length;
        if(!word.vertical)
            if (word.row>=0 && word.col>=0 && word.row<=14 && word.col+len-1<=14)
                    return true;
        if(word.vertical)
            if (word.col >= 0 && word.col <= 14 && word.row >= 0 && word.row + len - 1 <= 14)
                return true;
        return false;
    }
    boolean Onstar(Word word){
        if(!word.vertical)
            if(word.row==7 && word.col<=7 && word.col+word.tiles.length-1>=7)
                return true;
        if(word.vertical)
            if(word.col==7 && word.row<=7 && word.row+word.tiles.length-1>=7)
                return true;
        return false;
    }
    boolean Onside(Word word){
        if(!word.vertical) {
            int col=word.col;
            if (Right(word.row,word.col) || Left(word.row,(word.col)+(word.tiles.length-1)))
                return true;
            for (int i = 0; i < word.tiles.length; i++) {
                if (Up(word.row , col) || Down(word.row , col))
                    return true;
                col++;
            }
        }
        else {
            int row=word.row;
            if(Up(word.row,word.col) || Down((word.row)+(word.tiles.length-1),word.col))
                return true;
            for(int i=0; i<word.tiles.length; i++){
                if(Right(row,word.col) || Left(row,word.col))
                    return true;
                row++;
            }
        }return false;
    }
    boolean Overlap(Word word){
        int row=word.row;
        int col=word.col;
        if(!word.vertical){
            for (int i = 0; i < word.tiles.length; i++) {
                if (board[word.row][col] == null && word.tiles[i] == null)
                    return false;
                if(board[word.row][col]!=null && word.tiles[i]!=null)
                    return false;
                col++;
            }
        }
        else{
            for (int i = 0; i < word.tiles.length; i++){
                if (board[row][word.col] == null && word.tiles[i] == null)
                    return false;
                if (board[row][word.col]!=null && word.tiles[i]!=null)
                    return false;
                row++;
            }
        }return true;
    }
    boolean Cover(Word word){
        if(!word.vertical){
            for (int i = 0; i < word.tiles.length; i++) {
                if (board[word.row][word.col] != null && word.tiles[i] == null)
                    return true;
            }
        }
        else{
            for (int i = 0; i < word.tiles.length; i++){
                if (board[word.row][word.col] != null && word.tiles[i] == null)
                    return true;
            }
        }return false;
    }
    boolean Left(int i, int j){//ifthereistileonleft
        if(i>=0 && i<=14 && j-1>=0 && j-1<=14 && board[i][j-1]!=null)
            return true;
        return false;
    }
    boolean Right(int i, int j){//ifthereistileonright
        if(i>=0 && i<=14 && j+1>=0 && j+1<=14 && board[i][j+1]!=null)
            return true;
        return false;
    }
    boolean Up(int i, int j){//ifthereistileup
        if(i-1>=0 && i-1<=14 && j>=0 && j<=14 && board[i-1][j]!=null)
            return true;
        return false;
    }
    boolean Down(int i, int j){//ifthereistiledown
        if(i+1>=0 && i+1<=14 && j>=0 && j<=14 && board[i+1][j]!=null)
            return true;
        return false;
    }

    public boolean dictionaryLegal(Word word) {
        return true;
    }
    public ArrayList<Word> getWords(Word word){
        Tile[][] tempboard=getTiles();
        putonboard(tempboard,word);
        ArrayList<Word> arrwords=new ArrayList<Word>();
        Word w,ww; Tile[]tiles=new Tile[word.tiles.length];
        if(!word.vertical){
            int col1=word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[word.row][col1]; col1++;
            } ww=new Word(tiles,word.row,word.col,false); arrwords.add(ww);
            if(Left(word.row, word.col)) {
                w = Getfromleft(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Right(word.row, word.col+word.tiles.length-1)) {
                w= Getright(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int col=word.col;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Up(word.row,col) && word.tiles[k]!=null) {
                    w1 = Getfromup(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                else if(Down(word.row, col) && word.tiles[k]!=null) {
                    w1 = Getdown(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                col++;
            }
        }
        else{
            int row1=word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[row1][word.col]; row1++;
            } ww=new Word(tiles,word.row,word.col,true); arrwords.add(ww);
            if(Up(word.row, word.col)) {
                w= Getfromup(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Down(word.row+word.tiles.length-1, word.col)) {
                w=Getdown(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int row=word.row;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Left(row,word.col) && word.tiles[k]!=null) {
                   w1=Getfromleft(tempboard,row, word.col);
                   arrwords.add(w1);
                }
                else if(Right(row, word.col)&& word.tiles[k]!=null) {
                    w1=Getright(tempboard,row, word.col);
                    arrwords.add(w1);
                }
                row++;
            }
        }return arrwords;
   }

    Word Getfromup(Tile[][] tempboard,int i, int j){
        int row=i, countup=0,countdown=0, row1=i;
        while(Up(i,j)){
            countup++;
            i--;
        }
        while(Down(row,j)){
            countdown++;
            row++;
        }
        int first=row1-countup;
        int nrow= row1-countup;
        Tile[] tiles=new Tile[countdown+countup+1];
        for(int k=0; k<countdown+countup+1; k++) {
            tiles[k] = tempboard[nrow][j];
            nrow++;
        } Word word= new Word(tiles, first,j,true);
        return word;
    }
    Word Getdown(Tile[][] tempboard, int i, int j){
        int countdown=0;
        int first=i;
        while(Down(i,j)){
            countdown++;
            i++;
        }
        i=i-countdown;
        Tile[] tiles=new Tile[countdown+1];
        for(int k=0; k<countdown+1; k++){
            tiles[k]=tempboard[i][j];
            i++;
        }Word word=new Word(tiles,first,j,true);
        return word;
    }
    Word Getfromleft(Tile[][] tempbaord,int i, int j){
        int col=j;
        int countleft=0;
        int countright=0;
        while(Left(i,j)){
            countleft++;
            j--;
        }int first=j;
        int ncol=j;
        while(Right(i,col)){
            countright++;
            col++;
        }
        Tile[] tiles=new Tile[countleft+countright+1];
        for(int k=0; k<countleft+countright+1; k++){
            tiles[k]=tempbaord[i][ncol];
            ncol++;
        } Word word=new Word(tiles,i,first,false);
        return word;
    }
    Word Getright(Tile[][] tempboard ,int i, int j){
        int col=j;
        int first=j;
        int counterright=0;
        while (Right(i,j)){
            counterright++;
            j++;
        }Tile[] tiles=new Tile[counterright+1];
        for(int k=0; k<counterright+1; k++){
            tiles[k]= tempboard[i][col];
            col++;
        }
        Word word=new Word(tiles,i,first,false);
        return word;
    }
    int getScore(Word word) {
        int score = 0, yellow = 0, red = 0;
        if (!word.vertical) {
            int col = word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                if (color[word.row][col] == "Blue")
                    score += (word.tiles[i].score * 2);
                else if (color[word.row][col] == "Navy")
                    score += (word.tiles[i].score * 3);
                else if (color[word.row][col] == "Yellow" &&numofwordsonboard!=1) {
                    yellow++; score+=word.tiles[i].score;
                }
                else if (color[word.row][col] == "Red") {
                    red++; score+=word.tiles[i].score;;
                }
                else score += word.tiles[i].score;
                col++;
            }
        } else {
            int row = word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                if (color[row][word.col] == "Blue")
                    score += (word.tiles[i].score * 2);
                else if (color[row][word.col] == "Navy")
                    score += (word.tiles[i].score * 3);
                else if (color[row][word.col] == "Yellow"&&numofwordsonboard!=1) {
                    yellow++; score+=word.tiles[i].score;
                }
                else if (color[row][word.col] == "Red") {
                    red++; score+=word.tiles[i].score;
                }
                else score+=word.tiles[i].score;
                row++;
            }
        }
        if(yellow>0) score= (yellow * (score * 2));
        if(red>0) score= (red * (score * 3));
        if(numofwordsonboard==1)
            score= score*2;
        return score;
    }
    public int tryPlaceWord(Word word) {
        ArrayList<Word> arrwords;
        int score = 0;
        if(numofwordsonboard==0 && boardLegal(word)){
            putonboard(board,word);
            return getScore(word);
        }
        if (boardLegal(word)) {
            arrwords = getWords(word);
            for (int i = 0; i < arrwords.size(); i++)
                if (!dictionaryLegal(arrwords.get(i)))
                    return 0;
            putonboard(board, word);
            for (int i = 0; i < arrwords.size(); i++)
                score += getScore(arrwords.get(i));
            return score;
        }
        return 0;
    }
    void putonboard(Tile[][] board, Word word) {
        if(!word.vertical) {
            int col=word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                if(word.tiles[i]!=null)
                    board[word.row][col] = word.tiles[i];
                col++;
            }
        }
        else{
            int row=word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                if(word.tiles[i]!=null)
                    board[row][word.col] = word.tiles[i];
                row++;
            }
        }numofwordsonboard++;
    }
}