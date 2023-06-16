//
// Created by Virginia Niculescu on 11/20/20.
//

#include "test_shared_future.h"
#include "test_recursive_async_add.h"

using namespace std;
int test_shared_future()
{

    std::promise<void> ready_promise, t1_ready_promise, t2_ready_promise;
    std::shared_future<void> ready_future(ready_promise.get_future());
    std::chrono::time_point<std::chrono::high_resolution_clock> start;

    //t1_ready_promise'  implicitly captured in a lambda [&,.]
    // auto fun1 = [&/*, ready_future*/ ]()-> std::chrono::duration<double, std::milli> // aceeasi ready_future-> transmitere prin valoare sau prin referinta
    auto fun1 = [&, ready_future ]()-> std::chrono::duration<double, std::milli>
    {
        t1_ready_promise.set_value();   //mesaj de la t1 la main
        ready_future.wait(); // waits for the signal from main()
        return std::chrono::high_resolution_clock::now() - start;
    };

    //t2_ready_promise'  implicitly captured in a lambda
    auto fun2 = [&, ready_future]() ->  std::chrono::duration<double, std::milli> // aceeasi ready_future
    {  //t1_ready_promise.set_value(); Runtime error - setare din 2 threaduri diferite
        t2_ready_promise.set_value();
        ready_future.wait(); // waits for the signal from main()
        return std::chrono::high_resolution_clock::now() - start;
    };

    auto result1 = std::async(std::launch::async, fun1); //executie in thread diferit
    auto result2 = std::async(std::launch::async, fun2); //executie in thread diferit

    // wait for the threads to become ready
    t2_ready_promise.get_future().wait();
    t1_ready_promise.get_future().wait();


    // the threads are ready, start the clock
    start = std::chrono::high_resolution_clock::now();

    // signal the threads to go
    ready_promise.set_value();

    std::cout << "Thread 1 received the signal "
    << result1.get().count() << " ms after start\n"
    << "Thread 2 received the signal "
    << result2.get().count() << " ms after start\n";

    return 0;
}