#ifndef INDEXES_H
#define INDEXES_H

#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <strstream>

using namespace std;


class Indexes
{
    struct Course{
        char id[6];
        string cName;
        char iName[16];
        short weeks;
    };
    struct Primary{
		char pk[6];
		int offset;
	};
    struct Secondary{
		char name[16];
		short pos;
	};
	struct inList{
	    char pk[6];
	    short key;
	};

	char dataFilePath[20] = "data.txt";
	char primaryFilePath[20] = "primary.txt";
	char secondaryFilePath[20] = "secondary.txt";
	char iListFilePath[20] = "list.txt";

	vector<Primary> pIndex;
	vector<Secondary> sIndex;
	vector<inList> iList;

public:

    void addCourse();
    void add_s_index(const char *, const char *);
    short invertedList(short, const char *);


    void ChangeStates(bool);

    void sortIndexes();


    void load_from_files();
    void save_in_Files();


    int search_p_index(const char *);
    void deleteId(const char *);


    int search_s_index(const char *);
    void deleteName();
    void name_deleteList(short);
    void reconstruct_s_index();


    void printId(const char *);

    void printName(const char *);

    void updateId(const char *);
    void updateName();


    ~Indexes();
};

#endif // INDEXES_H
