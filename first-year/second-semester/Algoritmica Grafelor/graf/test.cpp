#include <iostream>
#include <fstream>
using namespace std;

void codare_prufer()
{
    bool gasit = false;
    while (!gasit)
    {
        
        for (int i = 0; i < n; i++)
        {
            gasit = true;
            if (fii[i] == 0)
            {
                gasit = false;
                fii[i]--;
                if (t[i] != -1)
                    fii[t[i]]--;

                k[nr++] = t[i];
                break;
            }
        }
            
    }
}

int min_din_secventa()
{
    int x = 0;
    while (true)
    {
        int ap = 0;
        for (int i = 0; i < n; i++)
        {
            if (k[i] == x)
                ap++;
        }
        if (ap == 0)
            return x;
        else x++;
    }
}

void decodare_prufer()
{
    for (int i = 0; i < n; i++)
    {
        x = k[0];
        y = min_din_secventa();
        t[y] = x;

        for (int j = 0; j < n - 1; j++)
            k[j] = k[j + 1];
        k[n - 1] = t[y];
    }
}

int main()
{
    int n, m;
    ifstream fin("in.txt");
    fin >> n;

    for (i = 0; i < n; i++)
    {
        fin >> t[i];
        if (t[i] != -1)
            fii[i]++; 
    }

    codare_prufer();

    for (int i = 0; i < nr; i++)
        cout<<k[i]<<"\n";
}