package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logic.Factory;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private final IActivity IACT;
	private final IOuting IOUT;
	private final IInscription IINS;
	private final IUser IUSR;

	private CreateUser creUsrInternalFrame;
	private CreateActivity creActInternalFrame; // Frame interno para dar de alta Actividad
	private ConsultActivities conActInternalFrame; // Frame interno para consultar por Actividad
	private ConfirmActivity confirmActInternalFrame; // Frame interno para consultar por Actividad
	private CreateOuting creOutInternalFrame; // Frame interno para dar de alta Salida
	private ConsultOuting conOutInternalFrame; // Frame interno para consultar por una Salida
	private CreateInscription creInsInternalFrame;

	private ConsultUser conUserInternalFrame;
	private ConsultInscriptions conInsInternalFrame;

	private JFrame mainFrame; // Frame principal
	private JMenuItem menuItem_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		menu();
//		this.loadPersistenceData();

		// Inicializacion
		Factory factory = Factory.getInstance(); // Se crea una instancia unica de fabrica, se guarda en la varible
													// fabrica
		this.IACT = factory.getIActivity(); // Se devuelve una instancia unica controlador de Activity
		this.IOUT = factory.getIOuting();
		this.IINS = factory.getIInscription();
		this.IUSR = factory.getIUser();

		// Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
		// De esta forma, no es necesario crear y destruir objetos lo que enlentece la
		// ejecución.
		// Cada InternalFrame usa un layout diferente, simplemente para mostrar
		// distintas opciones.

		this.creUsrInternalFrame = new CreateUser(this.IUSR);
		this.creUsrInternalFrame.setSize(455, 516);
		this.creUsrInternalFrame.setLocation(147, 0);
		this.creUsrInternalFrame.setVisible(false);

		this.creActInternalFrame = new CreateActivity(this.IACT, this.IUSR);
		this.creActInternalFrame.setLocation(30, 35);
		this.creActInternalFrame.setVisible(false);

		this.conActInternalFrame = new ConsultActivities(this.IACT);
		this.conActInternalFrame.setLocation(30, 35);
		this.conActInternalFrame.setVisible(false);

		this.confirmActInternalFrame = new ConfirmActivity(this.IACT);
		this.confirmActInternalFrame.setLocation(30, 35);
		this.confirmActInternalFrame.setVisible(false);

		this.creOutInternalFrame = new CreateOuting(this.IOUT, this.IACT);
		this.creOutInternalFrame.setLocation(6, 11);
		this.creOutInternalFrame.setVisible(false);

		this.conOutInternalFrame = new ConsultOuting(this.IOUT, this.IACT);
		this.conOutInternalFrame.setLocation(62, 11);
		this.conOutInternalFrame.setVisible(false);

		this.conUserInternalFrame = new ConsultUser(this.IUSR, this.IOUT);
		this.conUserInternalFrame.setLocation(30, 35);
		this.conUserInternalFrame.setVisible(false);

		this.conInsInternalFrame = new ConsultInscriptions(this.IACT, this.IOUT);
		this.conInsInternalFrame.setLocation(62, 11);
		this.conInsInternalFrame.setVisible(false);

		this.creInsInternalFrame = new CreateInscription(this.IOUT, this.IACT, this.IINS, this.IUSR);
		creInsInternalFrame.setSize(500, 505);
		creInsInternalFrame.setResizable(true);
		this.creInsInternalFrame.setLocation(62, 0);
		this.creInsInternalFrame.setVisible(false);

		/* Se agregan los internal frames al frame principal */
		this.mainFrame.getContentPane().setLayout(null);

		this.mainFrame.getContentPane().add(this.creInsInternalFrame);
		this.mainFrame.getContentPane().add(this.creUsrInternalFrame);
		this.mainFrame.getContentPane().add(this.creActInternalFrame);
		this.mainFrame.getContentPane().add(this.conActInternalFrame);
		this.mainFrame.getContentPane().add(this.confirmActInternalFrame);
		this.mainFrame.getContentPane().add(this.creOutInternalFrame);
		this.mainFrame.getContentPane().add(this.conOutInternalFrame);
		this.mainFrame.getContentPane().add(this.conUserInternalFrame);
		this.mainFrame.getContentPane().add(this.conInsInternalFrame);
	}

	public void menu() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 650, 572);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Menu de Inicio.
		menu = new JMenu("Inicio");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Ir a inicio");
		menuBar.add(menu);

		// Menu de Solicitudes.
		menu = new JMenu("Solicitudes");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		menuItem = new JMenuItem("Actividades");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para confirmar/rechazar una actividad
				confirmActInternalFrame.loadActivities();
				confirmActInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Menu de Registros.
		menu = new JMenu("Registros");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// Item registrar Usuario
		menuItem = new JMenuItem("Registrar Usuario");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para registrar un Usuario
				creUsrInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Item registrar Actividad
		menu.addSeparator();
		menuItem = new JMenuItem("Registrar Actividad");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para registrar una Actividad
				creActInternalFrame.setVisible(true);
				creActInternalFrame.loadActivities();
			}
		});
		menu.add(menuItem);

		// Item registrar Salida
		menu.addSeparator();
		menuItem = new JMenuItem("Registrar Salida");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para registrar una Salida
				creOutInternalFrame.loadActivities();
				creOutInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Item registrar Inscripcion
		menu.addSeparator();
		menuItem = new JMenuItem("Registrar Inscripcion");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para registrar una Salida
				creInsInternalFrame.loadActivities();
				creInsInternalFrame.loadUsers();
				creInsInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Menu de Consultas
		menu = new JMenu("Consultas");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("Consultas de registros");
		menuBar.add(menu);

		// Item consulta Usuario
		menuItem_1 = new JMenuItem("Consulta Usuario");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conUserInternalFrame.loadUsers();
				conUserInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem_1);

		// Item consulta Actividad
		menu.addSeparator();
		menuItem = new JMenuItem("Consulta Actividad");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conActInternalFrame.setVisible(true);
				conActInternalFrame.loadActivities();
			}
		});
		menu.add(menuItem);

		// Item consulta Salida
		menu.addSeparator();
		menuItem = new JMenuItem("Consulta Salida");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para consultar una Salida
				conOutInternalFrame.loadActivities();
				conOutInternalFrame.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Item consulta Inscripción
		menu.addSeparator();
		menuItem = new JMenuItem("Consultar Inscripción");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para consultar una inscripción
				conInsInternalFrame.setVisible(true);
				conInsInternalFrame.loadActivities();
			}
		});
		menu.add(menuItem);

		mainFrame.setJMenuBar(menuBar);

	}

