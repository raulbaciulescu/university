#include <iostream>
#include <fstream>

using namespace std;
ifstream fin("in.txt");

struct muchie
{
	int x, y, cost;
}graf[100001], arbore[100001];

int set[100001], n, m, c;
int k;

void sort()
{
	for (int i = 0; i < m - 1; i++)
	{
		for (int j = i + 1; j < m; j++)
			if (graf[i].cost > graf[j].cost)
			{
				muchie aux = graf[i];
				graf[i] = graf[j];
				graf[j] = aux;
			}
	}
}

void kruskal()
{
	c = 0;
	k = 0;
	for (int i = 0; i < n; i++)
		set[i] = i;

	sort();

	for (int i = 0; i < m; i++)
	{
		muchie muchie = graf[i];
		if (set[muchie.x] != set[muchie.y])
		{
			c += muchie.cost;
			arbore[k++] = muchie;
			int set_y = set[muchie.y];
			for (int j = 0; j < n; j++)
			{
				if (set[j] == set_y)
					set[j] = set[muchie.x];
			}
		}
	}
}

int main()
{
	fin >> n >> m;
	for (int i = 0; i < m; i++)
	{
		fin >> graf[i].x >> graf[i].y >> graf[i].cost;
	}

	kruskal();
	cout << c << "\n" << k << endl;
	for (int i = 0; i < k; i++)
		cout << arbore[i].x << " " << arbore[i].y << " " << endl;
	return 0;
}