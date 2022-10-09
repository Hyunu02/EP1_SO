import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.FileOutputStream;

public class Escalonador {
    public static void main(String[] args) {
        
    ArrayList <BCP> bcpTable = new ArrayList<BCP>();

	// Inicializa todos os processos na memoria e os coloca na tabela de 'prontos'.
	for(int i = 1; i <= 10; i++) {
        String path = "programas/";
		if(i < 10) {
			path += "0" + i + ".txt";
		} else {
			path += i + ".txt";
		}

		File arq = new File(path);
		File arqPrio = new File("programas/prioridades.txt");
		// Lê o conteudo do arquivo e o associa a uma nova instancia de BCP.
		try {
			Scanner sc = new Scanner(arq);
			Scanner scPrio = new Scanner(arqPrio);

			int count = 0;
			while(sc.hasNextLine() && scPrio.hasNextLine()) {
				String data = sc.nextLine();

				// Caso seja a primeira linha, cria a instancia de BCP e já atribui sua prioridade.
				if(count == 0) {
                    for(int j = 1; j < i; j++) {
                        scPrio.nextLine();
                    }
					int prioridade = Integer.parseInt(scPrio.nextLine());
					BCP processo = new BCP(data, prioridade);
                    bcpTable.add(processo);
				}
				count++;	
			}

		}   catch (FileNotFoundException e) {
		        System.out.println("Não foi possível ler o arquivo.");
                e.printStackTrace();
		    }
	    }

        Collections.sort(bcpTable, Comparator.comparing(BCP::getPrioridade));
        Collections.reverse(bcpTable);
        for(BCP b : bcpTable) {
            System.out.println(b);
        }
        File arqQuant = new File("programas/quantum.txt");
        int quantum = 0;
        try {
            Scanner sc = new Scanner(arqQuant);
            quantum = Integer.parseInt(sc.nextLine());
            System.out.println(quantum);
            FileOutputStream fos = new FileOutputStream("OIEMAE.txt"); 
        }
        catch (FileNotFoundException e) {
		        System.out.println("Não foi possível ler o arquivo.");
                e.printStackTrace();
		}
    }
}
