package mk.DotNetApp.analyser.common;

import java.util.LinkedList;

import mk.DotNetApp.PE.CLI.MetaDataTables.Method;

/**
 * @author kun_ma
 *
 */
public class MethodFlowInfectionChain {
	
	public String source = null;
	public String sink = null;
	
	public LinkedList<Method> infectionChain = null;
	
	public MethodFlowInfectionChain() {
		
	}
	
	public MethodFlowInfectionChain(String sink) {
		this.sink = sink;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setSink(String sink) {
		this.sink = sink;
	}
	
	public void setInfectionChain(LinkedList<Method> infectionChain) {
		this.infectionChain = infectionChain;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getSink() {
		return this.sink;
	}
	
	public LinkedList<Method> getInfectionChain() {
		return this.infectionChain;
	}
}
