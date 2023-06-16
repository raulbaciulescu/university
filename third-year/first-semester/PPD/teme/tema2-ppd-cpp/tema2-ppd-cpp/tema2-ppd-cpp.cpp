
#include <iostream>
#include <thread>
#include <chrono>
#include <cmath>
#include <vector>
#include <fstream>
#include <mutex>
#include <string>

using namespace std;
using namespace std::chrono;

const int SIZE = 10000;
const char PATH[100] = "D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\sequential-cpp.txt";
int p = 1;
int matrix[SIZE][SIZE];
int kernel[SIZE][SIZE];
int result[SIZE][SIZE];
int buffer[SIZE][SIZE];
int v[SIZE * SIZE];
//int** matrix;
//int** result;
//int** kernel;
//int* v;


class my_barrier
{
    public:
        my_barrier(int count) : thread_count(count), counter(0), waiting(0)
        {}

        void wait()
        {
            //fence mechanism
            std::unique_lock<std::mutex> lk(m);
            ++counter;
            ++waiting;
            cv.wait(lk, [&] {return counter >= thread_count; });
            cv.notify_one();
            --waiting;
            if (waiting == 0)
            {  //reset barrier
                counter = 0;
            }
            lk.unlock();
        }

    private:
        std::mutex m;
        std::condition_variable cv;
        int counter;
        int waiting;
        int thread_count;
};


my_barrier barrier(p);
void read_from_file(int& N, int& M, int& n, int& m)
{
    ifstream fin("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\date.txt");
    fin >> N >> M;
    //cout << N << " " << M << "\n";
    for (int i = 0; i < M; i++)
        for (int j = 0; j < N; j++)
            fin >> matrix[i][j];

    fin >> n >> m;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            fin >> kernel[i][j];

    fin.close();
}

void write_to_file(int m, int n)
{
    ofstream fout(PATH);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            fout << matrix[i][j] << " ";
        fout << "\n";
    }
    
    fout.close();
}

void write_to_file_dynamic(int** a, int m, int n)
{
    ofstream fout(PATH);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            fout << a[i][j] << " ";
        fout << "\n";
    }

    fout.close();
}

void run(int start, int end, int N, int M, int n, int m)
{
    for (int i = start; i < end; i++) {
        int value = 0;
        for (int k = -n / 2; k <= n / 2; k++)
            for (int l = -m / 2; l <= m / 2; l++) {
                int ii = i / N - k;
                int jj = i % N - l;
                if (ii < 0)
                    ii = 0;
                if (jj < 0)
                    jj = 0;
                if (ii >= M)
                    ii = M - 1;
                if (jj >= N)
                    jj = N - 1;
                value += v[ii * N + jj] * kernel[k + n / 2][l + n / 2];
            }

        result[i / N][i % N] = value;
    }
}

void run_dynamic(int start, int end, int N, int M, int n, int m)
{
    for (int i = start; i < end; i++) {
        int value = 0;
        for (int k = -n / 2; k <= n / 2; k++)
            for (int l = -m / 2; l <= m / 2; l++) {
                int ii = i / N - k;
                int jj = i % N - l;
                if (ii < 0)
                    ii = 0;
                if (jj < 0)
                    jj = 0;
                if (ii >= M)
                    ii = M - 1;
                if (jj >= N)
                    jj = N - 1;
                value += v[ii * N + jj] * kernel[k + n / 2][l + n / 2];
            }

        result[i / N][i % N] = value;
    }
}

void do_parallel_operation(int N, int M, int n, int m)
{
    int k = 0;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++)
            v[k++] = matrix[i][j];
    }

    int length = M * N;
    int intreg = length / p;
    int rest = length % p;
    int start = 0;
    int end = intreg;
    vector<thread> threads(p);

    for (int i = 0; i < p; i++)
    {
        if (rest > 0)
        {
            end++;
            rest--;
        }
        threads[i] = thread(run, start, end, N, M, n, m);

        start = end;
        end = start + intreg;
    }

    for (int i = 0; i < p; i++)
        threads[i].join();
}

void run_memory(int startI, int startJ, int endI, int endJ, int N, int M, int n, int m)
{
    int bufferSize = 0;
    int startBufferI, endBufferI;
    int startBufferJ, endBufferJ;
    for (int i = startI - n / 2; i <= endI + n / 2; i++) {
        for (int j = 0; j < N; j++) {
            int ii = i;
            if (ii >= M)
                ii = M - 1;
            if (ii < 0)
                ii = 0;
            buffer[bufferSize][j] = matrix[ii][j];
            if (i == startI) {
                startBufferI = bufferSize;
            }
            if (i == endI) {
                endBufferI = bufferSize;
            }
        }
        bufferSize++;
    }
    startBufferJ = startJ;
    endBufferJ = endJ;

    barrier.wait();

    int iBuff = startBufferI;
    for (int i = startI; i <= endI; i++) 
    {
        for (int j = 0; j < N; j++) 
        {
            int value = 0;
            bool okk = false;
            for (int k = -n / 2; k <= n / 2; k++)
                for (int l = -m / 2; l <= m / 2; l++) 
                {
                    if ((i == startI && j >= startJ) || (i > startI && i < endI) || (i == endI && j < endJ)) 
                    {
                        okk = true;
                        int ii = iBuff - k;
                        int jj = j - l;
                        if (jj < 0)
                            jj = 0;
                        if (jj >= N)
                            jj = N - 1;
                        value += buffer[ii][jj] * kernel[k + n / 2][l + n / 2];
                    }
                }
            if (okk)
                matrix[i][j] = value;
        }
        iBuff++;
    }
}

void do_parallel_memory_operation(int N, int M, int n, int m)
{
    // k = i * N + j
    // i = k / N
    // j = k / M
    int length = M * N;
    int intreg = length / p - 1;
    int fract = length % p;
    int start = 0;
    int end = intreg;
    vector<thread> threads(p);

    for (int i = 0; i < p; i++) 
    {
        if (fract > 0) 
        {
            end++;
            fract--;
        }

        int startI = start / N;
        int startJ = start % N;
        int endI = end / N;
        int endJ = end % N + 1;
        if (endJ == 0)
            endJ = N;

        threads[i] = thread(run_memory, startI, startJ, endI, endJ, N, M, n, m);
        end++;
        start = end;
        end += intreg;
    }

    for (int i = 0; i < p; i++)
        threads[i].join();
}

int main(int argc, char** argv)
{
    //cout << argc << " " << argv[1] << " ";
    //cout << argv[0];
    if (argc > 0)
        p = atoi(argv[1]);

    auto start_time = chrono::high_resolution_clock::now();
    int N, M, n, m;

    read_from_file(N, M, n, m);
    do_parallel_memory_operation(N, M, n, m);
    write_to_file(M, N);

    auto end_time = chrono::high_resolution_clock::now();
    auto duration = chrono::duration<double, milli>(end_time - start_time).count();
    fstream f(PATH, fstream::out | fstream::app);
    f << argv[1] << "\n";
    cout << duration;
}