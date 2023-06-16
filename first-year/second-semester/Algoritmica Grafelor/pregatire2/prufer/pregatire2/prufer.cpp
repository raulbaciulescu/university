#include <iostream>
#include <fstream>

using namespace std;
ifstream fin("in.txt");

void codare_prufer(int k[], int fii[], int t[], int n, int& nr)
{
	bool gasit = false;

	while (!gasit)
	{
		for (int i = 0; i < n; i++)
		{
			gasit = true;
			if (fii[i] == 0) //daca e frunza
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

int minim_din_secventa(int k[], int n)
{
	int x = 0;
	while (true)
	{
		int ap = 0;
		for (int i = 0; i < n; i++)
			if (k[i] == x)
				ap++;
		
		if (ap == 0)
			return x;
		else
			x++;
	}
}

void decodare_prufer(int k[], int t[], int n)
{
	for (int j = 0; j < n; j++)
	{
		int x = k[0];
		int y = minim_din_secventa(k, n);
		t[y] = x;

		for (int i = 0; i < n - 1; i++)
			k[i] = k[i + 1];
		k[n - 1] = y;
	}
}

int main()
{
	int n, t[50], new_t[50], fii[50] = { 0 }, k[50] = { 0 }, nr = 0, aux;
	fin >> n;
	for (int i = 0; i < n; i++)
	{
		fin >> aux;
		t[i] = aux;
		if (t[i] != -1)
			fii[t[i]]++;
	}

	codare_prufer(k, fii, t, n, nr);
	cout << "Codarea prufer: \n";
	cout << nr - 1 << "\n";
	for (int i = 0; i < nr - 1; i++)
		cout << k[i] << " ";
	
	decodare_prufer(k, new_t, nr);
	cout << "\nDecodarea prufer: \n";
	cout << nr << "\n";
	for (int i = 0; i < nr; i++)
		cout << new_t[i] << " ";
	return 0;
}
