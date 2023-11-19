package presentation;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

import logic.DataTypeActivity;
import logic.DataTypeOuting;
import logic.DataTypeProvider;
import logic.DataTypeTourist;

@SuppressWarnings("serial")
public class ShowData extends JInternalFrame {

	private JTextArea textArea;

	public ShowData() {
		setResizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 423, 197);
		setSize(400, 200);
		this.textArea = new JTextArea("Descripción");
		getContentPane().add(this.textArea);
	}

	public void setDataToInternalFrame(DataTypeActivity dta) {
		setTitle("Datos de Actividad");
		this.textArea.setEditable(false);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setLineWrap(true);

		this.textArea.setText("Nombre: " + dta.getName() + "\n" + "Costo: " + dta.getCost() + "\n" + "Descripcion: "
				+ dta.getDescription() + "\n" + "Duracion: " + dta.getDuration() + "\n" + "Ciudad: "
				+ dta.getState() + "\n" + "Fecha de alta: " + dta.getEntryDate());
		this.textArea.setVisible(true);
	}

	public void setDataToInternalFrame(DataTypeOuting dto) {
		setTitle("Datos de Salida");
		this.textArea.setEditable(false);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setLineWrap(true);

		this.textArea.setText("Nombre: " + dto.getName() + "\n" + "Fecha: " + dto.getDate() + "\n" + "Hora: "
				+ dto.getHour() + "\n" + "Cantidad maxima de turistas: " + dto.getMaxTourist() + "\n" + "Lugar: "
				+ dto.getPlace() + "\n" + "Fecha de alta: " + dto.getEntryDate());
		this.textArea.setVisible(true);
	}

	public void setDataToInternalFrame(DataTypeProvider dtp) {
		setTitle("Datos del Proveedor");
		this.textArea.setEditable(false);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setLineWrap(true);
		String webSite = (dtp.getWebSite() == null) ? "" : dtp.getWebSite();
		String text = "NickName: " + dtp.getNickname() + "\n" + "Nombre: " + dtp.getName() + "\n" + "Apellido: "
				+ dtp.getLastName() + "\n" + "Email: " + dtp.getEmail() + "\n" + "Cumpleaños: " + dtp.getBirthday()
				+ "\n" + "Contraseña: " + dtp.getPassword() + "\n" + "Descripción; " + dtp.getDescription() + "\n"
				+ "Sitio Web: " + webSite + "\n";

		this.textArea.setText(text);
		this.textArea.setVisible(true);

	}

	public void setDataToInternalFrame(DataTypeTourist dtt) {
		setTitle("Datos del Turista");
		this.textArea.setEditable(false);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setLineWrap(true);
		String text = "NickName: " + dtt.getNickname() + "\n" + "Nombre: " + dtt.getName() + "\n" + "Apellido: "
				+ dtt.getLastName() + "\n" + "Email: " + dtt.getEmail() + "\n" + "Cumpleaños: " + dtt.getBirthday()
				+ "\n" + "Contraseña: " + dtt.getPassword() + "\n" + "Nacionalidad; " + dtt.getNationality() + "\n";

		this.textArea.setText(text);
		this.textArea.setVisible(true);

	}

}
