import java.util.Arrays;

/**
 * Make sure that the equal method consider
 * two QR codes as identical if they have been flipped
 * by 0,1,2 or 3 quarter rotations
 *
 * For instance the two following matrices should be
 * considered equals if you flip your head by 180 degrees
 *
 *         boolean [][] t0 = new boolean[][] {
 *                 {false,true,false,false},
 *                 {false,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,true}
 *         };
 *
 *         // t2 is a version of t2 with two quarter rotations
 *         boolean [][] t2 = new boolean[][] {
 *                 {true,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,false},
 *                 {false,false,true,false}
 *         };
 */
public class QRcode {

    protected boolean [][] data;

    /**
     * Create a QR code object
     * @param data is a n x n square matrix
     */
    public QRcode(boolean [][] data) {
        this.data = data;
    }

    /**
     * Return true if the other matrix is identical up to
     * 0, 1, 2 or 3 number of rotations
     * @param o the other matrix to compare to
     * @return
     */
    @Override
    public boolean equals(Object o) {
        // TODO


        QRcode QR = (QRcode) o;
        boolean[][] QRData = QR.data;

        int n = this.data.length;

        //No rotation test

        boolean noRotationTest = true;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.data[i][j] != QRData[i][j]){
                    noRotationTest = false;
                    break;
                }
            }
            if (! noRotationTest){
                break;
            }
        }
        if (noRotationTest){
            return true;
        }

        // 90° rotation test

        boolean rotationTest1 = true;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.data[i][j] != QRData[j][n-1-i]){
                    rotationTest1 = false;
                    break;
                }
            }
            if (! rotationTest1){
                break;
            }
        }
        if (rotationTest1){
            return true;
        }

        // 180° rotation test

        boolean rotationTest2 = true;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.data[i][j] != QRData[n-1-i][n-1-j]){
                    rotationTest2 = false;
                    break;
                }
            }
            if (! rotationTest2){
                break;
            }
        }
        if (rotationTest2){
            return true;
        }


        // 270° rotation test

        boolean rotationTest3 = true;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.data[i][j] != QRData[n-1-j][i]){
                    rotationTest3 = false;
                    break;
                }
            }
            if (! rotationTest3){
                break;
            }
        }
        if (rotationTest3){
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        QRcode qr1 = new QRcode(new boolean[][]{{false, false, true},{true, false, false},{true, true, true}});
        QRcode qr2 = new QRcode(new boolean[][]{{true, false, false},{false, false, false},{true, true, true}});

        QRcode qr3 = new QRcode(new boolean[][]{{true, false, true},{true, false, true},{true, false, true}});
        QRcode qr4 = new QRcode(new boolean[][]{{true, false, true},{true, false, true},{true, false, true}});

        System.out.println(qr2.equals(qr1));
        System.out.println(qr3.equals(qr4));
    }
}
