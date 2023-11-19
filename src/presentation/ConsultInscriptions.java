package presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import enums.ActivityState;
import logic.DataTypeActivity;
import logic.DataTypeInscription;
import logic.DataTypeOuting;

@SuppressWarnings("serial")
public class ConsultInscriptions extends JInternalFrame {
	private IActivity activityController;
	private IOuting outingController;
	private JTextField txtFieldOName;
	private JTextField txtFieldODate;
	private JTextField txtFieldOHour;
	private JTextField txtFieldOMaxTour;
	private JTextField txtFieldOPlace;

	private String selActivity;
	private String selOuting;

	private JComboBox<DataTypeActivity> comboBoxActivities;
	private JComboBox<DataTypeOuting> comboBoxOut;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public ConsultInscriptions(IActivity IAC, IOuting IO) {
		activityController = IAC;
		outingController = IO;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar Inscripciones");
		setBounds(100, 100, 500, 400);

		// agregando listener
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cleanForm();
			}
		});

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 381, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblActivites = new JLabel("Activites");
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

		JLabel lblOutings = new JLabel("Outings");
		GridBagConstraints gbc_lblOutings = new GridBagConstraints();
		gbc_lblOutings.anchor = GridBagConstraints.EAST;
		gbc_lblOutings.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutings.gridx = 1;
		gbc_lblOutings.gridy = 3;
		getContentPane().add(lblOutings, gbc_lblOutings);

		comboBoxOut = new JComboBox<DataTypeOuting>();
		GridBagConstraints gbc_comboBoxOut = new GridBagConstraints();
		gbc_comboBoxOut.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOut.gridx = 2;
		gbc_comboBoxOut.gridy = 3;
		getContentPane().add(comboBoxOut, gbc_comboBoxOut);

		JLabel lblNameOuting = new JLabel("Name");
		GridBagConstraints gbc_lblNameOuting = new GridBagConstraints();
		gbc_lblNameOuting.anchor = GridBagConstraints.EAST;
		gbc_lblNameOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOuting.gridx = 1;
		gbc_lblNameOuting.gridy = 4;
		getContentPane().add(lblNameOuting, gbc_lblNameOuting);

		txtFieldOName = new JTextField();
		txtFieldOName.setEditable(false);
		txtFieldOName.setColumns(10);
		GridBagConstraints gbc_txtFieldOName = new GridBagConstraints();
		gbc_txtFieldOName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOName.gridx = 2;
		gbc_txtFieldOName.gridy = 4;
		getContentPane().add(txtFieldOName, gbc_txtFieldOName);

		JLabel lblDateOuting = new JLabel("Date");
		GridBagConstraints gbc_lblDateOuting = new GridBagConstraints();
		gbc_lblDateOuting.anchor = GridBagConstraints.EAST;
		gbc_lblDateOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOuting.gridx = 1;
		gbc_lblDateOuting.gridy = 5;
		getContentPane().add(lblDateOuting, gbc_lblDateOuting);

		txtFieldODate = new JTextField();
		txtFieldODate.setEditable(false);
		txtFieldODate.setColumns(10);
		GridBagConstraints gbc_txtFieldODate = new GridBagConstraints();
		gbc_txtFieldODate.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldODate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldODate.gridx = 2;
		gbc_txtFieldODate.gridy = 5;
		getContentPane().add(txtFieldODate, gbc_txtFieldODate);

		JLabel lblHourOuting = new JLabel("Hour");
		GridBagConstraints gbc_lblHourOuting = new GridBagConstraints();
		gbc_lblHourOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblHourOuting.anchor = GridBagConstraints.EAST;
		gbc_lblHourOuting.gridx = 1;
		gbc_lblHourOuting.gridy = 6;
		getContentPane().add(lblHourOuting, gbc_lblHourOuting);

		txtFieldOHour = new JTextField();
		txtFieldOHour.setEditable(false);
		txtFieldOHour.setColumns(10);
		GridBagConstraints gbc_txtFieldOHour = new GridBagConstraints();
		gbc_txtFieldOHour.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOHour.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOHour.gridx = 2;
		gbc_txtFieldOHour.gridy = 6;
		getContentPane().add(txtFieldOHour, gbc_txtFieldOHour);

		JLabel lblMaxTouristOuting = new JLabel("Max Tourists");
		GridBagConstraints gbc_lblMaxTouristOuting = new GridBagConstraints();
		gbc_lblMaxTouristOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxTouristOuting.anchor = GridBagConstraints.EAST;
		gbc_lblMaxTouristOuting.gridx = 1;
		gbc_lblMaxTouristOuting.gridy = 7;
		getContentPane().add(lblMaxTouristOuting, gbc_lblMaxTouristOuting);

		txtFieldOMaxTour = new JTextField();
		txtFieldOMaxTour.setEditable(false);
		txtFieldOMaxTour.setColumns(10);
		GridBagConstraints gbc_txtFieldOMaxTour = new GridBagConstraints();
		gbc_txtFieldOMaxTour.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOMaxTour.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOMaxTour.gridx = 2;
		gbc_txtFieldOMaxTour.gridy = 7;
		getContentPane().add(txtFieldOMaxTour, gbc_txtFieldOMaxTour);

		JLabel lblPlaceOuting = new JLabel("Place");
		GridBagConstraints gbc_lblPlaceOuting = new GridBagConstraints();
		gbc_lblPlaceOuting.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaceOuting.anchor = GridBagConstraints.EAST;
		gbc_lblPlaceOuting.gridx = 1;
		gbc_lblPlaceOuting.gridy = 8;
		getContentPane().add(lblPlaceOuting, gbc_lblPlaceOuting);

		txtFieldOPlace = new JTextField();
		txtFieldOPlace.setEditable(false);
		txtFieldOPlace.setColumns(10);
		GridBagConstraints gbc_txtFieldOPlace = new GridBagConstraints();
		gbc_txtFieldOPlace.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOPlace.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOPlace.gridx = 2;
		gbc_txtFieldOPlace.gridy = 8;
		getContentPane().add(txtFieldOPlace, gbc_txtFieldOPlace);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 10;
		gbc_scrollPane.gridwidth = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setRowHeaderView(table);
		table.setRowSelectionAllowed(false);

		comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxActivities.getSelectedIndex() != -1) {
					DataTypeActivity selectedActivity = (DataTypeActivity) comboBoxActivities.getSelectedItem();
					selActivity = selectedActivity.getName();

					DataTypeOuting[] outings = activityController.chooseActivity(selectedActivity.getName());
					loadOutings(outings);
				}
			}
		});

		comboBoxOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxOut.getSelectedIndex() != -1) {
					DataTypeOuting selectedOut = (DataTypeOuting) comboBoxOut.getSelectedItem();
					selOuting = selectedOut.getName();

					txtFieldODate.setText(selectedOut.getDate());
					txtFieldOHour.setText(selectedOut.getHour());
					txtFieldOMaxTour.setText(String.valueOf(selectedOut.getMaxTourist()));
					txtFieldOName.setText(selectedOut.getName());
					txtFieldOPlace.setText(selectedOut.getPlace());

					DataTypeInscription[] ins = outingController.chooseOuting(selActivity, selOuting);

					loadInscriptions(ins);
				}
			}
		});
	}

	public void loadInscriptions(DataTypeInscription[] inscriptions) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("nickname");
		model.addColumn("date");
		model.addColumn("cost");
		model.addColumn("touristNumber");

		Object[] header = new Object[4];
		header[0] = "Nickname";
		header[1] = "Date";
		header[2] = "Cost";
		header[3] = "Tourist Number";

		model.addRow(header);

		for (DataTypeInscription ins : inscriptions) {
			Object[] row = new Object[4];

			row[0] = ins.getTouristNickname();
			row[1] = ins.getInscriptionDate();
			System.out.println("Costo " + ins.getCosto());
			row[2] = ins.getCosto();
			System.out.println("TouristNumber " + ins.getTouristNumber());
			row[3] = ins.getTouristNumber();

			model.addRow(row);
		}
		this.table.setModel(model);
	}

	public void loadActivities() {
		DefaultComboBoxModel<DataTypeActivity> model;
		model = new DefaultComboBoxModel<DataTypeActivity>(this.activityController.listTouristActivities(ActivityState.CONFIRMADA));
		this.comboBoxActivities.setModel(model);
		this.comboBoxActivities.setSelectedIndex(-1);
	}

	public void loadOutings(DataTypeOuting[] outings) {
		DefaultComboBoxModel<DataTypeOuting> model;
		model = new DefaultComboBoxModel<DataTypeOuting>(outings);
		this.comboBoxOut.setModel(model);
		this.comboBoxOut.setSelectedIndex(-1);
		this.cleanTextFields();
	}
	
	private void cleanTextFields() {
		this.txtFieldODate.setText("");
		this.txtFieldOHour.setText("");
		this.txtFieldOMaxTour.setText("");
		this.txtFieldOName.setText("");
		this.txtFieldOPlace.setText("");
		
		DefaultTableModel modelT = new DefaultTableModel();
		this.table.setModel(modelT);
	}

	private void cleanForm() {
		this.txtFieldODate.setText("");
		this.txtFieldOHour.setText("");
		this.txtFieldOMaxTour.setText("");
		this.txtFieldOName.setText("");
		this.txtFieldOPlace.setText("");
		
		this.comboBoxActivities.setSelectedIndex(-1);
		this.comboBoxOut.setSelectedIndex(-1);

		DefaultTableModel modelT = new DefaultTableModel();
		this.table.setModel(modelT);
	}

}