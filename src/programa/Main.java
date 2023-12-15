package programa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Conta;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Conta> lista = new ArrayList<>();
		String path = "c:\\temp\\in.txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			String linha = br.readLine();
			
			while(linha != null && !linha.isBlank())
			{
				String[] campos = linha.split(",");
				String nome = campos[0];
				String email = campos[1];
				Double salario = Double.parseDouble(campos[2]);
				lista.add(new Conta(nome,email,salario));
				linha = br.readLine();
				
			}
			
			System.out.println("Digite o salario: ");
			double salario = sc.nextDouble();
			System.out.println("Email das pessoas com o salari é maior que " + salario);
			
			List<String> nomes = lista.stream()
					.filter(p -> p.getSalario() >= salario)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			nomes.forEach(System.out::println);
			
			double total = lista.stream()
					.filter(p -> p.getNome().charAt(0) == 'M')
					.map(p -> p.getSalario())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Soma do salario total das pessoas com a letra M: "+ String.format("%.2f", total));
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sc.close();
	}

}
