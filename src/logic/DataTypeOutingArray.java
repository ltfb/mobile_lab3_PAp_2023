package logic;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataTypeOutingArray{
	ArrayList<DataTypeOuting> outings;
	
	public DataTypeOutingArray() {
		
	};
	
	public ArrayList<DataTypeOuting> getOutings(){
		return outings;
	};
	
	public void setOutings(ArrayList<DataTypeOuting> act){
		outings=act;
	};
}