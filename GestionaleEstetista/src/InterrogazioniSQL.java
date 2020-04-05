import java.sql.* ;
import java.text.SimpleDateFormat ;
import java.util.ArrayList ;
import java.util.Calendar ;
import java.util.Random ;
import java.util.Scanner ;

public class InterrogazioniSQL
{
	//costuttori
	public InterrogazioniSQL ( )
		{
	
					try
						{
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch ( ClassNotFoundException e )
						{
							System.out.println ( "" ) ;
						}
					
					url ="jdbc:mysql://localhost:3306/CentroEstetico"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					username = "root";
					pwd = "Furiabuia";
					con =null;
		}
	//metodi
	public void open (  )
		{
			 try
				{
					con=DriverManager.getConnection(url,username,pwd);
				} catch ( SQLException e )
				{
					System.out.println ( "Impossibile stabilire la connessione con il server" ) ;
					return;
				}
		}
	public void	close (  ) 
		{
			try
				{
					con.close ( );
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore con la connessione" ) ;
				}
			
		}
	
	public boolean op1(String tipo, String desc, String data, String ora, int durata, String codiceF, int codice, int codice1)
		{	
			try
			{
				if(this.op2(data, ora))
				{
					String query = "insert into Appuntamento(Tipo, Descrizione, Data_appuntamento, Orario, Durata, Estetista, Cliente, Sala) "
									+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, tipo);
					st.setString(2, desc);
					st.setString(3, data);
					st.setString(4, ora);
					st.setInt(5, durata);
				
					String codiceFE = "";
					
					int oraUno = Integer.parseInt(ora.substring(0, 2)) + 1;
					int oraUnoBack =  Integer.parseInt(ora.substring(0, 2)) - 1;
					int oraDueBack =  Integer.parseInt(ora.substring(0, 2)) - 2;
					
					String oraInMeno = "";
					String dueOreInMeno = "";
					String oraInPiu = oraUno + ora.substring(2);
					
					if(oraUnoBack < 10 && oraDueBack < 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack >= 10 && oraDueBack < 10)
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack < 10 && oraDueBack >= 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					else
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista Estetista E, Specializzazione S, Specializzazione P " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E, Specializzazione S, Specializzazione P " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E, Specializzazione S, Specializzazione P " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1)))) "
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E, Specializzazione S, Specializzazione P " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1)))) " 
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					st.setString(6, codiceFE);
					st.setString(7, codiceF);
					
					String sala = "";
				
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S, Macchinario M, Macchinario N " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                               where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );	
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S, Macchinario M, Macchinario N " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S, Macchinario M, Macchinario N " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S, Macchinario M, Macchinario N " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice 
								+ " and P.Macchinario = " + codice1 
								+ " and E.Codice_fiscale = S.Estetista and E.Codice_fiscale = P.Estetista;" );
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					
					st.setString(8, sala);
					st.executeUpdate();
					
					Statement st2 = con.createStatement();
					ResultSet r2 = st2.executeQuery("select max(Codice) from Appuntamento");
					
					int codiceApp = r2.getInt("max(Codice)");
					
					Statement st1 = con.createStatement();
					st1.executeUpdate("insert into Utilizzo(Appuntamento, Macchinario) values(" + codiceApp + ", " + codice + ");");
					
					Statement st21 = con.createStatement();
					st21.executeUpdate("insert into Utilizzo(Appuntamento, Macchinario) values(" + codiceApp + ", " + codice1 + ");");
					return true;
				}
				else
				{
					return false;
				}
			}
			catch(SQLException e)
			{
				return false;
			}
		}

	public boolean op1(String tipo, String desc, String data, String ora, int durata, String codiceF, int codice)
		{
			try
			{
				if(this.op2(data, ora))
				{
					String query = "insert into Appuntamento(Tipo, Descrizione, Data_appuntamento, Orario, Durata, Estetista, Cliente, Sala) "
									+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, tipo);
					st.setString(2, desc);
					st.setString(3, data);
					st.setString(4, ora);
					st.setInt(5, durata);
				
					String codiceFE = "";
					
					int oraUno = Integer.parseInt(ora.substring(0, 2)) + 1;
					int oraUnoBack =  Integer.parseInt(ora.substring(0, 2)) - 1;
					int oraDueBack =  Integer.parseInt(ora.substring(0, 2)) - 2;
					
					String oraInMeno = "";
					String dueOreInMeno = "";
					String oraInPiu = oraUno + ora.substring(2);
					
					if(oraUnoBack < 10 && oraDueBack < 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack >= 10 && oraDueBack < 10)
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack < 10 && oraDueBack >= 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					else
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista  E join Specializzazione S on E.Codice_fiscale = S.Estetista " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
								+ " and S.Macchinario = " + codice + ";" );
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E join Specializzazione S on E.Codice_fiscale = S.Estetista " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
										+ " and S.Macchinario = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E join Specializzazione S on E.Codice_fiscale = S.Estetista " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1)))) "
								+ " and S.Macchinario = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista E join Specializzazione S on E.Codice_fiscale = S.Estetista " + 
								"where E.Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1)))) " + 
										"							 and S.Macchinario = " + codice + ";");
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					st.setString(6, codiceFE);
					st.setString(7, codiceF);
					
					String sala = "";
				
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S join Macchinario M on M.Nome_sala = S.Nome " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
										+ " and M.Codice = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S join Macchinario  M on M.Nome_sala = S.Nome " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
										+ " and M.Codice = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S join Macchinario  M on M.Nome_sala = S.Nome " + 
								"where S.Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1))))"
								+ " and M.Codice = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala S join Macchinario  M on M.Nome_sala = S.Nome " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                  where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))))"
										+ " and M.Codice = " + codice + ";");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					
					st.setString(8, sala);
					st.executeUpdate();
					
					Statement st2 = con.createStatement();
					ResultSet r2 = st2.executeQuery("select max(Codice) from Appuntamento");
					int codiceApp =0;
					if(!r2.first())
						{
							return false;
						}
					else
						{
							codiceApp = r2.getInt("max(Codice)");
						}

					Statement st1 = con.createStatement();
					st1.executeUpdate("insert into Utilizzo(Appuntamento, Macchinario) values(" + codiceApp + ", " + codice + ");");
					//con.commit ( );
					return true;
				}
				else
				{
					return false;
				}
			}
			catch(SQLException e)
			{
				return false;
			}
		}
		
	public boolean op1(String tipo, String desc, String data, String ora, int durata, String codiceF)
		{
			try
			{
				if(this.op2(data, ora))
				{
					String query = "insert into Appuntamento(Tipo, Descrizione, Data_appuntamento, Orario, Durata, Estetista, Cliente, Sala) "
									+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, tipo);
					st.setString(2, desc);
					st.setString(3, data);
					st.setString(4, ora);
					st.setInt(5, durata);
				
					String codiceFE = "";
					
					int oraUno = Integer.parseInt(ora.substring(0, 2)) + 1;
					int oraUnoBack =  Integer.parseInt(ora.substring(0, 2)) - 1;
					int oraDueBack =  Integer.parseInt(ora.substring(0, 2)) - 2;
					
					String oraInMeno = "";
					String dueOreInMeno = "";
					String oraInPiu = oraUno + ora.substring(2);
					
					if(oraUnoBack < 10 && oraDueBack < 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack >= 10 && oraDueBack < 10)
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = "0" + oraDueBack + ora.substring(2);
					}
					else if(oraUnoBack < 10 && oraDueBack >= 10)
					{
						oraInMeno = "0" + oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					else
					{
						oraInMeno = oraUnoBack + ora.substring(2);
						dueOreInMeno = oraDueBack + ora.substring(2);
					}
					
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista " + 
								"where Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista" + 
								"where Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista " + 
								"where Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Estetista " + 
								"where Codice_fiscale not in (select Estetista " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								codiceFE = res.getString("Codice_fiscale");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					st.setString(6, codiceFE);
					st.setString(7, codiceF);
					
					String sala = "";
				
					if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUno == 19)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					else if(oraUnoBack == 9 && oraDueBack == 8)
					{
						try
						{
							Statement st1 = con.createStatement();
							ResultSet res = st1.executeQuery("select * " + 
								"from Sala " + 
								"where Nome not in (select Sala " + 
								"					from Appuntamento " + 
								"                    where Data_appuntamento = '" + data + "'" +
								"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
								"                    or Codice in (select Codice " + 
								"								from Appuntamento " + 
								"                                where Data_appuntamento = '" + data + "'  and Orario < '" + ora + "' " + 
								"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
						
							
							if(!res.first())
							{
								return false;
							}
							else
							{
								sala = res.getString("Nome");
							}
						}
						catch(SQLException e)
						{
							return false;
						}
					}
					
					st.setString(8, sala);
					st.executeUpdate();
					return true;
				}
				else
				{
					return false;
				}
			}
			catch(SQLException e)
			{
				return false;
			}
		}
		
	public boolean op2(String data, String ora)
		{
			int oraUno = Integer.parseInt(ora.substring(0, 2)) + 1;
			int oraUnoBack =  Integer.parseInt(ora.substring(0, 2)) - 1;
			int oraDueBack =  Integer.parseInt(ora.substring(0, 2)) - 2;
			
			String oraInMeno = "";
			String dueOreInMeno = "";
			String oraInPiu = oraUno + ora.substring(2);
				
			if(oraUnoBack < 10 && oraDueBack < 10)
			{
				oraInMeno = "0" + oraUnoBack + ora.substring(2);
				dueOreInMeno = "0" + oraDueBack + ora.substring(2);
			}
			else if(oraUnoBack >= 10 && oraDueBack < 10)
			{
				oraInMeno = oraUnoBack + ora.substring(2);
				dueOreInMeno = "0" + oraDueBack + ora.substring(2);
			}
			else if(oraUnoBack < 10 && oraDueBack >= 10)
			{
				oraInMeno = "0" + oraUnoBack + ora.substring(2);
				dueOreInMeno = oraDueBack + ora.substring(2);
			}
			else
			{
				oraInMeno = oraUnoBack + ora.substring(2);
				dueOreInMeno = oraDueBack + ora.substring(2);
			}
		
			if(oraUno != 19 && oraUnoBack != 8 && oraDueBack != 8)
			{
				try
				{
					Statement st = con.createStatement();
					ResultSet res = st.executeQuery("select * " + 
						"from Sala " + 
						"where Nome not in (select Sala " + 
						"					from Appuntamento " + 
						"                    where Data_appuntamento = '" + data + "'" +
						"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
						"                    or Codice in (select Codice " + 
						"								from Appuntamento " + 
						"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
						"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
				
					
					if(!res.first())
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				catch(SQLException e)
				{
					return false;
				}
			}
			else if(oraUno == 19)
			{
				try
				{
					Statement st = con.createStatement();
					ResultSet res = st.executeQuery("select * " + 
						"from Sala " + 
						"where Nome not in (select Sala " + 
						"					from Appuntamento " + 
						"                    where Data_appuntamento = '" + data + "'" +
						"                    and Orario >= '" + ora + "' and Orario < '19:00:00' " + 
						"                    or Codice in (select Codice " + 
						"								from Appuntamento " + 
						"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
						"                                and ((Orario >= '" + dueOreInMeno + "' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
				
					
					if(!res.first())
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				catch(SQLException e)
				{
					return false;
				}
			}
			else if(oraUnoBack == 8)
			{
				try
				{
					Statement st = con.createStatement();
					ResultSet res = st.executeQuery("select * " + 
						"from Sala " + 
						"where Nome not in (select Sala " + 
						"					from Appuntamento " + 
						"                    where Data_appuntamento = '" + data + "'" +
						"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
						"                    or Codice in (select Codice " + 
						"								from Appuntamento " + 
						" 								where Data_appuntamento = '" + data + "' " +
						"                                 and Orario < '" + ora + "' " + 
						"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '09:00:00' and Durata = 1))));");
				
					
					if(!res.first())
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				catch(SQLException e)
				{
					return false;
				}
			}
			else if(oraUnoBack == 9 && oraDueBack == 8)
			{
				try
				{
					Statement st = con.createStatement();
					ResultSet res = st.executeQuery("select * " + 
						"from Sala " + 
						"where Nome not in (select Sala " + 
						"					from Appuntamento " + 
						"                    where Data_appuntamento = '" + data + "'" +
						"                    and Orario >= '" + ora + "' and Orario < '" + oraInPiu + "' " + 
						"                    or Codice in (select Codice " + 
						"								from Appuntamento " + 
						"                                 where Data_appuntamento = '" + data + "' and Orario < '" + ora + "' " + 
						"                                and ((Orario >= '09:00:00' and Durata = 2) or (Orario >= '" + oraInMeno + "' and Durata = 1))));");
				
					
					if(!res.first())
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				catch(SQLException e)
				{
					return false;
				}
			}
			
			return true;
		}
	
	public String op3(String data)
		{
			ArrayList<String> oreLibere = this.generateOreLibere();
			String result = "";
			
			for(int i = 0; i < oreLibere.size(); i++)
			{
				if(this.op2(data, oreLibere.get(i)))
				{
					result += oreLibere.get(i) + "\n";
				}
			}
			
			return result;
		}
	
	public String op4(String orario)
		{
			String data = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			int anno = Integer.parseInt(data.substring(0, 4));
			boolean isBisestile = false;
			
			if(anno%4 == 0)
			{
				if(anno%100 == 0)
				{
					if(anno%400 == 0)
					{
						isBisestile = true;
					}
					else
					{
						isBisestile = false;
					}
				}
				else
				{
					isBisestile = true;
				}
			}
			else
			{
				isBisestile = false;
			}
			
			String result = "";
			for(int count = 0; count <= 30; count++)
			{	
				if(this.op2(data, orario))
				{
					result += data + "\n";
				}
				
				int giorno = Integer.parseInt(data.substring(8));
				int mese = Integer.parseInt(data.substring(5, 7));
					
				if(mese == 2 && giorno == 28 && !isBisestile)
				{
					mese++;
					giorno = 1;
					data = data.substring(0, 5) + mese + "-0" + giorno;
				}
				else if(mese == 2 && giorno == 29 && isBisestile)
				{
					mese++;
					giorno = 1;
					data = data.substring(0, 5) + mese + "-0" + giorno;
				}	
				else if(giorno == 30 && (mese == 11 || mese == 4 || mese == 6 || mese == 9))
				{
					giorno = 1;
					mese++;
					if(mese == 4 || mese == 6)
					{
						data = data.substring(0, 5) + "0" + mese + "-0" + giorno;
					}
					else
					{
						data = data.substring(0, 5) + mese + "-0" + giorno;
					}
				}
				else if(giorno == 31 && mese != 12)
				{
					giorno = 1;
					mese++;
					if(mese < 10)
					{
						data = data.substring(0, 5) + "0" + mese + "-0" + giorno;
					}
					else
					{
						data = data.substring(0, 5) + mese + "-0" + giorno;
					}
				}
				else if(mese == 12 && giorno == 31)
				{
					giorno = 1;
					mese = 1;
					anno++;
					data = anno + "-0" + mese + "-0" + giorno;
				}
				else
				{
					giorno++;
					if(giorno < 10)
					{
						data = data.substring(0, 8) + "0" + giorno;
					}
					else
					{
						data = data.substring(0, 8) + giorno;
					}
				}
			}
			
			return result;
		}
	
	public String op5(int codiceMacchinario)
		{
			String primaOra="";
			String data="";
			try
				{
					Statement op = con.createStatement ( );
					java.util.Date date =new java.util.Date ( );
					Calendar caledario = Calendar.getInstance();
					caledario.setTime ( date );
					data = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
					
					boolean done=false;
					while(!done)
						{
							ArrayList < String > orelibere=generateOreLibere ( );
							ArrayList < String > oraInizio=new ArrayList<> ( );
							ArrayList < String > oraFine=new ArrayList<> ( );
							
							
							ResultSet orari=op.executeQuery ( " select Orario,Durata"
																	+ " from Appuntamento"
																	+ " where Data_appuntamento='"+data+"'and codice in( select Appuntamento "
																																	+ "  from Utilizzo"
																																	+ " where Macchinario="+codiceMacchinario+")"
																																	+ " order by Orario");
							if(!orari.first ( ))
								{
									return "non ci sono appuntamenti in giornata l'appuntamento può essere prenotato dalle ore 9:00 alle 18:00";
								}
							
							String oraI="",oraF="";
							
								do
								{
									oraI=orari.getString ( "Orario" ).substring ( 0,5 );
									oraInizio.add ( oraI );
									int fine=Integer.parseInt ( oraI.substring ( 0 ,2 ) );
									oraF=fine+orari.getInt ( "Durata" )+oraI.substring ( 2,5 );
									oraFine.add ( oraF );
								}while(orari.next ( ));
									
								
							
							for ( int i = 0 ; i < oraInizio.size ( ); i ++ )
								{
									int in=0,fin=0;
									for ( int j = 0 ; j < orelibere.size() ; j ++ )
										{
											if(orelibere.get (j).equals ( oraInizio.get ( i ) ))
												{
													in=j;
												}
											if(orelibere.get (j).equals ( oraFine.get ( i ) ))
												{
													fin=j;
												}
											
										}
									int k=in;
									while ( k<fin) 
										{
											orelibere.remove ( in );
											k++;
										}
								}
							if(orelibere.get ( 0 ).equals ( "19:00" ))
								{
									orelibere.remove ( 0 );
								}
							if(orelibere.size ( )>0)
								{
									done=true;
									primaOra=orelibere.get ( 0 );
								}
							else 
								{
									caledario.add(Calendar.DAY_OF_MONTH, 1);
									data = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
								}
								
						}
				} catch ( SQLException e )
				{
					return "errore";
				}
			
			return "l'appuntamento può essere prenotato in data "+data+" alle ore "+primaOra;
		}
	
	public String  op6 (  )
		{
			String lista="";
			try
				{
					Statement tipo = con.createStatement ( ) ;
					ResultSet listaTipologie=tipo.executeQuery ( " select Tipo,count(*) as numero_appuntamenti" + 
																		" from  Appuntamento" + 
																		" group by Tipo;");
					
					while(listaTipologie.next ( ))
						{
							lista+=String.format ( " |%20s\t|%10s\t|\n" , listaTipologie.getString ( "Tipo" ), listaTipologie.getString( "numero_appuntamenti" )); 
						}
				} catch ( SQLException e )
				{
					// restituisce una stringa vuota che viene controllata nel main
				}		
			return lista;
		}
	
	public boolean op7(String codiceF, String cognome, String nome, int eta, String sesso, String via, String citta, int cap)
		{
			try
			{	
				String query = "insert into Cliente(Codice_fiscale, Cognome, Nome, Età, Sesso, Via, Città, CAP) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement st1 = con.prepareStatement(query);
				st1.setString(1, codiceF);
				st1.setString(2,  cognome);
				st1.setString(3,  nome);
				st1.setInt(4, eta);
				st1.setString(5, sesso);
				st1.setString(6, via);
				st1.setString(7, citta);
				st1.setInt(8, cap);
				
				st1.executeUpdate();
				
				return true;
			}
			catch(SQLException e)
			{
				return false;
			}	
		}
		
	public boolean op8(String codiceF, String cognome, String nome, double stipendio, int eta, String assunzione, String contratto, int orario, int giorniLav, int special)
		{
			try
			{	
				String query = "insert into Estetista(Codice_fiscale, Cognome, Nome, Stipendio, Età, Data_assunzione, Tipo_contratto, Orario_di_Lavoro, Giorni_lavorativi, Specialità) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement st1 = con.prepareStatement(query);
				st1.setString(1, codiceF);
				st1.setString(2,  cognome);
				st1.setString(3,  nome);
				st1.setDouble(4, stipendio);
				st1.setInt(5, eta);
				st1.setString(6, assunzione);
				st1.setString(7, contratto);
				st1.setInt(8, orario);
				st1.setInt(9, giorniLav);
				st1.setInt(10, special);
				
				st1.executeUpdate();
				
				return true;
			}
			catch(SQLException e)
			{
				return false;
			}	
		}
		
	public boolean op8(String codiceF, String cognome, String nome, double stipendio, int eta, String assunzione, String contratto, int orario, int giorniLav, int special, int codice)
		{	
			try
			{	
				String query = "insert into Estetista(Codice_fiscale, Cognome, Nome, Stipendio, Età, Data_assunzione, Tipo_contratto, Orario_di_Lavoro, Giorni_lavorativi, Specialità) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				PreparedStatement st1 = con.prepareStatement(query);
				st1.setString(1, codiceF);
				st1.setString(2,  cognome);
				st1.setString(3,  nome);
				st1.setDouble(4, stipendio);
				st1.setInt(5, eta);
				st1.setString(6, assunzione);
				st1.setString(7, contratto);
				st1.setInt(8, orario);
				st1.setInt(9, giorniLav);
				st1.setInt(10, special);
				
				st1.executeUpdate();
				
				String query1 = "insert into Specializzazione(Estetista, Macchinario)"
						+ "values(?, ?);";
			
				PreparedStatement st2 = con.prepareStatement(query1);
				st2.setString(1, codiceF);
				st2.setInt(2, codice);
			
				st2.executeUpdate();
				return true;
			}
			catch(SQLException e)
			{
				return false;
			}
		}
	
	public String  op9 (  )
		{
			String lista="";
			try
				{
					Statement estetiste = con.createStatement ( ) ;
					ResultSet listaEstetiste=estetiste.executeQuery ( " select *" + 
																			" from estetista" + 
																			" where Ore_utilizzo_macchinari = ( select max(Ore_utilizzo_macchinari)"
																													+ " from estetista"
																													+ " where specialità=1);");
					
					lista+=String.format ( "|%40s\t|%20s\t|%20s\t|%10s\t|%3s\t|%10s\t|%10s\t|%10s\t|%10s\t|%10s\t|%10s\t|\n" , "Codice fiscale", "Cognome" , "Nome" , "Stipendio","Età", "Data assunzione","Tipo contratto","Ore di lavoro","Giorni lavorativi" ,"Ore utilizzo macchinari" ,"Specialità" ); 
					while(listaEstetiste.next ( ))
						{
							lista+=String.format ( "|%25s\t|%20s\t|%20s\t|%10s\t|%3s\t|%15s\t|%20s\t|%15s\t|%25s\t|%40s\t|%20s\t|\n" , listaEstetiste.getString ( "Codice_fiscale" ), listaEstetiste.getString ( "Cognome" ),listaEstetiste.getString ( "Nome" ),listaEstetiste.getString ( "Stipendio" ),listaEstetiste.getString ( "Età" ),listaEstetiste.getString ( "Data_assunzione" ),listaEstetiste.getString ( "Tipo_contratto" ),listaEstetiste.getString ( "Orario_di_lavoro" ),listaEstetiste.getString ( "Giorni_lavorativi" ),listaEstetiste.getString ( "Ore_utilizzo_macchinari" ),listaEstetiste.getString ( "Specialità" )); 
						}
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore nell'interrogazione" ) ;
				}		
			return lista;
		}
	
	public String op10 (String estetista )
		{
			Statement stMacchinario ;
			String info="";
			try
				{
					stMacchinario = con.createStatement ( ) ;
					ResultSet risultato=stMacchinario.executeQuery (	"select *"
																			+ " from Macchinario"
																			+ " where Codice in ( select Macchinario"
																								+ " from specializzazione "
																								+ " where estetista='"+estetista+"');");
					while(risultato.next ( ))
					{
						info+=String.format ( " |%30s\t|%30s\t|%20s\t|%20s\t|%8s\t|%4s\t|%4s\t|%8s\t|\n" , risultato.getString ( "Marca" ), risultato.getString ( "Nome" ),risultato.getString ( "Modello" ),risultato.getString ( "Descrizione" ),risultato.getString ( "Tipo" ),risultato.getString ( "Antinvecchiamento" ),risultato.getString ( "Dimagrimento" ),risultato.getString ( "Nome_sala" )); 
					}
					
				} 
			catch ( SQLException e )
				{
					return "Si è verificato un errore"; 
				}
			
			return info ;
					
		}
	
	public String op11(String codiceF, String contratto, int ore, int gg)
		{
			try
			{
				Statement st = con.createStatement();
				st.executeUpdate("update Estetista set Tipo_contratto = '" + contratto + "', Orario_di_lavoro = " + ore + ", Giorni_lavorativi = " + gg + " where Codice_fiscale = '" + codiceF + "';");
				
				return "AGGIORNAMENTO AVVENUTO CON SUCCESSO";
			}
			catch(SQLException e)
			{
				return "AGGIORNAMENTO FALLITO";
			}
		}
	
	public boolean  op12 (String cf,String data,String ora,String nuovaOra )	
		{
			Statement cliente ;
			ArrayList < String > sale=getNamesSala();
		
			Random salaGen=new Random ( );
			try
				{
					cliente = con.createStatement ( ) ;
					String fineApp="";
					int durata=0;
					int macchinario=0;
					
					
					
					ResultSet riscliente=cliente.executeQuery ("  select u.Macchinario,a.Durata,a.Codice " + 
																	"  from Appuntamento a join Utilizzo u on a.Codice=u.Appuntamento" + 
																	"  where a.Cliente = '"+cf+"' and a.Data_appuntamento='"+data+"' and a.Orario='"+ora+"';");
					
					if(!riscliente.first ( ))
						{
							ResultSet ris=cliente.executeQuery ("  select Durata,Codice " + 
																	"  from Appuntamento " + 
																	"  where Cliente = '"+cf+"' and Data_appuntamento='"+data+"' and Orario='"+ora+"';");
							if(!ris.first ( ))
								{
									return false;
								}
							else
								{
									durata=ris.getInt ( "Durata" );
									String durataString="0"+durata+":00+00";
									int codice=ris.getInt ( "Codice" );
									int fine = durata+Integer.parseInt ( nuovaOra.substring ( 0,2 ) );
									fineApp=fine+nuovaOra.substring ( 2 );
									if(fineApp.compareTo ( "19:00:00" )>0)
										{
											return false;
										}
									
									ResultSet appSala=cliente.executeQuery ( " select Sala" + 
																					" from Appuntamento" + 
																					" where Data_appuntamento='"+data+"' and Orario <='"+fineApp+"'  and time(orario+time('"+durataString+"')) >='"+nuovaOra+"'"
																					+ "group by sala;" );
									
									if(!appSala.first ( ))
										{
											String nuovaSala=sale.get ( salaGen.nextInt ( sale.size ( )) );
											cliente.executeUpdate ( " update Appuntamento "
																	+ " set Orario ='"+nuovaOra+"' , Sala ='"+nuovaSala+"'"
																	+ " where Codice ='"+codice+"';");
											return true;
										}
									else
										{
											ArrayList < String > saleOccupate=new ArrayList<> ( );
												do
												{
													saleOccupate.add ( appSala.getString ( "sala" ) );
												}while ( appSala.next ( ) );
												
												if(saleOccupate.size ( )==sale.size ( ))
													{
														return false;
													}
												for(int i =0;i<saleOccupate.size ( );i++)
													{
														sale.remove ( saleOccupate.get ( i ) );
													}
												
												String nuovaSala=sale.get ( salaGen.nextInt ( sale.size ( )) );
												cliente.executeUpdate ( " update appuntamento "
																		+ " set orario ='"+nuovaOra+"' , sala ='"+nuovaSala+"'"
																		+ " where codice ='"+codice+"';");
												return true;
										}
								
								}
							}
					else 
						{
							macchinario=riscliente.getInt ( "u.Macchinario" );
							durata=riscliente.getInt ( "a.Durata" );
							String durataString="0"+durata+":00:00";
							int codice=riscliente.getInt ( "a.Codice" );
							
							int fine = durata+Integer.parseInt ( nuovaOra.substring ( 0,2 ) );
							fineApp=fine+nuovaOra.substring ( 2 );
						
							if(fineApp.compareTo ( "19:00:00" )>0)
								{
									return false;
								}
								
							ResultSet risSale=cliente.executeQuery("select Sala "
																		+ " from Appuntamento"
																		+ " where Data_appuntamento = '"+data+"' and Orario <= '"+fineApp+"' and time( Orario + time('"+durataString+"')) >= '"+nuovaOra+"' and Sala in"
																		+"( select Nome_sala"
																		+ " from Macchinario"
																		+ " where Codice = '"+macchinario+"');");
																		
							
							if(risSale.first ( ))
								{
									return false;
								}
							else 
								{
									
									cliente.executeUpdate ( "update appuntamento "
															+ "set orario ='"+nuovaOra+"'"
															+ "where codice ='"+codice+"';");
									
									return true;
								}
						}
			

				} catch ( SQLException e )
				{
					
					return false;
				}
			
		}
		
	public String op13()
		{
			String info="";
			
			try
				{
					Statement op = con.createStatement ( ) ;
					ResultSet risultato=op.executeQuery ("select Sala,sum(Durata)as Ore_occupazione "
																+ " from Appuntamento"
																+ " group by Sala "
																+ "order by Sala;");
					
					while(risultato.next ( ))
						{
							info+=String.format ( " |%10s\t|%10s\t|\n" , risultato.getString ( "Sala" ), risultato.getString ( "Ore_occupazione" )); 
						}
																
					
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore impossibile eseguire l'operazione" ) ;
				}
			
			return info;
		}
	
	public String op14()
		{
			String infoMacchinario="";
			
			try
				{
					Statement op = con.createStatement ( ) ;
					ResultSet risultato=op.executeQuery ("select *"
																+ "	from macchinario"
																+ " where codice in (	select U.Macchinario"
																+ " from	utilizzo U join appuntamento A on U.Appuntamento = A.Codice"
																+ " group by U.Macchinario"
																+ " having sum(A.Durata)>1000);");
					
					while(risultato.next ( ))
						{
							infoMacchinario+=String.format ( " |%30s|%30s|%20s|%20s|%8s|%4s|%4s|%8s|\n" , risultato.getString ( "Marca" ), risultato.getString ( "Nome" ),risultato.getString ( "Modello" ),risultato.getString ( "Descrizione" ),risultato.getString ( "Tipo" ),risultato.getString ( "Antinvecchiamento" ),risultato.getString ( "Dimagrimento" ),risultato.getString ( "Nome_sala" )); 
						}
																
					
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore impossibile eseguire l'operazione" ) ;
				}
			
			return infoMacchinario;
		}
	
	@ SuppressWarnings ( "resource" )
	public  void op15(String tipo , String modello ,String descrizione,String nome,boolean dimagrimento,boolean antinvecchiamento, String marca, String nomeSala)
	{
		try
			{
				String dima="No";
				String antiv="No";
				if(dimagrimento)
					{
						dima="Sì";
					}
				else if(antinvecchiamento)
					{
						antiv="Sì";
					}
				
				Statement sala=con.createStatement ( );
				ResultSet risultato=sala.executeQuery ("select count(*) from Sala where Nome = '"+ nomeSala+"';");
				risultato.next ( );
				int ris=(risultato.getInt ( 1 ));
				
				if(ris>0)
					{
						PreparedStatement operazione=con.prepareStatement ( "insert into Macchinario(Marca, Nome, Modello, Descrizione, Tipo, Antinvecchiamento, Dimagrimento, Nome_sala)"
																																			+ "value(?,?,?,?,?,?,?,?);" );
						operazione.setString ( 1 , marca );
						operazione.setString ( 2 , nome );
						operazione.setString ( 3 , modello );
						operazione.setString ( 4 , descrizione);
						operazione.setString ( 5 , tipo );
						operazione.setString ( 6 , dima );
						operazione.setString ( 7 , antiv );
						operazione.setString ( 8 , nomeSala );
						
						operazione.executeUpdate ( );
						System.out.println ( "Macchinario inserito con successo" ) ;
						
						System.out.println ( "Selezionare estetista specializzata che può usare il macchianario" ) ;
						String listaEstetiste=showEstetistaSpecializzate  ( );
						if(!listaEstetiste.equals ( "" ))
							{
								boolean done = false;
								while(!done)
									{
										System.out.println ( listaEstetiste ) ;
										Scanner in = new Scanner ( System.in );
										String cf;
										System.out.print( "Selezionare un estetista tramite il C.F che può utilizzare il macchinario:" ) ;
										cf=in.nextLine ( );
										if(verificaEstetista ( cf ))
											{
												Statement macchina=con.createStatement ( );
												ResultSet ris2=macchina.executeQuery ( "select max(codice) from Macchinario ; ");
												PreparedStatement spec=con.prepareStatement ( "insert into Specializzazione(Estetista, Macchinario)"
																												+ "value(?,?);" );
												ris2.next ( );
												int mac=(ris2.getInt ( 1 ));
												spec.setString ( 1 , cf );
												spec.setInt( 2 , mac);
												spec.executeUpdate ( );
												
												System.out.println ( "Associazione dell'estetista al macchianrio eseguira con successo" ) ;
												done=true;
											}
										else
											{
												System.out.println ( "Codice fiscale non corretto " ) ;
											
											}
			
									}
															
							}
						else
							{
								System.out.println ( "Impossibile associare un macchinario ad un estetista in quanto non ci sono estetiste" ) ;
							}
						
					}
				else
					{
						System.out.println ( "Impossibile acquistare il nuovo macchinario in quanto la sala selezionata non esiste" ) ;
					}
			} catch ( SQLException e )
			{
				System.out.println ( "Si è verificato un errore nell'operazione" ) ;
			}
	}
	
	public String  showEstetistaSpecializzate (  )
		{
			Statement estetiste ;
			String lista="";
			try
				{
					estetiste = con.createStatement ( ) ;
					ResultSet listaEstetiste=estetiste.executeQuery ( "select * from Estetista where specialità=1; ");
					
					lista+=String.format ( " |%-20s\t|%-20s\t|%-20s\t|%-10s\t|%-5s\t|%-15s\t|%-15s\t|%-10s\t|%-10s\t|%-10s\t|%-10s\t|\n" , "Codice_fiscale", "Cognome", "Nome","Stipendio","Età","Data assunzione","Tipo contratto","Ore di lavoro","Giorni lavorativi","Ore utilizzo macchinari","Specialità"); 
					
					while(listaEstetiste.next ( ))
						{
							lista+=String.format ( "|%-20s\t|%-20s\t|%-20s\t|%-10s\t|%-5s\t|%-15s\t|%-20s\t|%-10s\t|%-15s\t|%-25s\t|%-10s\t|\n" , listaEstetiste.getString ( "Codice_fiscale" ), listaEstetiste.getString ( "Cognome" ),listaEstetiste.getString ( "Nome" ),listaEstetiste.getString ( "Stipendio" ),listaEstetiste.getString ( "Età" ),listaEstetiste.getString ( "Data_assunzione" ),listaEstetiste.getString ( "Tipo_contratto" ),listaEstetiste.getString ( "Orario_di_lavoro" ),listaEstetiste.getString ( "Giorni_lavorativi" ),listaEstetiste.getString ( "Ore_utilizzo_macchinari" ),listaEstetiste.getString ( "Specialità" )); 
						}
					
					return lista;
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore nell'interrogazione" ) ;
				}
			return lista;
		}
	public String  showEstetista(  )
		{
			Statement estetiste ;
			String lista="";
			try
				{
					estetiste = con.createStatement ( ) ;
					ResultSet listaEstetiste=estetiste.executeQuery ( "select * from Estetista ; ");
					
					lista+=String.format ( " |%-20s\t|%-20s\t|%-20s\t|%-10s\t|%-5s\t|%-15s\t|%-15s\t|%-10s\t|%-10s\t|%-10s\t|%-10s\t|\n" , "Codice_fiscale", "Cognome", "Nome","Stipendio","Età","Data assunzione","Tipo contratto","Ore di lavoro","Giorni lavorativi","Ore utilizzo macchinari","Specialità"); 
					
					while(listaEstetiste.next ( ))
						{
							lista+=String.format ( " |%-20s\t|%-20s\t|%-20s\t|%-10s\t|%-5s\t|%-15s\t|%-20s\t|%-10s\t|%-15s\t|%-25s\t|%-10s\t|\n" , listaEstetiste.getString ( "Codice_fiscale" ), listaEstetiste.getString ( "Cognome" ),listaEstetiste.getString ( "Nome" ),listaEstetiste.getString ( "Stipendio" ),listaEstetiste.getString ( "Età" ),listaEstetiste.getString ( "Data_assunzione" ),listaEstetiste.getString ( "Tipo_contratto" ),listaEstetiste.getString ( "Orario_di_lavoro" ),listaEstetiste.getString ( "Giorni_lavorativi" ),listaEstetiste.getString ( "Ore_utilizzo_macchinari" ),listaEstetiste.getString ( "Specialità" )); 
						}
					
					return lista;
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore nell'interrogazione" ) ;
				}
			return lista;
		}
	public boolean verificaMacchinario(int codice)
		{
			try
			{
				Statement st1 = con.createStatement();

				ResultSet ris=st1.executeQuery ("select Codice "
									+ "from Macchinario "
									+ "where Codice = " + codice + ";");
				
				if(!ris.first ( ))
					{
						return false;
					}
				else
					{
						return true;
					}
				
			}
			catch(SQLException e)
			{
				return false;
			}
			
		}

	public boolean verificaMacchinario(int codice, int codice2)
		{
			try
			{
				Statement st1 = con.createStatement();
				ResultSet r = st1.executeQuery("select A.Sala "
								+ "from Macchinario A, Macchinario B "
								+ "where A.Sala = B.Sala"
								+ " and A.Codice = " + codice 
								+ " and B.Codice = " + codice2 + ";");
				if(!r.first())
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			catch(SQLException e)
			{
				return false;
			}
			
		}
	
	boolean verificaEstetista(String codiceF)
		    {
		        try
		        {
		            Statement st = con.createStatement();
		            ResultSet ris=st.executeQuery(" select Codice_fiscale "
		                    + " from Estetista"
		                    + " where Codice_fiscale ='" + codiceF + "';");
		            if(!ris.first ( ))
					{
						return false;
					}
		            else
		            	{
		            		return true;
		            	}
		        
		        }
		        catch(SQLException e)
		        {
		            return false;
		        }
		      
		    }
	
	public ArrayList < String > getNamesSala (  )
		{
			ArrayList < String > sale=new ArrayList<> ( );
			
			try
				{
					Statement op=con.createStatement ( );
					ResultSet nomiSale=op.executeQuery ( " select Nome"
																+ " from Sala" );
					
					  if(nomiSale.first ( ))
						{
								do
								{
									sale.add ( nomiSale.getString ( "Nome" ) );
								}while ( nomiSale.next ( ) );
						}
				} catch ( SQLException e )
				{
					System.out.println ( "Si è verificato un errore nella lista delle sale" ) ;
				}
			
			return sale;
		}
	
	
	private ArrayList < String > generateOreLibere (  )
		{
			ArrayList < String > orelibere=new ArrayList<> ( );
			
			for(int ore=9;ore<19;ore++)
				{
					String orario="";
					String min="";
					if(ore<10)
					{
						orario+="0"+ore+":";
					}
					else
					{
						orario+=ore+":";
					}
					for(int minuti=0;minuti<60;minuti+=5)
					{
						min="";
						if(minuti<10)
						{
							min+="0"+minuti;
						}
						else 
						{
							min+=minuti;
						}
						orelibere.add ( orario+min );
					}
								
				}
				orelibere.add("19:00");
			
			return orelibere;
		}

	public String stampaAppuntamenti()
		{
			String appunti="";
			String data = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			try
				{
					Statement op =con.createStatement ( );
					
					ResultSet ris = op.executeQuery ( 	"select Codice,Tipo,Descrizione,Data_appuntamento, Orario, Durata,Cliente" + 
															" from appuntamento" + 
															" where Data_appuntamento>='"+data+"';" );
					
							appunti+=String.format (  " | %-3s\t| %-10s\t| %-10s\t| %-6s\t| %-15s\t| %-30s\t|  %-30s\t|\n"  , "Codice" , "Data","Orario" ,"Durata","Cliente","Tipo" ,"Descrizione");
					while(ris.next ( ))
						{
							appunti+=String.format (  " | %-10s\t| %-10s\t| %-10s\t| %-6s\t| %-20s\t| %-30s\t|  %-30s\t|\n"  , ris.getString ( "Codice" ),ris.getString ( "Data_appuntamento" ),ris.getString ( "Orario" ),ris.getString ( "Durata" ),ris.getString ( "Cliente" ),ris.getString ( "Tipo" ),ris.getString ( "Descrizione" ));
						}
				} 
			catch ( SQLException e )
				{
					System.out.println ( "Errore nella stampa degli appuntamenti" ) ;
				}
			return appunti;
		}

	public String stampaMacchinari()
		    {
		        String macchine="";
		     
		        String codice = "Codice";
		        String marca = "Marca"; 
		        String nome = "Nome";  
		        String modello = "Modello";
		        String tipo = "Tipo";
		        String antinvecchiamento = "Antinvecchiamento";
		        String dimagrimento = "Dimagrimento";
		        String sala = "Sala";
		        try
		            {
		                Statement op =con.createStatement ( );

		                ResultSet ris = op.executeQuery (     "select Codice,Marca, Nome, Modello, Tipo, Antinvecchiamento, Dimagrimento, Nome_sala" + 
		                                                        " from Macchinario;" );

		                macchine+=String.format (  " |%6s\t|%30s\t|%50s\t|%20s\t|%20s\t|%20s\t|%15s\t|%5s\t|\n"  , codice, marca, nome, modello, tipo, antinvecchiamento, dimagrimento, sala);
		                while(ris.next ( ))
		                    {
		                        macchine+=String.format (  " |%10s\t|%-30s\t|%50s\t|%-15s\t|%-20s\t|%-20s\t|%-20s\t|%-5s\t|\n"  , ris.getString ( "Codice" ),ris.getString ( "Marca" ),ris.getString ( "Nome" ),ris.getString ( "Modello" ),ris.getString ( "Tipo" ),ris.getString ( "Antinvecchiamento" ),ris.getString ( "Dimagrimento" ),ris.getString ( "Nome_sala" ));
		                    }
		            } 
		        catch ( SQLException e )
		            {
		                System.out.println ( "Errore nella stampa degli appuntamenti" ) ;
		            }
		        return macchine;
		    }
	
	public boolean verificaCliente(String codiceF)
	{
		try
		{
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select Codice_fiscale from Cliente where Codice_fiscale = '" + codiceF + "';");
			
			if(!r.first())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			return false;
		}
	}

	public String findContratto(String codiceF)
		{
			try
			{
				Statement st = con.createStatement();
				ResultSet r = st.executeQuery("select Tipo_contratto from Estetista where Codice_fiscale = '" + codiceF + "';");
				
				if(!r.first())
				{
					return "ERRORE";
				}
				else
				{
					return r.getString("Tipo_contratto");
				}
			}
			catch(SQLException e)
			{
				return "ERRORE";
			}
		}

	public boolean inserisciSpecializzazione(String codiceF, int codice)
		{
			try
			{
				String query1 = "insert into Specializzazione(Estetista, Macchinario)"
						+ "values(?, ?);";
			
				PreparedStatement st2 = con.prepareStatement(query1);
				st2.setString(1, codiceF);
				st2.setInt(2, codice);
			
				st2.executeUpdate();
			
				return true;
			}
			catch(SQLException e)
			{
				return false;
			}	
		}

	public boolean verificaSecondChoice(int codice)
	{
		try
		{
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("select count(Nome_sala)as Contatore\r\n" + 
																																"from Macchinario\r\n" + 
																																"where Nome_sala=(\r\n" + 
																																"select Nome_sala\r\n" + 
																																"from Macchinario\r\n" + 
																																"where Codice="+codice+");");
			int numSale=0;
			if(!r.first())
			{
				return false;
			}
			else
			{
				numSale=r.getInt ( "Contatore" );
				if(numSale>1)
					{
						return true;
					}
				else
					{
						return false;
					}
			}
		}
		catch (SQLException e )
		{
			return false;
		}
	}
	boolean verificaEstetistaSpecializzata(String codiceF)
		    {
		        try
		        {
		            Statement st = con.createStatement();
		            ResultSet ris=st.executeQuery(" select Codice_fiscale "
		                    + " from Estetista"
		                    + " where Specialità=1 and Codice_fiscale ='" + codiceF + "';");
		            if(!ris.first ( ))
					{
						return false;
					}
		            else
		            	{
		            		return true;
		            	}
		        
		        }
		        catch(SQLException e)
		        {
		            return false;
		        }
		      
		    }
	//variabili
	private static Connection con;
	private static String url;
	private static String username;
	private static String pwd;
}
