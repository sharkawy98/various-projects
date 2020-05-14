#include <iostream>
#include "Indexes.h"

using namespace std;

int main()
{

    Indexes i;
    i.load_from_files();

    while(true)
    {
        cout << "\n\n1) Add course \n2) Delete course (Id) \n3) Delete course (Instructor name) \n4) Print course (Id) \n"
                << "5) Print course (Instructor name) \n6) Update course (Id) \n7) Update course (Instructor name) \n8) Exit\n"
                << "\n>>Enter choice: ";

        char choice;
        cin >> choice;

        if(choice == '1'){
            cin.ignore();
            i.addCourse();
            i.save_in_Files();
        } else if(choice == '2'){
            string id;
            cout << "\nEnter the id: ";
            cin >> id;
            i.deleteId(id.c_str());
            i.reconstruct_s_index();
            i.save_in_Files();
        } else if(choice == '3'){
            i.deleteName();
            i.save_in_Files();
        } else if(choice == '4'){
            string id;
            cout << "\nEnter the id: ";
            cin >> id;
            i.printId(id.c_str());
        } else if(choice == '5'){
            string name;
            cout << "\nEnter the name: ";
            cin >> name;
            i.printName(name.c_str());
        } else if(choice == '6'){
            string id;
            cout << "\nEnter the id: ";
            cin >> id;
            cin.ignore();
            i.updateId(id.c_str());
            i.save_in_Files();
        } else if(choice == '7'){
            i.updateName();
            i.save_in_Files();
        } else
            break;
    }
}
