package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import enums.ActivityState;
import logic.DataTypeActivity;
import logic.DataTypeOuting;

@SuppressWarnings("serial")
public class ConsultActivities extends JInternalFrame {
	private IActivity activityController;

	private JTextField txtFieldAName;
	private JTextField txtFieldADesc;
	private JTextField txtFieldALength;
	private JTextField txtFieldACost;
	private JTextField txtFieldAEntryDate;
	private JTextField txtFieldAState;
	private JTextField txtFieldOName;
	private JTextField txtFieldODate;
	private JTextField txtFieldOHour;
	private JTextField txtFieldOMaxTour;
	private JTextField txtFieldOPlace;
	private JTextField txtFieldOEntryDate;

	private JComboBox<DataTypeActivity> comboBoxActivities;
	private JComboBox<DataTypeOuting> comboBoxOut;

	/**
	 * Create the frame.
	 */
	public ConsultActivities(IActivity IAC) {
		activityController = IAC;
		// cleanForm();

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar Actividades");
		setBounds(100, 100, 500, 466);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 291, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// agregando listener para cuando se cierra el internal frame
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		JLabel lblActivites = new JLabel("Actividades");
		GridBagConstraints gbc_lblActivites = new GridBagConstraints();
		gbc_lblActivites.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivites.anchor = GridBagConstraints.EAST;
		gbc_lblActivites.gridx = 1;
		gbc_lblActivites.gridy = 1;
		getContentPane().add(lblActivites, gbc_lblActivites);
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 2;
		gbc_comboBoxAct.gridy = 1;

		comboBoxActivities = new JComboBox<DataTypeActivity>();
		GridBagConstraints gbc_comboBoxActivities = new GridBagConstraints();
		gbc_comboBoxActivities.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxActivities.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActivities.gridx = 2;
		gbc_comboBoxActivities.gridy = 1;
		getContentPane().add(comboBoxActivities, gbc_comboBoxActivities);

		JLabel lblNameActivity = new JLabel("Nombre");
		GridBagConstraints gbc_lblNameActivity = new GridBagConstraints();
		gbc_lblNameActivity.anchor = GridBagConstraints.EAST;
		gbc_lblNameActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameActivity.gridx = 1;
		gbc_lblNameActivity.gridy = 2;
		getContentPane().add(lblNameActivity, gbc_lblNameActivity);

		txtFieldAName = new JTextField();
		txtFieldAName.setEditable(false);
		GridBagConstraints gbc_txtFieldAName = new GridBagConstraints();
		gbc_txtFieldAName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldAName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldAName.gridx = 2;
		gbc_txtFieldAName.gridy = 2;
		getContentPane().add(txtFieldAName, gbc_txtFieldAName);
		txtFieldAName.setColumns(10);

		JLabel lblDescriptionActivity = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescriptionActivity = new GridBagConstraints();
		gbc_lblDescriptionActivity.anchor = GridBagConstraints.EAST;
		gbc_lblDescriptionActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionActivity.gridx = 1;
		gbc_lblDescriptionActivity.gridy = 3;
		getContentPane().add(lblDescriptionActivity, gbc_lblDescriptionActivity);

		txtFieldADesc = new JTextField();
		txtFieldADesc.setEditable(false);
		txtFieldADesc.setColumns(10);
		GridBagConstraints gbc_txtFieldADesc = new GridBagConstraints();
		gbc_txtFieldADesc.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldADesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldADesc.gridx = 2;
		gbc_txtFieldADesc.gridy = 3;
		getContentPane().add(txtFieldADesc, gbc_txtFieldADesc);

		JLabel lblLengthActivity = new JLabel("Longitud");
		GridBagConstraints gbc_lblLengthActivity = new GridBagConstraints();
		gbc_lblLengthActivity.anchor = GridBagConstraints.EAST;
		gbc_lblLengthActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblLengthActivity.gridx = 1;
		gbc_lblLengthActivity.gridy = 4;
		getContentPane().add(lblLengthActivity, gbc_lblLengthActivity);

		txtFieldALength = new JTextField();
		txtFieldALength.setEditable(false);
		txtFieldALength.setColumns(10);
		GridBagConstraints gbc_txtFieldALength = new GridBagConstraints();
		gbc_txtFieldALength.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldALength.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldALength.gridx = 2;
		gbc_txtFieldALength.gridy = 4;
		getContentPane().add(txtFieldALength, gbc_txtFieldALength);

		JLabel lblCostActivity = new JLabel("Costo");
		GridBagConstraints gbc_lblCostActivity = new GridBagConstraints();
		gbc_lblCostActivity.anchor = GridBagConstraints.EAST;
		gbc_lblCostActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostActivity.gridx = 1;
		gbc_lblCostActivity.gridy = 5;
		getContentPane().add(lblCostActivity, gbc_lblCostActivity);

		txtFieldACost = new JTextField();
		txtFieldACost.setEditable(false);
		txtFieldACost.setColumns(10);
		GridBagConstraints gbc_txtFieldACost = new GridBagConstraints();
		gbc_txtFieldACost.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldACost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldACost.gridx = 2;
		gbc_txtFieldACost.gridy = 5;
		getContentPane().add(txtFieldACost, gbc_txtFieldACost);

		JLabel lblEntryDateActivity = new JLabel("Fecha de registro");
		GridBagConstraints gbc_lblEntryDateActivity = new GridBagConstraints();
		gbc_lblEntryDateActivity.anchor = GridBagConstraints.EAST;
		gbc_lblEntryDateActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntryDateActivity.gridx = 1;
		gbc_lblEntryDateActivity.gridy = 6;
		getContentPane().add(lblEntryDateActivity, gbc_lblEntryDateActivity);

		txtFieldAEntryDate = new JTextField();
		txtFieldAEntryDate.setEditable(false);
		txtFieldAEntryDate.setColumns(10);
		GridBagConstraints gbc_txtFieldAEntryDate = new GridBagConstraints();
		gbc_txtFieldAEntryDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldAEntryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldAEntryDate.gridx = 2;
		gbc_txtFieldAEntryDate.gridy = 6;
		getContentPane().add(txtFieldAEntryDate, gbc_txtFieldAEntryDate);

		JLabel lblStateActivity = new JLabel("Estado");
		GridBagConstraints gbc_lblStateActivity = new GridBagConstraints();
		gbc_lblStateActivity.anchor = GridBagConstraints.EAST;
		gbc_lblStateActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateActivity.gridx = 1;
		gbc_lblStateActivity.gridy = 7;
		getContentPane().add(lblStateActivity, gbc_lblStateActivity);

		txtFieldAState = new JTextField();
		txtFieldAState.setEditable(false);
		txtFieldAState.setColumns(10);
		GridBagConstraints gbc_txtFieldAState = new GridBagConstraints();
		gbc_txtFieldAState.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldAState.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldAState.gridx = 2;
		gbc_txtFieldAState.gridy = 7;
		getContentPane().add(txtFieldAState, gbc_txtFieldAState);

		JLabel lblOutings = new JLabel("Salidas");
		GridBagConstraints gbc_lblOutings = new GridBagConstraints();
		gbc_lblOutings.anchor = GridBagConstraints.EAST;
		gbc_lblOutings.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutings.gridx = 1;
		gbc_lblOutings.gridy = 9;
		getContentPane().add(lblOutings, gbc_lblOutings);

		comboBoxOut = new JComboBox<DataTypeOuting>();
		GridBagConstraints gbc_comboBoxOut = new GridBagConstraints();
		gbc_comboBoxOut.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOut.gridx = 2;
		gbc_comboBoxOut.gridy = 9;
		getContentPane().add(comboBoxOut, gbc_comboBoxOut);

		JLabel lblNameOuting = new JLabel("Nombre");
		GridBagConstraints gbc_lblNameOuting = new GridBagConstraints();
		gbc_lblNameOuting.anchor = GridBagConstraints.EAST;
		gbc_lblNameOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOuting.gridx = 1;
		gbc_lblNameOuting.gridy = 10;
		getContentPane().add(lblNameOuting, gbc_lblNameOuting);

		txtFieldOName = new JTextField();
		txtFieldOName.setEditable(false);
		txtFieldOName.setColumns(10);
		GridBagConstraints gbc_txtFieldOName = new GridBagConstraints();
		gbc_txtFieldOName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOName.gridx = 2;
		gbc_txtFieldOName.gridy = 10;
		getContentPane().add(txtFieldOName, gbc_txtFieldOName);

		JLabel lblDateOuting = new JLabel("Fecha");
		GridBagConstraints gbc_lblDateOuting = new GridBagConstraints();
		gbc_lblDateOuting.anchor = GridBagConstraints.EAST;
		gbc_lblDateOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOuting.gridx = 1;
		gbc_lblDateOuting.gridy = 11;
		getContentPane().add(lblDateOuting, gbc_lblDateOuting);

		txtFieldODate = new JTextField();
		txtFieldODate.setEditable(false);
		txtFieldODate.setColumns(10);
		GridBagConstraints gbc_txtFieldODate = new GridBagConstraints();
		gbc_txtFieldODate.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldODate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldODate.gridx = 2;
		gbc_txtFieldODate.gridy = 11;
		getContentPane().add(txtFieldODate, gbc_txtFieldODate);

		JLabel lblHourOuting = new JLabel("Hora");
		GridBagConstraints gbc_lblHourOuting = new GridBagConstraints();
		gbc_lblHourOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblHourOuting.anchor = GridBagConstraints.EAST;
		gbc_lblHourOuting.gridx = 1;
		gbc_lblHourOuting.gridy = 12;
		getContentPane().add(lblHourOuting, gbc_lblHourOuting);

		txtFieldOHour = new JTextField();
		txtFieldOHour.setEditable(false);
		txtFieldOHour.setColumns(10);
		GridBagConstraints gbc_txtFieldOHour = new GridBagConstraints();
		gbc_txtFieldOHour.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOHour.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOHour.gridx = 2;
		gbc_txtFieldOHour.gridy = 12;
		getContentPane().add(txtFieldOHour, gbc_txtFieldOHour);

		JLabel lblMaxTouristOuting = new JLabel("Máx. Turistas");
		GridBagConstraints gbc_lblMaxTouristOuting = new GridBagConstraints();
		gbc_lblMaxTouristOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxTouristOuting.anchor = GridBagConstraints.EAST;
		gbc_lblMaxTouristOuting.gridx = 1;
		gbc_lblMaxTouristOuting.gridy = 13;
		getContentPane().add(lblMaxTouristOuting, gbc_lblMaxTouristOuting);

		txtFieldOMaxTour = new JTextField();
		txtFieldOMaxTour.setEditable(false);
		txtFieldOMaxTour.setColumns(10);
		GridBagConstraints gbc_txtFieldOMaxTour = new GridBagConstraints();
		gbc_txtFieldOMaxTour.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOMaxTour.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOMaxTour.gridx = 2;
		gbc_txtFieldOMaxTour.gridy = 13;
		getContentPane().add(txtFieldOMaxTour, gbc_txtFieldOMaxTour);

		JLabel lblPlaceOuting = new JLabel("Lugar");
		GridBagConstraints gbc_lblPlaceOuting = new GridBagConstraints();
		gbc_lblPlaceOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaceOuting.anchor = GridBagConstraints.EAST;
		gbc_lblPlaceOuting.gridx = 1;
		gbc_lblPlaceOuting.gridy = 14;
		getContentPane().add(lblPlaceOuting, gbc_lblPlaceOuting);

		txtFieldOPlace = new JTextField();
		txtFieldOPlace.setEditable(false);
		txtFieldOPlace.setColumns(10);
		GridBagConstraints gbc_txtFieldOPlace = new GridBagConstraints();
		gbc_txtFieldOPlace.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOPlace.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOPlace.gridx = 2;
		gbc_txtFieldOPlace.gridy = 14;
		getContentPane().add(txtFieldOPlace, gbc_txtFieldOPlace);

		JLabel lblEntryDateOuting = new JLabel("Fecha de registro");
		GridBagConstraints gbc_lblEntryDateOuting = new GridBagConstraints();
		gbc_lblEntryDateOuting.insets = new Insets(0, 0, 0, 5);
		gbc_lblEntryDateOuting.anchor = GridBagConstraints.EAST;
		gbc_lblEntryDateOuting.gridx = 1;
		gbc_lblEntryDateOuting.gridy = 15;
		getContentPane().add(lblEntryDateOuting, gbc_lblEntryDateOuting);

		txtFieldOEntryDate = new JTextField();
		txtFieldOEntryDate.setEditable(false);
		txtFieldOEntryDate.setColumns(10);
		GridBagConstraints gbc_txtFieldOEntryDate = new GridBagConstraints();
		gbc_txtFieldOEntryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOEntryDate.gridx = 2;
		gbc_txtFieldOEntryDate.gridy = 15;
		getContentPane().add(txtFieldOEntryDate, gbc_txtFieldOEntryDate);

		comboBoxActivities.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxActivities.getSelectedIndex() != -1) {
					DataTypeActivity selectedActivity = (DataTypeActivity) comboBoxActivities.getSelectedItem();

					txtFieldACost.setText(selectedActivity.getCost());
					txtFieldADesc.setText(selectedActivity.getDescription());

					// Tipo de formato
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					// Formateando LocalDateTime a String
					String dateString = selectedActivity.getEntryDate().format(dateFormatter);

					txtFieldAEntryDate.setText(dateString);
					txtFieldALength.setText(Integer.toString(selectedActivity.getDuration()));
					txtFieldAName.setText(selectedActivity.getName());
					txtFieldAState.setText(selectedActivity.getState().toString());

					DataTypeOuting[] outings = IAC.chooseActivity(selectedActivity.getName());
					loadOutings(outings);
				}
			}
		});

		comboBoxOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxOut.getSelectedIndex() != -1) {
					DataTypeOuting selectedOut = (DataTypeOuting) comboBoxOut.getSelectedItem();
					txtFieldODate.setText(selectedOut.getDate());
					txtFieldOEntryDate.setText(selectedOut.getEntryDate().toString());
					txtFieldOHour.setText(selectedOut.getHour());
					txtFieldOMaxTour.setText(String.valueOf(selectedOut.getMaxTourist()));
					txtFieldOName.setText(selectedOut.getName());
					txtFieldOPlace.setText(selectedOut.getPlace());
				}
			}
		});
	}

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model = new DefaultComboBoxModel<DataTypeActivity>(
				this.activityController.listTouristActivities(ActivityState.CONFIRMADA));
		this.comboBoxActivities.setModel(model);
		this.comboBoxActivities.setSelectedIndex(-1);
	}

	public void loadOutings(DataTypeOuting[] outings) {
		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>(outings);
		this.comboBoxOut.setModel(model);
		this.comboBoxOut.setSelectedIndex(-1);
	}

	private void cleanForm() {
		this.txtFieldACost.setText("");
		this.txtFieldADesc.setText("");
		this.txtFieldAEntryDate.setText("");
		this.txtFieldALength.setText("");
		this.txtFieldAName.setText("");
		this.txtFieldAState.setText("");

		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>();
		this.comboBoxOut.setModel(model); // Seteo el combo de Outings en vacío hasta que no seleccione una Activity

		this.txtFieldODate.setText("");
		this.txtFieldOEntryDate.setText("");
		this.txtFieldOHour.setText("");
		this.txtFieldOMaxTour.setText("");
		this.txtFieldOName.setText("");
		this.txtFieldOPlace.setText("");
	}
}