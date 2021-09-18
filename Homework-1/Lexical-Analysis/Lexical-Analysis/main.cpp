//
//  main.cpp
//  Lexical-Analysis
//
//  Created by YzFENG on 2021/9/17.
//

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <string>
#include <ctype.h>


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

bool isPunctuator(char ch) // check if the given character is a punctuator or not
{
    if (ch == ' ' || ch == '+' || ch == '-' || ch == '*' ||
        ch == '/' || ch == ',' || ch == ';' || ch == '>' ||
        ch == '<' || ch == '=' || ch == '(' || ch == ')' ||
        ch == '[' || ch == ']' || ch == '{' || ch == '}' ||
        ch == '&' || ch == '|' || ch == ' ' || ch == '\t' || ch == '\n')
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

bool isOperator(char ch) // check if the given character is an operator or not
{
    if (ch == '+' || ch == '-' || ch == '*' || ch == '%' ||
        ch == '/' || ch == '>' || ch == '<' || ch == '!' ||
        ch == '=' || ch == '|' || ch == '&' || ch == '^' ||
        ch == '~' || ch == '?' || ch == ':')
    {
       return true;
    }
    return false;
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

bool isNumber(char* str) // check if the given substring is a number or not
{
    int i, len = strlen(str),numOfDecimal = 0;
    if (len == 0)
    {
        return false;
    }
    for (i = 0 ; i < len ; i++)
    {
        if (numOfDecimal > 1 && str[i] == '.')
        {
            return false;
        } else if (numOfDecimal <= 1)
        {
            numOfDecimal++;
        }
        if (str[i] != '0' && str[i] != '1' && str[i] != '2'
            && str[i] != '3' && str[i] != '4' && str[i] != '5'
            && str[i] != '6' && str[i] != '7' && str[i] != '8'
            && str[i] != '9' || (str[i] == '-' && i > 0))
            {
                return false;
            }
    }
    return true;
}

char* subString(string realStr, int l, int r) // extract the required substring from the main string
{
    char* str = (char*) malloc(sizeof(char) * (r - l + 2));
    for (int i = l; i <= r; i++)
    {
        str[i - l] = realStr[i];
        str[r - l + 1] = '\0';
    }
    return str;
}


void lexicalAnalyse(string str)
{
    int left = 0, right = 0;
    unsigned long len = str.size();
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
        
        // identify the preprocessor statement
        if (str[left] == '#')
        {
            cout << str << " IS A PREPROCESSOR STATEMENT";
            break;
        }
        
        if (isSeparator(str[left]) == true && left == right) {
            left ++;
            right = left;
        }
        else if (isOperator(str[left]) == true && left == right) {
            int i = left + 1;
            for ( ; i < len; i++) {
                if (isOperator(str[i]) == false) {
                    break;
                }
            }
            char* sub = subString(str, left, i - 1);
            cout << sub << " IS A OPERATOR\n";
            right = i;
            left = right;
        }
        else if ((isSeparator(str[right]) || isOperator(str[right]) || right == len) && left != right)
        {
            char* sub = subString(str, left, right - 1); // extract substring
            if (isKeyword(sub) == true)
            {
                cout<< sub <<" IS A KEYWORD\n";
            }
            else if (isNumber(sub) == true)
            {
                cout<< sub <<" IS A NUMBER\n";
            }
            else if (validIdentifier(sub) == true)
            {
                cout<< sub <<" IS A VALID IDENTIFIER\n";
            }
            else if (validIdentifier(sub) == false)
            {
                cout<< sub <<" IS NOT A VALID IDENTIFIER\n";
            }
            left = right;
        }
        
        if (isSeparator(str[right]) == false && isOperator(str[right]) == false && right <= len) {
            right ++;
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    using namespace std;
    ifstream infile;
    infile.open("/Users/fxb/Desktop/大三上/Java语言程序设计/Homework-1/Lexical-Analysis/Lexical-Analysis/test.c");
    string line;
    while (getline(infile, line)) {
        cout << line << endl;
        lexicalAnalyse(line);
        cout << endl;
    }
    infile.close();
    return 0;
}
