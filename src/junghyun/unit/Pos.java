package junghyun.unit;

public class Pos {

    private int compuX = 0;
    private int compuY = 0;

    private char humX = 0;
    private int humY = 0;

    private String errorStr = null;
    private boolean isError = false;

    public Pos(int x, int y) {
        this.compuX = x;
        this.compuY = y;

        this.humX = Pos.intToEng(this.compuX);
        this.humY = this.compuY +1;
    }

    public Pos(String str) {
        this.isError = true;
        this.errorStr = str;
    }

    //기능

    public static int engToInt(char str) {
        return str-97;
    }

    public static char intToEng(int inter) {
        return (char) ((char) inter+97);
    }

    public static boolean checkSize(int x, int y) {
        return (x >= 0) && (x <= 14) && (y >= 0) && (y <= 14);
    }

    //데이터 출력

    public int getCompuX() {
        return this.compuX;
    }

    public int getCompuY() {
        return this.compuY;
    }

    public char getHumX() {
        return this.humX;
    }

    public int getHumY() {
        return this.humY;
    }

    public boolean isError() {
        return this.isError;
    }

    public String getErrorStr() {
        return this.errorStr;
    }

}
