package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

//import logic.DataTypeProvider;
//import logic.DataTypeTourist;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//import org.eclipse.persistence.internal.expressions.SubSelectDatabaseTable;

import exceptions.UserException;
import logic.DataTypeProvider;
import logic.DataTypeTourist;

@SuppressWarnings("serial")
public class CreateUser extends JInternalFrame {
	private IUser userController;
	private JTextField txtFieldName;
	private JTextField txtFieldLName;
	private JTextField txtFieldNick;
	private JTextField txtFieldEmail;
	private JTextField txtFieldBday;
	private JTextField txtFieldPass;
	private JTextField txtFieldDesc;
	private JTextField txtFieldWeb;
	private JTextField txtFieldNation;

	/**
	 * Create the frame.
	 */
	public CreateUser(IUser IU) {
		userController = IU;
		// cleanForm();

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar Usuario");
		setBounds(100, 100, 365, 470);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 44, 94, 74, 64, 85, 66, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblName = new JLabel("Nombre");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridwidth = 2;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);

		txtFieldName = new JTextField();
		GridBagConstraints gbc_txtFieldName = new GridBagConstraints();
		gbc_txtFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldName.gridwidth = 3;
		gbc_txtFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldName.gridx = 2;
		gbc_txtFieldName.gridy = 1;
		getContentPane().add(txtFieldName, gbc_txtFieldName);
		txtFieldName.setColumns(10);

		JLabel lblLName = new JLabel("Apellido");
		GridBagConstraints gbc_lblLName = new GridBagConstraints();
		gbc_lblLName.gridwidth = 2;
		gbc_lblLName.anchor = GridBagConstraints.EAST;
		gbc_lblLName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLName.gridx = 0;
		gbc_lblLName.gridy = 2;
		getContentPane().add(lblLName, gbc_lblLName);

		txtFieldLName = new JTextField();
		txtFieldLName.setColumns(10);
		GridBagConstraints gbc_txtFieldLName = new GridBagConstraints();
		gbc_txtFieldLName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldLName.gridwidth = 3;
		gbc_txtFieldLName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldLName.gridx = 2;
		gbc_txtFieldLName.gridy = 2;
		getContentPane().add(txtFieldLName, gbc_txtFieldLName);

		JLabel lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.gridwidth = 2;
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 3;
		getContentPane().add(lblNickname, gbc_lblNickname);

		txtFieldNick = new JTextField();
		txtFieldNick.setColumns(10);
		GridBagConstraints gbc_txtFieldNick = new GridBagConstraints();
		gbc_txtFieldNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldNick.gridwidth = 3;
		gbc_txtFieldNick.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldNick.gridx = 2;
		gbc_txtFieldNick.gridy = 3;
		getContentPane().add(txtFieldNick, gbc_txtFieldNick);

