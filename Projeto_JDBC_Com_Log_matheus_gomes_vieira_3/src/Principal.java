import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Principal {
    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "";
        
        
    	Log meuLogger = new Log("Log.txt");
		try {
		meuLogger.logger.setLevel(Level.FINE);
		meuLogger.logger.info("Programa iniciou");
		meuLogger.logger.info("Programa executando");
		
		} catch(Exception ex) {
			meuLogger.logger.info("Exception"+ ex.getMessage());
			ex.printStackTrace();
		}
        
        BancoDados bd = new BancoDados();
        bd.conectar(url, user, password);
        
        Scanner s = new Scanner(System.in);
        
        
        System.out.println("Digite o CPF:");
        int coluna1 = s.nextInt();
        System.out.println("Digite o Nome:");
        String coluna2 = s.next();
        System.out.println("Digite a Idade:");
        int coluna3 = s.nextInt();
        
        String inserir1 = "INSERT INTO pessoa (ID, nome, idade) VALUES ('" + coluna1 + "', '" + coluna2 + "', " + coluna3 + ")";
       
        
        bd.inserirAlterarExcluir(inserir1);
        
        
        
        String consultar = "SELECT * FROM pessoa";
        bd.consultar(consultar);
        meuLogger.logger.info("O resultado ficou assim "+ inserir1);
        meuLogger.logger.warning("Programa finalizado");
        bd.desconectar();
       }
    }

