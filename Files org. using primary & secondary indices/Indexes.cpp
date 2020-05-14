#include "Indexes.h"

//
void Indexes::addCourse()
{
    ChangeStates(true);
	ofstream fout(dataFilePath, ios::app);

	Course c;
	cout << "\nEnter course id: "; 			    cin.getline(c.id, 6);
	cout << "\nEnter course name: ";		    cin >> c.cName;
	cin.ignore();
	cout << "\nEnter instructor name: ";	    cin.getline(c.iName, 16);
	cout << "\nEnter # of weeks: ";	            cin >> c.weeks;
	cin.ignore();

	char buffer[100];
	strcpy(buffer, c.id);		        	strcat(buffer, "|");
	strcat(buffer, c.cName.c_str());		strcat(buffer, "|");
	strcat(buffer, c.iName);	    strcat(buffer, "|");
	strcat(buffer, (char*)&c.weeks);	    strcat(buffer, "|");
	short len = strlen(buffer);

	fout.seekp(0, ios::end);
	int offset = fout.tellp();
	fout.write((char*)&len, sizeof(len));
	fout.write(buffer, len);

	// append to primary vector
	Primary pTemp;
	strcpy(pTemp.pk, c.id);
	pTemp.offset = offset;
	pIndex.push_back(pTemp);

	// append to secondary vector
    add_s_index(c.iName, c.id);

    sortIndexes();
	fout.close();
}

//
void Indexes::add_s_index(const char *iName, const char *id)
{
    bool repeat = false;

	for(unsigned int i=0; i < sIndex.size(); i++){
        if(!strcmp(sIndex[i].name, iName)){
            sIndex[i].pos = invertedList(sIndex[i].pos, id);
            repeat = true;
            break;
        }
	}

	if(!repeat){
        Secondary sTemp;
        sTemp.pos = invertedList(-1, id);
        strcpy(sTemp.name, iName);
        sIndex.push_back(sTemp);
	}
}

//
short Indexes::invertedList(short prev, const char* id)
{
    inList l;
    strcpy(l.pk, id);
    l.key = prev;
    iList.push_back(l);

    return iList.size();
}

//
void Indexes::ChangeStates(bool state)
{
    ofstream p(primaryFilePath, ios::out | ios::in);
	ofstream s(secondaryFilePath, ios::out | ios::in);
	p.seekp(0, ios::beg);
	s.seekp(0, ios::beg);
	p << state;
	s << state;
	p.close();
	s.close();
}

//
void Indexes::sortIndexes()
{
    // sort primary index
    sort(pIndex.begin(), pIndex.end(),
         [](const Primary &a, const Primary &b) {
            return atoi(a.pk) < atoi(b.pk);
    });

    // sort secondary index
    sort(sIndex.begin(), sIndex.end(),
         [](const Secondary &a, const Secondary &b) {
            int cmp = strcmp(a.name, b.name);
            return (cmp < 0)? true : false;
    });
}

//
void Indexes::save_in_Files()
{
    ofstream p(primaryFilePath, ios::trunc);
    ofstream s(secondaryFilePath, ios::trunc);
    ofstream l(iListFilePath, ios::trunc);

    ChangeStates(false);

    p.seekp(1, ios::beg);
    s.seekp(1, ios::beg);

    unsigned int i;

	for (i = 0; i < pIndex.size(); i++)
	{
		Primary temp = pIndex[i];
		p.write((char*)&temp, sizeof(temp)); //fixed len record, fixed len field
	}
	p.close();

	for (i = 0; i < sIndex.size(); i++)
	{
		Secondary temp = sIndex[i];
		s.write((char*)&temp, sizeof(temp)); //fixed len record, fixed len field
	}
	s.close();

	for (i = 0; i < iList.size(); i++)
	{
		inList temp = iList[i];
		l.write((char*)&temp, sizeof(temp)); //fixed len record, fixed len field
	}
	l.close();
}

//
void Indexes::load_from_files()
{
    ifstream p(primaryFilePath);
    ifstream s(secondaryFilePath);
    ifstream l(iListFilePath);

    p.seekg(1, ios::beg);
    s.seekg(1, ios::beg);

    Primary pTemp;
    while(p.read((char*)&pTemp, sizeof(pTemp))){
        pIndex.push_back(pTemp);
        cout << pTemp.pk << "------" << pTemp.offset << endl;
    }
    cout << endl;

    Secondary sTemp;
    while(s.read((char*)&sTemp, sizeof(sTemp))){
        sIndex.push_back(sTemp);
        cout << sTemp.name << "*****" << sTemp.pos << endl;
    }
    cout << endl;

    inList iTemp;
    while(l.read((char*)&iTemp, sizeof(iTemp))){
        iList.push_back(iTemp);
        cout << iTemp.pk << "~~~~~~~" << iTemp.key << endl;
    }
    cout << endl;

    p.close();
    s.close();
    l.close();
}

