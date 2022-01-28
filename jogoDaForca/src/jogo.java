import java.util.Scanner;
import java.util.Random;
public class jogo {

	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		int op=0,stop=0;
		String [][] bancoDeDados = new String[51][51];
		
		preenchimento(bancoDeDados);
		do {
			System.out.println("-- -- -- Forca -- -- --");
			System.out.println("1. Gerenciar Temas");
			System.out.println("2. Gerenciar Palavras");
			System.out.println("3. Jogar");
			System.out.println("4. Sair");
			
			System.out.print("\ndigite a opção desejada: ");
			op = input.nextInt();
	
			switch (op) {
			case 1:
				gerenciadorTemas(bancoDeDados);
				break;
			case 2:
				gerenciadorPalavras(bancoDeDados);
				break;
			case 3:
				jogar(bancoDeDados);
				break;
			case 4:
				System.out.println("Obrigado por jogar, volte sempre!");
				stop=1;
				break;
			default:
				System.out.println("A opção não é válida");
				break;
			}
		}while(stop==0);

	}
	
	//Métodos do menu principal......................
	
	static void gerenciadorTemas(String[][] bancoDeDados){
		int op=0;
		//variável que guarda a operação
			Scanner input = new Scanner(System.in);
			System.out.println("-- -- -- Temas -- -- --");
			System.out.println("1. Cadastrar novo tema");
			System.out.println("2. Excluir um tema");
			System.out.println("3. Buscar tema existente");
			System.out.print("\ndigite a opção desejada: ");
			op = input.nextInt();
		
		switch (op) {
		case 1:
			cadastrarTema(bancoDeDados);
			break;
		case 2:
			excluirTema(bancoDeDados);
			break;
		case 3:
			buscarTema(bancoDeDados);
			break;
		default:
			System.out.println("A opção não é válida");
			break;
		}
	}
	
	static void gerenciadorPalavras(String[][] bancoDeDados){
		int op=0;
		Scanner input = new Scanner(System.in);
		System.out.println("-- -- -- Palavras -- -- --");
		System.out.println("1. Cadastrar nova palavra");
		System.out.println("2. Excluir uma palavra");
		System.out.println("3. Buscar uma palavra");
		System.out.println("4. Listar palavras de um tema");
		System.out.print("\ndigite a opção desejada: ");
		op = input.nextInt();
	
	switch (op) {
	case 1:
		cadastrarPalavra(bancoDeDados);
		break;
	case 2:
		excluirPalavra(bancoDeDados);
		break;
	case 3:
		buscarPalavra(bancoDeDados);
		break;
	case 4:
		listarPalavra(bancoDeDados);
		break;
	default:
		System.out.println("A opção não é válida");
		break;
	}
	}
	
	static void jogar(String[][] bancoDeDados){
		Scanner input = new Scanner(System.in);
		int i=0,posicao_tema=0; //posicao_tema guarda o "i" do tema que for o escolhido
		String controle,temaEscolhido; //a variavel controle coleta as strings das posições para fazer comparações
		
		System.out.println("-- -- -- Vamos Jogar -- -- --");
		System.out.println("selecione um dos temas para jogar");
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle!=null) {
				System.out.println(bancoDeDados[i][0]);
			}
		}
		System.out.println("Digite um tema: ");
		temaEscolhido = input.nextLine();
		
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle==null){
				
			}else if(controle.equals(temaEscolhido)==true) {
				posicao_tema=i;
			}
		}
		
		//--------------Partida-----------------
		char [] tentativas = new char[40];
		char letra1,letra2;
		int x=0,acerto=0,j,k,tam_palavra,erros=0,acert_moment=0,jafoi,jogarNovamente=0;
		String palavra;
		
		/*o "x" guarda o valor randomico gerado, acert_moment serve para o calculo do erro,
		 *  ela inicia com 0, se esse valor não for alterado significa que a letra tentada 
		 *  não tem na palavra.
		*/
		
		Random aleatorio = new Random();
		
		System.out.println("Sorteando uma palavra...");
		System.out.println("  Vamos Começar  ");
		
		do {
			x=aleatorio.nextInt(51);
			palavra=bancoDeDados[posicao_tema][x];
		}while(palavra==null);
		
		i=0; k=0; acerto=0;
		for(i=0;i<40;i++) {	
			
			jafoi=0;
			
			//System.out.println("A palavra da vez é: "+palavra);
			System.out.println("Escolha uma letra: ");
			tentativas[i]= input.next().charAt(0);
			tam_palavra=palavra.length();
			//System.out.println("tamanho da palavra: "+tam_palavra);
			acert_moment=0;
			
			k=0;
			if(i>0) {
				for(k=0;k<i;k++) {
					letra1=tentativas[i];
					letra2=tentativas[k];	
					if(letra1 == letra2) {
						jafoi=1;
					}
				}
			}
			
			if(jafoi==1) {
				System.out.println("Essa letra já foi utilizada, tente outra letra");
			}else {
					j=0;
				for(j=0;j<tam_palavra;j++) {
					letra1=tentativas[i];
					letra2=palavra.charAt(j);
					if(letra1 == letra2) {
						acerto=acerto+1;
						acert_moment=1;
						System.out.print(letra1);
					}else {
						System.out.print(" _ ");
					}
				}
				System.out.println("\nPontuação: "+acerto);
				if(acert_moment==0) {
					erros=erros+1;
				}
				System.out.println("Erros: "+erros);
				
				if(acerto==tam_palavra) {
					System.out.println("\n***Parabéns, voce ganhou!!***");
					System.out.println("\n  Deseja jogar novamente?  ");
					System.out.println("1. SIM");
					System.out.println("2. NÃO");
					jogarNovamente = input.nextInt();
						switch(jogarNovamente) {
						case 1:jogar(bancoDeDados);break;
						case 2:i=40;
						}
					i=40;
				}
				if(erros==5) {
					System.out.println("\n***Você perdeu***\n");
					System.out.println("\n  Deseja jogar novamente?  ");
					System.out.println("1. SIM");
					System.out.println("2. NÃO");
					jogarNovamente = input.nextInt();
						switch(jogarNovamente) {
						case 1:jogar(bancoDeDados);break;
						case 2:i=40;
						}
					i=40;
				}
			}	
		}
	}

	//Submétodos..dos..Temas............................
	
	static void cadastrarTema(String[][] bancoDeDados){
			Scanner input = new Scanner(System.in);
			int i,exist=0;
			String controle,temaEscolhido;
			
			System.out.println("Digite um tema: ");
			temaEscolhido = input.nextLine();
			
			for(i=0;i<51;i++) {
				controle = bancoDeDados[i][0];
				if(controle==null) {
					//System.out.println("Tema Escolhido: "+temaEscolhido);
				}else {
				//System.out.println(controle.equals(temaEscolhido));
				if(controle.equals(temaEscolhido)==true) {
					System.out.println("O tema já existe");
					i=52;
					exist=1;
					}else {
						
					}
				}
			}
			//System.out.println("saiu do laço");
			if(exist==0) {
				i=0;
				for(i=0;i<51;i++) {
					controle = bancoDeDados[i][0];
					if(controle==null) {
						bancoDeDados[i][0]=temaEscolhido;
						i=52;
						System.out.println("Tema adicionado com sucesso");
					}
				}
			}
	}
	
	static void excluirTema(String[][] bancoDeDados){
			Scanner input = new Scanner(System.in);
			int i,j=0,exist=0;
			String controle,temaEscolhido;
			
			System.out.println("Digite o tema que deseja excluir: ");
			temaEscolhido = input.nextLine();
			
			for(i=0;i<51;i++) {
				controle = bancoDeDados[i][0];
				if(controle==null) {
					//System.out.println("Tema Escolhido: "+temaEscolhido);
				}else {
				//System.out.println(controle.equals(temaEscolhido));
				if(controle.equals(temaEscolhido)==true) {
					//System.out.println("O tema já existe");
					j=i;
					i=52;
					exist=1;
					}else {
						
					}
				}
			}
			if(exist==1){
				i=0;
				exist=0;
				for(i=1;i<51;i++) {
					controle = bancoDeDados[j][i];
					if(controle!=null) {
						System.out.println("o tema não pode ser excluído");
						exist=1;
					}
				}
				if(exist==0) {
					bancoDeDados[j][0]=null;
					System.out.println("o tema foi excluído");
				}
			}
		}
	
	static void buscarTema(String[][] bancoDeDados){
		int i=0;
		String controle;
		
		System.out.println("Temas existentes: ");
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle!=null) {
				System.out.println(bancoDeDados[i][0]);
			}
		}
	}
	
	//Submétodos..das..Palavras............................
	
	static void cadastrarPalavra(String[][] bancoDeDados) {
		Scanner input = new Scanner(System.in);
		int i=0,j=0,k=0,exist=0,sem_temas=0;
		String controle,temaEscolhido,palavraEscolhida;
		
		System.out.println("Escolha o tema em que você deseja cadastrar a palavra");
		System.out.println("Temas existentes: ");
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle!=null) {
				System.out.println(bancoDeDados[i][0]);
				sem_temas=1;
			}
		}
		System.out.println("Digite o tema: ");
		temaEscolhido = input.nextLine();
		i=0;
		if(sem_temas==0) {
			System.out.println("Cadastre um tema antes de adicionar uma palavra");
		}else {
			for(i=0;i<51;i++){
				controle = bancoDeDados[i][0];
				if(controle==null) {
				
				}else {
					if(controle.equals(temaEscolhido)==true){
						System.out.println("Digite a palavra que será adicionada: ");
						palavraEscolhida = input.nextLine();
						for(j=1;j<51;j++) {
							controle = bancoDeDados[i][j];
							if(controle==null) {
							
							}else {
								if(controle.equals(palavraEscolhida)==true) {
								System.out.println("A palavra já existe nesse tema\n");
								exist=1;
								i=52;
								j=52;
								}
							}
						}
					
			
						if(exist==0) {
							for(k=1;k<51;k++) {
							controle = bancoDeDados[i][k];
							if(controle==null) {
								System.out.println("A palavra foi adicionada\n");
								bancoDeDados[i][k]=palavraEscolhida;
								k=52;
								i=52;
							}
						}
					}
				}
			}
		}
	}
}
	static void excluirPalavra(String[][] bancoDeDados) {
		Scanner input = new Scanner(System.in);
		int i=0,j=0,k=0,exist=0,sem_temas=0;
		String controle,temaEscolhido;
		
		System.out.println("Escolha o tema em que você deseja excluir a palavra");
		System.out.println("Temas existentes: ");
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle!=null) {
				System.out.println(bancoDeDados[i][0]);
				sem_temas=1;
			}
		}
		System.out.println("Digite o tema: ");
		temaEscolhido = input.nextLine();
		
		if(sem_temas==0) {
			System.out.println("Cadastre um tema antes de excluir uma palavra");
		}else {
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle==null) {
				
			}else {
				if(controle.equals(temaEscolhido)==true) {
					System.out.println("Digite a palavra que será excluída: ");
					temaEscolhido = input.nextLine();
					for(j=0;j<51;j++) {
						controle = bancoDeDados[i][j];
						if(controle==null) {
							
						}else if(controle.equals(temaEscolhido)==true) {
							System.out.println("A palavra foi excluída com sucesso\n");
							bancoDeDados[i][j]=null;
							exist=1;
							i=52;
							j=52;
						}
					}
				}
			}
		}
		if(exist==0) {
			System.out.println("Palavra não encontrada\n");
		}
		}
	}
	static void buscarPalavra(String[][] bancoDeDados) {
		Scanner input = new Scanner(System.in);
		int i=0,j=0,k=0,exist=0,sem_temas=0;
		String controle,palavraEscolhida;
		
		System.out.println("Digite uma palavra para realizar a busca: ");
		palavraEscolhida = input.nextLine();
		
		for(i=0;i<51;i++) {
			for(j=1;j<51;j++) {
				controle = bancoDeDados[i][j];
				if(controle==null) {
					
				}else if(controle.equals(palavraEscolhida)==true) {
					System.out.println("Palavra: '"+palavraEscolhida+"' encontrada no tema = "+bancoDeDados[i][0]);
					exist=1;
				}
			}
		}
		if(exist==0){
			System.out.println("Palavra não encontrada\n");
		}
	}
	static void listarPalavra(String[][] bancoDeDados) {
		Scanner input = new Scanner(System.in);
		int i=0,j=0,k=0,exist=0,sem_temas=0;
		String controle,temaEscolhido;
		
		System.out.println("Selecione um dos temas para a listagem ");
		System.out.println("Temas existentes: ");
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle!=null) {
				System.out.println(bancoDeDados[i][0]);
				sem_temas=1;
			}
		}
		System.out.println("Digite o tema: ");
		temaEscolhido = input.nextLine();
		
		for(i=0;i<51;i++){
			controle = bancoDeDados[i][0];
			if(controle==null) {
				
			}else if(controle.equals(temaEscolhido)==true) {
				System.out.println("As palavras contidas nesse tema são:");
				for(j=1;j<51;j++) {
				if(bancoDeDados[i][j]!=null) {
				System.out.println(bancoDeDados[i][j]);
				exist=1;
				}
				}
			}
		}
		if(exist==0){
			System.out.println("Esse tema está vazio\n");
		}
	}
	
	static void preenchimento(String[][] bancoDeDados) {
		bancoDeDados[0][0]="animais"; bancoDeDados[1][0]="casa";     bancoDeDados[2][0]="esportes";   bancoDeDados[3][0]="frutas";      bancoDeDados[4][0]="países";
		bancoDeDados[0][1]="boi";     bancoDeDados[1][1]="porta";    bancoDeDados[2][1]="futebol";    bancoDeDados[3][1]="banana";      bancoDeDados[4][1]="argentina";
		bancoDeDados[0][2]="coala";   bancoDeDados[1][2]="cama";     bancoDeDados[2][2]="voleibol";   bancoDeDados[3][2]="morango";     bancoDeDados[4][2]="angola";
		bancoDeDados[0][3]="elefante";bancoDeDados[1][3]="janela";   bancoDeDados[2][3]="queimada";   bancoDeDados[3][3]="abacaxi";     bancoDeDados[4][3]="brasil";
		bancoDeDados[0][4]="gaivota"; bancoDeDados[1][4]="telhado";  bancoDeDados[2][4]="natação";    bancoDeDados[3][4]="mamão";       bancoDeDados[4][4]="chile";
		bancoDeDados[0][5]="macaco";  bancoDeDados[1][5]="parede";   bancoDeDados[2][5]="tênis";      bancoDeDados[3][5]="kiwi";        bancoDeDados[4][5]="china";
		bancoDeDados[0][6]="tigre";   bancoDeDados[1][6]="estante";  bancoDeDados[2][6]="basquete";   bancoDeDados[3][6]="tamarindo";   bancoDeDados[4][6]="egito";
		bancoDeDados[0][7]="porco";   bancoDeDados[1][7]="televisão";bancoDeDados[2][7]="ciclismo";   bancoDeDados[3][7]="abacate";     bancoDeDados[4][7]="equador";
		bancoDeDados[0][8]="cachorro";bancoDeDados[1][8]="mesa";     bancoDeDados[2][8]="patinação";  bancoDeDados[3][8]="acerola";     bancoDeDados[4][8]="honduras";
		bancoDeDados[0][9]="hiena";   bancoDeDados[1][9]="fogão";    bancoDeDados[2][9]="surfe";      bancoDeDados[3][9]="amora";       bancoDeDados[4][9]="israel";
		bancoDeDados[0][10]="jabuti"; bancoDeDados[1][10]="cozinha";  bancoDeDados[2][10]="canoagem";  bancoDeDados[3][10]="carambola";  bancoDeDados[4][10]="jamaica";
	}
}