package org.noip.andrewvasiliev.makemaximum.objects;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by root on 06.11.15.
 */
public class GameBoard {
    private int locFieldSize;
    public StructPlayers players[] = new StructPlayers[2];
    private int puzzle[];
    public boolean vertical_move; //true - игрок должен выбрать ячейку по вертикали; false - по горизонтали
    public int current_position; //текущая координата на поле. если vertical_move = true, то горизонтальная позиция столбца. если vertical_move = false, то вертикальная позиция строки.
    public int current_player; //игрок, который должен сделать ход

    public void GameBoard(int FieldSize, int[] restoredField) {
        locFieldSize = FieldSize;

        puzzle = new int[locFieldSize * locFieldSize];

        players[0] = new StructPlayers();
        players[1] = new StructPlayers();

        if (restoredField != null) {
            //восстанавливаем прошлую игру
            //LoadCurrentStatus();
        } else {
            puzzle = getPuzzle(locFieldSize);
            vertical_move = true;
            current_position = (locFieldSize - 1) / 2;
            players[0].name = "Игрок 1";
            players[0].points = 0;
            players[1].name = "Игрок 2";
            players[1].points = 0;
            current_player = 0;
        }


    }

    private int[] getPuzzle(int fs) {
        int[] puz;
        int count,        //количество повторений в поле для каждого значения (1,2,3,4,5,6,7,8,9)
                step,     //случайный шаг для заполнения следующей позиции значением
                currPos,  //текущая позиция
                maxIndex, //максимальная позиция в массиве
                posLeft = 0;  //количество оставшихся незаполненных позиций


        Random locRandom = new Random();

        puz = new int[fs * fs];
        currPos = 0;
        maxIndex = fs * fs - 1;
        Arrays.fill(puz, ' ');
        puz[maxIndex / 2] = 0;
        count = (fs * fs) / 9;

        //перебираем все значения для игрового поля (1..9)
        int val = 0;
        while (true) {
            val++;
            if (val == 10) {
                val = 1;
            }

            //распологаем случайным образом значение VAL на игровом поле
            posLeft = 0;
            step = locRandom.nextInt(maxIndex) + 1;
            int k = currPos;
            while (true) {
                k++;
                if (k > maxIndex) {
                    k = 0;
                }
                if ((k == currPos) && (posLeft == 0)) {
                    //мы прошли по кругу и пустых мест уже нет. все заполнено.
                    break;
                }
                if (puz[k] == ' ') {
                    posLeft++;
                    step--;
                    if (step == 0) {
                        //помещаем на поле значение
                        puz[k] = val;
                        currPos = k;
                        break;
                    }
                }
            }

            if (posLeft == 0) {
                //все поле заполнено. выходим.
                break;
            }
        }
        return puz;
    }

    /** Return the tile at the given coordinates */
    public int getTile(int x, int y) {
        return puzzle[y * locFieldSize + x];
    }

    /** Change the tile at the given coordinates */
    private void setTile(int x, int y, int value) {
        puzzle[y * locFieldSize + x] = value;
    }

    protected int getfieldSize() {
        return (locFieldSize);
    }

    /** Return a string for the tile at the given coordinates */
    protected String getTileString(int x, int y) {
        int v = getTile(x, y);
        if (v == 0)
            return "";
        else
            return String.valueOf(v);
    }

    private boolean CheckEndGame () {
        int count = 0;
        for (int i = 0; i < locFieldSize; i++) {
            if (vertical_move) {
                if (getTile(current_position, i) != 0)
                    count++;
            } else {
                if (getTile(i, current_position) != 0)
                    count++;
            }
        }
        if (count>0)
            return false;
        return true;
    }

    public String getPlayerName(int i){
        return players[i].name;
    }

    public int getPlayerScore(int i){
        return players[i].points;
    }

    public void playerMove (/*int movePosition*/ int x, int y){
        int moveX, moveY;
        moveX = x;
        moveY = y;
        /*
        if (vertical_move) {
            moveX = current_position;
            moveY = movePosition;
        } else {
            moveX = movePosition;
            moveY = current_position;
        }*/
        players[current_player].points += getTile(moveX, moveY);
        setTile(moveX, moveY, 0);
        current_player ^= 1;
        //current_position = movePosition;

        current_position = vertical_move?y:x;
        vertical_move = !vertical_move;
    }

    public boolean isCorrectMove (int x, int y) {
        if ( (getTile(x,y) != 0) && (vertical_move?(current_position==x):(current_position==y)) ) {
            return true;
        }
        return false;
    }
}
