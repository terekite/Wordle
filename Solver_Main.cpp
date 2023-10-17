#include <iostream>
#include <vector>
#include <string>
#include <fstream>

using namespace std;

string getUpper(string str);
bool isQWord(string str);

# include "testMe.txt" // UNCOMMENT WHEN READY: this will include test code from the testMe.txt file

int main() {
  testMe(); // UNCOMMENT WHEN READY: this function will test the isQWord() function
  vector<string> qWords;
  int longestLocation = 0; // location in qWords of longest
  int totalWordsRead = 0;
  ifstream fileIn;
  ofstream fileOut;

  // 1) open files
  fileIn.open("DictionaryLarge.txt");
  fileOut.open("dataOut.txt");
  
  // 2) add words with Q to the qWords vector
  string s;
  while (!fileIn.eof()) {
    fileIn >> s;
    totalWordsRead++;
    if (isQWord(s))
      qWords.push_back(s);
  }
  
  // 3) find biggest q word in the vector
  if (qWords.size() == 0) { // there might not be any!
    cout << "No Q words found, exiting program";
    exit(0);
  }
  for (int i = 0; i < qWords.size(); i++) {
    if (qWords.at(i).size() > qWords.at(longestLocation).size())
      longestLocation = i;
  }

  // 4) write to out file all of the q words that come before the biggest word
  for (int i = 0; i < longestLocation; i++) {
    fileOut << getUpper( qWords.at(i) ) << endl;
  }

  // 5) print results to console
  cout << "Count of q-words in input file: "
       << totalWordsRead << endl
       << "Count of q-words in output file: "
       << longestLocation << endl
       << "Largest q-word encountered: "
       << getUpper( qWords.at(longestLocation) ) << endl;
    
} // end main()


// Determines whether a string has a q or Q in it
bool isQWord(string str) {
  for (char c : str) { // note - the for each loop here means that an empty string would return false as well, as the loop wouldn't execute
    if (c == 'q' || c == 'Q')
      return true;
  }
  return false;
}

// Returns the uppercase version of a given string
string getUpper(string str) {
  string res = "";
  for (char c : str) {
    if (c >= 'A' && c <= 'Z')
      res.push_back(c);
    else
      res.push_back(c - 32);
  }
  return res;
}

