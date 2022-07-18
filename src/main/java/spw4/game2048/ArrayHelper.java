package spw4.game2048;

public class ArrayHelper {
    public static int[] invertArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    public static int[] getColumn(int[][] array, int index){
        int[] column = new int[array[0].length];
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        return column;
    }
}
