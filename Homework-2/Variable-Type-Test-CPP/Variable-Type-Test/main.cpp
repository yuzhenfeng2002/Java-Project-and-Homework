//
//  main.cpp
//  Variable-Type-Test
//
//  Created by YzFENG on 2021/10/3.
//

#include <iostream>

const int MAX = 10;

int main(int argc, const char * argv[]) {
    short factorial = 1;
//    unsigned short factorial = 1;
//    int factorial = 1;
//    unsigned int factorial = 1;
//    long factorial = 1;
//    unsigned long factorial = 1;
//    long long factorial = 1;
//    unsigned long long factorial = 1;
//    float factorial = 1;
//    double factorial = 1;
//    long double factorial = 1;
    for (int n = 2; n < MAX; ++n)
    {
        factorial = factorial * n;
        std::cout << n << "! = " << factorial << std::endl;
    }
}
