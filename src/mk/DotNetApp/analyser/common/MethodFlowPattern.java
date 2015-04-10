package mk.DotNetApp.analyser.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author kun_ma
 *
 */
public class MethodFlowPattern {
	
	public static Map<Integer, String> actionMap = new HashMap<Integer, String>();
	static {
		int key = 1;
		String action = "Location leaks by Http";
		actionMap.put(key, action);
		
		key = 1 << 1;
		action = "Contact leaks by Http";
		actionMap.put(key, action);
		
		key = 1 << 2;
		action = "Appointment leaks by Http";
		actionMap.put(key, action);
		
		key = 1 << 3;
		action = "SMS leaks by Http";
		actionMap.put(key, action);
	}
	
	public static int getActionCode(String action) {
		Iterator<Entry<Integer, String>> it = actionMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, String> entry = it.next();
			if (entry.getValue().equals(action)) {
				return entry.getKey();
			}
		}
		
		return 0;
	}
	
	public static Map<String, List<String>> sinkPattern = new HashMap<String, List<String>> ();
	static {
		String key = "Http";
		List<String> methodsList = new ArrayList<String> ();
		// for Windows RT
		methodsList.add("System.Net.Requests-System.Net.WebRequest-GetResponseAsync");  
		methodsList.add("System.Net.Requests-System.Net.WebResponse-GetResponseStream");
		// for Windows Phone 8
		methodsList.add("System.Net-System.Net.HttpWebRequest-BeginGetRequestStream");  // EndGetRequestStream
		methodsList.add("System.Net-System.Net.HttpWebRequest-BeginGetResponse");   // EndGetResponse
		methodsList.add("System.Net-System.Net.HttpWebResponse-GetResponseStream");
		
//		methodsList.add("System.Net-System.Net.WebRequest-Create");
//		methodsList.add("System.Net-System.Net.WebRequest-CreateHttp");  
		methodsList.add("System.Net-System.Net.WebRequest-BeginGetRequestStream");  // EndGetRequestStream
		methodsList.add("System.Net-System.Net.WebRequest-BeginGetResponse");  // EndGetResponse
		
		methodsList.add("System.Net-System.Net.WebResponse-GetResponseStream");
		sinkPattern.put(key, methodsList);
		
//		methodsList = new ArrayList<String> ();
//		key = "SMS Sending";
//		methodsList.add("");
//		sinkPattern.put(key, methodsList);
		
	}
	
	public static Map<String, List<String>> sourcePattern = new HashMap<String, List<String>> ();
	static {
		String key = "Location";
		List<String> methodsList = new ArrayList<String> ();
		// for Windows RT
		methodsList.add("Windows-Windows.Devices.Geolocation.Geolocator-GetGeopositionAsync"); 
		// for Windows Phone 8
		methodsList.add("System.Device.Location-System.Device.Location.CivicAddressResolver-ResolveAddress");
		methodsList.add("System.Device.Location-System.Device.Location.CivicAddressResolver-ResolveAddressAsync");
		sourcePattern.put(key, methodsList);
		
		/*
		 * UserData, 主类为 Contacts、Contact、Appointments 和 Appointment 类
		 */
		methodsList = new ArrayList<String> ();
		key = "Contact";
		methodsList.add("Microsoft.Phone-Microsoft.Phone.UserData.Contacts-SearchAsync");
		sourcePattern.put(key, methodsList);
		
		methodsList = new ArrayList<String> ();
		key = "Appointment";
		methodsList.add("Microsoft.Phone.UserData-Microsoft.Phone.UserData.Appointments-SearchAsync");
		sourcePattern.put(key, methodsList);
		
		methodsList = new ArrayList<String> ();
		key = "SMS";
		methodsList.add("Windows.Phone.Networking.NetworkOperators-Windows.Phone.Networking.NetworkOperators.SmsInterceptor-GetQueuedMessages");
		sourcePattern.put(key, methodsList);
		
//		methodsList.clear();
//		key = "Wallet";
//		methodsList.add("Microsoft.Phone.Wallet-Microsoft.Phone.Wallet.*-*"); 
//		sourcePattern.put(key, methodsList);
		
	}
	
	public static Map<String, List<String>> actionPattern = new HashMap<String, List<String>> ();
	static {
		String key = "";
		List<String> methodsList = new ArrayList<String> ();
		methodsList.add("");
		actionPattern.put(key, methodsList);
	}

	/*
	 * sourcePattern, type 1
	 * sinkPattern, type 2
	 * actionPattern, type 3
	 */
	public static List<String> getPatternList(int type) {
		List<String> patternList = new ArrayList<String> ();
		
		Iterator<Entry<String, List<String>>> it = null;
		switch (type) {
		case 1:
			it = sourcePattern.entrySet().iterator();
			break;
		case 2:
			it = sinkPattern.entrySet().iterator();
			break;
		case 3:
			it = actionPattern.entrySet().iterator();
			break;

		default:
			break;
		}
		
		
		while(it.hasNext()) {
			patternList.addAll(it.next().getValue());
		}
		
		return patternList;
	}
	
	public static String getPatternKey(String method, int type) {
		Iterator<Entry<String, List<String>>> it = null;
		switch (type) {
		case 1:
			it = sourcePattern.entrySet().iterator();
			break;
		case 2:
			it = sinkPattern.entrySet().iterator();
			break;
		case 3:
			it = actionPattern.entrySet().iterator();
			break;

		default:
			break;
		}
		
		while(it.hasNext()) {
			Entry<String, List<String>> entry = it.next();
			if (entry.getValue().contains(method)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(MethodFlowPattern.sourcePattern.get("Contact"));
	}
}
