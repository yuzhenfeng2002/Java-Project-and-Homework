//
//  main.cpp
//  Lexical-Analysis
//
//  Created by YzFENG on 2021/9/17.
//

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

bool isSeparator(char ch)
{
    if (ch == ' ' || ch == ',' || ch == ';' || ch == '(' || ch == ')' ||
        ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == '\t' || ch == '\n')
    {
        return true;
    }
    return false;
}

bool isOperator(char ch) // check if the given character is an operator or not
{
    if (ch == '+' || ch == '-' || ch == '*' || ch == '%' ||
        ch == '/' || ch == '>' || ch == '<' || ch == '!' ||
        ch == '=' || ch == '|' || ch == '&' || ch == '^' ||
        ch == '~' || ch == '?' || ch == ':' || ch == '.')
    {
       return true;
    }
    return false;
}

bool validIdentifier(char* str) // check if the given identifier is valid or not
{
    if (!(str[0] == '_' || (str[0] <= 'z' && str[0] >= 'a') || (str[0] <= 'Z' && str[0] >= 'A'))) {
        return false;
    }
    for (int i = 1; i < strlen(str); i++) {
        if (!(str[i] == '_'
              || (str[i] <= 'z' && str[i] >= 'a')
              || (str[i] <= 'Z' && str[i] >= 'A')
              || (str[i] <= '9' && str[i] >= '0'))) {
            return false;
        }
    }
    return true;
}

bool isKeyword(char *str) // check if the given substring is a keyword or not
{
    if (!strcmp(str, "if") || !strcmp(str, "else") || !strcmp(str, "while") || !strcmp(str, "do")
        || !strcmp(str, "break") ||  !strcmp(str, "continue") || !strcmp(str, "int") || !strcmp(str, "double")
        || !strcmp(str, "float") || !strcmp(str, "return") || !strcmp(str, "char") || !strcmp(str, "case")
        || !strcmp(str, "long") || !strcmp(str, "short") || !strcmp(str, "signed") || !strcmp(str, "switch")
        || !strcmp(str, "unsigned") || !strcmp(str, "void") || !strcmp(str, "static") || !strcmp(str, "struct")
        || !strcmp(str, "sizeof") || !strcmp(str,"default") || !strcmp(str, "volatile") || !strcmp(str, "typedef")
        || !strcmp(str, "enum") || !strcmp(str, "const") || !strcmp(str, "union") || !strcmp(str, "extern")
        || !strcmp(str, "bool") || !strcmp(str, "for") || !strcmp(str, "goto") || !strcmp(str, "register")
        || !strcmp(str, "auto"))
    {
        return true;
    }
    else
    {
       return false;
    }
}

char* subString(string realStr, int l, int r) // extract the required substring from the main string
{
    char* str = (char*) malloc(sizeof(char) * (r - l + 2));
    for (int i = l; i <= r; i++)
    {
        str[i - l] = realStr[i];
    }
    str[r - l + 1] = '\0';
    return str;
}

void lexicalAnalyse(string str)
{
    static bool is_in_comment = false;
    unsigned long len = str.size();
    if (!is_in_comment)
    {
        if (str[0] == '#') {
            cout << str << " IS A PREPROCESSOR STATEMENT\n";
            return;
        }
        int left = 0, right = 0;
        while (right <= len && left <= right) {
            // identify the string
            if (str[left] == '"') {
                int i = left + 1;
                for ( ; i < len; i++) {
                    if (str[i] == '"') {
                        break;
                    }
                }
                char* sub = subString(str, left, i);
                cout << sub << " IS A STRING\n";
                right = i + 1;
                left = right;
            }
            
            // identify comment begin with "//"
            if (left < len - 1 && str[left] == '/' && str[left + 1] == '/') {
                char* sub = subString(str, left, int(len) - 1);
                cout << '"' << sub << "\" IS A COMMENT\n";
                return;
            }
            
            // identify comment begin with "/*"
            if (left < len - 1 && str[left] == '/' && str[left + 1] == '*') {
                int i = left + 2;
                for ( ; i < len - 1; i++) {
                    if (str[i] == '*' && str[i + 1] == '/') {
                        break;
                    }
                }
                if (str[i] == '*' && str[i + 1] == '/') {
                    char* sub = subString(str, left, i + 1);
                    cout << '"' << sub << "\" IS A COMMENT\n";
                    right = i + 2;
                    left = right;
                }
                else
                {
                    is_in_comment = true;
                    return;
                }
            }
            
            // identify the character
            if (str[left] == '\'') {
                int i = left + 1;
                for ( ; i < len; i++) {
                    if (str[i] == '\'') {
                        break;
                    }
                }
                char* sub = subString(str, left, i);
                cout << sub << " IS A CHARACTER\n";
                right = i + 1;
                left = right;
            }
            
            // identify the number
            if (str[left] <= '9' && str[left] >= '0') {
                int i = left + 1;
                for ( ; i < len; i++) {
                    if (!((str[i] <= '9' && str[i] >= '0') || str[i] == '.' || str[i] == 'e' ||
                          str[i] == 'E' || str[i] == '+' || str[i] == '-')) {
                        break;
                    }
                }
                char* sub = subString(str, left, i - 1);
                cout << sub << " IS A NUMBER\n";
                right = i;
                left = right;
            }
            
            // identify the separator
            if (isSeparator(str[left]) == true && left == right) {
                left ++;
                right = left;
            }
            
            // identify the operator
            else if (isOperator(str[left]) == true && left == right) {
                int i = left + 1;
                for ( ; i < len; i++) {
                    if (isOperator(str[i]) == false) {
                        break;
                    }
                }
                char* sub = subString(str, left, i - 1);
                cout << sub << " IS AN OPERATOR\n";
                right = i;
                left = right;
            }
            
            // identify the keyword and identifier
            else if ((isSeparator(str[right]) || isOperator(str[right]) || right == len) && left != right)
            {
                char* sub = subString(str, left, right - 1); // extract substring
                if (isKeyword(sub) == true)
                {
                    cout<< sub <<" IS A KEYWORD\n";
                }
                else if (validIdentifier(sub) == true)
                {
                    cout<< sub <<" IS A VALID IDENTIFIER\n";
                }
                else if (validIdentifier(sub) == false)
                {
                    cout<< sub <<" IS AN INVALID IDENTIFIER\n";
                }
                left = right;
            }
            
            if (isSeparator(str[right]) == false && isOperator(str[right]) == false && right <= len) {
                right ++;
            }
        }
    }
    else
    {
        int i = 0;
        for ( ; i < len - 1; i++) {
            if (str[i] == '*' && str[i + 1] == '/') {
                break;
            }
        }
        if (str[i] == '*' && str[i + 1] == '/') {
            char* sub = subString(str, i + 2, int(len) - 1);
            is_in_comment = false;
            cout << "STATEMENTS ABOVE INCLUDE SEVERAL COMMENTS\n";
            lexicalAnalyse(sub);
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    string infile_name = "/Users/fxb/Desktop/大三上/Java语言程序设计/Homework-1/Lexical-Analysis/Lexical-Analysis/test.c";
    string outfile_name = "/Users/fxb/Desktop/大三上/Java语言程序设计/Homework-1/Lexical-Analysis/Lexical-Analysis/test_result.c";
    ifstream infile;
    ofstream outfile;
    infile.open(infile_name);
    outfile.open(outfile_name);
    
    string line;
    while (getline(infile, line)) {
        cout << line << endl;
        lexicalAnalyse(line);
        cout << endl;
    }
    infile.close();
    return 0;
}