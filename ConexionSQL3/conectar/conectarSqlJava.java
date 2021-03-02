
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import javax.swing.JOptionPane;

public class conectarSqlJava {

		public static Connection conexion;
		

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			crearTiendaInformatica();
			crearAlmacenes();
			crearEmpresa();
			peliculasSalas();
			losDirectores();
			piezasProveedores();
			losCientificos();
			grandesAlmacenes();
			losInvestigadores();
			
			
			//createDB("BD_prueba1");
			//createTable("BD_prueba1","Tabla_1");
			//insertData("BD_prueba1","Tabla_1","name", "lastname", "age", "H");
			//getValues("BD_prueba1","Tabla_1");
			//deleteRecord("BD_prueba1","Tabla_1", "1");
			closeConnection();
		}
		
		
		//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
		public static void openConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion=DriverManager.getConnection("jdbc:mysql://192.168.0.32:3306","remote","Gio95021208966*");//credenciales temporales
				System.out.print("Server Connected");
				fecha();
				
			}catch(SQLException | ClassNotFoundException ex  ){
				System.out.print("No se ha podido conectar con mi base de datos");
				fecha();
				System.out.println(ex.getMessage());
				
			}
			
		}
			
		//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
		public static void closeConnection() {
			try {
		
				conexion.close();
				System.out.print("Server Disconnected");
				fecha();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.print("Error cerrando conexion");
				fecha();
			}
		}
		
		//METODO QUE CREA LA BASE DE DATOS
		public static void createDB(String name) {
			try {
				String Query="CREATE DATABASE "+ name;
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				
			//JOptionPane.showMessageDialog(null, "Se ha creado la DB " +name+ "de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
			}	
		}

		//METODO QUE CREA TABLAS MYSQL
		public static void createTable(String db,String name, String variables) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ variables;
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertData(String db, String table_name, String name, String lastname, String age, String gender) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (Nombre, Apellido, Edad, Sexo) VALUE(" 
						+ "\"" + name + "\", "
						+ "\"" + lastname + "\", "
						+ "\"" + age + "\", "
						+ "\"" + gender + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public static void getValues(String db, String table_name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM " + table_name;
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("ID: " +  resultSet.getString("ID") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Apellido:" + resultSet.getString("Apellido") +  " "
							+ "Edad: " +  resultSet.getString("Edad") + " "
							+ "Sexo: " +  resultSet.getString("Sexo") + " "
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		
		}
		 
		//METODO QUE LIMPIA TABLAS MYSQL
		public static void deleteRecord(String db, String table_name, String ID) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Registros de tabla ELIMINADOS con exito!");
							
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
			}
		}	

		
		//METODO QUE ELIMINA TABLAS MYSQL
		public static void deleteTAbla(String db, String table_name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DROP TABLE " + table_name + ";";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("TABLA ELIMINADA con exito!");
							
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error borrando la tabla");
			}
		}	
		
		
		//METODO QUE MUESTRA FECHA
		public static void fecha() {
			Date date = new Date();
			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			System.out.println(" - " + hourdateFormat.format(date));
			}
		
//Ejercicio1, Llamada para crear BD, tablas e insertar registros
		public static void crearTiendaInformatica() {
			createDB("conTiendaInformatica");
			createTable("conTiendaInformatica","fabricantes","(codigo INT AUTO_INCREMENT, "
					+ "nombre VARCHAR(100), PRIMARY KEY(codigo));");
			createTable("conTiendaInformatica","articulos","(codigo INT AUTO_INCREMENT, nombre VARCHAR(100), "
					+ "precio INT, fabricante INT,PRIMARY KEY(codigo),FOREIGN KEY (fabricante) REFERENCES fabricantes(codigo));");

			 insertFabricantes("conTiendaInformatica","fabricantes", "Creative-Labs");
			 insertFabricantes("conTiendaInformatica","fabricantes", "Hewlett-Packard");
			 insertFabricantes("conTiendaInformatica","fabricantes","Iomega");
			 insertFabricantes("conTiendaInformatica","fabricantes", "Fujitsu");
			 insertFabricantes("conTiendaInformatica","fabricantes", "Panasonic");

			 insertArticulos("conTiendaInformatica", "articulos", "Hard drive","240","5");
			 insertArticulos("conTiendaInformatica", "articulos", "Memory","120","3");
			 insertArticulos("conTiendaInformatica", "articulos", "ZIP drive","150","4");
			 insertArticulos("conTiendaInformatica", "articulos", "Floppy disk","5","1");
			 insertArticulos("conTiendaInformatica", "articulos", "Monitor","240","1");

		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertFabricantes(String db, String table_name, String nombre) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo, nombre) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertArticulos(String db, String table_name, String nombre, String precio, String fabricante) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo,nombre, precio, fabricante) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + precio+ "\", "
						+ "\"" + fabricante + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
	
		
