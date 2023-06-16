#include <iostream>
#include <fstream>
using namespace std;

void codare_prufer(int k[], int fii[], int t[], int n, int& nr) {
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

int main(int argc, char* argv[])
{
    //cout << argc;
    //cout << argv[0] << " " << argv[1] << " " << argv[2];
    int n, t[50], fii[50] = { 0 }, k[50] = { 0 }, nr = 0, aux;
    ifstream fin(argv[1]);
    ofstream fout(argv[2]);
    fin >> n;
    for (int i = 0; i < n; i++) {
        fin >> aux;
        t[i] = aux;
        if (t[i] != -1)
            fii[t[i]]++;
    }
    codare_prufer(k, fii, t, n, nr);
    fout << nr - 1 << "\n";
    for (int i = 0; i < nr - 1; i++)
        fout << k[i] << " ";
    return 0;
}
