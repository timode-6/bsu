void pi_SPMD() {

  omp_set_num_threads(8);

  int MaxTHREADS = 8;
  int i;
  double h, x, res;
  double start, delta;

  h = 1. / n;

  int num_thrds = 0;
  int thread_id = 0;
  double local_sum = 0;

  std::vector<double> partial_sums(MaxTHREADS);
  for (int i = 0; i < MaxTHREADS; ++i) {
    partial_sums[i] = 0;
  }

  start = omp_get_wtime();

#pragma omp parallel shared(partial_sums, num_thrds) private(thread_id, i, x, local_sum)
  {
    thread_id = omp_get_thread_num();
#pragma omp single
    {
      num_thrds = omp_get_num_threads();
    }

    local_sum = 0;
    for (int i = thread_id; i < n; i += num_thrds) {
      x = h * (i + 0.5);
      local_sum += 4. / (1. + x * x);
    }

    partial_sums[thread_id] = local_sum;
  }

  delta = omp_get_wtime() - start;

  double sum = 0.;
  for (int i = 0; i < MaxTHREADS; ++i) {
    sum += partial_sums[i];
  }

  res = h * sum;

  std::cout << "res = " << res << std::endl;
  std::cout << delta * 1000;
}