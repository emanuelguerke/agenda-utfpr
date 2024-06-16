package dao;

import java.io.IOException;
import java.sql.*;


/**
 *
 * @author a2488094
 */
public class BancoDadosTeste {
    
    public static void main(String[] args) {
        try {
            
            Connection conn = BancoDados.conectar();
            System.out.println("Conex�o estabeleciada");
            
            BancoDados.desconectar();
            System.out.println("Conex�o finalizada");
            
        } catch(SQLException | IOException e) {
            
            System.out.println(e.getMessage());
            
        } 
    }
}