//Ejercicio2, Llamada para crear BD, tablas e insertar registros
		public static void crearEmpresa() {
			createDB("conEmpresa");
			createTable("conEmpresa","departamentos","(CODIGO int NOT NULL, NOMBRE varchar(255) NOT NULL,"
					+ "PRESUPUESTO decimal(10,0) NOT NULL, PRIMARY KEY (CODIGO));");
			createTable("conEmpresa","caja","(DNI int NOT NULL,`NOMBRE` varchar(255) NOT NULL,`APELLIDOS` varchar(255) NOT NULL,"
					+ "`DEPARTAMENTO` int NOT NULL,  PRIMARY KEY (`DNI`), KEY `DEPARTAMENTO` (`DEPARTAMENTO`), "
					+ "CONSTRAINT `caja_ibfk_1` FOREIGN KEY (`DEPARTAMENTO`) REFERENCES `departamentos` (`CODIGO`));");
			
			insertDepartamentos("conEmpresa","departamentos","37","Accounting","15000") ;
			insertDepartamentos("conEmpresa","departamentos","59","Human Resources","240000" ) ;
			insertDepartamentos("conEmpresa","departamentos","32","Production","150000" ) ;
			insertDepartamentos("conEmpresa","departamentos","51","Purchasing","70000" ) ;
			insertDepartamentos("conEmpresa","departamentos", "33","Marketing","65000") ;
			
			insertcaja("conEmpresa","caja","152934485","Anand","Manikutty","32" ) ;
			insertcaja("conEmpresa","caja", "222364883","Carol","Smith","37") ;
			insertcaja("conEmpresa","caja", "326587417","Joe","Stevens","37") ;
			insertcaja("conEmpresa","caja", "332154719","Mary-Anne","Foster","59") ;
			insertcaja("conEmpresa","caja", "332569843","George","Donnell","51") ;
		}

	
		
		public static void insertDepartamentos(String db, String table_name, String codigo, String nombre, String presupuesto) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (CODIGO,NOMBRE,PRESUPUESTO) VALUE(" 
						+ "\"" + codigo + "\", "
						+ "\"" + nombre+ "\", "
						+ "\"" + presupuesto + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertcaja(String db, String table_name, String DNI, String nombre, String apellido, String departamento) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (DNI,NOMBRE,APELLIDOS,DEPARTAMENTO) VALUE(" 
						+ "\"" + DNI + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + apellido+ "\", "
						+ "\"" + departamento + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		
//Ejercicio3, Llamada para crear BD, tablas e insertar registros	
		public static void crearAlmacenes() {
			createDB("conAlmacenes");
			createTable("conAlmacenes","almacen","(CODIGO int AUTO_INCREMENT,LUGAR varchar(100) NOT NULL,"
					+ " CAPACIDAD int NOT NULL,  PRIMARY KEY (CODIGO));");
			createTable("conAlmacenes","caja","( NUMREFERENCIA char(5) NOT NULL,CONTENIDO varchar(100) NOT NULL,"
					+ "VALOR double NOT NULL, ALMACEN int NOT NULL, PRIMARY KEY (NUMREFERENCIA), "
					+ "KEY ALMACEN (ALMACEN), CONSTRAINT cajas_ibfk_1 FOREIGN KEY (ALMACEN) REFERENCES almacen (CODIGO));");
			
			insertAlmacen("conAlmacenes","almacen","Barcelona","4") ;
			insertAlmacen("conAlmacenes","almacen","Bilbao","7") ;
			insertAlmacen("conAlmacenes","almacen","Los Angeles","2") ;
			insertAlmacen("conAlmacenes","almacen","Madrid","5") ;
			insertAlmacen("conAlmacenes","almacen","Castellon","2") ;
			
			insertCajaA("conAlmacenes","caja", "4H8P","Rocks","250","1");
			insertCajaA("conAlmacenes","caja", "4RT3","Scissors","190","4" ) ;
			insertCajaA("conAlmacenes","caja", "7G3H","Rocks","200","1") ;
			insertCajaA("conAlmacenes","caja", "8JN6","Papers","75","1") ;
			insertCajaA("conAlmacenes","caja", "8Y6U","Papers","50","3") ;
		
		}
		
		public static void insertAlmacen(String db, String table_name, String lugar, String capacidad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (CODIGO,LUGAR,CAPACIDAD) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + lugar + "\", "
						+ "\"" + capacidad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertCajaA(String db, String table_name, String numReferencia, String contenido, String valor, String almacen) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (NUMREFERENCIA,CONTENIDO,VALOR,ALMACEN) VALUE(" 
						+ "\"" + numReferencia + "\", "
						+ "\"" + contenido + "\", "
						+ "\"" + valor + "\", "
						+ "\"" + almacen + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		

		
//Ejercicio4, Llamada para crear BD, tablas e insertar registros	
		public static void peliculasSalas() {
			//createDB("conpeliculasSalas");
			/*createTable("conpeliculasSalas","peliculas","(`CODIGO` int AUTO_INCREMENT,`NOMBRE` varchar(255) NOT NULL, "
					+ "`CALIFICACIONEDAD` varchar(255) DEFAULT NULL,  PRIMARY KEY (`CODIGO`));");
			createTable("conpeliculasSalas","salas","(`CODIGO` int AUTO_INCREMENT, `NOMBRE` varchar(255) NOT NULL, "
					+ "`PELICULA` int DEFAULT NULL, PRIMARY KEY (`CODIGO`), KEY `PELICULA` (`PELICULA`), "
					+ "CONSTRAINT `salas_ibfk_1` FOREIGN KEY (`PELICULA`) REFERENCES `peliculas` (`CODIGO`));");
			*/
			insertPeliculas("conpeliculasSalas","peliculas", "Singin in the Rain","G") ;
			insertPeliculas("conpeliculasSalas","peliculas", "The Wizard of Oz","G") ;
			insertPeliculas("conpeliculasSalas","peliculas","The Quiet Man","NULL") ;
			insertPeliculas("conpeliculasSalas","peliculas","North by Northwest","NULL") ;
			insertPeliculas("conpeliculasSalas","peliculas","The Last Tango in Paris","NC-17") ;

			insertSalas("conpeliculasSalas","salas","Imperial","1" ) ;
			insertSalas("conpeliculasSalas","salas","Majestic","3" ) ;
			insertSalas("conpeliculasSalas","salas", "Royale","5") ;
			insertSalas("conpeliculasSalas","salas","Paraiso","3" ) ;
			insertSalas("conpeliculasSalas","salas", "Imperial","1") ;
		
		
		}
		

		
		public static void insertPeliculas(String db, String table_name, String nombre, String calificacionEdad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (CODIGO,NOMBRE, CALIFICACIONEDAD) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + calificacionEdad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertSalas(String db, String table_name, String nombre, String pelicula) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (CODIGO,NOMBRE, PELICULA) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + pelicula + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}	
		
		
//Ejercicio5, Llamada para crear BD, tablas e insertar registros
		public static void losDirectores() {
			createDB("conlosDirectores");
			createTable("conlosDirectores","despacho","(numero INT,capacidad INT,PRIMARY KEY(numero));");
			createTable("conlosDirectores","directores","(dni VARCHAR(8),nomApels VARCHAR(255),dniJefe VARCHAR(8),"
					+ "despacho INT,PRIMARY KEY(dni),FOREIGN KEY(despacho) REFERENCES despacho(numero),"
					+ "FOREIGN KEY(dniJefe) REFERENCES directores(dni));");
			
			insertDespacho("conlosDirectores","despacho","1","12") ;
			insertDespacho("conlosDirectores","despacho", "2","22") ;
			insertDespacho("conlosDirectores","despacho", "3","2" ) ;
			insertDespacho("conlosDirectores","despacho", "4","5") ;
			insertDespacho("conlosDirectores","despacho", "5","7") ;

			insertDirectores("conlosDirectores","directores", "42400237","Giovanny Rodriguez","42400237","2") ;
			insertDirectores("conlosDirectores","directores", "54403237","Luis Lopez","54403237","2") ;
			insertDirectores("conlosDirectores","directores", "44407637","Orlando Garrido","42400237","2") ;
			insertDirectores("conlosDirectores","directores", "23400237","Manuel Zapata","54403237","2") ;
			insertDirectores("conlosDirectores","directores", "47240024","Javier Zamorano","44407637","2") ;
		

		}
		
		public static void insertDespacho(String db, String table_name, String numero, String capacidad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (numero,capacidad) VALUE(" 
						+ "\"" + numero + "\", "
						+ "\"" + capacidad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertDirectores(String db, String table_name, String dni, String nomApels,String dniJefe, String despacho) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (dni, nomApels,dniJefe,despacho) VALUE(" 
						+ "\"" + dni+ "\", "
						+ "\"" + nomApels + "\", "
						+ "\"" + dniJefe + "\", "
						+ "\"" + despacho+ "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}	
		
//Ejercicio6, Llamada para crear BD, tablas e insertar registros
		public static void piezasProveedores() {
			createDB("conpiezasProveedores");
			createTable("conpiezasProveedores","piezas","(codigo INT AUTO_INCREMENT, nombre VARCHAR(100), PRIMARY KEY(codigo));");
			createTable("conpiezasProveedores","proveedores","(id CHAR(4), nombre VARCHAR(100), PRIMARY KEY(id));");
			createTable("conpiezasProveedores","suministra","(codigoPieza INT, idProveedor CHAR(4), precio INT,  "
					+ "PRIMARY KEY(codigoPieza, idProveedor), FOREIGN KEY(codigoPieza) REFERENCES piezas(codigo), "
					+ "FOREIGN KEY(idProveedor) REFERENCES proveedores(id));");
			
			insertPiezas("conpiezasProveedores","piezas","Freno de disco") ;
			insertPiezas("conpiezasProveedores","piezas","Diferencial") ;
			insertPiezas("conpiezasProveedores","piezas","Transmision") ;
			insertPiezas("conpiezasProveedores","piezas","Motor") ;
			insertPiezas("conpiezasProveedores","piezas","Volante") ;

			insertProveedores("conpiezasProveedores","proveedores","1001","Giovanny Rodriguez") ;
			insertProveedores("conpiezasProveedores","proveedores","1002","Andres Ledezma") ;
			insertProveedores("conpiezasProveedores","proveedores","1003","Miguel Lopez") ;
			insertProveedores("conpiezasProveedores","proveedores","1004","Raul Cortes") ;
			insertProveedores("conpiezasProveedores","proveedores","1005","Milena Abadia") ;
		
			insertSuministra("conpiezasProveedores","suministra", "2","1002","2") ;
			insertSuministra("conpiezasProveedores","suministra", "2","1003","32") ;
			insertSuministra("conpiezasProveedores","suministra", "4","1003","12") ;
			insertSuministra("conpiezasProveedores","suministra", "1","1004","222") ;
			insertSuministra("conpiezasProveedores","suministra", "3","1004","33") ;
		}
		
		public static void insertPiezas(String db, String table_name, String nombre) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo,nombre) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre+ "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertProveedores(String db, String table_name, String id, String nombre) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (id, nombre) VALUE(" 
						+ "\"" + id+ "\", "
						+ "\"" + nombre + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
		}
			
			
		public static void insertSuministra(String db, String table_name, String codigoPieza, String idProveedor,String precio) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
								
					String Query = "INSERT INTO " + table_name + " (codigoPieza, idProveedor, precio) VALUE(" 
							+ "\"" + codigoPieza + "\", "
							+ "\"" + idProveedor + "\", "
							+ "\"" + precio + "\"); ";
					Statement st = conexion.createStatement();
					st.executeUpdate(Query);
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}			
		}
		
		
//Ejercicio7, Llamada para crear BD, tablas e insertar registros
		public static void losCientificos() {
			createDB("conlosCientificos");
			createTable("conlosCientificos","cientificos","(dni VARCHAR(8), nomApels VARCHAR(255), PRIMARY KEY(dni));");
			createTable("conlosCientificos","proyectos","(id CHAR(4), nombre VARCHAR(255), horas INT, PRIMARY KEY(id));");
			createTable("conlosCientificos","asignado_a","(cientifico VARCHAR(8), proyecto CHAR(4), PRIMARY KEY(cientifico, proyecto), "
					+ "FOREIGN KEY(cientifico) REFERENCES cientificos(dni), FOREIGN KEY(proyecto) REFERENCES proyectos(id));");
			
			insertCientificos("conlosCientificos","cientificos","48686712","Giovanny Rodriguez") ;
			insertCientificos("conlosCientificos","cientificos","43232915","Javier Zamorano") ;
			insertCientificos("conlosCientificos","cientificos","49807666","Orlando Restrepo") ;
			insertCientificos("conlosCientificos","cientificos","47320336","Valeria Arias") ;
			insertCientificos("conlosCientificos","cientificos","49605500","Luisa Martinez") ;

			insertProyectos("conlosCientificos","proyectos","1001","Rayos X en 4D","172") ;
			insertProyectos("conlosCientificos","proyectos","1002","Rastreados de Mosquitos","124") ;
			insertProyectos("conlosCientificos","proyectos","1003","Araña Trabajadora","153") ;
			insertProyectos("conlosCientificos","proyectos","1004","La revolucion Osea","50") ;
			insertProyectos("conlosCientificos","proyectos","1005","Protesis controlada por cerebro","85") ;

			insertAsignados("conlosCientificos","asignado_a","48686712","1004") ;
			insertAsignados("conlosCientificos","asignado_a","43232915","1002") ;
			insertAsignados("conlosCientificos","asignado_a","49807666","1003") ;
			insertAsignados("conlosCientificos","asignado_a","47320336","1005") ;
			insertAsignados("conlosCientificos","asignado_a","49605500","1004") ;
		}

		
		public static void insertCientificos(String db, String table_name, String dni, String nomApels) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (dni, nomApels) VALUE(" 
						+ "\"" + dni + "\", "
						+ "\"" + nomApels + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertProyectos(String db, String table_name, String id, String nombre, String horas) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (id, nombre, horas) VALUE(" 
						+ "\"" + id+ "\", "
						+ "\"" + nombre+ "\", "
						+ "\"" + horas + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
		}
			
			
		public static void insertAsignados(String db, String table_name, String cientifico, String proyecto) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
								
					String Query = "INSERT INTO " + table_name + " (cientifico, proyecto) VALUE(" 
							+ "\"" + cientifico + "\", "
							+ "\"" + proyecto + "\"); ";
					Statement st = conexion.createStatement();
					st.executeUpdate(Query);
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}			
		}
		
		
//Ejercicio8, Llamada para crear BD, tablas e insertar registros
		public static void grandesAlmacenes() {
			createDB("congrandesAlmacenes");
			createTable("congrandesAlmacenes","cajeros","(codigo INT AUTO_INCREMENT, nomApels VARCHAR(255), PRIMARY KEY(codigo));");
			createTable("congrandesAlmacenes","productos","(codigo INT AUTO_INCREMENT, nombre VARCHAR(100), precio INT, PRIMARY KEY(codigo));");
			createTable("congrandesAlmacenes","maquinaRegistradora","(codigo INT AUTO_INCREMENT, piso INT, PRIMARY KEY(codigo));");
			createTable("congrandesAlmacenes","venta","(cajero INT, maquina INT, producto INT, PRIMARY KEY(cajero,maquina,producto), "
					+ "FOREIGN KEY(cajero) REFERENCES cajeros(codigo),  FOREIGN KEY(producto) REFERENCES productos(codigo), "
					+ "FOREIGN KEY(maquina) REFERENCES  maquinaRegistradora(codigo));");
			
			insertCajeros("congrandesAlmacenes","cajeros","Giovanny Rodriguez") ;
			insertCajeros("congrandesAlmacenes","cajeros","Javier Zamorano") ;
			insertCajeros("congrandesAlmacenes","cajeros","Orlando Restrepo") ;
			insertCajeros("congrandesAlmacenes","cajeros","Valeria Arias") ;
			insertCajeros("congrandesAlmacenes","cajeros","Luisa Martinez") ;

			insertProductos("congrandesAlmacenes","productos","Huevo","2") ;
			insertProductos("congrandesAlmacenes","productos","Leche","1") ;
			insertProductos("congrandesAlmacenes","productos","Azucar","1") ;
			insertProductos("congrandesAlmacenes","productos","Sal","1") ;
			insertProductos("congrandesAlmacenes","productos","Harina","2") ;

			insertMaquinas("congrandesAlmacenes","maquinaRegistradora","1") ;
			insertMaquinas("congrandesAlmacenes","maquinaRegistradora","2") ;
			insertMaquinas("congrandesAlmacenes","maquinaRegistradora","2") ;
			insertMaquinas("congrandesAlmacenes","maquinaRegistradora","3") ;
			insertMaquinas("congrandesAlmacenes","maquinaRegistradora","1") ;
			
			insertVenta("congrandesAlmacenes","venta","1","2","5") ;
			insertVenta("congrandesAlmacenes","venta","2","2","4") ;
			insertVenta("congrandesAlmacenes","venta","2","4","5") ;
			insertVenta("congrandesAlmacenes","venta","3","5","3") ;
			insertVenta("congrandesAlmacenes","venta","5","3","1") ;
		}

		

		
		public static void insertCajeros(String db, String table_name, String nomApels) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo, nomApels) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nomApels + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertProductos(String db, String table_name, String nombre, String precio) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo, nombre, precio) VALUE(" 
						+ "\"" + "0" + "\", "
						+ "\"" + nombre+ "\", "
						+ "\"" + precio + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
		}
			
			
		public static void insertMaquinas(String db, String table_name, String piso) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
								
					String Query = "INSERT INTO " + table_name + " (codigo,piso) VALUE(" 
							+ "\"" + "0" + "\", "
							+ "\"" + piso + "\"); ";
					Statement st = conexion.createStatement();
					st.executeUpdate(Query);
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}			
		}
		
		public static void insertVenta(String db, String table_name, String cajero, String maquina, String producto) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (cajero,maquina,producto) VALUE(" 
						+ "\"" + cajero + "\", "
						+ "\"" + maquina + "\", "
						+ "\"" + producto + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}			
	}
		
		
