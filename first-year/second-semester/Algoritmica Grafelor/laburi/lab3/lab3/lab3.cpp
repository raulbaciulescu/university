
#include <iostream>
#include <fstream>

using namespace std;
ifstream fin(".in");
int i, j, minim, a[102][102], S[102], D[102], T[102], poz, n, m, x, y, val, c[102][102];
int const inf = 1000000;


void dijkstra(int r)
{
    for (i = 1; i <= n; i++)
        for (j = 1; j <= n; j++)
            if (a[i][j] == 0)
                a[i][j] = inf;
    /// Pas 1
    S[r] = 1;
    for (i = 1; i <= n; i++)
    {
        D[i] = a[r][i];
        if (a[r][i] != inf)
            T[i] = r;
    }
    D[r] = 0;
    /// Pas 2
    for (i = 1; i <= n - 1; i++)
    {
        minim = inf;
        for (j = 1; j <= n; j++)
            if (S[j] == 0 && D[j] < minim)
                minim = D[j], poz = j;
        S[poz] = 1;
        for (j = 1; j <= n; j++)
            if (S[j] == 0 && D[poz] + a[poz][j] < D[j])
                D[j] = D[poz] + a[poz][j], T[j] = poz;
    }
}
int min(int a, int b)
{
    if (a < b)
        return a;
    else
        return b;
}
void johnson()
{
    for (i = 1; i <= n; i++)
        for (j = 1; j <= n; j++)
        {
            if (c[i][j] == 0 && i != j)
                c[i][j] = inf;
        }

    for (int k = 1; k <= n; k++)
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
                c[i][j] = min(c[i][j], c[i][k] + c[k][j]);
}
void johnson2()
{

}
int main()
{
    int p;
    fin >> n >> p;
    while (fin >> x >> y >> val)
        c[x][y] = a[x][y] = val;
    dijkstra(p);
    for (i = 1; i <= n; i++)
    {
        if (D[i] == inf)
            D[i] = -1;
        cout << D[i] << " ";
    }
    cout << "\nJohnson:\n";
    for (i = 1; i <= n; i++)
    {
        for (j = 1; j <= n; j++)
            cout << c[i][j] << " ";
        cout << "\n";
    }
}