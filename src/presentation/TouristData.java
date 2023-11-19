package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import logic.DataTypeOuting;
import logic.DataTypeTourist;

@SuppressWarnings("serial")
public class TouristData extends JInternalFrame {

	private JLabel lblOutingsT;
	private JComboBox<DataTypeOuting> comboBoxOutingsT;
	private JButton btnSearchOutingT;
	private JLabel lblTouristData;
	private JTextArea textAreaT;

	private IUser userController;

	ShowData showDataInternalFrame;

	public TouristData(IUser iUsr) {
		this.userController = iUsr;

		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 414, 327);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		this.showDataInternalFrame = new ShowData();
		showDataInternalFrame.setSize(300, 275);
		showDataInternalFrame.setLocation(48, 0);
		this.showDataInternalFrame.setVisible(false);
		getContentPane().setLayout(null);
		getContentPane().add(this.showDataInternalFrame);

		lblOutingsT = new JLabel("Salidas asociadas:");
		lblOutingsT.setBounds(63, 65, 114, 16);
		lblOutingsT.setVerticalAlignment(SwingConstants.BOTTOM);
		getContentPane().add(lblOutingsT);

		comboBoxOutingsT = new JComboBox<DataTypeOuting>();
		comboBoxOutingsT.setBounds(0, 87, 241, 27);
		getContentPane().add(comboBoxOutingsT);

		btnSearchOutingT = new JButton("Buscar");
		btnSearchOutingT.setBounds(275, 86, 85, 29);
		btnSearchOutingT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdOutingConsultationActionPerformed(e);
			}
		});
		getContentPane().add(btnSearchOutingT);

		lblTouristData = new JLabel("Datos del turista:");
		lblTouristData.setBounds(66, 120, 109, 16);
		getContentPane().add(lblTouristData);

		textAreaT = new JTextArea();
		textAreaT.setBounds(0, 141, 389, 140);
		getContentPane().add(textAreaT);
	}

	private void cmdOutingConsultationActionPerformed(ActionEvent arg0) {
		if (checkForm()) {
			DataTypeOuting dto = (DataTypeOuting) this.comboBoxOutingsT.getSelectedItem();

			this.showDataInternalFrame.setDataToInternalFrame(dto);
			this.showDataInternalFrame.setVisible(true);
		}
	}

	private boolean checkForm() {
		if (this.comboBoxOutingsT.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Elija una salida a consultar", "Turista", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void cleanForm() {
		this.comboBoxOutingsT.setSelectedIndex(-1);
		this.textAreaT.setText("");
	}

	public void loadOutings(DataTypeTourist dtt) {
		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>(this.userController.listTouristOutings(dtt));
		this.comboBoxOutingsT.setModel(model);
		this.comboBoxOutingsT.setSelectedIndex(-1);
	}

	public void setDataToTextArea(DataTypeTourist dtt) {
		setTitle("Datos del Turista");
		this.textAreaT.setEditable(false);
		this.textAreaT.setWrapStyleWord(true);
		this.textAreaT.setLineWrap(true);
		String text = "NickName: " + dtt.getNickname() + "\n" + "Nombre: " + dtt.getName() + "\n" + "Apellido: "
				+ dtt.getLastName() + "\n" + "Email: " + dtt.getEmail() + "\n" + "Cumpleaños: " + dtt.getBirthday()
				+ "\n" + "Contraseña: " + dtt.getPassword() + "\n" + "Nacionalidad: " + dtt.getNationality() + "\n";

		this.textAreaT.setText(text);
		this.textAreaT.setVisible(true);

	}

}
