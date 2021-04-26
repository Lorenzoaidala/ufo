package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBprova {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=Lollolol98";

		try {
			Connection conn = DriverManager.getConnection(jdbcURL); //creo la connessione col DB
			String sql ="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape <>'' "
					+ "ORDER BY shape ASC "; // bisogna lasciare uno spazio alla fine di ogni riga prima di "
			PreparedStatement st = conn.prepareStatement(sql);// lo statement è il "mezzo" con cui comunico col DB
			

			ResultSet res = st.executeQuery(); // con executeQuery lo statement la esegue e mi ridà un risultato
			// se nella query ho SELECT si usa il metodo executeQuery, se invece ho itruzioni come INSERT o UPDATE si usa il metodo executeUpdate e restituisce un int che indica il numero di righe inserite/aggiornate 
			List <String>formeUFO = new ArrayList<String>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUFO.add(forma);
			}
			System.out.println(formeUFO);
			
			
			String sql2 = " SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ?";
			String parametroShape = "circle";
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, parametroShape);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			st2.close();
			System.out.println("Gli UFO di forma "+parametroShape+" sono: "+count);
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
