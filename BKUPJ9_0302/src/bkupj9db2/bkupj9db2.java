package bkupj9db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class bkupj9db2 {
	static Connection conn = DriverManager.getConnection("jdbc:oracle:thin:FSPA4J/FSPA4J@193.6.5.58:1521:XE");
	static Statement s;
	static ResultSet rs;
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user from dual");
			rs.next();
			System.out.println(rs.getString(1));
			rs.close();
			conn.close();
			
			System.out.println("hello");
		}
		catch (Exception ee) {
			System.out.println("hiba:" + (ee.getMessage()));
		}

}

public void DriverReg() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Sikeres Driver regisztralas\n");
	}
	catch (Exception e) {
		System.err.println(e.getMessage());
	}
}

public void Connect() {
	Connection conn = null;
	String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	String user = "H22_BKUPJ9";
	String pwd = "BKUPJ9";
	try {
		conn = DriverManager.getConnection(url, user, pwd);
		System.out.println("Sikeres kapcsolodas¡s\n");
	}catch (Exception e) {
		System.err.println(e.getMessage());
	}
}

public void Lekapcs() {
	if (conn != null) {
		try {
		conn.close();
		System.out.println("Sikeres lekapcsolodas \n");
	}catch (Exception e) {
		System.err.println(e.getMessage());
	}
	}
}

public void InsterTul(String tulajazon, String nev, String szemig, String szulhely, String szulido) {
	String sqlp = " insert into Tulajdonos values(" + tulajazon +", "+nev+", "+szemig+", "+szulhely+","+szulido+")";
	try {
		s = conn.createStatement();
		s.execute(sqlp);
	}catch (SQLException e) {
		e.printStackTrace();
	}
}

public void Statikusfelvitel() {
	if (conn !=null) {
		String sqlp_tul = "insert into tulaj values(1, 'Toth Mate', " + " 'Miskolc', to_date('1980.05.12', 'yyy.mm.dd'))";
		String[] sqlp = {
				"insert into auto values ('aaa111','opel','piros',2014,1650000,1)",
				"insert into auto values ('bbb222','mazda','null',2016,2800000,1)",
				"insert into auto (rsz, tipus, evjarat,ar) values ('ccc333','ford',2009,1500000)"
		};
		try {
			s = conn.createStatement();
			s.executeUpdate(sqlp_tul);
			System.out.println("Tulaj felvetele\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		for (int i=0; i<sqlp.length; i++) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp[i]);
				System.out.println("Auto felvetele\n");
				s.close();
			} catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}

public void StatikusTablaLetrehozas() {
	String sqlp_auto = "create table auto ( rsz char(6) primary key, "+"tipus char(10) not null, szin char (10) default 'feher'"+"evjarat number(4), ar number(8) check(ar>100) )";
	String sqlp_tulaj = "create table tulaj ( id number(3) primary key, "+"nev char(20) not null, cim char (20) szuldatum(date),)";
	if (conn != null) {
		try {
			s = conn.createStatement();
			s.executeUpdate(sqlp_auto);
			System.out.println("Auto tabla letrejott\n");
			s.executeUpdate(sqlp_tulaj);
			System.out.println("Tulajdonos tabla letrejott\n");
			s.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}

public void StatikusLekerdezes() {
	if (conn != null) {
		String sqlp = "select * from auto";
		System.out.println("Rendszam Tipus Szin Evjarrat    Ar   Tulaj");
		System.out.println("-----------------------------------------");
		try {
			s = conn.createStatement();
			s.executeQuery(sqlp);
			rs = s.getResultSet();
			while(rs.next()) {
				String rsz = rs.getString("rsz");
				String tipus = rs.getString("tupus");
				String szin = rs.getString("szin");
				int evjarat = rs.getInt("evjarat");
				int ar = rs.getInt("ar");
				int tulaj_id = rs.getInt("tulaj_id");
				System.out.println(rsz+"\t\t"+tipus+"\t"+szin+"\t"+evjarat+"\t"+ar+"\t"+tulaj_id);
			}
			rs.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}

public void StatikusAdattorles() {
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Torlendo auto rendszama: ");
	String rsz = sc.next();
	String sqlp = "delete from auto where rsz like '" + rsz + "'";
	if (conn != null) {
		try {
			s =conn.createStatement();
			s.executeUpdate(sqlp);
			s.close();
			System.out.println(rsz + " rendszamu auto torolve\n");
		}catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}

public void proba2 (String dnev) {
	try {
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		Connection kap = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","u1","u1");
		
		Statement pp = kap.createStatement();
		ResultSet eredm = pp.executeQuery("select nev, fizetes from dolgozok where nev = '"+dnev+",");
		while (eredm.next()) {
			System.out.println(eredm.getString("nev") + " : " + eredm.getInt ("fizetes"));
		}
		eredm.close();
		kap.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}