package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logic.DataTypeProvider;
import logic.DataTypeTourist;
import logic.DataTypeUser;

@SuppressWarnings("serial")
public class ConsultUser extends JInternalFrame {

	IUser userController;

	private ProviderData providerDataInternalFrame;
	private TouristData touristDataInternalFrame;

	JComboBox<DataTypeUser> comboBoxUsers;

	/**
	 * Create the frame.
	 */
	public ConsultUser(IUser iUse, IOuting iOut) {

		userController = iUse;

		setResizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consultar Usuario");
		setMaximizable(true);
		setClosable(true);

		setBounds(100, 100, 438, 325);

		this.providerDataInternalFrame = new ProviderData(iUse, iOut);
		providerDataInternalFrame.setBounds(-6, 6, 414, 279);
		this.providerDataInternalFrame.setTitle("Proveedor");
		this.providerDataInternalFrame.setClosable(true);
		this.providerDataInternalFrame.setVisible(false);
		getContentPane().setLayout(null);
		getContentPane().add(providerDataInternalFrame);

		this.touristDataInternalFrame = new TouristData(iUse);
		touristDataInternalFrame.setBounds(-6, 6, 414, 279);
		touristDataInternalFrame.setTitle("Turista");
		touristDataInternalFrame.setClosable(true);
		this.touristDataInternalFrame.setVisible(false);

		getContentPane().add(touristDataInternalFrame);

		JLabel title = new JLabel("Usuarios Registrados");
		title.setBounds(92, 39, 223, 29);
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		getContentPane().add(title);

		comboBoxUsers = new JComboBox<DataTypeUser>();
		comboBoxUsers.setBounds(55, 114, 325, 27);
		getContentPane().add(comboBoxUsers);
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.setBounds(156, 176, 92, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdUserConsultationActionPerformed(e);
			}
		});
		getContentPane().add(btnNewButton);

	}

	public void loadUsers() {
		DefaultComboBoxModel<DataTypeUser> model;
		model = new DefaultComboBoxModel<DataTypeUser>();
		for (DataTypeUser user : this.userController.listUsers()) {
			model.addElement(user);
			System.out.println(user.getNickname());
		}
		this.comboBoxUsers.setModel(model);
	}

	protected void cmdUserConsultationActionPerformed(ActionEvent arg0) {
		if (checkForm()) {
			// Obtengo datos de los controles Swing
			String usernick = (String) (this.comboBoxUsers.getSelectedItem().toString());
			DataTypeUser us = userController.dataUser(usernick);
			if (us instanceof DataTypeProvider) {
				DataTypeProvider pro = (DataTypeProvider) us;
				System.out.println(pro);
				this.providerDataInternalFrame.setDataToTextArea(pro);
				this.providerDataInternalFrame.loadActivities(pro);
				this.providerDataInternalFrame.setVisible(true);

			} else {
				DataTypeTourist tu = (DataTypeTourist) us;
				System.out.println(tu);
				this.touristDataInternalFrame.setDataToTextArea(tu);
				this.touristDataInternalFrame.loadOutings(tu);
				this.touristDataInternalFrame.setVisible(true);
			}
		}
	}

	private boolean checkForm() {
		boolean emptyFields = (this.comboBoxUsers.getSelectedIndex() == -1);
		if (emptyFields) {
			JOptionPane.showMessageDialog(this, "No selecciono ningun Usuario", "Error de Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
