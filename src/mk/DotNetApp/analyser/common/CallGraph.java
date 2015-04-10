package mk.DotNetApp.analyser.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author kun_ma
 * 
 */
public class CallGraph {
	private static Log log = LogFactory.getLog(CallGraph.class);

	public Set<GraphNode> graph = new HashSet<GraphNode>();

	public int maxStack;
	public int top;
	public GraphNode[] stack;

	private Set<LinkedList<GraphNode>> infectionChainSet = new HashSet<LinkedList<GraphNode>>();

	public Set<LinkedList<GraphNode>> GetInfectionChainSet() {
		return this.infectionChainSet;
	}

	public CallGraph(Set<GraphNode> set, int size) {
		this.graph = set;
		this.maxStack = size;

		this.stack = new GraphNode[this.maxStack];
		this.top = -1;
	}

	public void Reset() {

	}

	public void PopStack() {
		if (this.top <= -1) {
			return;
		}

		this.stack[this.top] = null;
		this.top -= 1;
	}

	/*
	 * push node into stack set access flag
	 */
	public void PushStack(GraphNode node) {
		if (this.top >= this.maxStack) {
			System.out.println("Stack Overflow!");
			return;
		}

		node.accessFlag = 1; // set access flag
		this.top += 1;
		this.stack[this.top] = node;
	}

	public void PrintStack(String sep) {
		LinkedList<GraphNode> infectionChain = new LinkedList<GraphNode>();
		System.out.println("**************************************");
		for (int i = 0; i <= this.top; i++) {
			System.out.println(this.stack[i] + sep);
			infectionChain.add(this.stack[i]);
		}
		System.out.println("**************************************");
		
		//Remove Duplicates
		if (!this.ExistChain(infectionChain)) {
			this.infectionChainSet.add(infectionChain);
		}

	}

	public boolean ExistChain(LinkedList<GraphNode> newNode) {
		if (null == this.infectionChainSet) {
			return false;
		}

		for (LinkedList<GraphNode> oldNode : this.infectionChainSet) {
			if (newNode.equals(oldNode)) {
				return true;
			}
			
			if (oldNode.containsAll(newNode)) {
				return this.IsSubChain(oldNode, newNode);
			} else if (newNode.containsAll(oldNode)) {
				return this.IsSubChain(newNode, oldNode);
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean IsSubChain(LinkedList<GraphNode> parent,
			LinkedList<GraphNode> child) {
		Iterator<GraphNode> pIt = parent.iterator();
		Iterator<GraphNode> cIt = child.iterator();
		while (cIt.hasNext()) {
			GraphNode cE = cIt.next();
			GraphNode pE = pIt.next();
			if (!cE.equals(pE)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * DFS
	 */
	public void Backward(GraphNode node) {
		this.PushStack(node);
		while (true) {
			if (-1 == this.top) {
				break;
			}

			node = node.GetUnAccessedParent();
			if (null == node) {
				PrintStack(" <-- ");
				this.PopStack();
				if (-1 == this.top) {
					break;
				}
				node = this.stack[this.top];
			} else {
				this.PushStack(node);
			}
		}
	}

	/*
	 * DFS
	 */
	public void Forward(GraphNode node) {
		this.PushStack(node);
		while (true) {
			if (-1 == this.top) {
				break;
			}

			node = node.GetUnAccessedChild();
			if (null == node) {
				PrintStack(" --> ");
				this.PopStack();
				if (-1 == this.top) {
					break;
				}
				node = this.stack[this.top];
			} else {
				this.PushStack(node);
			}
		}
	}

}
