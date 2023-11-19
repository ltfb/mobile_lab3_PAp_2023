package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.OutingException;
import logic.DataTypeActivity;
import logic.DataTypeOuting;

@SuppressWarnings("serial")
public class CreateOuting extends JInternalFrame {

	private IOuting outingController;
	private IActivity activityController;

	// Los componentes gráficos se agregan como atributos de la clase
	// para facilitar su acceso desde diferentes métodos de la misma.
	private JTextField textFieldName;
	private JTextField textFieldDate;
	private JTextField textFieldHour;
	private JTextField textFieldPlace;
	private JTextField textFieldMaxTouristNumber;
	private JLabel lblChooseActivity;
	private JLabel lblEnterName;
	private JLabel lblEnterDate;
	private JLabel lblEnterHour;
	private JLabel lblEnterPlace;
	private JLabel lblEnterMaxTouristNumber;

	private JComboBox<DataTypeActivity> comboBoxActivities;

	JButton acceptButton;
	JButton cancelButton;

	public CreateOuting(IOuting iOUT, IActivity iACT) {
		// Se inicializa con el controlador de Salidas
		outingController = iOUT;
		activityController = iACT;

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar una Salida");
		setBounds(10, 40, 512, 328);

		// En este caso utilizaremos el GridBagLayout que permite armar una grilla
		// en donde las filas y columnas no son uniformes.
		// Conviene trabajar este componente desde la vista de diseño gráfico y sólo
		// manipular los valores para ajustar alguna cosa.
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 140, 137, 0 };
		gridBagLayout.rowHeights = new int[] { 25, 30, 30, 30, 0, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblChooseActivity = new JLabel("Actividades Disponibles:");
		GridBagConstraints gbc_lblChooseActivity = new GridBagConstraints();
		gbc_lblChooseActivity.anchor = GridBagConstraints.EAST;
		gbc_lblChooseActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseActivity.gridx = 0;
		gbc_lblChooseActivity.gridy = 0;
		getContentPane().add(lblChooseActivity, gbc_lblChooseActivity);

		comboBoxActivities = new JComboBox<DataTypeActivity>();
		GridBagConstraints gbc_comboBoxActivities = new GridBagConstraints();
		gbc_comboBoxActivities.gridwidth = 2;
		gbc_comboBoxActivities.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxActivities.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActivities.gridx = 1;
		gbc_comboBoxActivities.gridy = 0;
		getContentPane().add(comboBoxActivities, gbc_comboBoxActivities);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// el nombre de la actividad. El texto está alineado horizontalmente a la
		// derecha para
		// que quede casi pegado al campo de texto.
		lblEnterName = new JLabel("Nombre de la salida:");
		lblEnterName.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnterName = new GridBagConstraints();
		gbc_lblEnterName.fill = GridBagConstraints.BOTH;
		gbc_lblEnterName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterName.gridx = 0;
		gbc_lblEnterName.gridy = 1;
		getContentPane().add(lblEnterName, gbc_lblEnterName);

		// Una campo de texto (JTextField) para ingresar el nombre de la actividad.
		// Por defecto es posible ingresar cualquier string.
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 2;
		gbc_textFieldName.fill = GridBagConstraints.BOTH;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la fecha de Salida. El texto está alineado horizontalmente a la
		// derecha para que quede casi pegado al campo de texto.
		lblEnterDate = new JLabel("Fecha:");
		lblEnterDate.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnterDate = new GridBagConstraints();
		gbc_lblEnterDate.fill = GridBagConstraints.BOTH;
		gbc_lblEnterDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterDate.gridx = 0;
		gbc_lblEnterDate.gridy = 2;
		getContentPane().add(lblEnterDate, gbc_lblEnterDate);

		// Una campo de texto (JTextField) para ingresar la fecha de Salida.
		textFieldDate = new JTextField();
		GridBagConstraints gbc_textFieldDate = new GridBagConstraints();
		gbc_textFieldDate.gridwidth = 2;
		gbc_textFieldDate.fill = GridBagConstraints.BOTH;
		gbc_textFieldDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDate.gridx = 1;
		gbc_textFieldDate.gridy = 2;
		getContentPane().add(textFieldDate, gbc_textFieldDate);
		textFieldDate.setColumns(10);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la hora de Salida. El texto está alineado horizontalmente a la
		// derecha para que quede casi pegado al campo de texto.
		this.lblEnterHour = new JLabel("Hora:");
		this.lblEnterHour.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnterHour = new GridBagConstraints();
		gbc_lblEnterHour.fill = GridBagConstraints.BOTH;
		gbc_lblEnterHour.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterHour.gridx = 0;
		gbc_lblEnterHour.gridy = 3;
		getContentPane().add(this.lblEnterHour, gbc_lblEnterHour);

		// Una campo de texto (JTextField) para ingresar la hora de Salida.
		this.textFieldHour = new JTextField();
		GridBagConstraints gbc_textFieldHour = new GridBagConstraints();
		gbc_textFieldHour.gridwidth = 2;
		gbc_textFieldHour.fill = GridBagConstraints.BOTH;
		gbc_textFieldHour.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHour.gridx = 1;
		gbc_textFieldHour.gridy = 3;
		getContentPane().add(this.textFieldHour, gbc_textFieldHour);
		this.textFieldHour.setColumns(10);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// el lugar de Salida. El texto está alineado horizontalmente a la
		// derecha para que quede casi pegado al campo de texto.
		this.lblEnterPlace = new JLabel("Lugar:");
		this.lblEnterPlace.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnterPlace = new GridBagConstraints();
		gbc_lblEnterPlace.fill = GridBagConstraints.BOTH;
		gbc_lblEnterPlace.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterPlace.gridx = 0;
		gbc_lblEnterPlace.gridy = 4;
		getContentPane().add(this.lblEnterPlace, gbc_lblEnterPlace);

		// Una campo de texto (JTextField) para ingresar el lugar de Salida.
		this.textFieldPlace = new JTextField();
		GridBagConstraints gbc_textFieldPlace = new GridBagConstraints();
		gbc_textFieldPlace.gridwidth = 2;
		gbc_textFieldPlace.fill = GridBagConstraints.BOTH;
		gbc_textFieldPlace.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPlace.gridx = 1;
		gbc_textFieldPlace.gridy = 4;
		getContentPane().add(this.textFieldPlace, gbc_textFieldPlace);
		this.textFieldPlace.setColumns(10);

		lblEnterMaxTouristNumber = new JLabel("Cantidad maxima de turistas:");
		GridBagConstraints gbc_lblEnterMaxTouristNumber = new GridBagConstraints();
		gbc_lblEnterMaxTouristNumber.anchor = GridBagConstraints.EAST;
		gbc_lblEnterMaxTouristNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterMaxTouristNumber.gridx = 0;
		gbc_lblEnterMaxTouristNumber.gridy = 5;
		getContentPane().add(lblEnterMaxTouristNumber, gbc_lblEnterMaxTouristNumber);

		textFieldMaxTouristNumber = new JTextField();
		GridBagConstraints gbc_textFieldMaxTouristNumber = new GridBagConstraints();
		gbc_textFieldMaxTouristNumber.gridwidth = 2;
		gbc_textFieldMaxTouristNumber.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMaxTouristNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMaxTouristNumber.gridx = 1;
		gbc_textFieldMaxTouristNumber.gridy = 5;
		getContentPane().add(textFieldMaxTouristNumber, gbc_textFieldMaxTouristNumber);
		textFieldMaxTouristNumber.setColumns(10);

		// Un botón (JButton) con un evento asociado que permite registrar el usuario.
		// Dado que el código de registro tiene cierta complejidad, conviene delegarlo
		// a otro método en lugar de incluirlo directamente de el método actionPerformed
		this.acceptButton = new JButton("Aceptar");
		this.acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdOutingRegistrationActionPerformed(arg0);
			}
		});

		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.fill = GridBagConstraints.BOTH;
		gbc_acceptButton.insets = new Insets(0, 0, 0, 5);
		gbc_acceptButton.gridx = 1;
		gbc_acceptButton.gridy = 6;
		getContentPane().add(this.acceptButton, gbc_acceptButton);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanForm();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 6;
		getContentPane().add(this.cancelButton, gbc_cancelButton);
	}

	// Este método es invocado al querer registrar una Salida, funcionalidad
	// provista por la operación del sistema outingRegistration().
	// Previamente se hace una verificación de los campos, particularmente que no
	// sean vacíos.
	// Tanto en caso de que haya un error (de verificación o de registro) o no, se
	// despliega
	// un mensaje utilizando un panel de mensaje (JOptionPane).
	protected void cmdOutingRegistrationActionPerformed(ActionEvent arg0) {

		if (checkForm()) {
			boolean successRegistration = false;

			// Obtengo datos de los controles Swing
			String outName = this.textFieldName.getText();
			String outDate = this.textFieldDate.getText();
			String outHour = this.textFieldHour.getText();
			String outPlace = this.textFieldPlace.getText();
			int outMaxTourist = Integer.parseInt(this.textFieldMaxTouristNumber.getText());

			LocalDateTime entryDate = LocalDateTime.now();

			DataTypeOuting dtOuting = new DataTypeOuting(outName, outDate, outHour, outMaxTourist, outPlace, entryDate);

			try {
				String actName = ((DataTypeActivity) this.comboBoxActivities.getSelectedItem()).getName();
				outingController.outingRegistration(actName, dtOuting);
				successRegistration = true;

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La Salida se ha creado con éxito", "Registrar Salida",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ActivityException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Salida", JOptionPane.ERROR_MESSAGE);
			} catch (OutingException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Salida", JOptionPane.ERROR_MESSAGE);
			}

			if (successRegistration) {
				// Limpio el internal frame antes de cerrar la ventana
				cleanForm();
				setVisible(false);
			}
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	// Este tipo de chequeos se puede realizar de otras formas y con otras librerías
	// de Java,
	// por ejemplo impidiendo que se escriban caracteres no numéricos al momento de
	// escribir en
	// en el campo de la cantidad maxima de turistas, o mostrando un mensaje de
	// error apenas el foco pasa
	// a otro campo.
	private boolean checkForm() {
		// Obtengo datos de los controles Swing
		int activity = this.comboBoxActivities.getSelectedIndex();
		String outName = this.textFieldName.getText();
		String outDate = this.textFieldDate.getText();
		String outHour = this.textFieldHour.getText();
		String outPlace = this.textFieldPlace.getText();
		String outMaxTourist = this.textFieldMaxTouristNumber.getText();

		if ((activity == -1) || outName.isEmpty() || outDate.isEmpty() || outHour.isEmpty() || outPlace.isEmpty()
				|| outMaxTourist.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Salida",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(outMaxTourist);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad maxima de turistas debe ser un numero", "Registrar Salida",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Metodo que permite cargar un nuevo modelo para el combo con la informacion
	// actualizada de usuarios, provista por la operacion del sistema getUsuarios().
	// Se invoca el metodo antes de hacer visible el JInternalFrame
	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model; // Este modelo se crea para carga el combo
		model = new DefaultComboBoxModel<DataTypeActivity>(this.activityController.listTouristActivities(ActivityState.CONFIRMADA));
		this.comboBoxActivities.setModel(model);
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void cleanForm() {
		this.comboBoxActivities.setSelectedIndex(-1);
		this.textFieldName.setText("");
		this.textFieldDate.setText("");
		this.textFieldHour.setText("");
		this.textFieldPlace.setText("");
		this.textFieldMaxTouristNumber.setText("");
	}

}