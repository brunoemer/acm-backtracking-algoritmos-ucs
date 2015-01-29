#include<cstdio>
#include<cstring>
#include<iostream>
 
using namespace std;
 
int numeroTestes, comprimento, largura, linhaIndy, colunaIndy;
// vetores para gerar as opções de caminho no vetor da estrada
int x[] = { -1, 0, 1 };
int y[] = { 0, -1, 0 };

//entrada de dados
char entradas[100];
char estrada[100][100];

//IEHOVA
char iehova[] = { 'I', 'E', 'H', 'O', 'V', 'A', '#' };

string caminho[] = { "left", "forth", "right" };
 
void procuraCaminho(int i, int j, int s) {
	//verifica se atingiu o destino
    if(estrada[i][j] == '#') {
    	//adiciona /n na saida
    	cout << endl;
        return;
    }
 
    //verifica as 3 opções do caminho
    for(int k = 0; k < 3; k++){
    	
    	//verifica se a posição é valida
        if(j + x[k] >= 0 && j + x[k] < largura && i + y[k] >= 0){
		    //verifica se a posição atual considerando o deslocamento para frente, esquerda e direita contém uma letra valida para formar a palavra
            if(estrada[i + y[k]][j + x[k]] == iehova[s]) {
            	//adiciona caminho direção no caminho de saida
                cout << caminho[k];
                //verifica se ja encontrou "IEHOVA"
                if(s < 6){
                	cout << " ";
				} 
                procuraCaminho(i + y[k], j + x[k], s + 1);
            }
       }
       
   }
}
 
int main() {
	//le o numero de casos de testes
    scanf("%d", &numeroTestes);
    
    while(numeroTestes--) {
    	
    	//le comprimento e a altura do caminho
        scanf("%d %d\n", &comprimento, &largura);
        
        //le o caminho
        for(int i = 0; i < comprimento; i++) {
            gets(entradas);
            strcpy(estrada[i], entradas);
        }
        
        //percorre a estrada para encontrar posicao Indy's
        //leitura é coluna por linha 
        for(int i = 0; i < comprimento; i++){
            for(int j = 0; j < largura; j++){
                if(estrada[i][j] == '@') {
                 //guarda posicao Indy's 
				  linhaIndy = i, colunaIndy = j;
			    }
			}
		}
		
        //chama função para procurar caminhos
        procuraCaminho(linhaIndy, colunaIndy, 0);  
             
	}
}

