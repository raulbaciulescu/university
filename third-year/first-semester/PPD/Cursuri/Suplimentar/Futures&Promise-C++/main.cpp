#include <iostream>
#include <functional>
#include "test_future.h"
#include "test_shared_future.h"
#include "test_future_vector.h"
#include "test_recursive_async_add.h"
#include "test_prod_div.h"
#include "test_shared_future_div.h"
using namespace std;

int main(int argc, const char * argv[])
{
    int a=2, b=3;
    //se preiau prin valoare valorile variabilelor externe functiei lambda 
    function<bool()> f = [a,b]()-> bool{return (a!=b ); };
    if (f())
        cout<<"test lambda true"<<endl;
    else cout<<"test lambda false"<<endl;

      if (test_future()==0)               cout<<"Success - test future"<<endl;
//         if (test_shared_future()==0)        cout<<"Success - test shared future"<<endl;//
//      if (test_future_vector()==0)        cout<<"Success - test future vector"<<endl;
//
//      std::vector<int> v(10000, 2);     cout<<"sum="<<test_recursive_async_sum(v.begin(),v.end()) <<endl;
//    if (test_prod_div()==0)               cout<<"Success - test prod div"<<endl;
//    if (test_shared_future_div()==0)               cout<<"Success - test shared future div"<<endl;
    return 0;
}
