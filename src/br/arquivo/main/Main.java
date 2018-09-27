package br.arquivo.main; 
import br.artigo.AnaliseSentimentos.Analisador;
import br.artigo.AnaliseSentimentos.Util; 
import br.artigo.AnaliseSentimentos.Util; 

 

public class Main {
	public static final String ARQUIVO_FRASES = "frases3.txt";
	public static final String SIMBOLO_DIVISORIA = ";";
	
	public static void main(String[] args) {		 
		Analisador analisadorNovo = new Analisador() {}; 
		String frasesStr = Util.buscarArquivo(ARQUIVO_FRASES);
		String[] listaFrases = frasesStr.split(SIMBOLO_DIVISORIA);		
		
		
		String dominio = "Nike"; 
		int contador = 1;
		for(String frase : listaFrases) {
			int sentimento = analisadorNovo.analyseAqui(frase, dominio);
			System.out.println(contador + "-" + frase);			
			System.out.print("     --> analise : ");
		 	
            if(sentimento <0) {
            	System.out.println("NEGATIVO" + sentimento); 
            } 
            else if(sentimento == 0) {
            	System.out.println("NEUTRO " + sentimento);
            }
            else {
            	System.out.println("POSITIVO " + sentimento);
            }
			contador++;

		}
				
		}
	}

