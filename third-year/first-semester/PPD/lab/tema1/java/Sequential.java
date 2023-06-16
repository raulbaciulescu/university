import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Sequential extends Worker {
    @Override
    public void doOperation() {
        result = new int[M][N];

        long startTime = System.nanoTime();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int value = 0;
                for (int k =  - n / 2; k <= n / 2; k++)
                    for (int l = - m / 2; l <= m / 2; l++) {
                        int ii = i - k;
                        int jj = j - l;
                        if (ii < 0)
                            ii = 0;
                        if (jj < 0)
                            jj = 0;
                        if (ii >= M)
                            ii = M - 1;
                        if (jj >= N)
                            jj = N - 1;

                        value += matrix[ii][jj] * kernel[k + n / 2][l + n / 2];
                    }
                result[i][j] = value;
            }
        }
        long endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime)/1E6);//ms
    }
}
