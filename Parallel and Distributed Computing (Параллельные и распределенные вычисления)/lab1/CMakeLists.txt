cmake_minimum_required(VERSION 3.23)
project(HW)

set(CMAKE_CXX_STANDARD 17)

include_directories(${CMAKE_SOURCE_DIR}/gtest)

add_executable(HW tests.cpp gtest/gtest-all.cc gtest/gtest_main.cc)

# add_executable(HW lab1.cpp)

# set(CMAKE_CXX_FLAGS "-fopenmp")
