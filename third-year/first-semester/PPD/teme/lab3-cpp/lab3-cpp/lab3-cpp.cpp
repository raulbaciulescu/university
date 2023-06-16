#include <iostream>
#include <thread>
#include <chrono>
#include <cmath>
#include <vector> // forteaza salvarea in heap, heap-ul e mai lent decat stack-ul

using  namespace std;

void print_vector(vector<int> x)
{
    for (auto el : x)
        cout << el << " ";
    cout << "\n";
}

//void init(vector<int> x, int n)
//{
//    for (int i = 0; i < n; i++)
//    {x[i] = rand() % bound;
//    }
//}

int operation(int x, int y)
{
    return sqrt(pow(x, 4)) + sqrt(pow(y, 4));
}

void run(int start, int end, vector<int>& a, vector<int>& b, vector<int>& c)
{
    for (int i = start; i < end; i++)
        c[i] = operation(a[i], b[i]);
}

int main()
{
    int n = 1000000;
    int bound = 900000;
    int p = 8;
    
    vector<int> a(n);
    vector<int> b(n);
    vector<int> c(n); // secvential
    vector<int> d(n);

    srand(time(NULL));
    auto start_time = chrono::high_resolution_clock::now();

    for (int i = 0; i < n; i++)
    {
        a[i] = rand() % bound;
        b[i] = rand() % bound;
    }
    auto end_time = chrono::high_resolution_clock::now();

    for (int i = 0; i < n; i++)
    {
        c[i] = operation(a[i], b[i]);
    }

    //print_vector(a);
    //print_vector(b);
    cout << "Secvential:\n";
    cout << "Timp secvential: " << chrono::duration<double, milli>(end_time - start_time).count() << "\n";
    //print_vector(c);

    int intreg = n / p, rest = n % p;
    int start = 0;
    int end = intreg;
    vector<thread> threads(p);

    start_time = chrono::high_resolution_clock::now();
    for (int i = 0; i < p; i++)
    {
        if (rest > 0)
        {
            end++;
            rest--;
        }
        threads[i] = thread(run, start, end, ref(a), ref(b), ref(d));

        start = end;
        end = start + intreg;
    }

    for (int i = 0; i < p; i++)
        threads[i].join();
    end_time = chrono::high_resolution_clock::now();

    cout << "Paralel:\n";
    cout << "Timp paralel: " << chrono::duration<double, milli>(end_time - start_time).count();
    //print_vector(d);

    return 0;
}