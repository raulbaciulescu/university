
#include <iostream>
#include <thread>
#include <chrono>
#include <cmath>
#include <vector>
#include <fstream>

using namespace std;
using namespace std::chrono;

const int SIZE = 10000;
const char PATH[100] = "D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\sequential-cpp.txt";
const int p = 1;
int matrix[SIZE][SIZE];
int kernel[SIZE][SIZE];
int result[SIZE][SIZE];
int v[SIZE * SIZE];
//int** matrix;
//int** result;
//int** kernel;
//int* v;


void read_from_file(int &N, int& M, int& n, int& m)
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

//void read_from_file_dynamic(int& N, int& M, int& n, int& m)
//{
//    ifstream fin("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\date.txt");
//    fin >> N >> M;
//    matrix = new int* [M];
//    for (int i = 0; i < M; i++)
//        matrix[i] = new int[N];
//
//    kernel = new int* [n];
//    for (int i = 0; i < n; i++)
//        kernel[i] = new int[m];
//
//    //cout << N << " " << M << "\n";
//    for (int i = 0; i < M; i++)
//        for (int j = 0; j < N; j++)
//            fin >> matrix[i][j];
//
//    fin >> n >> m;
//    for (int i = 0; i < n; i++)
//        for (int j = 0; j < m; j++)
//            fin >> kernel[i][j];
//
//    fin.close();
//}

//void write_to_file(int a[SIZE][SIZE], int m, int n)
//{
//    ofstream fout(PATH);
//    for (int i = 0; i < m; i++)
//    {
//        for (int j = 0; j < n; j++)
//            fout << a[i][j] << " ";
//        fout << "\n";
//    }
//    
//    fout.close();
//}

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

//void print_matrix(int a[SIZE][SIZE], int m, int n)
//{
//    for (int i = 0; i < m; i++)
//    {
//        for (int j = 0; j < n; j++)
//            cout << a[i][j] << " ";
//        cout << "\n";
//    }
//}

void do_sequential_operation_dynamic(int N, int M, int n, int m)
{
    int** result = new int* [m];
    for (int i = 0; i < m; i++)
        result[i] = new int[n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int value = 0;
            for (int k = -n / 2; k <= n / 2; k++)
                for (int l = -m / 2; l <= m / 2; l++) {
                    int ii = i - k;
                    int jj = j - l;
                    if (ii < 0)
                        ii = 0;
                    if (jj < 0)
                        jj = 0;
                    if (ii >= m)
                        ii = m - 1;
                    if (jj >= n)
                        jj = n - 1;

                    value += matrix[ii][jj] * kernel[k + n / 2][l + n / 2];
                }
            result[i][j] = value;
        }
    }

    for (int i = 0; i < m; i++)
        delete[] result[i];
    delete[] result;
}

void do_sequential_operation(int N, int M, int n, int m)
{
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            int value = 0;
            for (int k = -n / 2; k <= n / 2; k++)
                for (int l = -m / 2; l <= m / 2; l++) {
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
}

void run(int start, int end, int N, int M, int n, int m)
{
    for (int i = start; i < end; i++) {
            int value = 0;
            for (int k =  - n / 2; k <= n / 2; k++)
                for (int l = - m / 2; l <= m / 2; l++) {
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

void do_parallel_operation_dynamic(int N, int M, int n, int m)
{
    //int k = 0;
    //v = new int[M * N];
    //for (int i = 0; i < M; i++) {
    //    for (int j = 0; j < N; j++)
    //        v[k++] = matrix[i][j];
    //}

    //result = new int* [M];
    //for (int i = 0; i < M; i++)
    //    result[i] = new int[N];

    //int length = M * N;
    //int intreg = length / p;
    //int rest = length % p;
    //int start = 0;
    //int end = intreg;
    //vector<thread> threads(p);

    //for (int i = 0; i < p; i++)
    //{
    //    if (rest > 0)
    //    {
    //        end++;
    //        rest--;
    //    }
    //    threads[i] = thread(run_dynamic, start, end, N, M, n, m);

    //    start = end;
    //    end = start + intreg;
    //}

    //for (int i = 0; i < p; i++)
    //    threads[i].join();

    //delete[] v;
    //for (int i = 0; i < M; i++)
    //    delete[] result[i];
    //delete[] result;
}

int main(int argc, char** argv)
{
    //if (argc > 0)
    // p = int(argv[0])
    auto start_time = chrono::high_resolution_clock::now();
	int N, M, n, m;

    read_from_file(N, M, n, m);
    ////do_sequential_operation(N, M, n, m, matrix, kernel);
    do_parallel_operation(N, M, n, m);


    //ifstream fin("D:\\Facultate\\PPD\\teme\\tema1\\src\\main\\resources\\date.txt");
    //fin >> N >> M;
    //matrix = new int* [M];
    //for (int i = 0; i < M; i++)
    //    matrix[i] = new int[N];

    //for (int i = 0; i < M; i++)
    //    for (int j = 0; j < N; j++)
    //        fin >> matrix[i][j];

    //fin >> n >> m;
    //kernel = new int* [n];
    //for (int i = 0; i < n; i++)
    //    kernel[i] = new int[m];
    //for (int i = 0; i < n; i++)
    //    for (int j = 0; j < m; j++)
    //        fin >> kernel[i][j];

    ////do_sequential_operation_dynamic(N, M, n, m, matrix, kernel);
    //do_parallel_operation_dynamic(N, M, n, m);
    //for (int i = 0; i < n; i++)
    //    delete[] kernel[i];
    //delete[] kernel;

    //for (int i = 0; i < M; i++)
    //    delete[] matrix[i];
    //delete[] matrix;


    auto end_time = chrono::high_resolution_clock::now();
    auto duration = chrono::duration<double, milli>(end_time - start_time).count();
    fstream f(PATH, fstream::out | fstream::app);
    f << duration << "\n";
    cout << duration;
}