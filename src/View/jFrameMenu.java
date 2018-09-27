package View; 

import java.awt.BorderLayout;
import br.artigo.AnaliseSentimentos.Analisador;
import br.artigo.AnaliseSentimentos.Util;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class jFrameMenu extends JFrame {
	public static String Retorno = ";";

	/**
	 * Launch the application.
	 */
	
	public static final String ARQUIVO_FRASES = "frases3.txt"; 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrameMenu frame = new jFrameMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jFrameMenu() {
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		
		ImageIcon imagem = new ImageIcon(getClass().getResource("analise.jpeg"));
		JLabel label = new JLabel(imagem);
		
		getContentPane().add(label);
		
		setSize(668, 512);
		setLocationRelativeTo(this);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 608);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);		
		
		
		JMenuItem mntmDigitao = new JMenuItem("Buscar Arquivo");
		mntmDigitao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  JFrame frame = new JFrame("Informe o dominio");

				  String dominio = JOptionPane.showInputDialog(frame, "Informe o domínio para a mineração?");
 
				
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setApproveButtonText("Analisar");
			    int status = fileChooser.showOpenDialog(null);

			    if (status == JFileChooser.APPROVE_OPTION) {
			      File selectedFile = fileChooser.getSelectedFile(); 
			      
			      String url = selectedFile.getParent()+'\\'+selectedFile.getName(); 
			      String ARQUIVO_FRASESS =url;
			      
			  	Analisador analisadorNovo = new Analisador() {}; 
			  	
				String frasesStr = Util.buscarArquivo(ARQUIVO_FRASESS);
				String[] listaFrases = frasesStr.split(";");
				
				/**/
				/*System.out.println("*********************");
				ArrayList<String> R___Resultado2 = new ArrayList<String>();	
				String[] palavrasVariidas;
				int cont = 0;
				for(String frase : listaFrases) {
					 String[] Frasesaida = analisadorNovo.retirarCaracteresEspeciais(frase).split(" ");
					 for(String palavra : Frasesaida) {
						 cont++;						  
						 System.out.println(palavra + " - " +  cont);
						 
						 if(R___Resultado2.contains(palavra)) {
							 //System.out.println("----SIM---- " );
						 } else {
							 R___Resultado2.add( palavra);
						 }
						 
						 
						 
						  
					 }
					 
					
				}
				 System.out.println("----Inicio d Palavras para polarizar ---- " );
				Iterator<String> itrq = R___Resultado2.iterator();
			      while(itrq.hasNext()) {
			         Object element1 = itrq.next();
			         System.out.println(element1 + "   ");
			         
			      }
			      System.out.println("---- FIM Palavras para polarizar ---- " );
				
				
				 
				System.out.println("*********************");   */
				 
				/**/
				
				
				/*  Melhoria na aplicação... se em uma frase estiver 
				 * [ficou legal] é positivo 
				 * [Não ficou legal] é negatico
				 * então quarda sempre a ultima palavra para ver se é um não, assim o não mulda o sentido da frase
				 *  */

				JTextPane textPane = new JTextPane();
											 		
				int contador = 1;
				int contadorPositivo = 0;
				int contadorNeutro = 0;
				int contadorNegativo = 0;
				ArrayList<String> R___Resultado = new ArrayList<String>();				 
				
				for(String frase : listaFrases) {
					int sentimento = analisadorNovo.analizar(frase, dominio);
					R___Resultado.add(contador + "-" + frase);
					Retorno =   contador + "-" + frase +"\n"; 
					Retorno = Retorno + "    --> analise : " ;
				 
				 	
		            if(sentimento <0) {
		            	R___Resultado.add(" --NEGATIVO->"+sentimento);
		            	Retorno = Retorno + "NEGATIVO ->"+sentimento ; 
		            	contadorNegativo++;
		            } 
		            else if(sentimento == 0) { 
		            	R___Resultado.add( " --NEUTRO"); 
		            	contadorNeutro++;
		            }
		            else {
		            	R___Resultado.add(" -- POSITIVO->"+sentimento);
		            	Retorno = Retorno + "  POSITIVO ->"+sentimento ; 
		            	contadorPositivo++;
		            }
					contador++;

				}
				
				
				Retorno = "";
				
				contador = contador -1;
				
				
				float porcentagem_positiva = 0;
				float porcentagem_negativa = 0;
				float porcentagem_neutra = 0;
				 
				
				porcentagem_positiva = (contadorPositivo * 100)/contador;
				porcentagem_negativa = (contadorNegativo * 100)/contador;
				porcentagem_neutra = (contadorNeutro * 100)/contador;
				
				 R___Resultado.add("  "  );
				 R___Resultado.add(" Resumo"  );
				 R___Resultado.add("  "  );
				 
				 R___Resultado.add("Positivo = "+contadorPositivo +" de " +contador + " ->  "  + String.format("%.2f", porcentagem_positiva) + "%"   );
			     R___Resultado.add("Negativo = "+contadorNegativo +" de " +contador + " -> "  + String.format("%.2f", porcentagem_negativa) + "%"  );
			     R___Resultado.add("Neutro = "+contadorNeutro +" de " +contador  + " -> "  + String.format("%.2f", porcentagem_neutra) + "%"  );
			     
				Iterator<String> itr = R___Resultado.iterator();
			      while(itr.hasNext()) {
			         Object element = itr.next();
			         System.out.println(element + " ");
			         Retorno = Retorno + element + " \n";
			      }
				
				getContentPane().add(textPane, BorderLayout.CENTER); 
				
		 
				
				System.out.println(Retorno);
				
				if (Retorno.length() > 0) {
					JOptionPane.showMessageDialog(null,Retorno);
					 
					
				}
			      
				
				///////////
		/*		https://www.devmedia.com.br/gerando-pdf-itext/18843
		 * https://www.youtube.com/watch?v=sxArv-GskLc
				
			 Document document = new Document();
			      try {
			          
			          PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF_DevMedia.pdf"));
			          document.open();
			          
			          // adicionando um parágrafo ao documento
			          document.add(new Paragraph("Gerando PDF em Java - metadados"));
			          document.addSubject("Gerando PDF em Java");
			          document.addKeywords("www.devmedia.com.br");
			          document.addCreator("iText");
			          document.addAuthor("Davi Gomes da Costa");
			}
			      catch(DocumentException de) {
			          System.err.println(de.getMessage());
			      }
			      catch(IOException ioe) {
			          System.err.println(ioe.getMessage());
			      }
			      document.close();   */
				///////////
			      
			      
			      
			    } else if (status == JFileChooser.CANCEL_OPTION) {
			      System.out.println("canceled");
			    }
			}
		});
		mnArquivo.add(mntmDigitao);
		
		
		
		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		
		
	}

}
