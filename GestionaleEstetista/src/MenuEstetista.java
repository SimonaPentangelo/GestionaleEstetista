import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class MenuEstetista {
	public static void main(String[] arg) 
	{
		int scelta=0;
		boolean flag=false;
		Scanner input=new Scanner ( System.in );
		
		InterrogazioniSQL query=new InterrogazioniSQL ();
		for(;;)
			{
				query.open ( );
				System.out.println ( "+-------------------------------------------------------------------------------------------------------------------------------------------------+");
				System.out.println ( "|																																					");
				System.out.println ( "|1)   Prenotazione di un appuntamento con assegnazione di sala ed estetista															            ");
				System.out.println ( "|2)   Verifica della possibilità di effettuare un appuntamento per un determinato giorno dell’anno ad una determinata ora			                ");
				System.out.println ( "|3)   Visualizzazione degli orari disponibili per un appuntamento in un determinato giorno											            ");
				System.out.println ( "|4)   Visualizzazione dei giorni disponibili per un appuntamento in un determinato orario;												        ");
				System.out.println ( "|5)   Visualizzazione del primo appuntamento disponibile per l’utilizzo di un determinato macchinario								                ");
				System.out.println ( "|6)   Visualizzazione del numero di appuntamenti effettuati in un anno per ogni tipo													            ");
				System.out.println ( "|7)   Inserimento di un nuovo cliente																											    ");
				System.out.println ( "|8)   Assunzione di una nuova estetista																										    ");
				System.out.println ( "|9)   Visualizzazione dell’estetista che ha lavorato in maggior numero di ore sui macchinari nell’anno corrente						            ");
				System.out.println ( "|10)  Visualizzazione dei macchinari che può utilizzare un’estetista																		        ");
				System.out.println ( "|11)  Modifica del contratto di un’estetista																									    ");
				System.out.println ( "|12)  Modifica dell’orario di un appuntamento (se possibile)																				        ");
				System.out.println ( "|13)  Visualizzazione delle ore di occupazione delle sale in un anno																		        ");
				System.out.println ( "|14)  Visualizzazione dei macchinari che sono stati utilizzati per più di 1000 ore in un anno												        ");
				System.out.println ( "|15)  Acquisto di un nuovo macchinario																										    ");
				System.out.println ( "|16)  Esci																																		");
				System.out.println ( "|																																					");
				System.out.println ( "+-------------------------------------------------------------------------------------------------------------------------------------------------+");				
					
		
				while(!flag)
					{
						try
							{
								System.out.print ( "Inserire un numero per la tua scelta: " ) ;
								scelta=Integer.parseInt ( input.nextLine ( ) );
								clear ( );
								flag=true;
							} 
						catch ( RuntimeException e )
							{
								System.out.println ( "Errore nell'inserimento.... devi inserire un numero." ) ;
							}
					}
				flag=false;
				
				switch(scelta)
				{
					
					case 1:
					{
						boolean done = false;
						boolean presente = false;
						System.out.println("INSERIRE IL CLIENTE");
						String codiceF = "";
						String nome = "";
						String cognome = "";
						int eta = 0;
						String via = "";
						String citta = "";
						String sesso = "";
						int cap = 0;
						while(!done)
						{
							System.out.print("Inserisci codice fiscale: ");
							codiceF = input.nextLine();
							if(codiceF.length() == 16)
							{
								done = true;
							}
							else
							{
								System.out.println("CODICE FISCALE NON VALIDO");
							}
						}
						done = false;
						if(!query.verificaCliente(codiceF))
						{
						
							do
								{
									System.out.print("Inserisci nome: ");
									nome = input.nextLine();
									if(!nome.equals ( "" ))
									{
										done= true;
									}
									else
										{
											System.out.println ( "Il campo è vuoto...riprovare" ) ;
										}
									
								} while ( !done ) ;
							done=false;
							do
								{
									System.out.print("Inserisci cognome: ");
									cognome = input.nextLine();
									
									if(!cognome.equals ( "" ))
										{
											done= true;
										}
										else
											{
												System.out.println ( "Il campo è vuoto...riprovare" ) ;
											}
								} while ( !done ) ;
							
						
							done = false;
							while(done != false)
							{
								System.out.print("Inserire età: ");
								eta = Integer.parseInt(input.nextLine());
								if(eta > 0)
								{
									done = true;
								}
								else
								{
									System.out.println("ETÀ NON VALIDA");
								}
							}		
						
							done = false;
							while(!done)
							{
								System.out.print("Inserire sesso: ");
								sesso = input.nextLine();
								if(sesso.equalsIgnoreCase("m") || sesso.equalsIgnoreCase("f"))
								{
									done = true;
								}
								else
								{
									System.out.println("SESSO NON VALIDO");
								}
							}
						done = false;
							do
								{
									System.out.print("Inserire via: ");
									via = input.nextLine();
									
									if(!via.equals ( "" ))
										{
											done = true;
										}
									else
										{
											System.out.println ( "Il campo è vuoto.... riprovare" ) ;
										}
								} while ( !done ) ;
							
							done = false;
							do
								{
									System.out.print("Inserire città: ");
									citta = input.nextLine();
								
									if(!citta.equals ( "" ))
										{
											done = true;
										}
									else
										{
											System.out.println ( "Il campo è vuoto.... riprovare" ) ;
										}
								} while ( !done ) ;
						
							done = false;
							while(!done)
							{
								System.out.print("Inserire CAP: ");
								String c = input.nextLine();
								if(c.length() == 5)
								{
									try
									{
										cap = Integer.parseInt(c);
										done = true;
									}
									catch(RuntimeException e)
									{
										System.out.println("CAP NON VALIDO");
									}
								}
								else
								{
									System.out.println("CAP NON VALIDO");
								}
							}
						}
						else
						{
							System.out.println("CLIENTE PRESENTE, NON SERVE INSERIRE ALTRI DATI");
							presente = true;
						}
						
						System.out.println("INSERISCI L'APPUNTAMENTO");
						done=false;
						String tipo="";
						do
							{

								System.out.print("Inserisci il tipo di appuntamento: ");
								tipo = input.nextLine();
							
								if(!tipo.equals ( "" ))
									{
										done = true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto.... riprovare" ) ;
									}
							} while ( !done ) ;
						
							System.out.print("Inserisci una descrizione: ");
							String desc = input.nextLine();
						
						String data = "";
						do
						{
							System.out.print("Inserisci una data (yyyy-mm-gg): ");
							data = input.nextLine();
						}
						while(data.length() != 10);
						
						boolean done1 = false;
						while(!done1)
	                    {
	                        try { 
	                                SimpleDateFormat sf = new SimpleDateFormat ("yyyy-MM-dd"); 
	                                sf.setLenient (false); 
	                                sf.parse (data);
	                                    java.util.Date dt =new java.util.Date ( );
	                                Calendar caledario = Calendar.getInstance();
	                                caledario.setTime ( dt );
	                                String data1 = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
	                                if(data.compareTo (data1)>=0)
	                                    {
	                                        done1=true;
	                                    }
	                                else
	                                {
	                                System.out.println ( "DATA INSERITA NON VALIDA" ) ;
	                                System.out.print("Inserisci una data (yyyy-mm-gg): ");
	            					data = input.nextLine();
	                                }
	                              } 
	                        catch (Exception e) 
	                                {
	                                    System.out.println ( "DATA INSERITA NON VALIDA" ) ;
	                                    System.out.print("Inserisci una data (yyyy-mm-gg): ");
	                					data = input.nextLine();
	                                } 
	                        }
						
						System.out.print("Inserisci orario (hh:mm): ");
						String orario = input.nextLine() + ":00";
						
						boolean bandiera = false;
						while(!bandiera)
						{
							try
							{
								if(orario.length() == 8)
								{
									int ore = 0;
									int minuti = 0;
									ore = Integer.parseInt(orario.substring(0, 2));
									minuti = Integer.parseInt(orario.substring(3, 5));
									bandiera = true;
									if(ore < 9 || ore > 18)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									
									else if(ore == 18 && minuti > 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti%5 != 0 && minuti != 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti < 0 || minuti > 55)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
								
									else if(orario.charAt(2) != ':')
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}

								}
								else
								{
									System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
									System.out.print("Inserisci orario (hh:mm): ");
									orario = input.nextLine() + ":00";
								}
							}
							catch(NumberFormatException e)
							{
								System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
								System.out.print("Inserisci orario (hh:mm): ");
								orario = input.nextLine() + ":00";
							}	
						}
						
						done1 = false;
						int durata = 0;
						while(!done1)
						{
							try
							{
								System.out.print("Inserire durata dell'appuntamento: ");
								durata = Integer.parseInt(input.nextLine());
								if(durata > 0 && durata <= 2)
								{
									if(durata == 2 && orario.equals("18:00:00"))
									{
										System.out.println("DURATA NON VALIDA (SFORA OLTRE LE 19)");
									}
									done1 = true;
								}
								else
								{
									System.out.println("DURATA NON VALIDA (MIN 1, MAX 2)");
								}
							}
							catch(RuntimeException e)
							{
								System.out.println("DURATA NON VALIDA (MIN 1, MAX 2)");
							}
						}
						
						System.out.print("Serve un macchinario? (Sì/NO): ");
						String scelta1 = input.nextLine();
						
						while(!scelta1.equalsIgnoreCase("sì") && !scelta1.equalsIgnoreCase("no"))
						{
							System.out.print("Serve un macchinario? (Sì/NO): ");
							scelta1 = input.nextLine();
						}
						
						int codice = 0;
						if(scelta1.equalsIgnoreCase("sì"))
						{
							done=false;
							boolean verifica = false;
							while(!verifica)
							{
								
								System.out.println(query.stampaMacchinari());
								do
									{
										System.out.print("Inserisci macchinario (codice): ");
										try
											{
												codice = Integer.parseInt(input.nextLine());
												done=true;
											} catch ( RuntimeException e )
											{
												System.out.println ( "Errore devi inserire un codice numerico" ) ;
											}
										
									} while ( !done ) ;
								
								if(!query.verificaMacchinario(codice))
								{
									System.out.println("MACCHINARIO NON PRESENTE");
								}
								else
								{
									verifica = true;
								}
							}
						}
						
						String scelta2 = "";
						int codice1 = 0;
						if(scelta1.equalsIgnoreCase("sì") && query.verificaSecondChoice(codice))
						{
							System.out.print("Serve un secondo macchinario? (Sì/NO): ");
							scelta2 = input.nextLine();
						
							while(!scelta2.equalsIgnoreCase("sì") && !scelta2.equalsIgnoreCase("no"))
							{
								System.out.print("Serve un secondo macchinario? (Sì/NO): ");
								scelta2 = input.nextLine();
							}
						
							if(scelta2.equalsIgnoreCase("sì"))
							{
								done = false;
								boolean verifica = false;
								while(!verifica)
								{
									System.out.println(query.stampaMacchinari());
									do
										{
											System.out.print("Inserisci macchinario (codice): ");
											try
												{
													codice1 = Integer.parseInt(input.nextLine());
													done=true;
												} catch ( RuntimeException e )
												{
													System.out.println ( "Errore devi inserire un codice numerico" ) ;
												}
											
										
										} while ( !done ) ;
									if(codice == codice1)
										{ System.out.println("MACCHINARIO GIÀ SELEZIONATO"); }
									else if(!query.verificaMacchinario(codice1, codice))
									{
										System.out.println("MACCHINARI IN SALE DIVERSE");
									}
									else
									{
										verifica = true; 
									}
								}
							}
						}
						
						if(!presente)
						{
							query.op7(codiceF, cognome, nome, eta, sesso, via, citta, cap);
						}
						
						boolean result = false;
						if(scelta1.equalsIgnoreCase("sì") && scelta2.equalsIgnoreCase("sì"))
						{
							result = query.op1(tipo, desc, data, orario, durata, codiceF, codice1, codice);
						}
						else if(scelta1.equalsIgnoreCase("sì") && !scelta2.equalsIgnoreCase("sì"))
						{
							result = query.op1(tipo, desc, data, orario, durata, codiceF, codice);
						}
						else
						{
							result  = query.op1(tipo, desc, data, orario, durata, codiceF);
						}
						
						if(result)
						{
							System.out.println("PRENOTAZIONE AVVENUTA CON SUCCESSO");
						}
						else
						{
							System.out.println("PRENOTAZIONE FALLITA");
						}
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
						
					} break;
					
					case 2:
					{
						System.out.println("INSERISCI I DATI PER LA VERIFICA");
						
						String data = "";
						do
						{
							System.out.print("Inserisci una data (yyyy-mm-gg): ");
							data = input.nextLine();
						}
						while(data.length() != 10);
						
						boolean done = false;
						while(!done)
	                    {
	                        try { 
	                                SimpleDateFormat sf = new SimpleDateFormat ("yyyy-MM-dd"); 
	                                sf.setLenient (false); 
	                                sf.parse (data);
	                                    java.util.Date dt =new java.util.Date ( );
	                                Calendar caledario = Calendar.getInstance();
	                                caledario.setTime ( dt );
	                                String data1 = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
	                                if(data.compareTo (data1)>=0)
	                                    {
	                                        done=true;
	                                    }
	                                else
	                              	 {
	                              		 System.out.println ( "DATA INSERITA NON VALIDA" ) ;
			                                System.out.print("Inserisci una data (yyyy-mm-gg): ");
			            			data = input.nextLine();

	                              	 }
	                              } 
	                        catch (Exception e) 
	                                {
	                                    System.out.println ( "DATA INSERITA NON VALIDA" ) ;
	                                    System.out.print("Inserisci una data (yyyy-mm-gg): ");
	                					data = input.nextLine();
	                                } 
	                        }
						
						System.out.print("Inserisci orario (hh:mm): ");
						String orario = input.nextLine() + ":00";
				
						boolean bandiera = false;
						while(!bandiera)
						{
							try
							{
								if(orario.length() == 8)
								{
									int ore = 0;
									int minuti = 0;
									ore = Integer.parseInt(orario.substring(0, 2));
									minuti = Integer.parseInt(orario.substring(3, 5));
									bandiera = true;
									if(ore < 9 || ore > 18)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(ore == 18 && minuti > 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti%5 != 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti < 0 || minuti > 55)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
								
									else if(orario.charAt(2) != ':')
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}

								}
								else
								{
									System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
									System.out.print("Inserisci orario (hh:mm): ");
									orario = input.nextLine() + ":00";
								}
							}
							catch(NumberFormatException e)
							{
								System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
								System.out.print("Inserisci orario (hh:mm): ");
								orario = input.nextLine() + ":00";
							}	
						}
						
						
						if(query.op2(data, orario))
						{
							System.out.println("VERIFICA EFFETTUATA: DISPONIBILE");
						}
						else
						{
							System.out.println("VERIFICA EFFETTUATA: NON DISPONIBILE");
						}
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
						
					} break;
					
					case 3:
					{
						System.out.println("TROVARE GLI ORARI DISPONIBILI PER TALE GIORNO");
						
						String data = "";
						do
						{
							System.out.print("Inserisci una data (yyyy-mm-gg): ");
							data = input.nextLine();
						}
						while(data.length() != 10);
						
						boolean done = false;
						while(!done)
	                    {
	                        try { 
	                                SimpleDateFormat sf = new SimpleDateFormat ("yyyy-MM-dd"); 
	                                sf.setLenient (false); 
	                                sf.parse (data);    
	                               java.util.Date dt =new java.util.Date ( );
	                                Calendar caledario = Calendar.getInstance();
	                                caledario.setTime ( dt );
	                                String data1 = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
	                                if(data.compareTo (data1)>=0)
	                                    {
	                                        done=true;
	                                    }
	                                else
	                              	  {
	                              		  System.out.println ( "DATA INSERITA NON VALIDA" ) ;
	                              		  
	                              		  do
	  								{
	  									System.out.print("Inserisci una data (yyyy-mm-gg): ");
	  									data = input.nextLine();
	  								}
	  								while(data.length() != 10);
	                              	  }
	                              } 
	                        catch (Exception e) 
	                                {
	                                    System.out.println ( "DATA INSERITA NON VALIDA" ) ;
	                                    
	                                    do
  								{
  									System.out.print("Inserisci una data (yyyy-mm-gg): ");
  									data = input.nextLine();
  								}
  								while(data.length() != 10);
	                                } 
	                        }
						
						System.out.println(query.op3(data));
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
						
					} break;
					
					
					case 4:
					{
						System.out.println("TROVARE I GIORNI DISPONIBILI PER TALE ORARIO");
						System.out.print("Inserisci orario (hh:mm): ");
						String orario = input.nextLine() + ":00";
						
						boolean bandiera = false;
						while(!bandiera)
						{
							try
							{
								if(orario.length() == 8)
								{
									int ore = 0;
									int minuti = 0;
									ore = Integer.parseInt(orario.substring(0, 2));
									minuti = Integer.parseInt(orario.substring(3, 5));
									bandiera = true;
									if(ore < 9 || ore > 18)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(ore == 18 && minuti > 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti%5 != 0)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(minuti < 0 || minuti > 55)
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}
									else if(orario.charAt(2) != ':')
									{
										System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
										System.out.print("Inserisci orario (hh:mm): ");
										orario = input.nextLine() + ":00";
										bandiera = false;
									}

								}
								else
								{
									System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
									System.out.print("Inserisci orario (hh:mm): ");
									orario = input.nextLine() + ":00";
								}
							}
							catch(NumberFormatException e)
							{
								System.out.println("ERRORE NELL'INSERIMENTO DELL'ORARIO");
								System.out.print("Inserisci orario (hh:mm): ");
								orario = input.nextLine() + ":00";
							}	
						}
						
						System.out.println(query.op4(orario));
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
						
					} break;
					
					case 5:
					{
						System.out.println("RICERCA PRIMO APPUNTAMENTO DISPONIBILE PER UTILIZZARE UN DETERMINATO MACCHINARIO\n");
						System.out.println (  query.stampaMacchinari ( ));
						int codice = 0;
						
						boolean verifica = false;
						while(!verifica)
						{
							System.out.print("Inserisci macchinario (codice): ");
							codice = Integer.parseInt(input.nextLine());
								
							if(!query.verificaMacchinario(codice))
							{
								System.out.println("MACCHINARIO NON PRESENTE");
							}
							else
							{
								verifica = true;
							}
						}
						
						System.out.println(query.op5(codice));
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					} break;

					case 6:
					{
						System.out.println ( "VISUALIZZAZIONE DEL NUMERO DI APPUNTAMENTI EFFETTUATI IN UN ANNO PER OGNI TIPO" ) ;
						String tipo=query.op6();
						if(tipo.equals ( "" ))
							{
								System.out.println ( "Si è verificato un errore nell'interrogazione" ) ;
							}
						else
							{
								System.out.println ( tipo ) ;
							}
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}break;
					case 7:
					{
			                    boolean flag1 = false;

			                    System.out.println("INSERIRE IL CLIENTE");

			                    String codiceF = "";
								String nome = "";
								String cognome = "";
								int eta = 0;
								String via = "";
								String citta = "";
								String sesso = "";
								int cap = 0;
								
								boolean assente = false;
								
									while(!assente)
									{
										while(flag1 != true)
										{
											System.out.print("Inserisci codice fiscale: ");
											codiceF = input.nextLine();
											if(codiceF.length() == 16)
											{
												flag1 = true;
											}
											else
											{
												System.out.println("CODICE FISCALE NON VALIDO");
											}
										}
									
										if(!query.verificaCliente(codiceF))
										{
											assente = true;
											flag1=false;
											do
												{
													System.out.print("Inserisci nome: ");
													nome = input.nextLine();
													if(!nome.equals ( "" ))
														{
															flag1=true;
														}
													else {
														System.out.println ( "Il campo è vuoto... riprovare" ) ;
													}
												} while ( !flag1 ) ;
											flag1=false;
											do
												{
													System.out.print("Inserisci cognome: ");
													cognome = input.nextLine();
													if(!cognome.equals ( "" ))
														{
															flag1=true;
														}
													else {
														System.out.println ( "Il campo è vuoto... riprovare" ) ;
													}
												} while ( !flag1 ) ;
								
											flag1 = false;
											while(!flag1)
											{	
												System.out.print("Inserire età: ");
												try
													{
														eta = Integer.parseInt(input.nextLine());
														if(eta > 0)
															{
																flag1 = true;
															}
															else
															{
																System.out.println("ETÀ NON VALIDA");
															}
													} catch ( Exception e )
													{
														System.out.println ( "L'eta deve essere un numero... riprovare" ) ;
													}
												
											
										}		
								
										flag1 = false;
										while(flag1 != true)
										{
											System.out.print("Inserire sesso: ");
											sesso = input.nextLine();
											if(sesso.equalsIgnoreCase("m") || sesso.equalsIgnoreCase("f"))
											{
												flag1 = true;
											}
											else
											{
												System.out.println("SESSO NON VALIDO");
											}
										}
										
										flag1=false;
										do
											{
												System.out.print("Inserire via: ");
												via = input.nextLine();
												if(!via.equals ( "" ))
													{
														flag1=true;
													}
												else {
													System.out.println ( "Il campo è vuoto... riprovare" ) ;
												}
											} while ( !flag1 ) ;
										
										flag1=false;
										do
											{
												System.out.print("Inserire città: ");
												citta = input.nextLine();
												if(!citta.equals ( "" ))
													{
														flag1=true;
													}
												else {
													System.out.println ( "Il campo è vuoto... riprovare" ) ;
												}
											} while ( !flag1 ) ;
										
										
										flag1 = false;
										while(flag1 != true)
										{
											System.out.print("Inserire CAP: ");
											String c = input.nextLine();
											if(c.length() == 5)
											{
												try
												{
													cap = Integer.parseInt(c);
													flag1 = true;
												}
												catch(RuntimeException e)
												{
													System.out.println("CAP NON VALIDO");
												}
											}
											else
											{
												System.out.println("CAP NON VALIDO");
											}
										}
									}
									else
									{
										System.out.println("CLIENTE GIÀ PRESENTE");
										flag1=false;
										while(flag1 != true)
											{
												System.out.print("Inserisci codice fiscale: ");
												codiceF = input.nextLine();
												if(codiceF.length() == 16)
												{
													flag1 = true;
												}
												else
												{
													System.out.println("CODICE FISCALE NON VALIDO");
												}
											}
									}
			                    }
								
									if(query.op7(codiceF, cognome, nome, eta, sesso, via, citta, cap))
									{
										System.out.println("INSERIMENTO RIUSCITO");
									}
									else
									{
										System.out.println("INSERIMENTO FALLITO");
									}
									System.out.println ( "Premi invio per continuare" ) ;
									input.nextLine ( );
									query.close ( );
									clear ( );
									
							} break;
							
					case 8:
					{
						boolean flag1 = false;
							
						System.out.println("INSERIMENTO DI UN'ESTETISTA");
						int codice = 0;
						int codice1 = 0;
						String assunzione = "";
						int eta = 0;
						double stipendio = 0;
						String nome = "";
						String cognome = "";
						String contratto = "";
						String codiceF = "";
						int giorniLav = 0;
						boolean assente = false;
						
						while(!assente)
						{
							
							while(flag1 != true)
							{
								System.out.print("Inserisci codice fiscale: ");
								codiceF = input.nextLine();
								if(codiceF.length() == 16)
								{
									flag1 = true;
								}
								else
								{
									System.out.println("CODICE FISCALE NON VALIDO");
								}
							}
							
							if(!query.verificaEstetista(codiceF))
							{
								assente = true;
								flag1=false;
								do
									{
										System.out.print("Inserisci nome: ");
										nome = input.nextLine();
										if(!nome.equals ( "" ))
											{
												flag1=true;
											}
										else {
											System.out.println ( "Il campo è vuoto... riprovare" ) ;
										}
									} while ( !flag1 ) ;
								flag1=false;
								do
									{
										System.out.print("Inserisci cognome: ");
										cognome = input.nextLine();
										if(!cognome.equals ( "" ))
											{
												flag1=true;
											}
										else {
											System.out.println ( "Il campo è vuoto... riprovare" ) ;
										}
									} while ( !flag1 ) ;
								flag1 = false;
								while(flag1 != true)
								{	
									try
									{	
										System.out.print("Inserire stipendio: ");
										stipendio = Double.parseDouble(input.nextLine());
										if(stipendio > 0)
										{	
											flag1 = true;
										}
										else
										{
											System.out.println("STIPENDIO NON VALIDO");
										}
									}
									catch(RuntimeException e)
									{
										System.out.println("STIPENDIO NON VALIDO");
									}
								}
						
								flag1 = false;
								while(flag1 != false)
								{
									try
									{
										System.out.print("Inserire età: ");
										eta = Integer.parseInt(input.nextLine());
										if(eta >= 18)
										{
											flag1 = true;
										}	
										else
										{
											System.out.println("ETÀ NON VALIDA");
										}
									}
									catch(RuntimeException e)
									{
										System.out.println("ETÀ NON VALIDA");
									}
								}		
							
								flag1=false;
								do
									{
										System.out.print("Inserire il tipo di contratto: ");
										contratto = input.nextLine();
										if(!contratto.equals ( "" ))
											{
												flag1=true;
											}
										else {
											System.out.println ( "Il campo è vuoto... riprovare" ) ;
										}
									} while ( !flag1 ) ;
								
								
								assunzione = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());							
						
								int ore = 0;
								boolean done1 = false;
								while(!done1)
								{
									try
									{
										System.out.print("Inserire le ore  di lavoro (a numero): ");
										ore = Integer.parseInt(input.nextLine());
							
										if(ore > 0  &&  ore <= 8)
										{
											done1 = true;
										}
										else
										{
											System.out.println("ORE INSERITE NON VALIDE");
										}
									}
									catch(RuntimeException e)
									{
										System.out.println("ORE INSERITE NON VALIDE");
									}
								}
					
								boolean done2 = false;
								while(!done2)
								{	
									try
									{
										System.out.print("Inserire il numero dei giorni lavorativi: ");
										String gg = input.nextLine();
										giorniLav = Integer.parseInt(gg);
										
										if(giorniLav > 0 && giorniLav <= 5)
										{	
											done2 = true;
										}	
										else
										{	
											System.out.println("NUMERO DI GIORNI NON VALIDO");
										}
									}
									catch(RuntimeException e)
									{
										System.out.println("NUMERO DI GIORNI NON VALIDO");
									}	
								}
						
								flag1=false;
								String res ="";
								do
									{
										System.out.print("Specializzato/a? ");
										res = input.nextLine();
										if(!res.equals ( "" ))
											{
												flag1=true;
											}
										else {
											System.out.println ( "Il campo è vuoto... riprovare" ) ;
										}
									} while ( !flag1 ) ;
								
								while(!res.equalsIgnoreCase("sì") && !res.equalsIgnoreCase("no"))
								{
									System.out.println("Specializzato/a? (Sì/NO): ");
									res = input.nextLine();
								}
						
								if(res.equalsIgnoreCase("sì"))
								{
									boolean verifica = false;
									flag1=false;
									while(!verifica)
									{
										System.out.println(query.stampaMacchinari());
										do
											{
												System.out.print("Inserisci macchinario (codice): ");
												try
													{
														codice = Integer.parseInt(input.nextLine());
														flag1=true;
														
													} catch ( Exception e )
													{
														System.out.println ( "Il codice deve essere numerico...riprovare" ) ;
													}
												
											} while ( !flag1 ) ;
										
										if(!query.verificaMacchinario(codice))
										{
											System.out.println("MACCHINARIO NON PRESENTE");
										}
										else
										{
											verifica = true;
										}
									}
									
									if(query.op8(codiceF, cognome, nome, stipendio, eta, assunzione, contratto, ore, giorniLav, 1, codice))
									{
										System.out.println("INSERIMENTO RIUSCITO");
									}	
									else
									{
										System.out.println("INSERIMENTO FALLITO");
									}
									
									verifica = false;
									while(!verifica && res.equalsIgnoreCase("sì"))
									{
										System.out.print("Possiede altre specializzazioni? ");
										String choice = input.nextLine();
										if(choice.equalsIgnoreCase("sì"))
										{	
											flag1=false;
											while(!verifica)
											{
												System.out.println(query.stampaMacchinari());
												do
													{
														System.out.print("Inserisci macchinario (codice): ");
														try
															{
																codice1 = Integer.parseInt(input.nextLine());
																flag1=true;
															} catch ( Exception e )
															{
																System.out.println ( "Il codice deve essere numerico...riprovare" ) ;
															}
														
													} while ( !flag1 ) ;
												
												if(!query.verificaMacchinario(codice))
												{
													System.out.println("MACCHINARIO NON PRESENTE");
												}
												else
												{
													if(codice1 == codice)
													{
														System.out.println("MACCHINARIO GIÀ SELEZIONATO");
													}
													else
													{
														verifica = true;
													}
												}	
											}	
									
											query.inserisciSpecializzazione(codiceF, codice1);
											verifica = false;
										}	
										else
										{
											verifica = true;
										}	
									}
								}
								else
								{
									if(query.op8(codiceF, cognome, nome, stipendio, eta, assunzione, contratto, ore, giorniLav, 0))
									{
										System.out.println("INSERIMENTO RIUSCITO");
									}	
									else
									{
										System.out.println("INSERIMENTO FALLITO");
									}
								}	
							}
							else
							{
								System.out.println("ESTETISTA GIÀ PRESENTE");
								flag1 = false;
								while(flag1 != true)
								{
									System.out.print("Inserisci codice fiscale: ");
									codiceF = input.nextLine();
									if(codiceF.length() == 16)
									{
										flag1 = true;
									}
									else
									{
										System.out.println("CODICE FISCALE NON VALIDO");
									}
								}
							}
						}
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					} break;
					
					case 9:
					{
						System.out.println ( "Visualizzazione dell’estetista che ha lavorato in maggior numero di ore sui macchinari nell’anno corrente" ) ;
						String estetista=query.op9 ( );
						
						if(estetista.equals ( "" ))
							{
								System.out.println ( "Nessun estetista utilizza un macchinario" ) ;
							}
						else
							{
								System.out.println ( estetista ) ;
							}

						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
						
					}break;
					case 10:
					{
						System.out.println ( "Visualizzazione dei macchinari che può utilizzare un’estetista\n" ) ;
						String cf="";
						String estetiste=query.showEstetistaSpecializzate  ( );
						if(!estetiste.equals ( "" ))
							{
								boolean done=false;
								System.out.println ( "Selezionare un estetista tramite il codice fiscale per visualizzare i macchinari che può utilizzare" ) ;
								System.out.println ( estetiste ) ;
								System.out.print ( "Inserire il codice fiscale:" ) ;
								
								
								while ( !done )
									{
										cf=input.nextLine ( );
										if(cf.equals ( "" ))
											{
												System.out.print ( "Inserire il codice fiscale:" ) ;
											}
										else
											{
												done=true;
											}
									}
									if(query.verificaEstetistaSpecializzata ( cf))
									{
										String macchinari=query.op10(cf);
										if(macchinari.equals ( "" ))
											{
												System.out.println ( "L'estetista non può utilizzare nessun macchinario" ) ;
											}
										else
											{
												System.out.println ( macchinari ) ;
											}
									}
									else
										{
											System.out.println ( "Hai inserito un codice fiscale che non corrisponde a nessuna estetista" ) ;
										}
									
								}
						else
							{
								System.out.println ( "Operazione non riuscita non ci sono estetiste nel database" ) ;
							}
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}break;
					case 11:
					{
						System.out.println("SELEZIONA L'ESTETISTA PER CUI VUOI MODIFICARE IL CONTRATTO");
						System.out.println(query.showEstetista());
						String codiceF = "";
						boolean flag1 = false;
						while(!flag1)
						{
							System.out.print("Inserisci codice fiscale: ");
							codiceF = input.nextLine();
							if(codiceF.length() == 16)
							{
								if(query.verificaEstetista(codiceF))
								{ flag1 = true; }
								else
								{ System.out.println("ESTETISTA NON PRESENTE"); }
							}
							else
							{
								System.out.println("CODICE FISCALE NON VALIDO");
							}
						}
						
						System.out.println("Il contratto dell'estetista è: " + query.findContratto(codiceF));
						String cont = "";
						boolean done1 = false;							
						do
						{
								System.out.print("Inserire il nuovo contratto: ");
								cont = input.nextLine();
								if(!cont.equals ( "" ))
									{
										done1=true;
									}
								else {
										{
											System.out.println ( "Il campo è vuoto" ) ;
										}
								}
						} while ( !done1 ) ;

						
						int ore = 0;
						done1=false;
						while(!done1)
						{
							try
							{
								System.out.print("Inserire il nuovo orario di lavoro (a numero): ");
								ore = Integer.parseInt(input.nextLine());
							
								if(ore > 0 && ore <= 8)
								{
									done1 = true;
								}
								else
								{
									System.out.println("ORE INSERITE NON VALIDE");
								}
							}
							catch(RuntimeException e)
							{
								System.out.println("ORE INSERITE NON VALIDE");
							}
						}
						
						int giorniLav = 0;
						boolean done2 = false;
						while(!done2)
						{
							try
							{
								System.out.print("Inserire il nuovo numero di giorni lavorativi (a numero): ");
								String gg = input.nextLine();
								giorniLav = Integer.parseInt(gg);
								
								if(giorniLav > 0 && giorniLav <= 6)
								{
									done2 = true;
								}
								else
								{
									System.out.println("NUMERO DI GIORNI NON VALIDO");
								}
							}
							catch(RuntimeException e)
							{
								System.out.println("NUMERO DI GIORNI NON VALIDO");
							}
						}
						
						double stipendio = 0;
						do
						{
							System.out.print("Inserire il nuovo stipendio: ");
							try
								{
									stipendio = Double.parseDouble(input.nextLine());
								} catch ( Exception e )
								{
									System.out.println ( "Lo stipendio deve essere numerico" ) ;
								}
							
						} while(stipendio <= 0);
						System.out.println(query.op11(codiceF, cont, ore, giorniLav));
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					} break;
					case 12:
					{
						System.out.println (" Modifica dell’orario di un appuntamento (se possibile)\n" ) ;
						String Appuntamento=query.stampaAppuntamenti ( );
						if(Appuntamento.equals ( "" ))
							{
								System.out.println ( "Impossibile modificare l'orario poichè tutti gli appuntamenti sono gia passati" ) ;
							}
						else
							{
								System.out.println ( Appuntamento ) ;
								boolean done=false;
								String time="",cf="",date="",newTime="";
								
								while ( !done )
									{
										System.out.print("Inserire il codice fiscale: ");
										cf=input.nextLine ( );
										if(cf.length ( )==16)
											{
												done=true;
											}
										else {
											System.out.println ( "Formato del codice fiscale non valido" ) ;
										}
									}
								done = false;
								
								while(!done)
									{
										System.out.print("Inserisci una data (yyyy-mm-gg): ");
										date=input.nextLine ( );
										try { 
											    SimpleDateFormat sf = new SimpleDateFormat ("yyyy-MM-dd"); 
											    sf.setLenient (false); 
											    sf.parse (date);
											    	java.util.Date dt =new java.util.Date ( );
												Calendar caledario = Calendar.getInstance();
												caledario.setTime ( dt );
												String data = new SimpleDateFormat("yyyy-MM-dd").format(caledario.getTime());
												if(date.compareTo (data)>=0)
													{
														done=true;
													}
												else
													{
														System.out.println ( "la data inserita non è valida" ) ;
													}
											  } 
										catch (Exception e) 
												{
													System.out.println ( "La data inserita non è valida" ) ;
												} 
										}
									
						
								done = false;
								
								while(!done)
									{
										System.out.print("Inserisci orario (hh:mm): ");
										time = input.nextLine();
										
										if(time.length ( )==5 && time.charAt ( 2 )==':' )
											{
												String minuti=time.substring ( 3,5 );
												String ore=time.substring ( 0,2 );
												try
													{
														int ora = Integer.parseInt ( ore );
														int minuto = Integer.parseInt ( minuti );
														
														if(9<=ora &&ora<19)
															{
																if(0<=minuto && minuto<60)
																	{
																		done=true;
																	}
																else 
																	{
																		System.out.println ( "errore nell'inserimento dell'orario" ) ;
																	}
															}
														else
															{
																System.out.println ( "errore nell'inserimento dell'orario" ) ;
															}
														
													} catch ( NumberFormatException e )
													{
														System.out.println ( "errore nell'inserimento dell'orario" ) ;
													}
											}
										else 
											{
												System.out.println ( "errore nell'inserimento dell'orario" ) ;
											}
										
									}
								
								done = false;
								
								while(!done)
									{
										System.out.print("Inserire nuovo orario  (hh:mm): ");
										newTime = input.nextLine();
										
										if(newTime.length ( )==5 && newTime.charAt ( 2 )==':' )
											{
												String minuti=newTime.substring ( 3,5 );
												String ore=newTime.substring ( 0,2 );
												try
													{
														int ora = Integer.parseInt ( ore );
														int minuto = Integer.parseInt ( minuti );
														
														if(9<=ora &&ora<19)
															{
																if(0<=minuto && minuto<60)
																	{
																		done=true;
																	}
																else 
																	{
																		System.out.println ( "errore nell'inserimento dell'orario" ) ;
																	}
															}
														else
															{
																System.out.println ( "errore nell'inserimento dell'orario" ) ;
															}
														
													} catch ( NumberFormatException e )
													{
														System.out.println ( "errore nell'inserimento dell'orario" ) ;
													}
											}
										else 
											{
												System.out.println ( "errore nell'inserimento dell'orario" ) ;
											}
										
									}
								if(query.op12 ( cf , date , time,newTime ))
									{
										System.out.println ( "Modifica dell'orario avvenuta con successo" ) ;
									}
								else
									{
										System.out.println ( "Impossibile modificare l'orario potrebbe esserci un altro appuntamento oppure hai fornito dati in input errati" ) ;
									}
							}
						
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}break;
					case 13:
					{
						System.out.println ( "Visualizzazione delle ore di occupazione delle sale in un anno" ) ;
						String sala=query.op13 ( );
						
						if(sala.equals ( "" ))
							{
								System.out.println ( "Non viene usata nessuna sala" ) ;
								
							}
						else
							{
								System.out.println ( sala ) ;
							}
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}
					break;
					case 14:
					{
						System.out.println ( "Visualizzazione dei macchinari che sono stati utilizzati per più di 1000 ore in un anno" ) ;
						String macchinari=query.op14 ( );
						
						if(macchinari.equals ( "" ))
							{
								System.out.println ( "Non ci sono macchinari che sono stati utilizzati per più di 1000 ore" ) ;
								
							}
						else
							{
								System.out.println ( macchinari ) ;
							}
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}
					break;
					case 15:
					{
						System.out.println ( "Acquisto di un nuovo macchinario\n" ) ;
						String tipo,modello,descrizione,nome,marca,nomeSala;
						boolean dimagrimento=false,antinvecchiamento=false,done=false;
						
						do
							{
								System.out.print("Inserire la marca  del macchinario: " ) ;
								marca=input.nextLine ( );
								if(!marca.equals ( "" ))
									{
										done=true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto....riprovare" ) ;
									}
							} while (!done ) ;
						
						done =false;
						do
							{
								System.out.print("Inserire il nome del macchinario: " ) ;
								nome=input.nextLine ( );
								if(!nome.equals ( "" ))
									{
										done=true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto...riprovare" ) ;
									}
							} while (!done ) ;
						
						done = false ;
						
						do
							{
								System.out.print("Inserire il modello del macchinario: " ) ;
								modello=input.nextLine ( );
								if(!nome.equals ( "" ))
									{
										done=true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto...riprovare" ) ;
									}
							} while (!done ) ;
						
						done = false ;
						
						System.out.print("Inserire una descrizione per il macchinario: " ) ;
						descrizione=input.nextLine ( );
						
						do
							{
								System.out.print("Inserire il tipo macchinario(viso,corpo,): " ) ;
								tipo=input.nextLine ( );
								if(!tipo.equals ( "" ))
									{
										done=true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto...riprovare" ) ;
									}
							} while (!done && !tipo.equalsIgnoreCase("corpo") && !tipo.equalsIgnoreCase("Viso")) ;
						
						done=false;
						
								String select="";
								if(tipo.equalsIgnoreCase ( "viso" ))
									{
										while(!done)
											{
												System.out.print ( "Il macchinario fornisce un trattamento di antinvecchiamento(s/n) : " ) ;
												select=input.nextLine ( );
												if(!select.equalsIgnoreCase ( "s" )&&!select.equalsIgnoreCase ( "n" ))
													{
														System.out.println ( "La lettera inserita è errata" ) ;
													}
												if(select.equalsIgnoreCase ( "s" ))
													{
														antinvecchiamento=true;
														done=true;
													}
												else if(select.equalsIgnoreCase ( "n" ))
													{
														antinvecchiamento=false;
														done=true;
													}
											
											}
	
									}
								else if(tipo.equalsIgnoreCase ( "corpo" ))
									{
										while(!done)
											{
												System.out.print ( "Il macchinario fornisce un trattamento di dimagrimento(s/n) : " ) ;
												select=input.nextLine ( );
												if(!select.equalsIgnoreCase ( "s" )&&!select.equalsIgnoreCase ( "n" ))
													{
														System.out.println ( "La lettera inserita è errata" ) ;
													}
												if(select.equalsIgnoreCase ( "s" ))
													{
														dimagrimento=true;
														done=true;
													}
												else if(select.equalsIgnoreCase ( "n" ))
													{
														dimagrimento=false;
														done=true;
													}

											}
								
							}
						
						do
							{
								System.out.print("Inserire il nome della sala in cui inserire il macchinario: " ) ;
								nomeSala=input.nextLine ( );
								if(!nomeSala.equals ( "" ))
									{
										done=true;
									}
								else
									{
										System.out.println ( "Il campo è vuoto...riprovare" ) ;
									}
							} while (!done ) ;
						
						
						query.op15 ( tipo , modello , descrizione , nome , dimagrimento , antinvecchiamento , marca , nomeSala );
						System.out.println ( "Premi invio per continuare" ) ;
						input.nextLine ( );
						query.close ( );
						clear ( );
						
					}
					break;
					case 16:input.close(); 
								query.close ( );
								clear ( );
					return;
					
					default:System.out.println ( "Il numero selezionato non corrisponde a nessuna scelta... riprovare" ) ;break;
					
				}
				
			}
		
		
}
private static void clear (  )
	{
		for(int i=0;i<10000;i++)
			{
				System.out.println (  ) ;
			}
	}
}
