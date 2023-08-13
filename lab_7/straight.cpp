#include<iostream>
#include<fstream>
#include<sstream>
#include<ostream>
#include<string>
#include<algorithm>
#include<chrono>


int main() {
	auto start = std::chrono::high_resolution_clock::now();

	std::ifstream in("input.txt");
	std::ofstream out("output.txt");
	std::string str;
	while(getline(in, str)) {
		std::istringstream ss(str);
		std::string temp;
		while (getline(ss, temp, ' ')) {
			std::reverse(temp.begin(), temp.end());
		    out << temp << " ";
		}
		out << "\n";
	} 

	auto stop = std::chrono::high_resolution_clock::now();
	std::chrono::duration<double> diff = stop - start;
	std::cout << diff.count() << " seconds\n";
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>
		(diff).count() << " milliseconds\n";
	printf("\n");
}