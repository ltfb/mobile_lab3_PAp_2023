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

import exceptions.ActivityException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeProvider;

@SuppressWarnings("serial")
public class CreateActivity extends JInternalFrame {

	private IActivity actController;
	private IUser userController;

	private JLabel lblChooseProvider;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblCost;
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JTextField textFieldDuration;
	private JTextField textFieldCost;
	private JTextField textFieldCity;
	private JComboBox<DataTypeProvider> comboBoxProviders;
	private JLabel lblDuration;
	private JLabel lblCity;

	public CreateActivity(IActivity iAC, IUser iUC) {
		// Se inicializa con el controlador de actividades
		actController = iAC;
		userController = iUC;

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar una Actividad");
		setBounds(100, 100, 500, 300);

		// En este caso utilizaremos el GridBagLayout que permite armar una grilla
		// en donde las filas y columnas no son uniformes.
		// Conviene trabajar este componente desde la vista de diseño gráfico y sólo
		// manipular los valores para ajustar alguna cosa.
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 135, 219, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 51, 30 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse
		// el nombre de la actividad. El texto está alineado horizontalmente a la
		// derecha para
		// que quede casi pegado al campo de texto.
		lblChooseProvider = new JLabel("Escoja proveedor:");
		lblChooseProvider.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblChooseProvider = new GridBagConstraints();
		gbc_lblChooseProvider.anchor = GridBagConstraints.EAST;
		gbc_lblChooseProvider.fill = GridBagConstraints.VERTICAL;
		gbc_lblChooseProvider.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseProvider.gridx = 0;
		gbc_lblChooseProvider.gridy = 0;
		getContentPane().add(lblChooseProvider, gbc_lblChooseProvider);

		comboBoxProviders = new JComboBox<DataTypeProvider>();
		GridBagConstraints gbc_comboBoxProviders = new GridBagConstraints();
		gbc_comboBoxProviders.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxProviders.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxProviders.gridx = 1;
		gbc_comboBoxProviders.gridy = 0;
		getContentPane().add(comboBoxProviders, gbc_comboBoxProviders);

		this.lblName = new JLabel("Nombre");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		getContentPane().add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		this.lblDescription = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 2;
		getContentPane().add(lblDescription, gbc_lblDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 2;
		getContentPane().add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

        this.lblDuration = new JLabel("Duracion (hs)");
        GridBagConstraints gbc_lblDuration = new GridBagConstraints();
        gbc_lblDuration.anchor = GridBagConstraints.EAST;
        gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
        gbc_lblDuration.gridx = 0;
        gbc_lblDuration.gridy = 3;
        getContentPane().add(this.lblDuration, gbc_lblDuration);

		this.textFieldDuration = new JTextField();
		GridBagConstraints gbc_textFieldDuration = new GridBagConstraints();
		gbc_textFieldDuration.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDuration.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuration.gridx = 1;
		gbc_textFieldDuration.gridy = 3;
		getContentPane().add(textFieldDuration, gbc_textFieldDuration);
		textFieldDuration.setColumns(10);

        this.lblCost = new JLabel("Costo ($)");
        GridBagConstraints gbc_lblCost = new GridBagConstraints();
        gbc_lblCost.anchor = GridBagConstraints.EAST;
        gbc_lblCost.insets = new Insets(0, 0, 5, 5);
        gbc_lblCost.gridx = 0;
        gbc_lblCost.gridy = 4;
        getContentPane().add(lblCost, gbc_lblCost);

		this.textFieldCost = new JTextField();
		GridBagConstraints gbc_textFieldCost = new GridBagConstraints();
		gbc_textFieldCost.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCost.gridx = 1;
		gbc_textFieldCost.gridy = 4;
		getContentPane().add(textFieldCost, gbc_textFieldCost);
		textFieldCost.setColumns(10);

		this.lblCity = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 5;
		getContentPane().add(this.lblCity, gbc_lblCity);

		this.textFieldCity = new JTextField();
		GridBagConstraints gbc_textFieldCity = new GridBagConstraints();
		gbc_textFieldCity.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCity.gridx = 1;
		gbc_textFieldCity.gridy = 5;
		getContentPane().add(this.textFieldCity, gbc_textFieldCity);
		textFieldCity.setColumns(10);

		JButton btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdActivityRegistrationActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.anchor = GridBagConstraints.EAST;
		gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccept.gridx = 0;
		gbc_btnAccept.gridy = 6;
		getContentPane().add(btnAccept, gbc_btnAccept);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanForm();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 6;
		getContentPane().add(btnCancel, gbc_btnCancel);

	}

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeProvider> model; // Este modelo se crea para carga el combo
		model = new DefaultComboBoxModel<DataTypeProvider>();
		for (DataTypeProvider dtp : this.userController.listProviders()) {
			model.addElement(dtp);
		}
		this.comboBoxProviders.setModel(model);
	}

	private void cmdActivityRegistrationActionPerformed(ActionEvent e) {
		if (checkForm()) {
			// Obtengo datos de los controles Swing
			boolean successRegistration = false;
			DataTypeProvider provider = (DataTypeProvider) this.comboBoxProviders.getSelectedItem();
			String activityName = this.textFieldName.getText();
			String description = this.textFieldDescription.getText();
			String cost = this.textFieldCost.getText();
			String city = this.textFieldCity.getText();
			String duration = this.textFieldDuration.getText();
			LocalDateTime prueba=LocalDateTime.now();
			DataTypeActivity dta = new DataTypeActivity(activityName, description, Integer.parseInt(duration), cost,
					city, prueba);
			try {
				actController.activityRegistration(provider.getNickname(), dta);
				successRegistration = true;

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La Actividad se ha creado con éxito", "Registrar Actividad",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ActivityException ae) {
				JOptionPane.showMessageDialog(this, ae.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
			} catch (UserException ue) {
				JOptionPane.showMessageDialog(this, ue.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
			}

			if (successRegistration) {
				// Limpio el internal frame antes de cerrar la ventana
				cleanForm();
				setVisible(false);
			}
		}
	}

	private boolean checkForm() {
		if (this.comboBoxProviders.getSelectedIndex() == -1 || this.textFieldName.getText().isEmpty()
				|| this.textFieldDescription.getText().isEmpty() || this.textFieldCost.getText().isEmpty()
				|| this.textFieldCity.getText().isEmpty() || this.textFieldDuration.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Actividad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(this.textFieldDuration.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La duracion debe un numero", "Registrar Actividad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void cleanForm() {
		this.comboBoxProviders.setSelectedIndex(-1);
		this.textFieldName.setText("");
		this.textFieldDescription.setText("");
		this.textFieldCost.setText("");
		this.textFieldCity.setText("");
		this.textFieldDuration.setText("");
	}

}