//	public void loadData() {
//		UserManagement um = UserManagement.getinstance();
//		Tourist tourist1 = new Tourist("tourist1", "Carlos", "Rodriguez", "carlos@gmail.com", "12/01/10", "1234",
//				"Brasil");
//		Provider provider1 = new Provider("provider1", "Nicolas", "Jimenez", "nj@gmail.com", "01/01/10", "abcd",
//				"description1", "website.com");
//		um.addUser(tourist1);
//		um.addUser(provider1);
//
//		ActivitiesManagement am = ActivitiesManagement.getInstance();
//		Activity activity1 = new Activity("Act1", "Description algo", 10, "110,5", "Montevideo");
//		Activity activity2 = new Activity("Act2", "Description algo", 12, "210,5", "Canelones");
//		am.addActivity(activity1);
//		am.addActivity(activity2);
//
//		Outing outing1 = new Outing("Salida1", "10/01/23", "12", 30, "Ibiza", "Imagen1");
//		Outing outing2 = new Outing("Salida2", "11/01/23", "11", 20, "Santorini", "Imagen1");
//
//		provider1.getActivitiesMap().put(activity1.getName(), activity1);
//		activity1.addOuting(outing1);
//		activity1.addOuting(outing2);
//
//		Inscription inscription1 = new Inscription("22/08/24", 2, 24, tourist1);
//		Inscription inscription2 = new Inscription("08/08/99", 2, 34, tourist1);
//		outing1.addInscription(inscription1);
//		outing1.addInscription(inscription2);
//	}

//	public void loadPersistenceData() {
//		UserManagement um = UserManagement.getinstance();
//		Tourist tourist1 = new Tourist("tourist1", "Carlos", "Rodriguez", "carlos@gmail.com", "12/01/10", "1234",
//				"Brasil");
//		Provider provider1 = new Provider("provider1", "Nicolas", "Jimenez", "nj@gmail.com", "01/01/10", "abcd",
//				"description1", "website.com");
//		um.addUserPersistance(tourist1);
//		um.addUserPersistance(provider1);
//
//		ActivitiesManagement am = ActivitiesManagement.getInstance();
//		Activity activity1 = new Activity("Act1", "Description algo", 10, "110,5", "Montevideo");
//		Activity activity2 = new Activity("Act2", "Description algo", 12, "210,5", "Canelones");
//
////		Outing outing1 = new Outing("Salida1", "10/01/23", "12", 30, "Ibiza", "Imagen1");
////		Outing outing2 = new Outing("Salida2", "11/01/23", "11", 20, "Santorini", "Imagen1");
//
//		activity1.addOuting(outing1);
//		activity1.addOuting(outing2);
//
//		// Inicializando EntityManager
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba");
//		EntityManager em = emf.createEntityManager();
//
//		// Aca creamos una transaccion asociada con el entity manager y las entidades
//		// pasan a estar managed.
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		System.out.println("Voy a persistir");
//		em.persist(outing1);
//		em.persist(outing2);
//		System.out.println("Persisti");
//		tx.commit();
//
//		// Cerramos el Entity Manager. Es importante para que cierre las conexiones con
//		// la base de datos.
//		em.close();
//		emf.close();
//
//		am.addActivityPersistence(provider1, activity1);
//		am.addActivityPersistence(provider1, activity2);
//
//	}

}
