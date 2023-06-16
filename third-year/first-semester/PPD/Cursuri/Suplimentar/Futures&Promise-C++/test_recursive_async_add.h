//
// Created by Virginia Niculescu on 11/20/20.
//

#ifndef EXEMPLE_FUTURE_PROMISE_TEST_RECURSIVE_ASYNC_ADD_H
#define EXEMPLE_FUTURE_PROMISE_TEST_RECURSIVE_ASYNC_ADD_H
#include <iostream>
#include <vector>

#include <numeric>
#include <future>

using  namespace std;

typedef vector<int>::iterator RAIter ;

int test_recursive_async_sum(RAIter beg, RAIter end);
#endif //EXEMPLE_FUTURE_PROMISE_TEST_RECURSIVE_ASYNC_ADD_H
