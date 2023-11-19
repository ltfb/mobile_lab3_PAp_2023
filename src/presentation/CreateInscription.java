package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.InscriptionException;
import exceptions.OutingException;
import exceptions.UserException;
import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.DataTypeTourist;

@SuppressWarnings("serial")
public class CreateInscription extends JInternalFrame {

	private JTextField salNombre;
	private JTextField salFecha;
	private JTextField salHora;
	private JTextField salDisponibilidad;
	private JTextField turNickname;
	private JTextField turNombre;
	private JTextField turApellido;
	private JTextField turEmail;
	private JTextField insCantTuristas;
	private JTextField insFecha;
	private IUser iuser;
	private IActivity iact;
	private IOuting iout;
	private IInscription iins;
	private JTextField actNombre;
	private JTextField actDuracion;
	private JTextField actCosto;
	private DataTypeTourist touName;
	private DataTypeOuting outName;
	private DataTypeActivity actName;
	private JComboBox<DataTypeOuting> salComboBox;
	private JComboBox<DataTypeTourist> turComboBox;
	private JComboBox<DataTypeActivity> actComboBox;

	public CreateInscription(IOuting IOUT, IActivity IACT, IInscription IINS, IUser IUSER) {
		iuser = IUSER;
		iact = IACT;
		iout = IOUT;
		iins = IINS;

		setTitle("Inscripcion");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 560, 503);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 70, 116, 70, 114, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		JLabel lblSelecActividad = new JLabel("Selec. Actividad");
		GridBagConstraints gbc_lblSelecActividad = new GridBagConstraints();
		gbc_lblSelecActividad.anchor = GridBagConstraints.EAST;
		gbc_lblSelecActividad.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecActividad.gridx = 0;
		gbc_lblSelecActividad.gridy = 1;
		getContentPane().add(lblSelecActividad, gbc_lblSelecActividad);

