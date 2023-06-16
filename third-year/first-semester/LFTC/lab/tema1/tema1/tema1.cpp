//
//
#include <iostream>
using namespace std;

int r;
double aria, perimetru;
const double pi = 3.14;

int main()
{
    cin >> r;
    perimetru = 2 * pi * r;
    aria = pi * r * r;
    cout << perimetru << " ";
    cout << aria;
    return 0;
}


//
//
//#include <iostream>
//using namespace std;
//
//int x, y;
//
//int main()
//{
//    cin >> x;
//    cin >> y;
//    while (x != y)
//    {
//        if (x > y)
//        {
//            x = x - y;
//        }
//        else
//        {
//            y = y - x;
//        }
//    }
//    cout << x;
//    return 0;
//}

#include <iostream>
using namespace std;

int n, x, suma;

int main()
{
    cin >> n;
    while (n != 0)
    {
        cin >> x;
        suma = suma + x;
        n--;
    }
    cout << suma;
    return 0;
}
