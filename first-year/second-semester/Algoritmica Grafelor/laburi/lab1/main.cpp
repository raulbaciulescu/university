#include <iostream>
#include <fstream>
using namespace std;
int a[12][12], n, d[12][12];
int nr_noduri, nr_muchii, x, y;
int grad[10], viz[10];
ifstream fin(".in");

void dfs(int nod)
{
    int i;
    viz[nod] = 1;
    for(i = 1; i <= nr_noduri; i++)
    {
        if (a[nod][i] == 1 && viz[i] == 0)
            dfs(i);
    }
}
int main()
{
    ///matrice de adiacenta
    fin >> nr_noduri;
    for (int i = 1; i <= nr_noduri; i++)
        for (int j = 1; j <= nr_noduri; j++)
            fin >> a[i][j];

    /*for (int i = 1; i <= nr_noduri; i++)
    {
        for (int j = 1; j <= nr_noduri; j++)
            cout << a[i][j] << " ";
        cout << "\n";
    }*/

    ///lista de muchii
/*
    fin >> nr_noduri >> nr_muchii;
    for (int i = 1; i <= nr_muchii; i++)
    {
        fin >> x >> y;
        a[x][y] = a[y][x] = 1;
    }*/


    cout << "nodurile izolate: ";
    for (int i = 1; i <= nr_noduri; i++)
    {
        bool nu_e_izolat = false;
        for (int j = 1; j <= nr_noduri; j++)
            if (a[i][j] == 1)
                nu_e_izolat = true;

        if (nu_e_izolat == false)
            cout << i << " ";
    }
    cout << "\n";




    for (int i = 1; i <= nr_noduri; i++)
        for (int j = 1; j <= nr_noduri; j++)
            if (a[i][j] == 1)
                grad[i]++;
    bool regular = true;
    for (int i = 2; i <= nr_noduri; i++)
        if (grad[i] != grad[i - 1])
        {
            regular = false;
            break;
        }

    if (regular)
        cout << "graful e regular!\n";
    else
        cout << "graful nu e regular!\n";
    bool conex = true;
    dfs(1);
    for (int i = 1; i <= nr_noduri; i++)
        if (viz[i] != 1)
        {
            conex = false;
            break;
        }
    if (conex)
        cout << "graful e conex!\n";
    else
        cout << "graful nu e conex!\n";


    int inf = 99;
    for(int i = 1; i <= nr_noduri; i++)
    for(int j = 1;j <= nr_noduri; j++)
        if(i == j)
            d[i][j] = 0;
        else
            d[i][j] = inf;

    for (int i = 1; i <= nr_noduri; i++)
        for (int j = 1; j <= nr_noduri; j++)
            if (a[i][j] == 1)
                d[i][j] = 1;



    for(int k = 1 ; k <= nr_noduri ; ++k)
        for(int i = 1 ; i <= nr_noduri ; ++i)
            for(int j = 1 ; j <= nr_noduri ; ++j)
                if(d[i][k] != inf && d[k][j] != inf)
                    if (d[i][j] > d[i][k] + d[k][j])
                        d[i][j] = d[i][k] + d[k][j];

    for (int i = 1; i <= nr_noduri; i++)
    {
        for (int j = 1; j <= nr_noduri; j++)
            cout << d[i][j] << " ";
        cout << "\n";
    }
    return 0;
}
