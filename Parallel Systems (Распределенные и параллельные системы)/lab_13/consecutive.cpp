void pi() {
  double h, sum, x;
  sum = 0.;
  double start, delta;

  h = 1. / n;

  start = omp_get_wtime();

  for (int i = 0; i < n; i ++) {
    x = h * (i + 0.5);
    sum += 4. / (1. + x * x);
  }

  sum = h * sum;

  delta = omp_get_wtime() - start;

  std::cout << "res = " << sum << std::endl;
  std::cout << delta * 1000;
}