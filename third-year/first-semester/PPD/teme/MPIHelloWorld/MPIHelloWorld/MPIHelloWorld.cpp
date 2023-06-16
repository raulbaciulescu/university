#include <iostream>
#include <mpi.h>
using namespace std;

void print_vector(int x[], int n)
{
    for (int i = 0; i < n; i++)
        cout << x[i] << " ";
    cout << "\n";
}

// mpiexec -n 10 .\MPIHelloWorld.exe
//int main()
//{
//    MPI_Status status;
//    int p, my_rank;
//    const int n = 10;
//    int bound = 10;
//    srand(time(NULL));
//    int a[n], b[n], c[n];
//
//    MPI_Init(NULL, NULL);
//    MPI_Comm_size(MPI_COMM_WORLD, &p);
//    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
//
//    // in master
//    if (my_rank == 0)
//    {
//        for (int i = 0; i < n; i++)
//        {
//            a[i] = rand() % bound;
//            b[i] = rand() % bound;
//        }
//
//        int intreg = n / (p - 1);
//        int start = 0, end = intreg;
//        int rest = n % (p - 1);
//
//        for (int i = 1; i < p; i++)
//        {
//            if (rest > 0)
//            {
//                rest--;
//                end++;
//            }
//
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
//        for (int i = 1; i < p; i++)
//        {
//            MPI_Recv(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//            MPI_Recv(c + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
//        }
//
//        print_vector(c, n);
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
//        for (int i = start; i < end; i++)
//            c[i] = a[i] + b[i];
//
//        MPI_Send(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
//        MPI_Send(c + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD);
//
//        //print_vector(a, n); 
//        //print_vector(b, n);
//        //cout << my_rank << " " << start << " " << end;
//    }
//
//    //print_vector(a, n);
//    //print_vector(b, n);
//    //cout << p << " " << my_rank;
//
//    MPI_Finalize();
//}
//int main()
//{
//    MPI_Status status;
//    int p, my_rank;
//    const int n = 10;
//    int bound = 10;
//    srand(time(NULL));
//    int a[n], b[n], c[n];
//
//    MPI_Init(NULL, NULL);
//    MPI_Comm_size(MPI_COMM_WORLD, &p);
//    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
//
//    int* starts = new int[p];
//    int* offsets = new int[p];
//
//    int intreg = n / p;
//    int rest = n % p;
//    int start = 0, end = intreg;
//
//    int maxim_offset = end - start;
//    if (rest > 0)
//        maxim_offset++;
//
//    for (int i = 0; i < p; i++)
//    {
//        if (rest > 0)
//        {
//            rest--;
//            end++;
//        }
//        starts[i] = start;
//        offsets[i] = end - start;
//
//        start = end;
//        end += intreg;
//    }
//
//    int* aux_a = new int[maxim_offset];
//    int* aux_b = new int[maxim_offset];
//    int* aux_c = new int[maxim_offset];
//
//    if (my_rank == 0)
//    {
//        for (int i = 0; i < n; i++)
//        {
//            a[i] = rand() % bound;
//            b[i] = rand() % bound;
//        }
//    }
//
//    MPI_Scatterv(a, offsets, starts, MPI_INT, aux_a, maxim_offset, MPI_INT, 0, MPI_COMM_WORLD);
//    MPI_Scatterv(b, offsets, starts, MPI_INT, aux_b, maxim_offset, MPI_INT, 0, MPI_COMM_WORLD);
//
//    for (int i = 0; i < offsets[my_rank]; i++)
//        aux_c[i] = aux_a[i] + aux_b[i];
//
//    MPI_Gatherv(aux_c, offsets[my_rank], MPI_INT, c, offsets, starts, MPI_INT, 0, MPI_COMM_WORLD);
//
//    if (my_rank == 0)
//    {
//        print_vector(a, n);
//        print_vector(b, n);
//        print_vector(c, n);
//    }
//    MPI_Finalize();
//}


int main() {
    int nprocs, myrank;
    int i;
    int* a, * b;
    MPI_Status status;
    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &nprocs);
    MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
    a = (int*)malloc(nprocs * sizeof(int));
    b = (int*)malloc(nprocs * nprocs * sizeof(int));
    for (int i = 0; i < nprocs; i++) {
        a[i] = nprocs * myrank + i;
    }

    //for (int i = 0; i < nprocs; i++)
    //    printf("%d %d", myrank, a[i]);
    /*
    COD de COMPLETAT
    */
     for (i = 0; i < nprocs; i++) {
        b[i + nprocs * myrank] = a[i];
    }
    if (myrank > 0) {
        MPI_Recv(b, nprocs * (myrank + 1), MPI_INT, (myrank - 1), 10, MPI_COMM_WORLD, &status);
    }
    MPI_Send(b, nprocs * (myrank + 1), MPI_INT, (myrank + 1) % nprocs, 10, MPI_COMM_WORLD);
    if (myrank == 0) {
        MPI_Recv(b, nprocs * nprocs, MPI_INT, (myrank - 1), 10, MPI_COMM_WORLD, &status);
    }



    /*
    COD de COMPLETAT
    */
    if (myrank == 0) {
        for (i = 0; i < nprocs * nprocs; i++) {
            printf(" %d", b[i]);
        }
    }
    MPI_Finalize();
    return 0;
}
