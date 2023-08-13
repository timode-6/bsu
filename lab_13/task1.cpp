void pi_loop_parallelism() {
  int i;
  double h, sum, x;
  sum = 0.;
  double start, delta;

  h = 1. / n;

  start = omp_get_wtime();

#pragma omp parallel for reduction(+:sum) private(x)
  for (i = 0; i < n; i += 1)
  {
    x = h * i;
    sum += 4. / (1. + x * x);
  }

  sum = h * sum;

  delta = omp_get_wtime() - start;

  std::cout << "res = " << sum << std::endl;
  std::cout << delta * 1000;
}