// #include <iostream>
// #include <omp.h>
// using namespace std;

// void multiplicare(float* x, int start, int chunk) 
// {
//     int i;
//     for (i = 0; i < chunk; i++)
//         x[start + i] = start * 10;
// }

// int functie(float* x, int n)
// {
//     int i, p, size, istart, val = 0;
//     #pragma omp parallel default(shared) private(i, p, size, istart)
//     {
//         i = omp_get_thread_num();
//         p = omp_get_num_threads();
//         size = n / p;
//         istart = i * size;
//         if (i == p - 1) 
//             size = n - istart;
//         multiplicare(x, istart, size);
//         val = val + 10;
//     }
//     return val;
// }

// int main()
// {
//     omp_set_num_threads(9);
//     float tablou[1000];
//     int v = functie(tablou, 1000);
//     cout << v;
//     return 0;
// }
#include <stdio.h>
#include <stdlib.h>
#ifdef _OPENMP
#include <omp.h>
#endif

int main() {
   int tid = -1;
#pragma omp parallel private(tid) // Start of parallel region: forks threads
   {
     tid = omp_get_thread_num();  // default is number of CPUs on machine
     printf("Hello from Thread %d\n",tid);
     if(tid ==0) {
        printf("Number of threads = %d\n", omp_get_num_threads());
     }
   }  // ** end of the the parallel: joins threads
   return 0;
}
