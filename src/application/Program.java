package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Map<String, Double> totalSales = new HashMap<>();
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				String seller = fields[2];
				Double total = Double.parseDouble(fields[4]);
				
				if(totalSales.containsKey(seller)) {
					double salesSoFar = totalSales.get(seller);
					totalSales.put(seller, total + salesSoFar);
				}
				else {
					totalSales.put(seller, total);
				}
				
				line = br.readLine();
			}
			
			System.out.println("Total de vendas por vendedor: ");
			
			for(String key : totalSales.keySet()) {
				System.out.println(key + " - R$ " + String.format("%.2f", totalSales.get(key)));
			}
			
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}
}
