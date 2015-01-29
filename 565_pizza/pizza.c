#include <algorithm>  
#include <iostream>  
#include <cstring>  
#include <string>  
#include <vector>  
#include <cstdio>  
#include <stack>  
#include <list>  
using namespace std;  
   
char entradas[50][40];   
int opcoes[16];  
int intLaco, encontrou, totalEntradasSabores, flag;  
  
void encontraCombinacao(int posicaoLaco) {  
    int i, j;  
    int pos = 15;  
    memset(opcoes, -1, sizeof (opcoes));
	
	//preenche vetor com as opções de sabor pra verificar se será aceito 
    while (pos >= 0) {  
        opcoes[pos] = posicaoLaco & 1;  
        pos--;  
        posicaoLaco >>= 1;  
    }  
    
    //verifica se a combinação será aceita
    for (i = 0; i < totalEntradasSabores; i++) {  
        flag = 0;  
        for (j = 0; entradas[i][j] != ';'; j++) { 
		    //verifica se aceita sabor e se o mesmo esta nas opções
            if (entradas[i][j] == '+') {  
                j++;  
                int t = entradas[i][j] - 65;  
                if (opcoes[t]) {
                    flag = 1; 
				}
            }  
            //verifica se rejeita sabor e se o mesmo esta excluido das combinações
            if (entradas[i][j] == '-') {  
                j++;  
                int t = entradas[i][j] - 65;  
                if (opcoes[t] == 0) {
                    flag = 1;
				}
            }  
        }  
        if (flag == 0) 
            return; 
			
    }  
    
    //opção foi aceita
    if (i == totalEntradasSabores)  
        encontrou = 1;  
}  
  
//escreve resposta
void escreveResposta() {  
    if (encontrou) {  
        printf("Toppings: ");  
        for (int i = 0; i < 16; i++) {  
            if (opcoes[i])  
                printf("%c", i + 65);  
        }  
        printf("\n");  
    } else  
        printf("No pizza can satisfy these requests.\n");  
}  
  
int main() {  
    int i; 
	//processa entradas 
    while (gets(entradas[0]) && strcmp(entradas[0], ".")) {  
        i = 1;  
        encontrou = 0;  
        //le entradas
        while (gets(entradas[i])) {  
            if (strcmp(entradas[i], ".") == 0)  
                break;  
            i++;  
        }
		  
        totalEntradasSabores = i;  
        intLaco = 65535;  //posibilidades combinação sabores
        while (intLaco) {  
            encontraCombinacao(intLaco);  
            if (encontrou)  
                break; 
            intLaco--; 
        }  
        escreveResposta();  
    }  
    return 0;  
} 