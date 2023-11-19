package logic;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataTypeActivityArray{
	ArrayList<DataTypeActivity> activites;
	
	public DataTypeActivityArray() {
		
	};
	
	public ArrayList<DataTypeActivity> getActivites(){
		return activites;
	};
	
	public void setActivities(ArrayList<DataTypeActivity> act){
		activites=act;
	};
}