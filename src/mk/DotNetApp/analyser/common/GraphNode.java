package mk.DotNetApp.analyser.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import mk.DotNetApp.PE.CLI.MetaDataTables.Method;

/**
 * @author kun_ma
 * 
 */
public class GraphNode {

	public Set<GraphNode> parents = new HashSet<GraphNode>();
	public Set<GraphNode> children = new HashSet<GraphNode>();

	public Method method;
	public String signature;

	/*
	 * default, not access yet set to 0, access and not infect set to 1, access
	 * and infect set to 2
	 */
	public int accessFlag = 0;

	public GraphNode(Method m) {
		this.method = m;
		this.signature = this.method.getFullName();
		this.accessFlag = 0;
	}

	public GraphNode(String name) {
		this.signature = name;
		this.accessFlag = 0;
	}

	public GraphNode(Method m, String name) {
		this.method = m;
		this.signature = name;
		this.accessFlag = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (null == o || !this.getClass().equals(o.getClass())) {
			return false;
		}

		final GraphNode other = (GraphNode) o;
		if (this.signature.equals(other.signature)
		// && this.method.equals(other.method)
		) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// return this.method.hashCode();
		if (null == this.signature) {
			return this.method.getFullName().hashCode();
		} else {
			return this.signature.hashCode();
		}
	}
	
	public int testCode()
	{
		return super.hashCode();
	}

	@Override
	public String toString() {
		if (null != this.signature) {
			return this.signature;
		} else {
			return this.method.getFullName();
		}
	}

	public GraphNode GetUnAccessedParent() {
		Iterator<GraphNode> it = this.parents.iterator();
		while (it.hasNext()) {
			GraphNode p = it.next();
			if (0 == p.accessFlag) {
				return p;
			}
		}
		return null;
	}

	public GraphNode GetUnAccessedChild() {
		Iterator<GraphNode> it = this.children.iterator();
		while (it.hasNext()) {
			GraphNode c = it.next();
			if (0 == c.accessFlag) {
				return c;
			}
		}
		return null;
	}
}