		JLabel lblEmail = new JLabel("Mail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 2;
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 4;
		getContentPane().add(lblEmail, gbc_lblEmail);

		txtFieldEmail = new JTextField();
		txtFieldEmail.setColumns(10);
		GridBagConstraints gbc_txtFieldEmail = new GridBagConstraints();
		gbc_txtFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEmail.gridwidth = 3;
		gbc_txtFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldEmail.gridx = 2;
		gbc_txtFieldEmail.gridy = 4;
		getContentPane().add(txtFieldEmail, gbc_txtFieldEmail);

		JLabel lblBirthday = new JLabel("Fec. Nacimiento");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.gridwidth = 2;
		gbc_lblBirthday.anchor = GridBagConstraints.EAST;
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 0;
		gbc_lblBirthday.gridy = 5;
		getContentPane().add(lblBirthday, gbc_lblBirthday);

		txtFieldBday = new JTextField();
		txtFieldBday.setColumns(10);
		GridBagConstraints gbc_txtFieldBday = new GridBagConstraints();
		gbc_txtFieldBday.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldBday.gridwidth = 3;
		gbc_txtFieldBday.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldBday.gridx = 2;
		gbc_txtFieldBday.gridy = 5;
		getContentPane().add(txtFieldBday, gbc_txtFieldBday);

		JLabel lblPass = new JLabel("Contraseña");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.gridwidth = 2;
		gbc_lblPass.anchor = GridBagConstraints.EAST;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 0;
		gbc_lblPass.gridy = 6;
		getContentPane().add(lblPass, gbc_lblPass);

		txtFieldPass = new JPasswordField();
		txtFieldPass.setColumns(10);
		GridBagConstraints gbc_txtFieldPass = new GridBagConstraints();
		gbc_txtFieldPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPass.gridwidth = 3;
		gbc_txtFieldPass.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPass.gridx = 2;
		gbc_txtFieldPass.gridy = 6;
		getContentPane().add(txtFieldPass, gbc_txtFieldPass);

		JRadioButton rdbtnProvider = new JRadioButton("Provedor");
		rdbtnProvider.setSelected(true);
		GridBagConstraints gbc_rdbtnProvider = new GridBagConstraints();
		gbc_rdbtnProvider.anchor = GridBagConstraints.EAST;
		gbc_rdbtnProvider.gridwidth = 2;
		gbc_rdbtnProvider.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnProvider.gridx = 0;
		gbc_rdbtnProvider.gridy = 8;
		getContentPane().add(rdbtnProvider, gbc_rdbtnProvider);

		JLabel lblDesc = new JLabel("Descripción");
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.gridwidth = 2;
		gbc_lblDesc.anchor = GridBagConstraints.EAST;
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 0;
		gbc_lblDesc.gridy = 9;
		getContentPane().add(lblDesc, gbc_lblDesc);

		txtFieldDesc = new JTextField();
		txtFieldDesc.setColumns(10);
		GridBagConstraints gbc_txtFieldDesc = new GridBagConstraints();
		gbc_txtFieldDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldDesc.gridwidth = 3;
		gbc_txtFieldDesc.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldDesc.gridx = 2;
		gbc_txtFieldDesc.gridy = 9;
		getContentPane().add(txtFieldDesc, gbc_txtFieldDesc);

		JLabel lblWeb = new JLabel("Sitio Web");
		GridBagConstraints gbc_lblWeb = new GridBagConstraints();
		gbc_lblWeb.gridwidth = 2;
		gbc_lblWeb.anchor = GridBagConstraints.EAST;
		gbc_lblWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeb.gridx = 0;
		gbc_lblWeb.gridy = 10;
		getContentPane().add(lblWeb, gbc_lblWeb);

		txtFieldWeb = new JTextField();
		txtFieldWeb.setColumns(10);
		GridBagConstraints gbc_txtFieldWeb = new GridBagConstraints();
		gbc_txtFieldWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldWeb.gridwidth = 3;
		gbc_txtFieldWeb.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldWeb.gridx = 2;
		gbc_txtFieldWeb.gridy = 10;
		getContentPane().add(txtFieldWeb, gbc_txtFieldWeb);

		JRadioButton rdbtnTourist = new JRadioButton("Turista");
		GridBagConstraints gbc_rdbtnTourist = new GridBagConstraints();
		gbc_rdbtnTourist.anchor = GridBagConstraints.EAST;
		gbc_rdbtnTourist.gridwidth = 2;
		gbc_rdbtnTourist.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTourist.gridx = 0;
		gbc_rdbtnTourist.gridy = 12;
		getContentPane().add(rdbtnTourist, gbc_rdbtnTourist);

		JLabel lblNation = new JLabel("Nacionalidad");
		GridBagConstraints gbc_lblNation = new GridBagConstraints();
		gbc_lblNation.gridwidth = 2;
		gbc_lblNation.anchor = GridBagConstraints.EAST;
		gbc_lblNation.insets = new Insets(0, 0, 5, 5);
		gbc_lblNation.gridx = 0;
		gbc_lblNation.gridy = 13;
		getContentPane().add(lblNation, gbc_lblNation);

		txtFieldNation = new JTextField();
		txtFieldNation.setEnabled(false);
		txtFieldNation.setColumns(10);
		GridBagConstraints gbc_txtFieldNation = new GridBagConstraints();
		gbc_txtFieldNation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldNation.gridwidth = 3;
		gbc_txtFieldNation.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldNation.gridx = 2;
		gbc_txtFieldNation.gridy = 13;
		getContentPane().add(txtFieldNation, gbc_txtFieldNation);
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 2;
		gbc_comboBoxAct.gridy = 1;

		// Agrego los radiobutton a un grupo para que se deseleccione uno al seleccionar
		// otro
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnProvider);
		bg.add(rdbtnTourist);

