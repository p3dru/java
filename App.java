package inicio;

import java.net.InetAddress;
import java.util.Scanner;

import java.io.*;
import java.lang.String;


public class AppTracert {

	static final Runtime run = Runtime.getRuntime();
	static Process pro;
	static BufferedReader read;
	
	static void pingar(String host) {
		try {
			if (InetAddress.getByName(host).isReachable(5000))
				System.out.println("Ping Ok: " + host);
			else
				System.out.println("Ping não Ok: " + host);
		}
		catch (Exception e){
			System.err.println("Ping Falhou: " + host + " - " + e);
		}
	}
	
	
	public static void tracert(String host) {
		String cmd = "tracert " + host;
		
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join("& ", cmd));
			
			builder.redirectErrorStream(true);
			
			Process p = builder.start();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			
			while(true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				
				System.out.println(line);
			}
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o link a ser testado: ");
		String host = teclado.nextLine();
		pingar(host);
		
		
		System.out.print("\nDeseja verificar a rota até o endereço? (1 - Sim / 2 - Não): ");
		int escolha = teclado.nextInt();
		if (escolha == 1) {
			tracert(host);
		}
		
		teclado.close();
	}
}
