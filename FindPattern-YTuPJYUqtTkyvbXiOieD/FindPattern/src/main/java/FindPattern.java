public class FindPattern {

    public static boolean compareTable(int[] table1, int[] table2){
        for (int i = 0; i < table1.length; i++){
            if (table1[i] != table2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int find(int [] pattern, int [] sequence) {
        int[] patternCount = new int[16];

        for (int i : pattern){
            patternCount[i]++;
        }


        for (int i = 0; i < sequence.length - pattern.length + 1; i++){
            int[] tab = new int[16];
            for (int j = 0; j < pattern.length; j++){
                tab[sequence[i+j]]++;
            }
            if (compareTable(patternCount, tab)){
                return i;
            }
        }
        return -1;
    }
}
