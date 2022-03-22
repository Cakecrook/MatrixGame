package MatrixGame.model;

public class Cell {
    public static final int CELL_STRING_SIZE = 4;

    private int value;
    private int[] coordinate;
    private boolean taken;
    private String[] printStringArr;
    private String[] options;

    public int getValue() { return value; }
    public boolean isTaken() { return taken; }
    public void setTaken(boolean taken) { this.taken = taken; }
    public String[] getPrintStringArr() { return printStringArr; }
    public void setOption(int i, String s) { this.options[i] = s; }

    /*
     * Unused getters and setters
     * public void setValue(int value) { this.value = value; }
     * public int[] getCoordinate() { return coordinate; }
     * public void setCoordinate(int[] coordinate) { this.coordinate = coordinate; }
     */

    /**
     * Sets the elements in the Cell's printStringArr field equal to specific strings so that the cell looks nice when
     * printed
     */
    public void setPrintStringArr() {
        if (!this.isTaken()) {
            this.printStringArr[0] = this.options[0] + "--------" + this.options[1] + " ";
            this.printStringArr[1] = "| Val: " + this.value + " | ";
            this.printStringArr[2] = "| (" + this.coordinate[0] + ", " + this.coordinate[1] + ") | ";
            this.printStringArr[3] = this.options[2] + "--------" + this.options[3] + " ";
        }
        else {
            this.printStringArr[0] = "#--------# ";
            this.printStringArr[1] = "|        | ";
            this.printStringArr[2] = "|        | ";
            this.printStringArr[3] = "#--------# ";
        }
    }

    /**
     * Add an option to the Cell's list of options
     * @param opt char to be added
     */
    public void addOption(String opt) {
        switch (opt) {
            case "a":
                this.options[0] = "a";
                break;
            case "b":
                this.options[1] = "b";
                break;
            case "c":
                this.options[3] = "c";
                break;
            case "d":
                this.options[2] = "d";
                break;
            default:
                System.out.println("[ERROR] Invalid argument in addOption");
        }
    }

    /**
     * Creates a Cell object with value v and coordinate c
     * @param v int to be assigned to the Cell's value field
     * @param c int[] to be assigned to the Cell's coordinate field
     */
    Cell(int v, int[] c) {
        this.value = v;
        this.coordinate = c;
        this.taken = false;
        this.options = new String[4];
        this.options[0] = "#"; this.options[1] = "#"; this.options[2] = "#"; this.options[3] = "#";
        this.printStringArr = new String[CELL_STRING_SIZE];
    }
}