//Ejercicio9, Llamada para crear BD, tablas e insertar registros
		public static void losInvestigadores() {
			createDB("conlosInvestigadores");
			createTable("conlosInvestigadores","facultad","(codigo INT, nombre VARCHAR(100), PRIMARY KEY(codigo));");
			createTable("conlosInvestigadores","investigadores","(dni VARCHAR(8), nomApels VARCHAR(255), facultad INT, PRIMARY KEY(dni), FOREIGN KEY(facultad) REFERENCES facultad(codigo));");
			createTable("conlosInvestigadores","equipos","(numSerie CHAR(4), nombre VARCHAR(100), facultad INT, PRIMARY KEY(numSerie), FOREIGN KEY(facultad) REFERENCES facultad(codigo));");
			createTable("conlosInvestigadores","reserva","(dni VARCHAR(8),  numSerie CHAR(4), comienzo DATETIME, fin DATETIME, PRIMARY KEY(dni, numSerie),  FOREIGN KEY(dni) REFERENCES investigadores(dni), "
					+ "FOREIGN KEY(numSerie) REFERENCES equipos(numSerie));");
			
			insertFacultad("conlosInvestigadores","facultad","1001","Administracion") ;
			insertFacultad("conlosInvestigadores","facultad","1002","Arquitectura y diseño") ;
			insertFacultad("conlosInvestigadores","facultad","1003","Artes y Humanidades") ;
			insertFacultad("conlosInvestigadores","facultad","1004","Ciencias") ;
			insertFacultad("conlosInvestigadores","facultad","1005","Ciencias Sociales") ;
			

			insertInvestigadores("conlosInvestigadores","investigadores","48686712","Giovanny Rodriguez","1002");
			insertInvestigadores("conlosInvestigadores","investigadores","43232915","Javier Zamorano","1001");
			insertInvestigadores("conlosInvestigadores","investigadores","49807666","Orlando Restrepo","1002");
			insertInvestigadores("conlosInvestigadores","investigadores","47320336","Valeria Arias","1003");
			insertInvestigadores("conlosInvestigadores","investigadores","49605500","Luisa Martinez","1005");

			insertEquipos("conlosInvestigadores","equipos","2001","Le corbusier","1002") ;
			insertEquipos("conlosInvestigadores","equipos","2002","Amazonias","1001") ;
			insertEquipos("conlosInvestigadores","equipos","2003","Espartanos","1004") ;
			insertEquipos("conlosInvestigadores","equipos","2004","Literato","1005") ;
			insertEquipos("conlosInvestigadores","equipos","2005","Tucellia","1001") ;

			insertReserva("conlosInvestigadores","reserva","49640096","2001","2020-12-01 15:00:00","NOW()") ;
			insertReserva("conlosInvestigadores","reserva","49605500","2003","2020-12-01 15:00:00","NOW()") ;
			insertReserva("conlosInvestigadores","reserva","49807666","2004","2020-12-01 15:00:00","NOW()") ;
			insertReserva("conlosInvestigadores","reserva","46642895","2005","2020-12-01 15:00:00","NOW()") ;
			insertReserva("conlosInvestigadores","reserva","48978259","2001","2020-12-01 15:00:00","NOW()") ;
		}

		public static void insertFacultad(String db, String table_name, String codigo, String nombre) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (codigo, nombre) VALUE(" 
						+ "\"" + codigo + "\", "
						+ "\"" + nombre + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
		
		public static void insertInvestigadores(String db, String table_name, String dni, String nomApels, String facultad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (dni, nomApels, facultad) VALUE(" 
						+ "\"" + dni + "\", "
						+ "\"" + nomApels+ "\", "
						+ "\"" + facultad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
		}
			
			
		public static void insertEquipos(String db, String table_name, String numSerie, String nombre, String facultad) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
								
					String Query = "INSERT INTO " + table_name + " (numSerie, nombre, facultad) VALUE(" 
							+ "\"" + numSerie + "\", "
							+ "\"" + nombre + "\", "
							+ "\"" + facultad + "\"); ";
					Statement st = conexion.createStatement();
					st.executeUpdate(Query);
					
					System.out.println("Datos almacenados correctamente");;
					
				} catch (SQLException ex ) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
				}			
		}
		
		public static void insertReserva(String db, String table_name, String dni, String numSerie, String comienzo, String fin) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + table_name + " (dni, numSerie, comienzo, fin) VALUE(" 
						+ "\"" + dni + "\", "
						+ "\"" + numSerie + "\", "
						+ "\"" + comienzo + "\", "
						+ "\"" + fin + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}			
	}
		
		
		
	}
