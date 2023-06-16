//
// Created by Virginia Niculescu on 11/20/20.
//

#include "test_future_vector.h"
int twice(int m) {
    return 2 * m; //sau ceva mult mai complicat
}

int test_future_vector() {
    std::vector<std::future<int>> futures;

    for(int i = 0; i < 10; ++i) {
        std::future<int> f = std::async(std::launch::async, twice, i);
//           futures.push_back(f);//future is uncopyable ->future(const future&) = delete; ERROR
        futures.push_back(std::move(f));
//alternativ
//           futures.push_back (std::async(std::launch::async, twice, i));//implicit - async returns a future
    }

    //retrive and print the value stored in the future
    for(auto &e : futures) {
        std::cout << e.get() << std::endl;
    }

    return 0;
}


/*
 * std::move is used to indicate that an object t may be "moved from", i.e. allowing the efficient transfer of resources from t to another object.
 * In particular, std::move produces an xvalue expression that identifies its argument t.
 * It is exactly equivalent to a static_cast to an rvalue reference type.
 * For example, a move constructor of a linked list might copy the pointer to the head of the list and
 * store nullptr in the argument instead of allocating and copying individual nodes.
 *
 */