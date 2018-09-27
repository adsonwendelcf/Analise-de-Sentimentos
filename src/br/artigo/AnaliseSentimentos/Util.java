package br.artigo.AnaliseSentimentos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Util {
	public static String buscarArquivo(String nomeArquivo) {
		 String simboloDivisor = ";";
		String output = "";
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(nomeArquivo);
			br = new BufferedReader(fr);
			
			String linha = br.readLine();
			while (linha!=null) {		
				if (linha.length()>0) {
					if (output.length()>0)
						output += simboloDivisor;
					output +=  linha;
					linha = br.readLine();
				} else {
					linha = br.readLine();
				}
				
			 			
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output.trim();
	}
}
