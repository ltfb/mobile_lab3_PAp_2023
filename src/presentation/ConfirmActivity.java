package presentation;

import java.awt.GridBagConstraints;
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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import enums.ActivityState;
import logic.DataTypeActivity;

@SuppressWarnings("serial")
public class ConfirmActivity extends JInternalFrame {
	private IActivity activityController;

	private JComboBox<DataTypeActivity> comboBoxActivities;
	private JButton btnConfirm;
	private JButton btnReject;

	/**
	 * Create the frame.
	 */
	public ConfirmActivity(IActivity IAC) {
		activityController = IAC;
		// cleanForm();

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Confirmar/Rechazar Actividad");
		setBounds(100, 100, 411, 226);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 2;
		gbc_comboBoxAct.gridy = 1;
		getContentPane().setLayout(null);

		JLabel lblActivites = new JLabel("Actividades agregadas");
		lblActivites.setBounds(25, 32, 141, 16);
		getContentPane().add(lblActivites);

		btnConfirm = new JButton("Confirmar Actividad");
		btnConfirm.setBounds(19, 119, 177, 29);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdActivityConfirmationActionPerformed(e, true);
			}
		});

		comboBoxActivities = new JComboBox<DataTypeActivity>();
		comboBoxActivities.setBounds(171, 27, 193, 27);
		getContentPane().add(comboBoxActivities);
		getContentPane().add(btnConfirm);

		btnReject = new JButton("Rechazar Actividad");
		btnReject.setBounds(201, 119, 163, 29);
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdActivityConfirmationActionPerformed(e, false);
			}
		});
		getContentPane().add(btnReject);

	}

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model = new DefaultComboBoxModel<DataTypeActivity>(
				this.activityController.listTouristActivities(ActivityState.CONFIRMADA));
		this.comboBoxActivities.setModel(model);
		this.comboBoxActivities.setSelectedIndex(-1);
	}

	private void cmdActivityConfirmationActionPerformed(ActionEvent e, boolean isConfirmed) {
		if (checkForm()) {
			// Obtengo datos de los controles Swing
			DataTypeActivity activity = (DataTypeActivity) this.comboBoxActivities.getSelectedItem();
			String actName = activity.getName();

			if (isConfirmed) {
				this.activityController.confirmActivity(actName);
				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "Actividad confirmada.", "Confirmar/Rechazar Actividad",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				this.activityController.rejectActivity(actName);
				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "Actividad rechazada.", "Confirmar/Rechazar Actividad",
						JOptionPane.INFORMATION_MESSAGE);
			}

			// Limpio el internal frame antes de cerrar la ventana
			cleanForm();
			this.loadActivities();
		}
	}

	private boolean checkForm() {
		if (this.comboBoxActivities.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una actividad.", "Confirmar/Rechazar Actividad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void cleanForm() {
		this.comboBoxActivities.setSelectedIndex(-1);
	}

}