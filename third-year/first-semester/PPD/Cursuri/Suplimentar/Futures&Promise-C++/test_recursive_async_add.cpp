//
// Created by Virginia Niculescu on 11/20/20.
//

#include "test_recursive_async_add.h"

const int MAX=100;


// divide&impera
int parallel_sum(RAIter beg, RAIter end)
{
    auto len = end - beg;
    if(len < MAX)
        return std::accumulate(beg, end, 0);

    RAIter mid = beg.operator+(len/2);


    //  auto
    std::future<int> handle = std::async(std::launch::async, parallel_sum, mid, end);

    int sum = parallel_sum(beg, mid);

    return sum + handle.get();
}



int test_recursive_async_sum(RAIter beg, RAIter end)
{
    return  parallel_sum(beg, end);
}