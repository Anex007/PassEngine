package Pass_Engine.Hash;

import java.util.Random;

public class ProcessKey {

    final long Key;
    final long Choose;

    public ProcessKey(long key, long choose){
        this.Key = key;
        this.Choose = choose;
    }

    public String getRand(){
        Random rand4Chos = new Random(Choose);
        Random rand4Key = new Random(Key);

        int sub1;
        String rand = "";

        for (int i=0; i<12; i++){
            sub1 = rand4Key.nextInt(51);
            if(rand4Chos.nextBoolean()){
                rand+=getChar(sub1);
            }else {
                rand+=sub1;
            }
        }

        return rand;
    }

    public char getChar(int n){
        switch (n){
            case 0:
                return 'a';
            case 1:
                return 'A';
            case 2:
                return 'b';
            case 3:
                return 'B';
            case 4:
                return 'c';
            case 5:
                return 'C';
            case 6:
                return 'd';
            case 7:
                return 'D';
            case 8:
                return 'e';
            case 9:
                return 'E';
            case 10:
                return 'f';
            case 11:
                return 'F';
            case 12:
                return 'g';
            case 13:
                return 'G';
            case 14:
                return 'h';
            case 15:
                return 'H';
            case 16:
                return 'i';
            case 17:
                return 'I';
            case 18:
                return 'j';
            case 19:
                return 'J';
            case 20:
                return 'k';
            case 21:
                return 'K';
            case 22:
                return 'l';
            case 23:
                return 'L';
            case 24:
                return 'm';
            case 25:
                return 'M';
            case 26:
                return 'n';
            case 27:
                return 'N';
            case 28:
                return 'o';
            case 29:
                return 'O';
            case 30:
                return 'p';
            case 31:
                return 'P';
            case 32:
                return 'q';
            case 33:
                return 'Q';
            case 34:
                return 'r';
            case 35:
                return 'R';
            case 36:
                return 's';
            case 37:
                return 'S';
            case 38:
                return 't';
            case 39:
                return 'T';
            case 40:
                return 'u';
            case 41:
                return 'U';
            case 42:
                return 'v';
            case 43:
                return 'V';
            case 44:
                return 'w';
            case 45:
                return 'W';
            case 46:
                return 'x';
            case 47:
                return 'X';
            case 48:
                return 'y';
            case 49:
                return 'Y';
            case 50:
                return 'z';
            case 51:
                return 'Z';
            default:
                return '0';
        }
    }

    public int getInt(char cr){
        switch (cr){
            case 'a':
                return 0;
            case 'A':
                return 1;
            case 'b':
                return 2;
            case 'B':
                return 3;
            case 'c':
                return 4;
            case 'C':
                return 5;
            case 'd':
                return 6;
            case 'D':
                return 7;
            case 'e':
                return 8;
            case 'E':
                return 9;
            case 'f':
                return 10;
            case 'F':
                return 11;
            case 'g':
                return 12;
            case 'G':
                return 13;
            case 'h':
                return 14;
            case 'H':
                return 15;
            case 'i':
                return 16;
            case 'I':
                return 17;
            case 'j':
                return 18;
            case 'J':
                return 19;
            case 'k':
                return 20;
            case 'K':
                return 21;
            case 'l':
                return 22;
            case 'L':
                return 23;
            case 'm':
                return 24;
            case 'M':
                return 25;
            case 'n':
                return 26;
            case 'N':
                return 27;
            case 'o':
                return 28;
            case 'O':
                return 29;
            case 'p':
                return 30;
            case 'P':
                return 31;
            case 'q':
                return 32;
            case 'Q':
                return 33;
            case 'r':
                return 34;
            case 'R':
                return 35;
            case 's':
                return 36;
            case 'S':
                return 37;
            case 't':
                return 38;
            case 'T':
                return 39;
            case 'u':
                return 40;
            case 'U':
                return 41;
            case 'v':
                return 42;
            case 'V':
                return 43;
            case 'w':
                return 44;
            case 'W':
                return 45;
            case 'x':
                return 46;
            case 'X':
                return 47;
            case 'y':
                return 48;
            case 'Y':
                return 49;
            case 'z':
                return 50;
            case 'Z':
                return 51;
            default:
                return -1;
        }
    }
}