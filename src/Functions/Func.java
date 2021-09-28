package src.Functions;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.String;

public class Func{
    /* READ-WRITE FILES & DISPLAY RESULT */
    public static Matrix inputMatrix(String inputType){
        int m, n;
        String strm, strn;

        if (inputType == "SPL"){
            while(true){
                strm = JOptionPane.showInputDialog(null, "Enter number of rows: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strm == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                strn = JOptionPane.showInputDialog(null, "Enter number of columns: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strn == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                else{
                    break;
                }
            }
            m = Integer.parseInt(strm);
            n = Integer.parseInt(strn);
        }
        else if (inputType == "Determinan" || inputType == "Invers"){
            while(true){
                strm = JOptionPane.showInputDialog(null, "Enter size of matrix (n x n): ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strm == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                else{
                    break;
                }
            }
            m = Integer.parseInt(strm);
            n = m;
        }
        else if (inputType == "Interpolasi"){
            while(true){
                strm = JOptionPane.showInputDialog(null, "Enter number of points: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strm == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                else{
                    break;
                }
            }
            m = Integer.parseInt(strm);
            n = 2;
        }
        else{
            while(true){
                strm = JOptionPane.showInputDialog(null, "Enter banyak data: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strm == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                strn = JOptionPane.showInputDialog(null, "Enter banyak variabel peubah: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                if (strn == null){
                    Interface.user();
                }
                else if (!strm.matches("[0-9]*") || strm.contains(" ")){
                    continue;
                }
                else{
                    break;
                }
            }
            m = Integer.parseInt(strm);
            n = Integer.parseInt(strn);
        }

        Matrix matrix = new Matrix(m, n);
        String str;
        int i, j;
        for (i = 0; i <= getLastIdxRow(matrix); i++){
            for (j = 0; j <= getLastIdxCol(matrix); j++){
                while(true){
                    if (inputType == "Regresi"){
                        if (j == getLastIdxCol(matrix)){
                            str = JOptionPane.showInputDialog(null, "Data ke-" + (i+1) + "\nEnter y: ", "Input Matrix", JOptionPane.PLAIN_MESSAGE); 
                        }
                        else{
                            str = JOptionPane.showInputDialog(null, "Data ke-" + (i+1) + "\nEnter x" + (j+1) + ": ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                        }
                        if (str == null){
                            Interface.user();
                        }
                        else if (str.matches("[0-9.]*") && !str.contains(" ")){
                            break;
                        }
                    }
                    else{
                        str = JOptionPane.showInputDialog(null, "Enter elemen matrix pada posisi (" + (i+1) + ", " + (j+1) + "): ", "Input Matrix", JOptionPane.PLAIN_MESSAGE);
                        if (str == null){
                            Interface.user();
                        }
                        else if (str.matches("[0-9.]*") && !str.contains(" ")){
                            break;
                        }
                    }
                }
                double elmt = Double.parseDouble(str);
                setElmt(matrix, i, j, elmt);
            }
        }
        return matrix;
    }

    public static Matrix readMatrix(){
        String filename = JOptionPane.showInputDialog(null, "Enter filename:", "Read File", JOptionPane.PLAIN_MESSAGE);
        try {
            ArrayList<String> num = new ArrayList<String>();
            int rowcount = 0, colcount = 0;
            String filePath = ".\\test\\" + filename + ".txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String rl = br.readLine();
            while (rl != null){
                String[] rls = rl.split(" ");
                for (String x : rls){
                    num.add(x);
                }
                rowcount++;
                colcount = rls.length;
                rl = br.readLine();
            }
            br.close();
            Matrix m = new Matrix(rowcount, colcount);
            int i, j, par = 0;
            for (i = 0; i < rowcount; i++){
                for (j = 0; j < colcount; j++){
                    setElmt(m, i, j, Double.parseDouble(num.get(par)));
                    par++;
                }
            }
            return m;
        }
        catch (Exception ie) {
            JOptionPane.showMessageDialog(null, "Oops! Seems like we couldn't read your file.", "Uh-Oh...", JOptionPane.ERROR_MESSAGE);
            //ie.printStackTrace();
            Matrix m = new Matrix(0,0);
            Interface.user();
            return m;
        }
    }

    public static void writeMatrix(Matrix m){
        String filename = JOptionPane.showInputDialog(null, "Save file name as ______.txt (fill in d'blankz)", JOptionPane.PLAIN_MESSAGE);
        try {
            FileWriter writer = new FileWriter(".\\test\\" + filename + ".txt");
            int i, j;
            for (i = 0; i <= getLastIdxRow(m); i++){
                for (j = 0; j <= getLastIdxCol(m); j++) {
                    if (j != getLastIdxCol(m)){
                        writer.write(getElmt(m, i, j) + " ");
                    }
                    else{
                        writer.write(String.valueOf(getElmt(m, i, j)));
                    }
                }
                writer.write("\n");
            }        
            writer.close();
            JOptionPane.showMessageDialog(null, "Save berhasil!", "File Saved", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occured", "Uh-Oh...", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void displayMatrix(Matrix m){
        int i, j;
        for (i = 0; i <= getLastIdxRow(m); i++){
            for (j = 0; j <= getLastIdxCol(m); j++) {
                if (j != getLastIdxCol(m)){
                    System.out.print(getElmt(m, i, j) + " ");
                }
                else{
                    System.out.print(getElmt(m, i, j));
                }
            }
            System.out.print("\n");
        }
    }

    /* GET & SET */
    public static double getElmt(Matrix m, int i, int j){
        return m.contents[i][j];
    }

    public static void setElmt(Matrix m, int i, int j, double fill){
        m.contents[i][j] = fill;
    }

    public static int getLastIdxRow(Matrix m){
        return m.rows - 1;
    }

    public static int getLastIdxCol(Matrix m){
        return m.cols - 1;
    }

    /* OPERASI BARIS ELEMENTER */
    // parameter row, BUKAN idx row
    public static void multiplyOBE(Matrix m, int row, double multiplier){
        int j;
        for (j = 0; j <= getLastIdxCol(m); j++){
            double newElmt = getElmt(m, row - 1, j) * multiplier;
            setElmt(m, row - 1, j, newElmt);
        }
    }
    
    public static void divideOBE(Matrix m, int row, double divider) {
        int j;
        for (j = 0; j <= getLastIdxCol(m); j++) {
            double newElmt = getElmt(m, row - 1, j) / divider;
            setElmt(m, row - 1, j, newElmt);
        }
    }

    public static void switchOBE(Matrix m, int row1, int row2){
        double[] temp = m.contents[row1 - 1];
        m.contents[row1 - 1] = m.contents[row2 - 1];
        m.contents[row2 - 1] = temp;
    }

    public static void addOBE(Matrix m, int row1, int row2, double multiplier){
        int j;
        double addedVal;
        for (j = 0; j <= getLastIdxCol(m); j++){
            addedVal = getElmt(m, row2 - 1, j) * multiplier;
            setElmt(m, row1 - 1, j, getElmt(m, row1 - 1, j) + addedVal);
        }
    }

    /* VALIDASI */
    public static boolean isSizeEqual(Matrix m1, Matrix m2){
        return ((m1.rows == m2.rows) && (m1.cols == m2.cols)); 
    }

    public static boolean isEqual(Matrix m1, Matrix m2){
        if (!(isSizeEqual(m1, m2))){
            return false;
        }
        else{
            int i, j;
            for (i = 0; i <= getLastIdxRow(m1); i++) {
                for (j = 0; j <= getLastIdxCol(m1); j++) {
                    if (getElmt(m1, i, j) != getElmt(m2, i, j)){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static boolean isSquare(Matrix m){
        return (m.rows == m.cols);
    }

    /* MANIPULASI MATRIX LAINNYA */
    public static Matrix add(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows, m1.cols);
        int i, j;
        for (i = 0; i <= getLastIdxRow(m1); i++) {
            for (j = 0; j <= getLastIdxCol(m1); j++) {
                setElmt(m3, i, j, (getElmt(m1, i, j) + getElmt(m2, i, j)));
            }
        }
        return m3;
    }

    public static Matrix subtract(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows, m1.cols);
        int i, j;
        for (i = 0; i <= getLastIdxRow(m1); i++) {
            for (j = 0; j <= getLastIdxCol(m1); j++) {
                setElmt(m3, i, j, (getElmt(m1, i, j) - getElmt(m2, i, j)));
            }
        }
        return m3;
    }

    public static Matrix multiply(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows, m2.cols);
        int i, j;
        for (i = 0; i <= getLastIdxRow(m3); i++){
            for (j = 0; j <= getLastIdxCol(m3); j++){
                int x, temp;
                temp = 0;
                for (x = 0; x < m1.cols; x++){
                    temp += (getElmt(m1, i, x) * getElmt(m2, x, j));
                }
                setElmt(m3, i, j, temp);
                temp = 0;
            }
        }
        return m3;
    }
}
