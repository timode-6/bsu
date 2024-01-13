cmake_minimum_required(VERSION 3.22)
project(lab3)

set(CMAKE_CXX_STANDARD 20)

find_package(MPI REQUIRED)

add_executable(lab3 lab3.cpp)
target_link_libraries(lab3 PRIVATE MPI::MPI_CXX)
