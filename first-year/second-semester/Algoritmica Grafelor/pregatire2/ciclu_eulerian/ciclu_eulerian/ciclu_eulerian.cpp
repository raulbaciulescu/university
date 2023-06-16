#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;
ifstream fin("in.txt");

struct Graf
{
    int v, e;
    int** adj;

    Graf(int v, int e)
    {
        this->v = v;
        this->e = e;
        
        adj = new int* [v];
        for (int i = 0; i < v; i++)
            adj[i] = new int[v];

        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++)
                adj[i][j] = 0;
    }

    ~Graf()
    {
        for (int i = 0; i < v; i++)
            delete[] adj[i];
        delete[] adj;
    }
    
    void add_muchie(int x, int y)
    {
        adj[x][y] = 1;
        adj[y][x] = 1;
    }

    void delete_muchie(int x, int y) {
        adj[x][y] = -1;
        adj[y][x] = -1;
    }
};

int cauta_varf(Graf& g)
{
    //cautam un vf cu grad par de la care sa incepem
    for (int x = 0; x < g.v; x++)
    {
        int grad = 0;
        for (int y = 0; y < g.v; y++)
        {
            if (g.adj[x][y] == 1)
                grad++;
        }

        if (grad % 2 == 0)
            return x;
    }

}

void ciclu_eulerian(Graf& g, int x, vector<int>& rezultat)
{
    for (int y = 0; y < g.v; y++)
    {
        if (g.adj[x][y] == 1)
        {
            g.delete_muchie(x, y);
            ciclu_eulerian(g, y, rezultat);
        }
    }
    rezultat.push_back(x);
}

int main()
{
    int v, e;
    fin >> v >> e;
    Graf g(v, e);
    for (int i = 0; i < e; i++) {
        int u, v;
        fin >> u >> v;
        g.add_muchie(u, v);
    }

    int sursa = cauta_varf(g);
    vector<int> rez;
    ciclu_eulerian(g, sursa, rez);
    for (int el : rez) {
        cout << el << "  ";
    }
}
