package API;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import API.API_1;
import API.API_2;
import API.API_3;
import API.API_4;
//import db.ConnessioneMySQL;
import db.SaveMySQL;


//Si creerà una Api decisionale in grado di fornire delle proposte di assicurazione, di estensione di garanzia o di entrambe.

/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet(description = "ciaone", urlPatterns = { "/ManagementServlet" })
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	API_1 test_api_1 = new API_1();
	API_3 test_api_3 = new API_3();
	API_2 testLogin = new API_2();


	/////////////////////////////////////////////////
	public API_1 getTest_api_1() {
		return test_api_1;
	}

	public void setTest_api_1(API_1 test_api_1) {
		this.test_api_1 = test_api_1;
	}

	public API_3 getTest_api_3() {
		return test_api_3;
	}

	public void setTest_api_3(API_3 test_api_3) {
		this.test_api_3 = test_api_3;
	}

	public API_2 getTestLogin() {
		return testLogin;
	}

	public void setTestLogin(API_2 testLogin) {
		this.testLogin = testLogin;
	}

	/////////////////////////////////////////////////
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend"); // acquisizione parametro di gestione
		System.out.println("whatsend get: "+whatsend);
		if(whatsend.equals("home")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/home.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio		
		}
		if(whatsend.equals("form")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/prova.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio	
		}
		else if(whatsend.equals("login")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio	
		}else if(whatsend.equals("prop")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/prova3.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio	
		}else if(whatsend.equals("regist")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/registrazione.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio	
		}
		else if(whatsend.equals("logout")) {
			request.getSession().removeAttribute("testLogin");
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/home.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio	
		}
		if(whatsend.equals("api5")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/prova4.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio		
		}
		if(whatsend.equals("macchinari")) {
			ServletContext sc = request.getSession().getServletContext(); // richiamo della servlet
			RequestDispatcher rd = sc.getRequestDispatcher("/macchinari.jsp"); // passaggio dei parametri gestiti dalla servlet
			rd.forward(request, response); //stampaggio		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		/**
		//CONNESSIONE AL DB API, 	ESEMPIO
	    SaveMySQL saveMacchinario = new SaveMySQL();
	    try {
			Connection dbConnection = saveMacchinario.getDBConnection();
			System.out.println("FUNZIONA NELLA SERVLET");
		} catch (Exception e) {
			System.out.println("NON FUNZIONA NELLA SERVLET");
			e.printStackTrace();
		}**/

		//companyInDB = saveMacchinario.searchCompanies(); richiamo alla classe per il salvaggio dati su db
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend POST::" +whatsend);
		if(whatsend.equals("api_1")) {
			//int idmacchinario = request.getParameter("idmacchinario");

			String tip = request.getParameter("tip");
			System.out.println("tipologia: "+tip);

			double valore = Double.parseDouble(request.getParameter("valore")); //bisogna che sia un numero
			System.out.println("valore: "+valore);

			String ass_gar = request.getParameter("ass_gar");
			System.out.println("ass_gar: "+ass_gar);

			String matricola = request.getParameter("matricola");
			System.out.println("matricola: "+matricola);

			String durata = request.getParameter("durata");
			System.out.println("durata: "+durata);
			System.out.println(" ");
			//test_api_1.setIDMacchinario(idmacchinario);
			test_api_1.setTip(tip);
			test_api_1.setValore(valore);
			test_api_1.setAss_gar(ass_gar);
			test_api_1.setMatricola(matricola);
			test_api_1.setDurata(durata);

			//SaveMySQL saveMacchinario = new SaveMySQL();
			try {
				test_api_1.setMacchinario(test_api_1);
				test_api_1.setIDMacchinario(test_api_1.getId(test_api_1));
				//saveMacchinario.popolaDatabase();  //POPOLARE IL DATABASE DELLE PROPOSTE DI POLIZZA
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			request.getSession().removeAttribute("test");
			request.getSession().setAttribute("test", test_api_1);

			test_api_3.setOgg(test_api_1); 			
			request.getSession().removeAttribute("test2");
			request.getSession().setAttribute("test2", test_api_3);

			// creare la connessione al db sulla servlet
			// non può essere gestito nelle classi / api

			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/prova2.jsp");
			rd.forward(request, response);
		}

		if(whatsend.equals("prop")) {
			// modificare che viene richiesta api 3 e non api4
			String pag = request.getParameter("pag");
			System.out.println("pagamento: "+pag);
			test_api_3.setPag(pag);
			int offerta = Integer.parseInt(request.getParameter("offerta"));
			System.out.println("offerta	: "+offerta);
			API_2 test = (API_2) request.getSession().getAttribute("testLogin");
			try {
				test_api_3.generaPolizza(test_api_1, pag, offerta, test_api_3, test.getIdU(test));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/prova3.jsp");
			rd.forward(request, response);
		}
		if(whatsend.equals("login")) {

			String email = request.getParameter("email");
			System.out.println("email: "+email);
			String pass = request.getParameter("pass");
			System.out.println("pass: "+pass);
			try {
				if(testLogin.login(email, pass, testLogin)) {
					System.out.println("andato bene");
					request.getSession().removeAttribute("testLogin");
					request.getSession().setAttribute("testLogin", testLogin);
					setTestLogin(testLogin);
					System.out.println(testLogin.getNome());
				}
				else System.out.println("andato male");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
		if(whatsend.equals("regist")) {
			API_2 testLogin = new API_2();
			String nome = request.getParameter("nome");
			System.out.println("nome: "+nome);
			String cognome = request.getParameter("cognome");
			System.out.println("cognome: "+cognome);
			String email = request.getParameter("email");
			System.out.println("email: "+email);
			String pass = request.getParameter("pass");
			System.out.println("password: "+pass);

			Date nascita = Date.valueOf(request.getParameter("nascita"));
			System.out.println("nascita: "+nascita);

			String telefono = request.getParameter("telefono");
			System.out.println("telefono: "+telefono);
			testLogin.setNome(nome);
			testLogin.setCognome(cognome);
			testLogin.setEmail(email);
			testLogin.setPass(pass);
			testLogin.setTelefono(telefono);
			testLogin.setNascita(nascita);
			testLogin.setInserimento();
			//SaveMySQL saveUser = new SaveMySQL();
			try {
				testLogin.setUser(testLogin);
				testLogin.setId(testLogin.getIdU(testLogin));
				System.out.println(testLogin.getId());
				//saveMacchinario.popolaDatabase();  //POPOLARE IL DATABASE DELLE PROPOSTE DI POLIZZA
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().removeAttribute("testLogin");
			request.getSession().setAttribute("testLogin", testLogin);
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/postRegister.jsp");
			rd.forward(request, response);
			//test1.setIduser();
		}
		if(whatsend.equals("assistenza")) {
			String api = request.getParameter("api");
			System.out.println("api: "+api);
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/assistenza.jsp");
			rd.forward(request, response);
		}
		if(whatsend.equals("pagamenti")) {
			String api = request.getParameter("api");
			System.out.println("api: "+api);
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/pagamenti.jsp");
			rd.forward(request, response);
		}
	}

}