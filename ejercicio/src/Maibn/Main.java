package Maibn;
import Clases.ConecBD;
import java.sql.*;
public class Main {

    public static void main(String[] args) {


        ConecBD metodos = new ConecBD();

        Connection conec = metodos.iniciarConex("AlmacenesLeo");



    }

}
