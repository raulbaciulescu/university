
#include <iostream>
#include <fstream>
using namespace std;

ifstream fin(".in");
int x, y, n, D[10002], S[10002], a[1002][1002], val, P[100002];
int start, sfarsit, i, j, k;
int const inf = 10000000;


void dijkstra(int start)
{
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            if (a[i][j] == 0)
                a[i][j] = inf;
    for (i = 0; i < n; i++)
    {
        D[i] = a[start][i], S[i] = 0;
        if (a[start][i] != inf)
            P[i] = start;
    }
    D[start] = 0;
    S[start] = 1;
    P[start] = -1;
    for (i = 0; i < n; i++)
    {
        int minim = inf, pos = 0;
        for (j = 0; j < n; j++)
            if (S[j] == 0 && D[j] < minim)
                minim = D[j], pos = j;
        S[pos] = 1;

        for (j = 0; j < n; j++)
        {
            if (S[j] == 0 && a[pos][j] + D[pos] < D[j])
            {
                D[j] = a[pos][j] + D[pos];
                P[j] = pos;
            }
        }
    }
}
int main()
{
    fin >> n >> start >> sfarsit;
    while (fin >> x >> y >> val)
    {
        a[x][y] = val;
    }
    dijkstra(start);

    cout << "Distanta pana la fiecare nod: ";
    for (i = 0; i < n; i++)
    {
        if (D[i] == inf)
            D[i] = -1;
        cout << D[i] << " ";
    }

    cout << "\n";
    if (D[sfarsit] != -1)
    {   
        cout << "Drumul parcurs: ";
        cout << sfarsit << " ";
        while (P[sfarsit] != -1)
        {
            cout << P[sfarsit] << " ";
            sfarsit = P[sfarsit];
        }
    }
    else
    {
        cout << "Drumul nu a fost gasit!";
    }
    
}