package br.artigo.AnaliseSentimentos;
import br.artigo.AnaliseSentimentos.Util; 

public abstract class Analisador {   
	
	public int analizar(String frase, String dominio) { 
		frase = retirarCaracteresEspeciais(frase); 
		int pontuacao = BuscaPalavraDominio(frase, dominio); 
		return pontuacao;
	}

	public String retirarCaracteresEspeciais(String frase) {	
		String fraseProcessada = frase.toLowerCase();		
		fraseProcessada = fraseProcessada.replaceAll("[.;!?:,]", "");	
		fraseProcessada = fraseProcessada.trim();		 
		return fraseProcessada;
	}	
 
	protected int BuscaPalavraDominio(String frase, String dominio) {
		if(frase.toLowerCase().contains(dominio.toLowerCase())){
			
			int totalPositivas = buscaPalavraPositivas(frase) ;
			int totalNegativas = buscaPalavraNegativas(frase) ;			
			int classificacao =  totalPositivas - totalNegativas;
		
			return classificacao;
		}
		else
			return 0;
	}
	
	private int buscaPalavraPositivas(String frase) {	
		/*Polariza Palavras Positivas*/
		String palavrasPositivasStr = Util.buscarArquivo("positivas.txt");				 
		String[] listaPalavrasPositivas = palavrasPositivasStr.toLowerCase().split(";");			 
		int positivas = mineraPalavrasCalculo(frase, listaPalavrasPositivas);
		return positivas;
	}
	
	private int buscaPalavraNegativas(String frase) {
		/*Polariza Palavras Negativas*/
		String palavrasNegativasStr = Util.buscarArquivo("negativas.txt");
		String[] listaPalavrasNegativas = palavrasNegativasStr.toLowerCase().split(";");			
		int negativas = mineraPalavrasCalculo(frase, listaPalavrasNegativas);
		return negativas;
	}
	
	private int mineraPalavrasCalculo(String frase, String[] palavrasBusca) {
		int calculo = 0;
		
		
		
		for(String palavraPeso : palavrasBusca) {
			 String [] bancoLexicoPonderado = palavraPeso.split(",");
             String palavra = bancoLexicoPonderado[0];             
             int peso = Integer.parseInt(bancoLexicoPonderado[1].trim());			
             
             /*Localiza uma palavra na frase*/
             if(frase.contains(palavra)) {
            	            	 
				int ponderacao = qtdPalavras(frase, palavra);
            	/*int ponderacao = qtdPalavrasTrabalhoFuturo(frase, palavra, peso);*/
				calculo += peso * ponderacao;				
				frase = frase.replaceFirst(palavra, "");
			}
             
		}
		return calculo;
	}	
	
	private int qtdPalavras(String text, String palavra){
		int calculo = 0;		
		while(text.contains(palavra)){			
			text = text.replace(palavra,"");
			calculo++;
		}
		return calculo;				
	}
	
	private int qtdPalavrasTrabalhoFuturo(String text, String palavra, int peso){
		
		 
		
		 /**/
		int palavraMudaSentido = 0;
		int calculo = 0;
    	String [] frasePalavra  = text.split(" ");
    	for(String palavralocalizada : frasePalavra) {
    		
    		if (palavralocalizada.contains("não")) {
    			 System.out.println( " Não encontado ");
    			 palavraMudaSentido = 1;
    		} else {
    			palavraMudaSentido = 0;
    		}
    		
    		if (palavraMudaSentido == 0) {	
	    		while(palavralocalizada.contains(palavra)){		
	    			palavralocalizada = "";
	    			calculo = calculo + peso;
	    			 
	    		}
    		} else {
    			calculo = calculo + (peso*-1);
    			palavraMudaSentido = 0;
    		}
    		
    		
    	}
    	
    	/**/
		
		
		 
		return calculo;				
	}
}