//
int Indexes::search_p_index(const char *pk)
{
    for(size_t i = 0; i < pIndex.size(); i++)
        if(!strcmp(pIndex[i].pk, pk))
            return i;

    return -1;
}

//
void Indexes::deleteId(const char *pk)
{
    ChangeStates(true);

	fstream fout(dataFilePath, ios::out | ios::in);
	int address = search_p_index(pk);
	if (address == -1)
	{
		cout << "\nId not found";
		return;
	}

	fout.seekp(pIndex[address].offset + 2, ios::beg);
	fout << '*';

	pIndex.erase(pIndex.begin() + address);

	fout.close();
}

int Indexes::search_s_index(const char *name)
{
    for(size_t i = 0; i < sIndex.size(); i++)
        if(!strcmp(sIndex[i].name, name))
            return i;

    return -1;
}

//
void Indexes::deleteName()
{
    ChangeStates(true);
	string name; cout << "\nEnter name to delete  \n";  cin >> name;

	fstream fout(dataFilePath, ios::out | ios::in);
	int address = search_s_index(name.c_str());
	if (address == -1)
	{
		cout << "\nName not found";
		return;
	}

	name_deleteList(sIndex[address].pos);
	sIndex.erase(sIndex.begin()+address);

	reconstruct_s_index();
}

//
void Indexes::name_deleteList(short pos)
{
    short next = iList[pos-1].key;

    if(next == -1){
        deleteId(iList[pos-1].pk);
        iList.erase(iList.begin()+(pos-1));
    } else {
        deleteId(iList[pos-1].pk);
        iList.erase(iList.begin()+(pos-1));
        while(next != -1){
            short temp = iList[next-1].key;
            deleteId(iList[next-1].pk);
            iList.erase(iList.begin()+(next-1));
            next = temp;
        }
    }
}

//
void Indexes::reconstruct_s_index()
{
    sIndex.clear();
    iList.clear();

    ifstream fin(dataFilePath);
    char *buffer;
    string name, id;
    while(true){
		short len;
		fin.read((char*)&len, sizeof(len));
		if(fin.eof()) break;
		buffer = new char[len];
		fin.read(buffer, len);
		if (buffer[0] == '*') continue;

		istrstream strbuf(buffer);
        getline(strbuf, id, '|');
        getline(strbuf, name, '|'); // to escape course name
        getline(strbuf, name, '|');

        // append to secondary vector
        add_s_index(name.c_str(), id.c_str());

        delete []buffer;
	}

	fin.close();
	sortIndexes();
}

//
void Indexes::printId(const char *pk)
{
	ifstream fin(dataFilePath);

	int address = search_p_index(pk);
	if (address == -1)
	{
		cout << "\nthis id not exists";
		return;
	}

	short len;
	fin.seekg(pIndex[address].offset, ios::beg);
	fin.read((char*)&len, 2);

	char *buffer = new char[len];
	fin.read(buffer, len);

	istrstream stream(buffer);
	Course temp;
	stream.getline(temp.id, sizeof(temp.id),'|');
	getline(stream, temp.cName, '|');
    stream.getline(temp.iName, sizeof(temp.iName), '|');
    stream.getline((char*)&temp.weeks, sizeof(temp.weeks), '|');

	cout << "\n\nCourse info\n----------------" << "\n ID: " << temp.id << "\n Name: " << temp.cName
            << "\n Instructor: " << temp.iName << "\n Weeks: " << temp.weeks << endl;

	delete [] buffer;
	fin.close();
}

//
void Indexes::printName(const char *name)
{
	ifstream fin(dataFilePath);

	int address = search_s_index(name);
	if (address == -1)
	{
		cout << "\nthis name not exists";
		return;
	}

	short pos = sIndex[address].pos;
	short next = iList[pos-1].key;

    if(next == -1){
        printId(iList[pos-1].pk);
    } else {
        printId(iList[pos-1].pk);
        while(next != -1){
            short temp = iList[next-1].key;
            printId(iList[next-1].pk);
            next = temp;
        }
    }
}

//
void Indexes::updateId(const char *pk)
{
    ifstream fin(dataFilePath);

	int address = search_p_index(pk);
	if (address == -1)
	{
		cout << "\nthis id not exists";
		return;
	}

	deleteId(pk);
	reconstruct_s_index();
	addCourse();
}

//
void Indexes::updateName()
{
    string name; cout << "\nEnter instructor name\n";  cin >> name;
    cout << "\nThe courses of " << name << "\n-------------------------";
    printName(name.c_str());

    string id; cout << "\nEnter the id of the cursor you want update\n";  cin >> id;
    cin.ignore();
    updateId(id.c_str());
}

// destructor to save the indexes and the list
Indexes::~Indexes()
{
   save_in_Files();
}


