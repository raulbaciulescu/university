#include <iostream>
#include <fstream>
#include <vector>
#include <list>
using namespace std;
ifstream fin(".in");

int i, j, minim, a[102][102], S[102], D[102], T[102], poz, n, m, x, y, val, c[102][102], matr_incidenta[101][101], matr_inchidere[101][101];
int matr_distantelor[101][101], viz[101], comp[100002], d1[100002], d2[100002];
int const inf = 1000000;
vector<pair<int, char>> lista_muchii;

struct nod
{
    int info;
    nod* urm;
}*prim, *q, *p, *vec[101], *trans[101];

void matricea_de_incidenta()
{
    for (i = 0; i < lista_muchii.size(); i++)
    {
        matr_incidenta[lista_muchii[i].first - 1][i] = 1;
        matr_incidenta[lista_muchii[i].second - 1][i] = 1;
    }

    for (i = 0; i < n; i++)
    {
        for (j = 0; j < m; j++)
            cout << matr_incidenta[i][j] << " ";
        cout << "\n";
    }
}

void matricea_distantelor()
{
    for (i = 1; i <= n; i++)
    {
        for (j = 1; j <= n; j++)
            matr_distantelor[i][j] = a[i][j];
    }
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            if (matr_distantelor[i][j] == 0 and i != j)
                matr_distantelor[i][j] = inf;
    //roy-floyd
    for (int k = 1; k <= n; k++)
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
                if (matr_distantelor[i][k] != inf && matr_distantelor[k][j] != inf)
                    if (matr_distantelor[i][j] > matr_distantelor[i][k] + matr_distantelor[k][j])
                        matr_distantelor[i][j] = matr_distantelor[i][k] + matr_distantelor[k][j];
    for (int i = 0; i <= n; i++)
    {
        for (int j = 0; j <= n; j++)
            cout << matr_distantelor[i][j] << " ";
        cout << "\n";
    }
}


void dfs(int nod)
{
    viz[nod] = 1;
    cout << nod << " ";
    for (int i = 0; i < n; i++)
    {
        if (a[nod][i] == 1 && viz[i] == 0)
            dfs(i);
    }
}

void bfs(int nod)
{
    int coada[1001];
    for (i = 0; i < 50; i++)
        coada[i] = 0, viz[i] = 0;


    coada[1] = nod;
    viz[nod] = 1;
    int st = 1, dr = 1;
    while (st <= dr)
    {
        nod = coada[st];
        for (i = 1; i <= n; i++)
        {
            if (a[nod][i] == 1 && viz[i] == 0)
            {
                viz[i] = 1;
                dr++;
                coada[dr] = i;
            }
        }
        st++;
    }

    for (i = 1; i <= n; i++)
        cout << coada[i] << " ";
}
void inchiderea_tranzitiva()
{
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < n; j++)
            matr_inchidere[i][j] = a[i][j];
    }

    int k;
    for (k = 1; k <= n; k++)
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
                if (matr_inchidere[i][j] == 0)
                    matr_inchidere[i][j] = matr_inchidere[i][k] * matr_inchidere[k][j];
    for (i = 1; i <= n; i++)
    {
        for (j = 1; j <= n; j++)
            cout << matr_inchidere[i][j] << " ";
        cout << "\n";
    }
}
void moore(int start, int sfarsit)
{
    list<int> coada;
    int dist[1000], pred[1000], lungime[1001];
    for (i = 0; i <= n; i++)
    {
        viz[i] = 0;
        dist[i] = inf;
        pred[i] = NULL;
        lungime[i] = inf;
    }
    lungime[start] = 0;
    coada.push_back(start);
    while (!coada.empty())
    {
        int nod = coada.front();
        coada.pop_front();
        for (i = 1; i <= n; i++)
        {
            if (a[nod][i] == 1 && lungime[i] == inf)
            {
                lungime[i] = lungime[nod] + 1;
                coada.push_back(i);
                pred[i] = nod;
            }
        }
    }
    int k = sfarsit;
    cout << k << " ";
    while (pred[k] != NULL)
    {
        cout << pred[k] << " ";
        k = pred[k];
    }
}

void citire_liste_inlantuite()
{
    fin >> n >> m;
    for (i = 0; i < m; i++)
    {
        fin >> x >> y;

        p = new nod;
        p->info = y;
        p->urm = vec[x];
        vec[x] = p;

        p = new nod;
        p->info = x;
        p->urm = trans[y];
        trans[y] = p;
    }

    for (i = 1; i <= n; i++)
    {
        cout << i << " ";
        p = vec[i];
        while (p != NULL)
        {
            cout << p->info << " ";
            p = p->urm;
        }
        cout << "\n";
    }
}

void comp_tare_conexe()
{
    int nr = 0;
    for (i = 1; i <= n; i++)
    {
        if (comp[i] == 0)
        {
            //dfs cu trans, dfs normal
            //dfs1(i), dfs2(i);
            nr++;
            for (j = 1; j <= n; j++)
            {
                ///cout << j << " " << d1[j] << " " << d2[j] << "\n";
                if (d1[j] == 1 && d2[j] == 2)
                    comp[j] = nr;
                d1[j] = d2[j] = 0;
            }
            ///cout << "\n";
        }
    }
}

void dijkstra(int r)
{
    for (i = 1; i <= n; i++)
        for (j = 1; j <= n; j++)
            if (a[i][j] == 0)
                a[i][j] = inf;

    // pas1
    S[r] = 1;
    for (i = 1; i <= n; i++)
    {
        D[i] = a[r][i];
        /*if (a[r][i] != inf)
            T[i] = r;*/
    }
    D[r] = 0;
  
    // pas 2
    for (i = 1; i < n; i++)
    {
        minim = inf;
        for (j = 1; j <= n; j++)
            if (S[j] == 0 && D[j] < minim)
                minim = D[j], poz = j;
        S[poz] = 1;
        for (j = 1; j <= n; j++)
            if (S[j] == 0 && D[poz] + a[poz][j] < D[j])
                D[j] = D[poz] + a[poz][j];// , T[j] = poz;
    }

    for (i = 1; i <= n; i++)
    {
        if (D[i] == inf)
            D[i] = -1;
        cout << D[i] << " ";
    }
}


int main()
{

    //citire_liste_inlantuite();
    fin >> n >> m;
    for (i = 0; i < m; i++)
    {
        fin >> x >> y >> val;
        pair<int, int> pair;
        pair.first = x;
        pair.second = y;
        lista_muchii.push_back(pair);
        a[x][y] = val;
        //cout << x << " " << y << "\n";
    }

    //matricea_de_incidenta();
    //matricea_distantelor();
    //moore(1, 6);
    //dfs(1);
    dijkstra(4);
}
