#include<iostream>
#include<queue>
#include<tuple>
#include<condition_variable>
#include<thread>
#include<string>
#include<fstream>
#include<sstream>
#include<ostream>
#include<string>
#include<algorithm>
#include<chrono>

std::queue<std::string> q;
std::mutex mut;
std::condition_variable cv;
bool finished{ false };

std::ifstream in("input.txt");
std::ofstream out("output.txt");

static void producer() {
	std::string str;

	while (getline(in, str)) {
		std::istringstream ss(str);
		std::string temp;
		std::string line;
		while (getline(ss, temp, ' ')) {
			std::lock_guard<std::mutex> lk{ mut };
			std::reverse(temp.begin(), temp.end());
			//q.push(temp + " ");
			line += (temp + " ");
		}
		line += "\n";
		q.push(line);
		line = "";
	}
	{
		std::lock_guard<std::mutex> lk{ mut };
		finished = true;
	}
	cv.notify_all();
}

static void consumer() {
	while (!finished) {
		std::unique_lock<std::mutex> l{ mut };
		cv.wait(l, [] {return !q.empty() || finished; });

		while (!q.empty()) {
			out << q.front();
			q.pop();
		}
	}
}


int main() {
	auto start = std::chrono::high_resolution_clock::now();

	std::thread t1{producer};
	std::thread t2{consumer};

	t1.join();
	t2.join();

	auto stop = std::chrono::high_resolution_clock::now();
	std::chrono::duration<double> diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds> (diff).count() << " milliseconds\n";

	printf("finished!");
}