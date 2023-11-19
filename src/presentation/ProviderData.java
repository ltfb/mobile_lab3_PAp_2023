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

import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.DataTypeProvider;

@SuppressWarnings("serial")
public class ProviderData extends JInternalFrame {

	private JLabel lblOutingsP;
	private JComboBox<DataTypeOuting> comboBoxOutingsP;
	private JLabel lblActivities;
	private JComboBox<DataTypeActivity> comboBoxActivities;
	private JButton btnSearchActivity;
	private JButton btnSearchOutingP;
	private JLabel lblProviderData;
	private JTextArea textAreaP;

	private IUser userController;
	private IOuting outingController;

	ShowData showDataInternalFrame;

	public ProviderData(IUser iUsr, IOuting iOut) {
		this.userController = iUsr;
		this.outingController = iOut;

		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(6, 6, 402, 345);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		this.showDataInternalFrame = new ShowData();
		showDataInternalFrame.setSize(317, 293);
		showDataInternalFrame.setLocation(34, 0);
		this.showDataInternalFrame.setVisible(false);
		getContentPane().setLayout(null);
		getContentPane().add(this.showDataInternalFrame);

		lblActivities = new JLabel("Actividades del proveedor:");
		lblActivities.setBounds(34, 22, 167, 16);
		lblActivities.setVerticalAlignment(SwingConstants.BOTTOM);
		getContentPane().add(lblActivities);

		comboBoxActivities = new JComboBox<DataTypeActivity>();
		comboBoxActivities.setBounds(0, 66, 235, 27);
		comboBoxActivities.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxActivities.getSelectedIndex() != -1) {
					loadOutings((DataTypeActivity) comboBoxActivities.getSelectedItem());
				}
			}
		});
		getContentPane().add(comboBoxActivities);

		btnSearchActivity = new JButton("Buscar");
		btnSearchActivity.setBounds(266, 65, 85, 29);
		btnSearchActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdActivityConsultationActionPerformed(e);
			}
		});
		getContentPane().add(btnSearchActivity);

		lblOutingsP = new JLabel("Salidas asociadas:");
		lblOutingsP.setBounds(60, 99, 114, 16);
		lblOutingsP.setVerticalAlignment(SwingConstants.BOTTOM);
		getContentPane().add(lblOutingsP);

		btnSearchOutingP = new JButton("Buscar");
		btnSearchOutingP.setBounds(266, 120, 85, 29);
		btnSearchOutingP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdOutingConsultationActionPerformed(e);
			}
		});

		comboBoxOutingsP = new JComboBox<DataTypeOuting>();
		comboBoxOutingsP.setBounds(0, 121, 235, 27);
		getContentPane().add(comboBoxOutingsP);
		getContentPane().add(btnSearchOutingP);

		lblProviderData = new JLabel("Datos del proveedor:");
		lblProviderData.setBounds(52, 154, 131, 16);
		lblProviderData.setVerticalAlignment(SwingConstants.BOTTOM);
		getContentPane().add(lblProviderData);

		textAreaP = new JTextArea();
		textAreaP.setBounds(0, 175, 377, 124);
		getContentPane().add(textAreaP);
	}

	private void cmdOutingConsultationActionPerformed(ActionEvent arg0) {
		if (checkForm()) {
			DataTypeOuting dto = (DataTypeOuting) this.comboBoxOutingsP.getSelectedItem();

			this.showDataInternalFrame.setDataToInternalFrame(dto);
			this.showDataInternalFrame.setVisible(true);
		}
	}

	private void cmdActivityConsultationActionPerformed(ActionEvent arg0) {
		if (this.comboBoxActivities.getSelectedIndex() != -1) {
			DataTypeActivity dta = ((DataTypeActivity) this.comboBoxActivities.getSelectedItem());

			this.showDataInternalFrame.setDataToInternalFrame(dta);
			this.showDataInternalFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Elija una actividad a consultar", "Proveedor",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean checkForm() {
		if ((this.comboBoxActivities.getSelectedIndex() == -1) || (this.comboBoxOutingsP.getSelectedIndex() == -1)) {
			JOptionPane.showMessageDialog(this, "Elija una actividad y una salida a consultar", "Proveedor",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void cleanForm() {
		this.comboBoxActivities.setSelectedIndex(-1);
		this.comboBoxOutingsP.setSelectedIndex(-1);
		this.textAreaP.setText("");
	}

	public void loadActivities(DataTypeProvider dtp) {
		DefaultComboBoxModel<DataTypeActivity> model;
		model = new DefaultComboBoxModel<DataTypeActivity>(this.userController.listProviderActivities(dtp));
		this.comboBoxActivities.setModel(model);
		this.comboBoxActivities.setSelectedIndex(-1);
	}

	private void loadOutings(DataTypeActivity dta) {
		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>(this.outingController.getOutings(dta.getName()));
		this.comboBoxOutingsP.setModel(model);
	}

	public void setDataToTextArea(DataTypeProvider dtp) {
		setTitle("Datos del Proveedor");
		this.textAreaP.setEditable(false);
		this.textAreaP.setWrapStyleWord(true);
		this.textAreaP.setLineWrap(true);
		String webSite = (dtp.getWebSite() == null) ? "" : dtp.getWebSite();
		String text = "NickName: " + dtp.getNickname() + "\n" + "Nombre: " + dtp.getName() + "\n" + "Apellido: "
				+ dtp.getLastName() + "\n" + "Email: " + dtp.getEmail() + "\n" + "Cumpleaños: " + dtp.getBirthday()
				+ "\n" + "Contraseña: " + dtp.getPassword() + "\n" + "Descripción; " + dtp.getDescription() + "\n"
				+ "Sitio Web: " + webSite + "\n";

		this.textAreaP.setText(text);

	}

}