		actComboBox = new JComboBox<DataTypeActivity>();
		actComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actName = (DataTypeActivity) actComboBox.getSelectedItem();
				if (actName != null) {
					actNombre.setText(actName.getName());
					actDuracion.setText(Integer.toString(actName.getDuration()));
					actCosto.setText(actName.getCost());
					loadOutings();
				}
			}
		});
		GridBagConstraints gbc_actComboBox = new GridBagConstraints();
		gbc_actComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_actComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_actComboBox.gridx = 1;
		gbc_actComboBox.gridy = 1;
		getContentPane().add(actComboBox, gbc_actComboBox);

		JLabel lblSalidas = new JLabel("Selec. Salida:");
		GridBagConstraints gbc_lblSalidas = new GridBagConstraints();
		gbc_lblSalidas.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalidas.gridx = 2;
		gbc_lblSalidas.gridy = 1;
		getContentPane().add(lblSalidas, gbc_lblSalidas);

		salComboBox = new JComboBox<DataTypeOuting>();
		salComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outName = (DataTypeOuting) salComboBox.getSelectedItem();
				if (outName != null) {
					salNombre.setText(outName.getName());
					salFecha.setText(outName.getDate());
					salHora.setText(outName.getHour());
					salDisponibilidad.setText(Integer.toString(outName.getFreePlaces()));
				}
			}
		});

		GridBagConstraints gbc_salComboBox = new GridBagConstraints();
		gbc_salComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_salComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_salComboBox.gridx = 3;
		gbc_salComboBox.gridy = 1;
		getContentPane().add(salComboBox, gbc_salComboBox);

		JLabel lblNombreAct = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombreAct = new GridBagConstraints();
		gbc_lblNombreAct.anchor = GridBagConstraints.EAST;
		gbc_lblNombreAct.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreAct.gridx = 0;
		gbc_lblNombreAct.gridy = 2;
		getContentPane().add(lblNombreAct, gbc_lblNombreAct);

		actNombre = new JTextField();
		actNombre.setEditable(false);
		GridBagConstraints gbc_actNombre = new GridBagConstraints();
		gbc_actNombre.insets = new Insets(0, 0, 5, 5);
		gbc_actNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_actNombre.gridx = 1;
		gbc_actNombre.gridy = 2;
		getContentPane().add(actNombre, gbc_actNombre);
		actNombre.setColumns(10);

		JLabel lblNombreSal = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombreSal = new GridBagConstraints();
		gbc_lblNombreSal.anchor = GridBagConstraints.EAST;
		gbc_lblNombreSal.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreSal.gridx = 2;
		gbc_lblNombreSal.gridy = 2;
		getContentPane().add(lblNombreSal, gbc_lblNombreSal);

		salNombre = new JTextField();
		salNombre.setEditable(false);
		GridBagConstraints gbc_salNombre = new GridBagConstraints();
		gbc_salNombre.insets = new Insets(0, 0, 5, 5);
		gbc_salNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_salNombre.gridx = 3;
		gbc_salNombre.gridy = 2;
		getContentPane().add(salNombre, gbc_salNombre);
		salNombre.setColumns(10);

		JLabel lblDuracion = new JLabel("Duracion:");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.EAST;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 0;
		gbc_lblDuracion.gridy = 3;
		getContentPane().add(lblDuracion, gbc_lblDuracion);

		actDuracion = new JTextField();
		actDuracion.setEditable(false);
		GridBagConstraints gbc_actDuracion = new GridBagConstraints();
		gbc_actDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_actDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_actDuracion.gridx = 1;
		gbc_actDuracion.gridy = 3;
		getContentPane().add(actDuracion, gbc_actDuracion);
		actDuracion.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 3;
		getContentPane().add(lblFecha, gbc_lblFecha);

		salFecha = new JTextField();
		salFecha.setEditable(false);
		GridBagConstraints gbc_salFecha = new GridBagConstraints();
		gbc_salFecha.insets = new Insets(0, 0, 5, 5);
		gbc_salFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_salFecha.gridx = 3;
		gbc_salFecha.gridy = 3;
		getContentPane().add(salFecha, gbc_salFecha);
		salFecha.setColumns(10);

		JLabel lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 4;
		getContentPane().add(lblCosto, gbc_lblCosto);

		actCosto = new JTextField();
		actCosto.setEditable(false);
		GridBagConstraints gbc_actCosto = new GridBagConstraints();
		gbc_actCosto.insets = new Insets(0, 0, 5, 5);
		gbc_actCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_actCosto.gridx = 1;
		gbc_actCosto.gridy = 4;
		getContentPane().add(actCosto, gbc_actCosto);
		actCosto.setColumns(10);

		JLabel lblHora = new JLabel("Hora:");
		GridBagConstraints gbc_lblHora = new GridBagConstraints();
		gbc_lblHora.anchor = GridBagConstraints.EAST;
		gbc_lblHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblHora.gridx = 2;
		gbc_lblHora.gridy = 4;
		getContentPane().add(lblHora, gbc_lblHora);

		salHora = new JTextField();
		salHora.setEditable(false);
		GridBagConstraints gbc_salHora = new GridBagConstraints();
		gbc_salHora.insets = new Insets(0, 0, 5, 5);
		gbc_salHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_salHora.gridx = 3;
		gbc_salHora.gridy = 4;
		getContentPane().add(salHora, gbc_salHora);
		salHora.setColumns(10);

		JLabel lblLugaresLibres = new JLabel("Disponible:");
		GridBagConstraints gbc_lblLugaresLibres = new GridBagConstraints();
		gbc_lblLugaresLibres.anchor = GridBagConstraints.EAST;
		gbc_lblLugaresLibres.insets = new Insets(0, 0, 5, 5);
		gbc_lblLugaresLibres.gridx = 2;
		gbc_lblLugaresLibres.gridy = 5;
		getContentPane().add(lblLugaresLibres, gbc_lblLugaresLibres);

		salDisponibilidad = new JTextField();
		salDisponibilidad.setEditable(false);
		GridBagConstraints gbc_salDisponibilidad = new GridBagConstraints();
		gbc_salDisponibilidad.insets = new Insets(0, 0, 5, 5);
		gbc_salDisponibilidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_salDisponibilidad.gridx = 3;
		gbc_salDisponibilidad.gridy = 5;
		getContentPane().add(salDisponibilidad, gbc_salDisponibilidad);
		salDisponibilidad.setColumns(10);

		JLabel lblTuristas = new JLabel("Selec. Turista:");
		GridBagConstraints gbc_lblTuristas = new GridBagConstraints();
		gbc_lblTuristas.insets = new Insets(0, 0, 5, 5);
		gbc_lblTuristas.gridx = 0;
		gbc_lblTuristas.gridy = 7;
		getContentPane().add(lblTuristas, gbc_lblTuristas);

		turComboBox = new JComboBox<DataTypeTourist>();
		turComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				touName = (DataTypeTourist) turComboBox.getSelectedItem();
				if (touName != null) {
					turNickname.setText(touName.getNickname());
					;
					turNombre.setText(touName.getName());
					;
					turApellido.setText(touName.getLastName());
					;
					turEmail.setText(touName.getEmail());
					;
				}
			}
		});
		GridBagConstraints gbc_turComboBox = new GridBagConstraints();
		gbc_turComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_turComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_turComboBox.gridx = 1;
		gbc_turComboBox.gridy = 7;
		getContentPane().add(turComboBox, gbc_turComboBox);

		JLabel lblInscripcion = new JLabel("Inscripcion:");
		GridBagConstraints gbc_lblInscripcion = new GridBagConstraints();
		gbc_lblInscripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInscripcion.gridx = 2;
		gbc_lblInscripcion.gridy = 7;
		getContentPane().add(lblInscripcion, gbc_lblInscripcion);

		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 8;
		getContentPane().add(lblNickname, gbc_lblNickname);

		turNickname = new JTextField();
		turNickname.setEditable(false);
		GridBagConstraints gbc_turNickname = new GridBagConstraints();
		gbc_turNickname.insets = new Insets(0, 0, 5, 5);
		gbc_turNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_turNickname.gridx = 1;
		gbc_turNickname.gridy = 8;
		getContentPane().add(turNickname, gbc_turNickname);
		turNickname.setColumns(10);

		JLabel lblEmail = new JLabel("Nombre:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 9;
		getContentPane().add(lblEmail, gbc_lblEmail);

		turNombre = new JTextField();
		turNombre.setEditable(false);
		GridBagConstraints gbc_turNombre = new GridBagConstraints();
		gbc_turNombre.insets = new Insets(0, 0, 5, 5);
		gbc_turNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_turNombre.gridx = 1;
		gbc_turNombre.gridy = 9;
		getContentPane().add(turNombre, gbc_turNombre);
		turNombre.setColumns(10);

		JLabel lblCantTuristas = new JLabel("Cant. Turistas:");
		GridBagConstraints gbc_lblCantTuristas = new GridBagConstraints();
		gbc_lblCantTuristas.anchor = GridBagConstraints.EAST;
		gbc_lblCantTuristas.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantTuristas.gridx = 2;
		gbc_lblCantTuristas.gridy = 9;
		getContentPane().add(lblCantTuristas, gbc_lblCantTuristas);

		insCantTuristas = new JTextField();
		GridBagConstraints gbc_insCantTuristas = new GridBagConstraints();
		gbc_insCantTuristas.insets = new Insets(0, 0, 5, 5);
		gbc_insCantTuristas.fill = GridBagConstraints.HORIZONTAL;
		gbc_insCantTuristas.gridx = 3;
		gbc_insCantTuristas.gridy = 9;
		getContentPane().add(insCantTuristas, gbc_insCantTuristas);
		insCantTuristas.setColumns(10);

		JLabel lblNewLabel = new JLabel("Apellido:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 10;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		turApellido = new JTextField();
		turApellido.setEditable(false);
		GridBagConstraints gbc_turApellido = new GridBagConstraints();
		gbc_turApellido.insets = new Insets(0, 0, 5, 5);
		gbc_turApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_turApellido.gridx = 1;
		gbc_turApellido.gridy = 10;
		getContentPane().add(turApellido, gbc_turApellido);
		turApellido.setColumns(10);

		JLabel lblFechaIns = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFechaIns = new GridBagConstraints();
		gbc_lblFechaIns.anchor = GridBagConstraints.EAST;
		gbc_lblFechaIns.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaIns.gridx = 2;
		gbc_lblFechaIns.gridy = 10;
		getContentPane().add(lblFechaIns, gbc_lblFechaIns);

		insFecha = new JTextField();
		GridBagConstraints gbc_insFecha = new GridBagConstraints();
		gbc_insFecha.insets = new Insets(0, 0, 5, 5);
		gbc_insFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_insFecha.gridx = 3;
		gbc_insFecha.gridy = 10;
		getContentPane().add(insFecha, gbc_insFecha);
		insFecha.setColumns(10);

		JLabel lblEmail_1 = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1.gridx = 0;
		gbc_lblEmail_1.gridy = 11;
		getContentPane().add(lblEmail_1, gbc_lblEmail_1);

		turEmail = new JTextField();
		turEmail.setEditable(false);
		GridBagConstraints gbc_turEmail = new GridBagConstraints();
		gbc_turEmail.insets = new Insets(0, 0, 5, 5);
		gbc_turEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_turEmail.gridx = 1;
		gbc_turEmail.gridy = 11;
		getContentPane().add(turEmail, gbc_turEmail);
		turEmail.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanForm();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 13;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkForm()) {
					try {
						int costInscription = Integer.parseInt(insCantTuristas.getText())
								* Integer.parseInt(actCosto.getText());
						System.out.println("1: costo Inscripcion" + costInscription);
						System.out.println("2: cantidad de turistas" + insCantTuristas.getText());
						iins.addInscription(actName.getName(), touName.getNickname(), outName.getName(),
								Integer.parseInt(insCantTuristas.getText()), insFecha.getText(), costInscription);
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "La Inscripcion se ha creado con éxito",
								"Crear Inscripcion", JOptionPane.INFORMATION_MESSAGE);
						cleanForm();
						setVisible(false);
					} catch (InscriptionException e0) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, e0.getMessage(), "ERROR : Inscripcion",
								JOptionPane.ERROR_MESSAGE);
					} catch (ActivityException e1) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "ERROR : Actividades",
								JOptionPane.ERROR_MESSAGE);
					} catch (OutingException e2) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, e2.getMessage(), "ERROR : Salidas",
								JOptionPane.ERROR_MESSAGE);
					} catch (UserException e3) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, e3.getMessage(), "ERROR : Usuarios",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		GridBagConstraints gbc_btnInscribir = new GridBagConstraints();
		gbc_btnInscribir.insets = new Insets(0, 0, 5, 5);
		gbc_btnInscribir.gridx = 3;
		gbc_btnInscribir.gridy = 13;
		getContentPane().add(btnInscribir, gbc_btnInscribir);

	}

	public void loadUsers() {
		DefaultComboBoxModel<DataTypeTourist> model; // Este modelo se crea para carga el combobox
		model = new DefaultComboBoxModel<DataTypeTourist>(this.iuser.listArrayTourists());
		this.turComboBox.setModel(model);
		this.turComboBox.setSelectedIndex(-1);
	};

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model; // Este modelo se crea para carga el combobox
		model = new DefaultComboBoxModel<DataTypeActivity>(this.iact.listTouristActivities(ActivityState.CONFIRMADA));
		this.actComboBox.setModel(model);
		this.actComboBox.setSelectedIndex(-1);
	};

	public void loadOutings() {
		DefaultComboBoxModel<DataTypeOuting> model; // Este modelo se crea para carga el combobox
		model = new DefaultComboBoxModel<DataTypeOuting>(this.iout.getOutings(actNombre.getText()));
		this.salComboBox.setModel(model);
		this.salComboBox.setSelectedIndex(-1);
	};

	private boolean checkForm() {
		if (this.actComboBox.getSelectedIndex() == -1 || this.salComboBox.getSelectedIndex() == -1
				|| this.turComboBox.getSelectedIndex() == -1 || this.insCantTuristas.getText().isEmpty()
				|| this.insFecha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Inscripcion a Salida Turistica",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(this.insCantTuristas.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de turistas debe ser un numero",
					"Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Integer.parseInt(this.insCantTuristas.getText()) <= 0) {
			JOptionPane.showMessageDialog(this, "La cantidad de turistas debe ser mayor a 0",
					"Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void cleanForm() {
		this.salNombre.setText("");
		this.salFecha.setText("");
		this.salHora.setText("");
		this.salDisponibilidad.setText("");
		this.turNickname.setText("");
		this.turNombre.setText("");

		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>();
		DefaultComboBoxModel<DataTypeActivity> model1;
		model1 = new DefaultComboBoxModel<DataTypeActivity>();
		DefaultComboBoxModel<DataTypeTourist> model2;
		model2 = new DefaultComboBoxModel<DataTypeTourist>();
		this.salComboBox.setModel(model); // Seteo el combo de Outings en vacío hasta que no seleccione una Activity
		this.actComboBox.setModel(model1);
		this.turComboBox.setModel(model2);

		this.turApellido.setText("");
		this.turEmail.setText("");
		this.insCantTuristas.setText("");
		this.insFecha.setText("");
		this.actNombre.setText("");
		this.actDuracion.setText("");
		this.actCosto.setText("");
	};
}
