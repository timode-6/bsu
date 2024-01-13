#!/usr/bin/bash

export PMIX_MCA_pcompress_base_silence_warning=1

execfile="build/lab3"

# Args:
# - N, r1, threads (Q2)

for threads in {1..6}
do
    for N in 250 500 750 1000
    do
        for r1 in 25 50 75 100
        do
            mpiexec -n $threads "$execfile" $N $r1 $threads
        done
    done
done
