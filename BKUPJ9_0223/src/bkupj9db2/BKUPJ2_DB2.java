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
			System.out.println("Sikeres kapcsolodasás\n");
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
				System.out.println("Autó tábla létrejött\n");
				s.executeUpdate(sqlp_tulaj);
				System.out.println("Tulajdonos tábla létrejött\n");
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
			System.out.println("Autó tábla módosítva!\n");
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
			System.out.println("Autó tábla törölve!\n");
			s.executeUpdate(sqlp_tulaj);
			System.out.println("Tulajdonos tábla törölve!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusAdatfelvetel() {
		if(conn != null) {
			String sqlp_tul="insert into tulaj values (1, 'Tóth Máté', " + "'Miskolc', to_date('1980.05.12', 'yyyy.mm.dd'))";
			String sqlp[]= {
				"insert into auto values('aaa111', 'opel', 'piros', 2014, 1650000)",
				"insert into auto values('bbb222', 'mazda', 'null', 2016, 2650000)",
				"insert into auto (rsz, tipus, evjarat, ar) values('ccc333', 'ford',2009, 1500000)"
			};
			
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp_tul);
				System.out.println("Tulaj felvéve\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			
			for(int i = 0;i < sqlp.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp[i]);
					System.out.println("Autó felvéve\n");
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
			
			System.out.println("Kérem a rendszámot: ");
			String rsz = sc.next().trim();
			System.out.println("Kérem a típust: ");
			String tipus = sc.next().trim();
			System.out.println("Kérem a színt: ");
			String szin = sc.next().trim();
			System.out.println("Kérem az évjáratot: ");
			int evjarat = sc.nextInt();
			System.out.println("Kérem az árat: ");
			float ar = sc.nextFloat();
			System.out.println("Kérem a tulajdonos azonosítóját: ");
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
				System.out.println("Autó felvéve\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
