package satellite;

import java.io.*;

public class Satellite {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            int[] dims = getSize(in);
            int[][] oldPic = getPic(in, dims[0], dims[1]);
            int[][] newPic = getPic(in, dims[0], dims[1]);
            in.close();
            showResult(oldPic, newPic);
        } catch (IOException ex) {
            System.err.println("Error reading input file: " + ex.getMessage());
        }
    }

    public static int[] getSize(BufferedReader in) throws IOException {
        String[] tokens = in.readLine().trim().split("\\s+");
        return new int[]{Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
    }

    public static int[][] getPic(BufferedReader in, int rows, int cols) throws IOException {
        int[][] pic = new int[rows][cols];
        for (int r = 0; r < rows; r++) readLine(pic[r], in.readLine());
        return pic;
    }

    public static void readLine(int[] row, String text) {
        String[] tokens = text.trim().split("\\s+");
        for (int c = 0; c < row.length; c++) row[c] = Integer.parseInt(tokens[c]);
    }

    public static int[] getRect(int[][] oldPic, int[][] newPic) {
        int[] rect = {oldPic.length, -1, oldPic[0].length, -1};
        for (int r = 0; r < oldPic.length; r++) {
            for (int c = 0; c < oldPic[0].length; c++) {
                if (oldPic[r][c] != newPic[r][c]) changeRect(rect, r, c);
            }
        }
        return rect;
    }

    public static void changeRect(int[] rect, int r, int c) {
        if (r < rect[0]) rect[0] = r;
        if (r > rect[1]) rect[1] = r;
        if (c < rect[2]) rect[2] = c;
        if (c > rect[3]) rect[3] = c;
    }

    public static void showResult(int[][] oldPic, int[][] newPic) {
        int[] rect = getRect(oldPic, newPic);
        if (rect[0] > rect[1]) System.out.println("The two images are the same");
        else System.out.println((rect[0]+1) + " " + (rect[2]+1) + " " + (rect[1]+1) + " " + (rect[3]+1));
    }
}