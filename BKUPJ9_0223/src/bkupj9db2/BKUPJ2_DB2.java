package bkupj9db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class BKUPJ2_DB2 {

	static Connection conn = null;
	static Statement s = null;
	static PreparedStatement ps = null;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connect();
		StatikusTablaLetrehozas();
		StatikusTablaModosiTas();
		//StatikusAdatfelvetel();
		DinamikusAdatfelvetel();
		StatikusTablaTorles();
		
	}
	
	
	public static void Connect() {
		
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		String user = "H22_BKUPJ9";
		String pwd = "BKUPJ9";
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolodas�s\n");
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*public static void Lekapcs() {
		if (conn != null) {
			try {
			conn.close();
			System.out.println("Sikeres lekapcsolodas \n");
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		}
	}*/
	
	public static void StatikusTablaLetrehozas() {
		String sqlp_auto="create table auto(rsz char(6) primary key, " + "tipus char(10) not null, szin char(10) default 'feher', " +
				"evjarat number(4), ar number(8) check(ar>0))";
		String sqlp_tulaj="create table tulaj (id number(3) primary key, " + "nev char(20) not null, cim char(20), szuldatum date)";
		
		if(conn!=null) {
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp_auto);
				System.out.println("Aut� t�bla l�trej�tt\n");
				s.executeUpdate(sqlp_tulaj);
				System.out.println("Tulajdonos t�bla l�trej�tt\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public static void StatikusTablaModosiTas() {
		if(conn != null) {}
		try {
			String sqlp="alter table auto add(tulaj_id references tulaj";
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			System.out.println("Aut� t�bla m�dos�tva!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusTablaTorles() {
		if(conn != null) {}
		try {
			String sqlp_auto="DROP TABLE auto";
			String sqlp_tulaj="DROP TABLE tulaj";
			s=conn.createStatement();
			s.executeUpdate(sqlp_auto);
			System.out.println("Aut� t�bla t�r�lve!\n");
			s.executeUpdate(sqlp_tulaj);
			System.out.println("Tulajdonos t�bla t�r�lve!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusAdatfelvetel() {
		if(conn != null) {
			String sqlp_tul="insert into tulaj values (1, 'T�th M�t�', " + "'Miskolc', to_date('1980.05.12', 'yyyy.mm.dd'))";
			String sqlp[]= {
				"insert into auto values('aaa111', 'opel', 'piros', 2014, 1650000)",
				"insert into auto values('bbb222', 'mazda', 'null', 2016, 2650000)",
				"insert into auto (rsz, tipus, evjarat, ar) values('ccc333', 'ford',2009, 1500000)"
			};
			
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp_tul);
				System.out.println("Tulaj felv�ve\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			
			for(int i = 0;i < sqlp.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp[i]);
					System.out.println("Aut� felv�ve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	public static void DinamikusAdatfelvetel() {
		if(conn != null) {
			String sqlp="insert into auto(rsz, tipus, szin, evjarat, ar, tulaj_id)" + "values(?,?,?,?,?,?)";
			
			System.out.println("K�rem a rendsz�mot: ");
			String rsz = sc.next().trim();
			System.out.println("K�rem a t�pust: ");
			String tipus = sc.next().trim();
			System.out.println("K�rem a sz�nt: ");
			String szin = sc.next().trim();
			System.out.println("K�rem az �vj�ratot: ");
			int evjarat = sc.nextInt();
			System.out.println("K�rem az �rat: ");
			float ar = sc.nextFloat();
			System.out.println("K�rem a tulajdonos azonos�t�j�t: ");
			int tulaj_id = sc.nextInt();
			
			try {
				ps=conn.prepareStatement(sqlp);
				ps.setString(1, rsz);
				ps.setString(2, tipus);
				ps.setString(3, szin);	
				ps.setInt(4, evjarat);
				ps.setFloat(5, ar);
				ps.setInt(6, tulaj_id);
				ps.executeUpdate();
				ps.close();
				System.out.println("Aut� felv�ve\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
