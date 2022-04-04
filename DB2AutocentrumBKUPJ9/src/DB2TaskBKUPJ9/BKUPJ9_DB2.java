package DB2TaskBKUPJ9;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class BKUPJ9_DB2 {
	private static Connection conn = null;
	private static Statement s = null;
	private static PreparedStatement ps = null;
	private static Scanner sc = new Scanner(System.in);

	
	private static String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	private static String user = "H22_BKUPJ9";
	private static String pwd = "BKUPJ9";
	private static ResultSet rs;
	private static CallableStatement cs;
	
	
	public static void main(String[] args) {
		DriverReg();
		Connect();

		StatikusTablaTorles();
		StatikusTablaLetrehozas();
		StatikusTablaModosiTas();
		StatikusAdatfelvetel();
		StatikusAdattorles();

		StatikusLekerdezes();
		ModosithatoKurzor();
		DinamikusLekerdezes();
		DinamikusAdattorles();
	}
	
	public static void DriverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver Regisztrálás!\n");
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void Connect() {
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolodas\n");
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void StatikusTablaTorles() {
		if(conn != null) {}
		try {
			String sqlp_beszallito="DROP TABLE BESZALLITO";
			String sqlp_auto="DROP TABLE auto";
			String sqlp_vasarlo="DROP TABLE vasarlo";
			s=conn.createStatement();
			s.executeUpdate(sqlp_beszallito);
			System.out.println("Beszallito tábla törölve!\n");
			s.executeUpdate(sqlp_vasarlo);
			System.out.println("Vasarlo tábla törölve!\n");
			s.executeUpdate(sqlp_auto);
			System.out.println("Auto tábla törölve!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusTablaLetrehozas() {		
		String sqlp_beszallito="CREATE TABLE beszallito"
	            + "("
	            + " ID int not null,"
	            + " cegnev char(30) NOT NULL,"
	            + " szekhely char(40) NOT NULL,"
	            + " raktarkeszlet integer,"
	            + " szallitas date NOT NULL,"
	            + " PRIMARY KEY (ID)"
	            + ")";
		
		String sqlp_auto="CREATE TABLE auto"
	            + "("
	            + " rsz char(6) NOT NULL,"
	            + " beszallito_ID int NOT NULL,"
	            + " tipus char(20) NOT NULL,"
	            + " szin char(10) NOT NULL,"
	            + " motor char(10) NOT NULL,"
	            + " ar integer NOT NULL,"
	            + " gyartasi_ido date NOT NULL,"
	            + " PRIMARY KEY (rsz)"
	            + ")";
		
		String sqlp_vasarlo="CREATE TABLE vasarlo"
	            + "("
	            + " adoszam numeric(10) NOT NULL,"
	            + " nev char(12) NOT NULL,"
	            + " lakhely char(10) NOT NULL,"
	            + " szulido date NOT NULL,"
	            + " PRIMARY KEY (adoszam)"
	            + ")";
		
		if(conn!=null) {
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp_beszallito);
				System.out.println("Beszállító tábla létrejött\n");
				s.executeUpdate(sqlp_auto);
				System.out.println("Autó tábla létrejött\n");
				s.executeUpdate(sqlp_vasarlo);
				System.out.println("Vásárló tábla létrejött\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public static void StatikusTablaModosiTas() {
		if(conn != null) {}
		try {
			String sqlp="alter table vasarlo add(auto_id references auto)";
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			System.out.println("Autó tábla módosítva!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusAdatfelvetel() {
		if(conn != null) {
			String sqlp_besz[]={
					"insert into beszallito values (1, 'Ford Hungaria Bt.', 'Budapest, Fõ út 101.', 110, to_date('2022.05.12', 'yyyy.mm.dd'))",
					"insert into beszallito values (2, 'Skoda Hungaria Bt.', 'Budapest, Mellék út 101.', 200, to_date('2022.06.11', 'yyyy.mm.dd'))",
					"insert into beszallito values (3, 'Opel Hungaria Bt.', 'Budapest, Sáfrány út 3.', 300, to_date('2022.04.12', 'yyyy.mm.dd'))",
					"insert into beszallito values (4, 'Mazda Hungaria Bt.', 'Budapest, Vágány út 101.', 400, to_date('2022.07.09', 'yyyy.mm.dd'))"
					};
			
			String sqlp_auto[]= {
					"insert into auto values('jdc761',1, 'Ford Mondeo', 'piros', '1.8dt', 14000000, to_date('2022.01.12', 'yyyy.mm.dd'))",
					"insert into auto values('jbc767',2, 'Skoda Octavia', 'kék', '2.0 crtdi', 16000000, to_date('2021.03.12', 'yyyy.mm.dd'))",
					"insert into auto values('axk455',3, 'Opel Insignia', 'fehér', '1.7dti', 19000000, to_date('2022.09.21', 'yyyy.mm.dd'))",
					"insert into auto values('okl767',4, 'Mazda 6', 'piros', '1.6b', 11000000, to_date('2022.03.12', 'yyyy.mm.dd'))"
			};
			
			String sqlp_vas[]= {
					"insert into vasarlo values(8485825968, 'Próba László', 'Szerencs, Fõ út 101.', to_date('1998.01.12', 'yyyy.mm.dd'),'jdc761')",
					"insert into vasarlo values(8485825847, 'Próba Béla', 'Kazincbarcika, Fõ út 101.', to_date('1994.01.30', 'yyyy.mm.dd'),'jbc767')",
					"insert into vasarlo values(8485824724, 'Próba Péter', 'Eger, Fõ út 101.', to_date('1991.01.18', 'yyyy.mm.dd'),'axk455')",
					"insert into vasarlo values(8485827896, 'Próba Imre', 'Debrecen, Fõ út 101.', to_date('1997.01.21', 'yyyy.mm.dd'),'okl767')"
				};
			
			for(int i = 0;i < sqlp_besz.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp_besz[i]);
					System.out.println("Beszállító felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
			
			for(int i = 0;i < sqlp_auto.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp_auto[i]);
					System.out.println("Auto felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
			
			for(int i = 0;i < sqlp_vas.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp_vas[i]);
					System.out.println("Vásárló felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	public static void StatikusAdattorles() {
		System.out.println("Törlendõ beszallito: ");
		String id = sc.next();
		String sqlp="delete from beszallito where raktarkeszlet >0 AND ID like '"+id+"'";
		if(conn != null) {
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp);
				s.close();
				System.out.println(id + " azonosítójú beszállító törölve!\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public static void StatikusLekerdezes() {
		System.out.println("Add meg a táblát, amelynek szeretnéd az adatait:");
		String tabla = sc.next().trim();
		if(tabla.equals("auto")) {
			if(conn != null) {
				String sqlp="select * from auto";
				System.out.println("Rendszám BeszállítóID Típus Szín Motor Ár Évjárat");
				System.out.println("------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							String rsz = rs.getString("rsz");
							int beszallito_id=rs.getInt("beszallito_ID");
							String tipus = rs.getString("tipus");
							String szin = rs.getString("szin");
							String motor = rs.getString("motor");
							int ar = rs.getInt("ar");
							String evjarat = rs.getString("gyartasi_ido");
							
							System.out.println(rsz+"  "+beszallito_id+"  "+tipus+"  "+szin+"  "+motor+"  "+ar+"  "+evjarat);
					}
					Eldontendo();
					rs.close();
					
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}else if(tabla.equals("beszallito")) {
			if(conn != null) {
				String sqlp="select * from beszallito";
				System.out.println("ID Cegnev Székhely Raktarkeszlet Szállítás");
				System.out.println("------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							int id=rs.getInt("ID");
							String cegnev = rs.getString("cegnev");
							String szekhely = rs.getString("szekhely");
							int raktarkeszlet = rs.getInt("raktarkeszlet");
							String szallitas = rs.getString("szallitas");
							
							System.out.println(id+"  "+cegnev+"  "+szekhely+"  "+raktarkeszlet+"  "+szallitas);
					}
					Eldontendo();
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}else if(tabla.equals("vasarlo")){
			if(conn != null) {
				String sqlp="select * from vasarlo";
				System.out.println("Adoszam Nev Lakhely Születési idõ");
				System.out.println("------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							long adoszam=rs.getLong("adoszam");
							String nev = rs.getString("nev");
							String lakhely = rs.getString("lakhely");
							String szulido = rs.getString("szulido");
							
							System.out.println(adoszam+"  "+nev+"  "+lakhely+"  "+szulido);
					}
					Eldontendo();
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}else {
			System.out.println("Ilyen tábla nincs!");
			StatikusLekerdezes();
		}
	}
	
	public static void DinamikusLekerdezes() {
		System.out.println("Beszállító ID-je: ");
		String id = sc.next().trim();
		String sqlp = "select cegnev from beszallito where raktarkeszlet>0 AND ID= '"+id+"'";
		if(conn != null) {
			try {
				s=conn.createStatement();
				s.executeQuery(sqlp);
				rs=s.getResultSet();
				while(rs.next()) {
						String cegnev = rs.getString("cegnev");
						
						System.out.println("Cégnév: "+cegnev);
				}
				rs.close();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}	
	}
	
	public static void Eldontendo() {
		System.out.println("Szeretnéd egy másik tábal adatait?(I/N)");
		String igen_nem = sc.next().trim();
		if(igen_nem.equals("I") || igen_nem.equals("i") || igen_nem.equals("igen") || igen_nem.equals("Igen") || igen_nem.equals("IGEN")){
			StatikusLekerdezes();
		}else if(igen_nem.equals("N") || igen_nem.equals("nem") || igen_nem.equals("Nem") || igen_nem.equals("NEM") || igen_nem.equals("n")) {
			System.out.println("Értettem!");
		}else {
			System.out.println("Rossz választ adtál, kérlek igen-nem valamelyikével válaszolj! ");
			Eldontendo();
		}
	}
	
	public static void DinamikusAdattorles() {
		System.out.println("Törlendõ beszállító: ");
		String id = sc.next();
		String sqlp="delete from "+ user +".BESZALLITO where ID=?";
		if(conn != null) {
			try {
				ps=conn.prepareStatement(sqlp);
				ps.setString(1, id);
				ps.executeUpdate();
				ps.close();
				System.out.println(id + " azonosítójú beszállító törölve!\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	
	public static void ModosithatoKurzor() {
		System.out.println("Rendszám: ");
		String rsz = sc.next().trim();
		String sqlp = "select szin from auto where rsz= '"+rsz+"'";
		if(conn != null) {
			try {
				s=conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs=s.executeQuery(sqlp);
				while(rs.next()) {
					rs.updateString("szin", "lila");
					rs.updateRow();
				}
				System.out.println(rsz+" rendszámú autó színe lila lett!");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}	
	}

}
