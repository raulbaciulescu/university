//
// Created by Virginia Niculescu on 11/23/20.
//

#include "test_prod_div.h"
#include <future>
#include <iostream>
#include <thread>


void division(std::promise<int>&& intPromise, int a, int b)  {
    intPromise.set_value(a/b);
}
void product(std::promise<int>&& intPromise, int a, int b){
    intPromise.set_value(a*b);
}


int test_prod_div(){

    int a= 20;
    int b= 10;

    std::cout << std::endl;

    // define the promises
    std::promise<int> prodPromise;
    std::promise<int> divPromise;

    // get the futures
    std::future<int> prodResult= prodPromise.get_future();
    std::future<int> divResult = divPromise.get_future();

    // calculate the two operations in separate threads
    std::thread prodThread(product,std::move(prodPromise),a, b);
    prodThread.detach();
    std::thread divThread  (division,   std::move(divPromise), a, b);
    divThread.detach();

    // get the result
    std::cout << "20*10= " << prodResult.get() << std::endl;
    std::cout << "20/10= " << divResult.get() << std::endl;

//    prodThread.join();
//
//    divThread.join();

    std::cout << std::endl;
    return 0;

}