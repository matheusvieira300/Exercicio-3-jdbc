import java.io.IOException;
import java.sql.*;

public class BancoDados implements InterfaceBancoDados  {
    
    private Connection conexao;
    private Statement statement;
   
    
   
    
    @Override
    public void conectar (String db_url, String db_user, String db_password) throws IOException  {
        try {
        	Log logs = new Log("Log.txt");
        	logs.logger.info("testando");
            conexao = DriverManager.getConnection(db_url, db_user, db_password);
            statement = conexao.createStatement();
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void desconectar() throws IOException{
        try {
            statement.close();
            conexao.close();
            System.out.println("Conexão encerrada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar do banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void consultar(String db_query) {
        try {
            ResultSet resultado = statement.executeQuery(db_query);
            while (resultado.next()) {
                System.out.println(resultado.getString(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o banco de dados: " + e.getMessage());
        }
    }

    @Override
    public int inserirAlterarExcluir(String db_query) {
        try {
            int linhasAfetadas = statement.executeUpdate(db_query);
            System.out.println(linhasAfetadas + " linha(s) afetada(s).");
            return linhasAfetadas;
        } catch (SQLException e) {
            System.out.println("Erro ao executar operação no banco de dados: " + e.getMessage());
            return 0;
        }
    }
}
