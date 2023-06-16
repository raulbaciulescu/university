//
// Created by Virginia Niculescu on 11/20/20.
//

#include "test_future.h"


int a=1;
int add(int x, std::mutex& m){
    {
        m.lock();
        std::cout << "add " << std::this_thread::get_id() << " x="<< x<<std::endl;
        m.unlock();
    }
    return a+7;
}

int test_future(){

    //(1) future from a packaged_task; std::packaged_task wraps any Callable target
    std::mutex m;
    std::packaged_task<int()> task ([&m]() {   // wrap  a lambda function
        {
            m.lock();
            std::cout << "sleep task " << std::this_thread::get_id() << std::endl;
            m.unlock();
        }
        std::this_thread::sleep_for(std::chrono::seconds(2));
        return 70; }
    ); 
    
    // wrap the function add into a packaged_task
    std::packaged_task<int(int, std::mutex&)> task_add(add); // wrap a previously defined function add

    std::future<int> f1 = task.get_future();  // get the future - could be retrieve only once!
   // std::future<int> f2 = task.get_future(); //- error - second retrieve is not possible
    std::future<int> f1_add = task_add.get_future();  // get the future

    // task();                   //direct execution of the task - current thread
    // task_add(3,ref(m));        //direct execution of the task
   std::thread(std::move(task)).detach(); // launch on a different thread
    std::thread(std::move(task_add), 3, ref(m)).detach(); // launch on a different thread

    m.lock();
    std::cout<<"main thread ("<<std::this_thread::get_id()<<")"<< std::endl;
    m.unlock();


    std::cout<<f1_add.get()<<std::endl;        //  get the value from future - only once!
    std::cout<<f1.get()<<std::endl;           //  get the value from future

//    cout<<"second time"<<endl;    //error
//    std::cout<<f1_add.get()<<std::endl;        //  get the value from future
//    cout<<f1.get()<<endl;           //  get the value from future

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //(2) future from an async()
    std::cout<<"before async a="<<a<<std::endl; //global variables problems! Data-Race
        std::future<int> f2_async = std::async(
                std::launch::async,             //executes the callable object on a new thread of execution
                []()->int{
                    std::cout<<"async "<<std::this_thread::get_id()<<std::endl;
                    //std::this_thread::sleep_for(std::chrono::seconds(5));
                    std::cout<<"inside  async a="<<a<<std::endl;//NERECOMANDAT
                    return 8*a;
                });//!!!!access global variable - unrecommended

      

        std::future<int> f2_lazy = std::async(
                std::launch::deferred,       //does not spawn a new thread of execution. Instead, lazy evaluation
                [](){  std::cout<<"deferred "<<std::this_thread::get_id()<<std::endl;
                 //   std::this_thread::sleep_for(std::chrono::seconds(5));
                    return 7*a; });

        std::cout<<"main thread ("<<std::this_thread::get_id()<<")"<< std::endl;
        std::this_thread::sleep_for(std::chrono::seconds(5));

        a = 50;  //modify a in main thread!!!! INCONSISTENCY - Data race -Error

        std::cout<<"f2_async result="<<f2_async.get() << std::endl;
        std::cout<<"f2_lasy result-"<<f2_lazy.get() << std::endl;


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // (3) future from a promise
    //promise provides a facility to store a value or an exception that is later acquired
    // asynchronously via a std::future object created by the std::promise object.

    std::promise<int> p;
    std::future<int> f3 = p.get_future();

    std::thread( [& p]{
        std::cout<<"promise "<<std::this_thread::get_id()<<std::endl; 
        p.set_value_at_thread_exit(1000);
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }
    ).detach();


//        a=40;
//        std::cout << "Waiting...in main thread" << std::flush;
//
//        f1.wait();f1_add.wait();
    //     f2_async.wait(); //f2_lazy.wait();
    f3.wait();
    std::cout << "Done!\nResults are: "<< f3.get() << '\n';
    a=30;
     // std::cout << "Done!\nResults are: "<<"a*8="<< f2_async.get()<<"8="<<f2_lazy.get()<<' '  ;


    return 0;
}
