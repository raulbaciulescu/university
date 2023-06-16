#include <iostream>
#include <mpi.h>
#include <fstream>
#include <chrono>
using namespace std;

void print_vector(int x[], int n)
{
    for (int i = 0; i < n; i++)
        cout << x[i] << " ";
    cout << "\n";
}

int main()
{
    MPI_Status status;
    int p, my_rank;
    int bound = 10;
    srand(time(NULL));
    //int a[100001], b[100001], c[10001];
    int* a = new int[100001];
    int* b = new int[100001];
    int* c = new int[100001];
    int n1, n2, nmax, nmax2;

    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);

    int maxim_offset = 0;
    int* starts = new int[p];
    int* offsets = new int[p];
    int* aux_a;
    int* aux_b;
    int* aux_c;
    
    chrono::high_resolution_clock::time_point start_time;
    if (my_rank == 0)
    {
        start_time = chrono::high_resolution_clock::now();
        ifstream fin("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar1.txt");
        ifstream fin2("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar2.txt");
        //fin >> n1;
        //for (int i = n1 - 1; i >= 0; i--)
        //    fin >> a[i];
        //
        //fin2 >> n2;
        //for (int i = n2 - 1; i >= 0; i--)
        //    fin2 >> b[i];
        n1 = 1000;
        for (int i = 0; i < n1; i++)
            a[i] = rand() % bound;
        n2 = 1000;
        for (int i = 0; i < n2; i++)
            b[i] = rand() % bound;


        nmax = n1 > n2 ? n1 : n2;
        if (nmax % p != 0)
        {
            nmax = nmax + (p - nmax % p);
        }

        for (int i = n2; i < nmax; i++)
            b[i] = 0;
        for (int i = n1; i < nmax; i++)
            a[i] = 0;

        for (int i = 1; i < p; i++)
            MPI_Send(&nmax, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
    }
    if (my_rank != 0)
    {
        MPI_Recv(&nmax, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
    }
    
    int intreg = nmax / p;
    int start = 0, end = intreg;
    maxim_offset = end - start;
    aux_a = new int[maxim_offset];
    aux_b = new int[maxim_offset];
    aux_c = new int[maxim_offset + 5];


    for (int i = 0; i < p; i++)
    {
        starts[i] = start;
        offsets[i] = end - start;

        start = end;
        end += intreg;
    }

    MPI_Scatterv(a, offsets, starts, MPI_INT, aux_a, maxim_offset, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatterv(b, offsets, starts, MPI_INT, aux_b, maxim_offset, MPI_INT, 0, MPI_COMM_WORLD);

    int carry = 0;
    if (my_rank > 0)
    {
        //MPI_Recv(valoare, dimensiune, type, sursa, world, status)
        MPI_Recv(&carry, 1, MPI_INT, my_rank - 1, 0, MPI_COMM_WORLD, &status);
    }
    for (int i = 0; i < offsets[my_rank]; i++)
    {
        aux_c[i] = (aux_a[i] + aux_b[i] + carry) % 10;
        if (aux_a[i] + aux_b[i] + carry > 9)
            carry = 1;
        else
            carry = 0;
    }
    if (my_rank == p - 1)
    {
        aux_c[offsets[my_rank]] = 1;
    }
    else
    {
        //MPI_Send(valoare, dimensiune, type, destinatie, tag, world);
        MPI_Send(&carry, 1, MPI_INT, my_rank + 1, 0, MPI_COMM_WORLD);
    }

    MPI_Gatherv(aux_c, offsets[my_rank], MPI_INT, c, offsets, starts, MPI_INT, 0, MPI_COMM_WORLD);

    ofstream fout("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar3.txt");
    bool ok = false;
    if (my_rank == 0)
    {
        for (int i = nmax - 1; i >= 0; i--)
        {
            if (c[i] != 0)
                ok = true;
            if (ok)
                fout << c[i];
        }
        auto end_time = chrono::high_resolution_clock::now();
        auto duration = chrono::duration<double, milli>(end_time - start_time).count();
        cout << duration;
    }

    delete[] a;
    delete[] b;
    delete[] c;
    delete[] aux_a;
    delete[] aux_b;
    delete[] aux_c;
    delete[] starts;
    delete[] offsets;
    MPI_Finalize();
}

//int main()
//{
//    MPI_Status status;
//    int p, my_rank;
//    int bound = 10;
//    srand(time(NULL));
//    //int a[100001], b[100001], c[10001];
//    int* a = new int[100001];
//    int* b = new int[100001];
//    int* c = new int[100001];
//    int n1, n2, nmax, nmax2, start0, end0;
//
//    MPI_Init(NULL, NULL);
//    MPI_Comm_size(MPI_COMM_WORLD, &p);
//    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
//
//    int maxim_offset = 0;
//
//    // in master
//    if (my_rank == 0)
//    {
//        auto start_time = chrono::high_resolution_clock::now();
//        ifstream fin("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar1.txt");
//        ifstream fin2("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar2.txt");
//        //fin >> n1;
//        //for (int i = n1 - 1; i >= 0; i--)
//        //    fin >> a[i];
//
//        //fin2 >> n2;
//        //for (int i = n2 - 1; i >= 0; i--)
//        //    fin2 >> b[i];
//        n1 = 100;
//        for (int i = 0; i < n1; i++)
//            a[i] = rand() % bound;
//        n2 = 10000;
//        for (int i = 0; i < n2; i++)
//            b[i] = rand() % bound;
//
//        nmax = n1 > n2 ? n1 : n2;
//        if (nmax % p != 0)
//        {
//            nmax = nmax + (p - nmax % p);
//        }
//
//        for (int i = n2; i < nmax; i++)
//            b[i] = 0;
//        for (int i = n1; i < nmax; i++)
//            a[i] = 0;
//
//        int intreg = nmax / (p - 1);
//        int start = 0, end = intreg;
//
//        for (int i = 1; i < p; i++)
//        {
//            MPI_Send(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
//            MPI_Send(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
//
//            MPI_Send(a + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD);
//            MPI_Send(b + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD);
//
//            start = end;
//            end += intreg;
//        }
//
//        int carry = 0;
//        for (int i = 0; i < intreg; i++)
//        {
//            c[i] = (a[i] + b[i] + carry) % 10;
//            if (a[i] + b[i] + carry > 9)
//                carry = 1;
//            else
//                carry = 0;
//        }
//        MPI_Send(&carry, 1, MPI_INT, my_rank + 1, 0, MPI_COMM_WORLD);
//
//        for (int i = 1; i < p; i++)
//        {
//            MPI_Recv(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(c + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//        }
//        auto end_time = chrono::high_resolution_clock::now();
//        auto duration = chrono::duration<double, milli>(end_time - start_time).count();
//        cout << duration;
//    }
//    else
//    {
//        int start = 0, end = 0;
//        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//        MPI_Recv(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//
//        MPI_Recv(a + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//        MPI_Recv(b + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
//
//        int carry = 0;
//        MPI_Recv(&carry, 1, MPI_INT, my_rank - 1, 0, MPI_COMM_WORLD, &status);
//
//        for (int i = start; i < end; i++)
//        {
//            c[i] = (a[i] + b[i] + carry) % 10;
//            if (a[i] + b[i] + carry > 9)
//                carry = 1;
//            else
//                carry = 0;
//        }
//        if (my_rank == p - 1)
//        {
//            c[end] = 1;
//        }
//        else
//        {
//            //MPI_Send(valoare, dimensiune, type, destinatie, tag, world);
//            MPI_Send(&carry, 1, MPI_INT, my_rank + 1, 0, MPI_COMM_WORLD);
//        }
//
//        MPI_Send(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(c + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD);
//    }
//
//    ofstream fout("D:\\Facultate\\PPD\\teme\\tema3-ppd-cpp\\tema3-ppd-cpp\\numar3.txt");
//    bool ok = false;
//    if (my_rank == 0)
//    {
//        for (int i = nmax - 1; i >= 0; i--)
//        {
//            if (c[i] != 0)
//                ok = true;
//            if (ok)
//                fout << c[i];
//        }
//    }
//
//    delete[] a;
//    delete[] b;
//    delete[] c;
//    MPI_Finalize();
//}