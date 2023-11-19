package presentation;

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
import logic.DataTypeOuting;

@SuppressWarnings("serial")
public class ConsultOuting extends JInternalFrame {

	IOuting outingController;
	IActivity activityController;

	JComboBox<DataTypeActivity> comboBoxActivities;
	JComboBox<DataTypeOuting> comboBoxOutings;

	ShowData showDataInternalFrame;

	/**
	 * Create the frame.
	 */
	public ConsultOuting(IOuting iOUT, IActivity iACT) {
		// Se inicializa con el controlador de Salidas
		outingController = iOUT;
		activityController = iACT;

		getContentPane().setLayout(null);

		setResizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consultar Salida");
		setMaximizable(true);
		setClosable(true);

		setBounds(100, 100, 423, 300);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		this.showDataInternalFrame = new ShowData();
		showDataInternalFrame.setLocation(0, 34);
		this.showDataInternalFrame.setVisible(false);
		getContentPane().add(this.showDataInternalFrame);

		JLabel lblNewLabel = new JLabel("Escoja la Actividad asociada a la Salida:");
		lblNewLabel.setBounds(0, 22, 248, 16);
		getContentPane().add(lblNewLabel);

		this.comboBoxActivities = new JComboBox<DataTypeActivity>();
		comboBoxActivities.setBounds(0, 65, 399, 27);
		getContentPane().add(this.comboBoxActivities);
		this.comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdActivitySelectionActionPerformed(arg0);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Salidas asociadas:");
		lblNewLabel_1.setBounds(0, 97, 114, 16);
		getContentPane().add(lblNewLabel_1);

		this.comboBoxOutings = new JComboBox<DataTypeOuting>();
		comboBoxOutings.setBounds(0, 118, 399, 27);
		getContentPane().add(this.comboBoxOutings);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(157, 150, 85, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdOutingConsultationActionPerformed(e);
			}
		});
		getContentPane().add(btnNewButton);

	}

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model; // Este modelo se crea para carga el combobox
		model = new DefaultComboBoxModel<DataTypeActivity>(this.activityController.listTouristActivities(ActivityState.CONFIRMADA));
		this.comboBoxActivities.setModel(model);
		this.comboBoxActivities.setSelectedIndex(-1);
	}

	public void loadActivityOutings(String activityName) {
		DefaultComboBoxModel<DataTypeOuting> model; // Este modelo se crea para carga el combobox
		model = new DefaultComboBoxModel<DataTypeOuting>(this.outingController.getOutings(activityName));
		this.comboBoxOutings.setModel(model);
		this.comboBoxOutings.setSelectedIndex(-1);
	}

	private void cmdActivitySelectionActionPerformed(ActionEvent arg0) {
		if (this.comboBoxActivities.getSelectedIndex() != -1) {
			DataTypeActivity dta = (DataTypeActivity) this.comboBoxActivities.getSelectedItem();
			String actName = dta.getName();
			loadActivityOutings(actName);
		}
	}

	private void cmdOutingConsultationActionPerformed(ActionEvent arg0) {
		if (checkForm()) {
			// Obtengo datos de los controles Swing
			String activityName = ((DataTypeActivity) this.comboBoxActivities.getSelectedItem()).getName();
			String outingName = ((DataTypeOuting) this.comboBoxOutings.getSelectedItem()).getName();

			DataTypeOuting dto = outingController.consultOuting(activityName, outingName);
			System.out.println(dto);
			this.showDataInternalFrame.setDataToInternalFrame(dto);
			this.showDataInternalFrame.setVisible(true);
		}
	}

	private boolean checkForm() {
		boolean emptyFields = (this.comboBoxActivities.getSelectedIndex() == -1)
				|| (this.comboBoxOutings.getSelectedIndex() == -1);

		if (emptyFields) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Salida",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void cleanForm() {
		this.comboBoxActivities.setSelectedIndex(-1);
		this.comboBoxOutings.setSelectedIndex(-1);
	}

}