		JButton btnSave = new JButton("Guardar");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 15;
		getContentPane().add(btnSave, gbc_btnSave);

		btnSave.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				try {
					addUser(rdbtnTourist.isSelected(), userController);
				} catch (UserException ex) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, ex, "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanForm();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridwidth = 2;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 15;
		getContentPane().add(btnCancel, gbc_btnCancel);

		rdbtnProvider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnProvider.isSelected()) {
					txtFieldDesc.setEnabled(true);
					txtFieldWeb.setEnabled(true);
					txtFieldNation.setEnabled(false);
				} else {
					txtFieldDesc.setEnabled(false);
					txtFieldWeb.setEnabled(false);
					txtFieldNation.setEnabled(true);
				}
			}
		});

		rdbtnTourist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnProvider.isSelected()) {
					txtFieldDesc.setEnabled(true);
					txtFieldWeb.setEnabled(true);
					txtFieldNation.setEnabled(false);
				} else {
					txtFieldDesc.setEnabled(false);
					txtFieldWeb.setEnabled(false);
					txtFieldNation.setEnabled(true);
				}
			}
		});
	}

	private boolean checkForm() {
		String bday = txtFieldBday.getText();
		String desc = txtFieldDesc.getText();
		String email = txtFieldEmail.getText();
		String lname = txtFieldLName.getText();
		String name = txtFieldName.getText();
		String nation = txtFieldNation.getText();
		String nick = txtFieldNick.getText();
		String pass = txtFieldPass.getText();

		boolean EmptyProvider = (txtFieldDesc.isEnabled() && (bday.isEmpty() || email.isEmpty() || lname.isEmpty()
				|| name.isEmpty() || nick.isEmpty() || pass.isEmpty() || desc.isEmpty()));
		boolean EmptyTourist = (txtFieldNation.isEnabled() && (bday.isEmpty() || email.isEmpty() || lname.isEmpty()
				|| name.isEmpty() || nick.isEmpty() || pass.isEmpty() || nation.isEmpty()));

		if (EmptyProvider || EmptyTourist) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacíos", "Crear Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void addUser(boolean isTourist, IUser UC) throws UserException {
		if (checkForm()) {
			if (isTourist) {
				DataTypeTourist tourist = new DataTypeTourist(txtFieldNick.getText(), txtFieldName.getText(),
						txtFieldLName.getText(), txtFieldEmail.getText(), txtFieldBday.getText(),
						txtFieldPass.getText(), txtFieldNation.getText());
				try {
					UC.addTourist(tourist);
					JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Crear Usuario",
							JOptionPane.INFORMATION_MESSAGE);
					cleanForm();
					setVisible(false);
				} catch (UserException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Crear Usuario", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				DataTypeProvider provider = new DataTypeProvider(txtFieldNick.getText(), txtFieldName.getText(),
						txtFieldLName.getText(), txtFieldEmail.getText(), txtFieldBday.getText(),
						txtFieldPass.getText(), txtFieldDesc.getText(), txtFieldWeb.getText());
				try {
					UC.addProvider(provider);
					JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Crear Usuario",
							JOptionPane.INFORMATION_MESSAGE);
					cleanForm();
					setVisible(false);
				} catch (UserException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Crear Usuario", JOptionPane.ERROR_MESSAGE);
				}
			}
			List<DataTypeProvider> prov = this.userController.listProviders();
			for (DataTypeProvider p : prov) {
				System.out.println("Nick: " + p.getNickname() + " Email: " + p.getEmail());
			}
			List<DataTypeTourist> tour = this.userController.listTourists();
			for (DataTypeTourist t : tour) {
				System.out.println("Nick: " + t.getNickname() + " Email: " + t.getEmail());
			}
		}
	}

	private void cleanForm() {
		txtFieldBday.setText("");
		txtFieldDesc.setText("");
		txtFieldEmail.setText("");
		txtFieldLName.setText("");
		txtFieldName.setText("");
		txtFieldNation.setText("");
		txtFieldNick.setText("");
		txtFieldPass.setText("");
		txtFieldWeb.setText("");
	}
}