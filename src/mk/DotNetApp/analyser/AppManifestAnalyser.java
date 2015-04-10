package mk.DotNetApp.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mk.DotNetApp.Utils.XmlParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;

/**
 * @author makun
 * 
 */
public class AppManifestAnalyser {
	private static Log log = LogFactory.getLog(AppManifestAnalyser.class);

	private String appManifestFilePath = null;
	private XmlParser xmlParser = null;

	private Map<String, Object> identityMap = new HashMap<String, Object>();
	private Map<String, Object> propertiesMap = new HashMap<String, Object>();
	private List<String> capabilityList = new ArrayList<String>();
	private List<String> deviceCapabilityList = new ArrayList<String>();
	private String appEntryPoint = null;

	public AppManifestAnalyser() {

	}

	public AppManifestAnalyser(String appManifestFilePath) throws Exception {
		this.appManifestFilePath = appManifestFilePath;

		this.xmlParser = new XmlParser(appManifestFilePath);
		this.xmlParser.init();

	}

	public void LoadPackageInfo() {
		// load Identity info
		List<Element> elements = this.xmlParser.getElement(
				this.xmlParser.rootElement, "Identity");
		Element identityElement = elements.get(0);
		this.identityMap.put("Name",
				this.xmlParser.getElementAttribute(identityElement, "Name"));
		this.identityMap.put("Publisher", this.xmlParser.getElementAttribute(
				identityElement, "Publisher"));
		this.identityMap.put("Version",
				this.xmlParser.getElementAttribute(identityElement, "Version"));
		this.identityMap.put("ProcessorArchitecture", this.xmlParser
				.getElementAttribute(identityElement, "ProcessorArchitecture"));

		// load Properties info
		elements = this.xmlParser.getElement(xmlParser.rootElement,
				"Properties");
		Element propertiesElement = elements.get(0);
		Element displayNameElement = this.xmlParser.getElement(
				propertiesElement, "DisplayName").get(0);
		propertiesMap.put("DisplayName",
				this.xmlParser.getElementValue(displayNameElement));
		Element publisherDisplayNameElement = this.xmlParser.getElement(
				propertiesElement, "PublisherDisplayName").get(0);
		propertiesMap.put("PublisherDisplayName",
				this.xmlParser.getElementValue(publisherDisplayNameElement));
		Element logoElement = this.xmlParser.getElement(propertiesElement,
				"Logo").get(0);
		propertiesMap.put("Logo", this.xmlParser.getElementValue(logoElement));
		Element descriptionElement = this.xmlParser.getElement(
				propertiesElement, "Description").get(0);
		propertiesMap.put("Description",
				this.xmlParser.getElementValue(descriptionElement));

		// load Capabilities info
		elements = this.xmlParser.getElement(xmlParser.rootElement,
				"Capabilities");
		Element CapabilitiesElement = elements.get(0);
		// Capability
		elements = this.xmlParser.getElement(CapabilitiesElement, "Capability");
		String capability = null;
		for (Element capabilityElement : elements) {
			capability = this.xmlParser.getElementAttribute(capabilityElement,
					"Name");
			this.capabilityList.add(capability);
		}
		// DeviceCapability
		elements = this.xmlParser.getElement(CapabilitiesElement,
				"DeviceCapability");
		String deviceCapability = null;
		for (Element deviceCapabilityElement : elements) {
			deviceCapability = this.xmlParser.getElementAttribute(
					deviceCapabilityElement, "Name");
			this.deviceCapabilityList.add(deviceCapability);
		}

		// load Applications Info
		elements = this.xmlParser.getElement(xmlParser.rootElement,
				"Applications");
		Element ApplicationsElement = elements.get(0);
		// Application
		elements = this.xmlParser
				.getElement(ApplicationsElement, "Application");
		Element applicationElement = elements.get(0);
		this.appEntryPoint = this.xmlParser.getElementAttribute(
				applicationElement, "EntryPoint");

	}

	public Map<String, Object> GetIdentity() {
		return this.identityMap;
	}

	public Map<String, Object> GetProperties() {
		return this.propertiesMap;
	}

	public List<String> GetCapabilities() {
		return this.capabilityList;
	}

	public List<String> GetDeviceCapabilities() {
		return this.deviceCapabilityList;
	}

	public String GetAppEntryPoint() {
		return this.appEntryPoint;
	}

	public static void main(String[] args) throws Exception {
		String testFile = "E:\\MK_Projects\\DotNetAppGuard\\Win8Metro\\GoEverywhere_v1_2089\\AppxManifest.xml";
		AppManifestAnalyser analyser = new AppManifestAnalyser(testFile);
		analyser.LoadPackageInfo();
		Iterator<Map.Entry<String, Object>> it = analyser.GetIdentity()
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String strEntry = String.format("%s----%s", entry.getKey()
					.toString(), entry.getValue().toString());
			log.info(strEntry);
		}

		it = analyser.GetProperties().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String strEntry = String.format("%s----%s", entry.getKey()
					.toString(), entry.getValue().toString());
			log.info(strEntry);
		}

		for (String strEntry : analyser.GetCapabilities()) {
			log.info(strEntry);
		}

		for (String strEntry : analyser.GetDeviceCapabilities()) {
			log.info(strEntry);
		}

		System.out.println(analyser.GetAppEntryPoint());
	}

}
