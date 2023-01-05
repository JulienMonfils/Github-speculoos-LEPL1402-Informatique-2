import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// You are allowed to add imports here

public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        int row;
        List<Integer> columns;
    }


    public static List<Integer> analyseRow(int[] row, int value){
        ArrayList<Integer> l = new ArrayList<>();

        for (int i = 0; i < row.length; i++){
            if (row[i] == value){
                l.add(i);
            }
        }
        return l;
    }

    public static Result findValue(int[][] matrix, int value, int poolSize) {
        // TODO
        // Hint:
        // One row of the matrix -> One future.



        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        List<Future<List<Integer>>> futureList = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++){
            int j = i;
            futureList.add(executor.submit(() -> analyseRow(matrix[j], value)));
        }

        int maxIndex = 0;
        int maxVal = 0;
        List<Integer> maxList = null;

        for (int i = 0; i < futureList.size(); i++){
            try{
                List<Integer> res = futureList.get(i).get();
                if (res != null){
                    if (res.size() > maxVal){
                        maxIndex = i;
                        maxVal = res.size();
                        maxList = res;
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        Result result = new Result();
        result.columns = maxList;
        result.row = maxIndex;

        executor.shutdown();

        return result;
    }
}
