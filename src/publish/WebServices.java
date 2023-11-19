
package publish;

import java.util.ArrayList;

import enums.ActivityState;
import exceptions.ActivityException;
import exceptions.UserException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logic.DataTypeActivity;
import logic.DataTypeActivityArray;
import logic.DataTypeOuting;
import logic.DataTypeOutingArray;
import logic.Factory;
import presentation.IActivity;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {

	private Endpoint endpoint = null;

	Factory f = new Factory();
	IActivity IA = f.getIActivity();

	// Constructor
	public WebServices() {
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publish() {
		endpoint = Endpoint.publish("http://localhost:9128/webservices", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	@WebMethod
	public DataTypeActivityArray listTouristActivities(ActivityState addedActs) {
		DataTypeActivity[] acts = IA.listTouristActivities(addedActs);
		ArrayList<DataTypeActivity> act = new ArrayList<DataTypeActivity>();
		for (DataTypeActivity a : acts) {
			act.add(a);
		}
		DataTypeActivityArray result = new DataTypeActivityArray();
		result.setActivities(act);
		return result;
	}

	@WebMethod
	public DataTypeOutingArray chooseActivity(String actName) {
		DataTypeOuting[] outi = IA.chooseActivity(actName);
		ArrayList<DataTypeOuting> out = new ArrayList<DataTypeOuting>();
		for (DataTypeOuting o : outi) {
			out.add(o);
		}
		DataTypeOutingArray result = new DataTypeOutingArray();
		result.setOutings(out);
		return result;
	}
	
	@WebMethod
	public void activityRegistration(String provName, DataTypeActivity dta) throws ActivityException, UserException{
		Factory f = new Factory();
		IActivity IAct = f.getIActivity();
		IAct.activityRegistration(provName, dta);
	}
	
	@WebMethod
	public void confirmActivity(String actName){
		Factory f = new Factory();
		IActivity IAct = f.getIActivity();
		IAct.confirmActivity(actName);
	}
	
	@WebMethod
	public void rejectActivity(String actName){
		Factory f = new Factory();
		IActivity IAct = f.getIActivity();
		IAct.rejectActivity(actName);
	}
	
}
