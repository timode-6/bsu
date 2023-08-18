#include<vector>
#include<iostream>
#include<chrono>
#include<thread>
#include<numeric>
#include<functional>
#include<random>
#include<ctime>
#include<cstdlib>

template<typename Iterator, typename T>
struct accumulate_block
{
	void operator()(Iterator first, Iterator last, T& result)
	{
		result = std::accumulate(first, last, result);
	}
};

template<typename Iterator, typename T>
T parallel_accumulate(Iterator first, Iterator last, T init)
{
	unsigned long const length = std::distance(first, last);
	if (!length)
		return init;
	unsigned long const min_per_thread = 25;
	unsigned long const max_threads =
		(length + min_per_thread - 1) / min_per_thread;
	unsigned long const hardware_threads = 5;
	//std::thread::hardware_concurrency();
	unsigned long const num_threads =
		std::min(hardware_threads != 0 ? hardware_threads : 2, max_threads);
	unsigned long const block_size = length / num_threads;
	std::vector<T> results(num_threads);
	std::vector<std::thread> threads(num_threads - 1);
	Iterator block_start = first;
	for (unsigned long i = 0; i < (num_threads - 1); ++i)
	{
		Iterator block_end = block_start;
		std::advance(block_end, block_size);
		threads[i] = std::thread(
			accumulate_block<Iterator, T>(),
			block_start, block_end, std::ref(results[i]));
		block_start = block_end;
	}
	accumulate_block<Iterator, T>()(
		block_start, last, results[num_threads - 1]);

	for (auto& entry : threads)
		entry.join();

	return std::accumulate(results.begin(), results.end(), init);
}
const uint64_t n = 1000000000;
int main() {

	uint64_t* v = new uint64_t[n];
	srand(time(0));

	int p = 2;//количество дочерних потоков

	for (int i = 0; i < n; i++) {
		v[i] = (rand() % 1000) + 1;
	}

	std::cout << std::endl;



	// параллельное аккумулирование
	auto start = std::chrono::high_resolution_clock::now();

	uintmax_t result = parallel_accumulate(v, v + n - 1, 0);

	auto stop = std::chrono::high_resolution_clock::now();
	std::chrono::duration<double> diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";

	std::cout << "\nThe Parallel Result -> " << result << "\n\n";




	// прямое аккумулирование
	start = std::chrono::high_resolution_clock::now();

	result = std::accumulate(v, v + n-1, 0);

	stop = std::chrono::high_resolution_clock::now();
	diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";

	std::cout << "\nThe Straight Result -> " << result << "\n\n";
}