import java.net.InetAddress;
import java.util.Scanner;

import java.io.*;
import java.lang.String;

public class App {
    static final Runtime run = Runtime.getRuntime();
    static Process pro;
    static BufferedReader read;


    static void pingar(String host) {
        try{
            if (InetAddress.getByName(host).isReachable(5000))
                System.out.println("Ping Ok: " + host);
            else
                System.out.println("Ping falhou: " + host);
        }
        catch (Exception e){
            System.err.println("Ping Falhou: " + host + "-" + e);
        }
    }

    public static void tracert(String host){
        System.out.print("Digite o host/ip que deseja testar: ");
        String[] comandos_cmd = {
            "tracert " + host,
            //"echo 1",
            //"echo 2",
        };

        try{
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join("& ", comandos_cmd));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while(true){
                line = r.readLine();
                if (line == null){
                    break;
                }

                System.out.println(line);
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }


    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o link a se testar a seguir: ");
        String host = teclado.nextLine();
        pingar(host);

        System.out.print("\nDeseja verificar a rota até o endereço? (1 - Sim / 2 - Não): ");
        int escolha = teclado.nextInt();
        if (escolha == 1){
            tracert(host);
        }


        teclado.close();

    }
}

//Fonte principal sobre tracerouter: https://pt.stackoverflow.com/questions/57073/executar-comandos-do-cmd-pelo-java