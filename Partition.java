/**
 * 
 * Disjoint Partition of a set providing an union-find data structure
 * where clusters are implemented as linked lists of elements of type T 
 * Each cluster is represented by a Dnode of a doubly linked list of clusters
 * Each cluster/Dnode, points to a singly linked list of Node each containing an element in the cluster.
 * For efficient implementation of method find, each Node points to the Dnode of the cluster it belongs to.
 * 
 * @author Lucia Moura
 *
 * @param <T>
 */
public class Partition <T> {
	
	// inner class specifying a node in the singly linked lists
	public class Node {
		private Node next;
		private T element;
		private Dnode cluster=null;
		public Node (T element, Node next, Dnode cluster) {
			this.element=element;
			this.next=next;
			this.cluster=cluster;
		}

		public T getElement(){
			return this.element;
		}
	}
	
	// inner class specifying a node in the doubly linked list of clusters
	public class Dnode {
		private Node first;
		private Dnode next, prev;
		private int size;

		Dnode(Node first, Dnode prev, Dnode next) {
			this.first=first;
			this.prev=prev;
			this.next=next;
			this.size=0;
		}

		public Node getFirst() {
			return this.first;
		}

		public void addFirst(Node p) {
			this.first = new Node(p.cluster.first.getElement(), p.cluster.first, this );
		}

		public void addLast(Node p){
			if (this.first == null) {
				addFirst(p);
			} else {
				Node tmp = this.first;


				while (tmp.next != null) {
					//tmp.cluster = this;
					tmp = tmp.next;
				}


				tmp.next = p; // append to the end of singly linked list

			}
		}



	}
		
	private Dnode headCluster, tailCluster; // references to the dummy head and tail of the doubly linked list
	private int countClusters; // size of doubly linked list (not counting the dummies)
	
	public Partition() {	
		// creates an empty doubly linked list of clusters with dummy head and tail
		headCluster=new Dnode(null,null,null);
		tailCluster=new Dnode(null,headCluster,null);
		headCluster.next=tailCluster;
		countClusters=0;
	}
	
	public int numClusters() {
		return countClusters;
	}
	/**
	 * makeCluster creates a new cluster formed by the given element and inserts at the end of the list of clusters
	 * @param element
	 */
	public Node makeCluster(T element) {  // nothing needs to be changed here
		Node newNode=new Node(element,null,null);
		Dnode newCluster=new Dnode(newNode,tailCluster.prev,tailCluster);
		tailCluster.prev.next=newCluster;
		tailCluster.prev=newCluster;
		newCluster.first.cluster=newCluster;
		newCluster.size++;
		countClusters++;
		return newNode;
	}

	/****** for students to implement ***
	 * find returns the Dnode corresponding to the cluster where the node belongs to
	 * 
	 */
	public Dnode find(Node node) { // this is very short
		// **************** TODO: to be implemented by students 
		//System.out.print("\n>>>>>>>> Partition<T>: 'find' method needs to be implemented <<<<<<<<\n"); // to be removed



		return node.cluster; // returns node's doubly linked list


	}
	
	/******** for students to implement ****
	 *  union returns merges the cluster where node p belongs to with the one node q belongs to.
	 *  This method must run in O(min(n_q,n_p)) time, where n_p is the size of the cluster of node p
	 *  and n_q is the size of the cluster of node q.
	 *  Note: You must do appropriate updates on the linked list and double linked list and its corresponding
	 *  nodes and sizes to correctly reflect the fact that the clusters have been merged.
	 *  */
	public void union (Node p, Node q) {


		if (p.cluster.size > q.cluster.size) { // check to see which singly linked list is shorter // this makes sure the algorithm runs in O(min(n_q, n_p))

			p.cluster.size += q.cluster.size; // if p is larger, q merges to p

			p.cluster.addLast(q); // add q'list to p's list // this method implemented in the Node class


			// delete q's cluster
			if (headCluster == q.cluster) {
				headCluster = q.cluster.next;
			}

			if (q.cluster.next != tailCluster) {
				q.cluster.next.prev = q.cluster.prev;
			}

			if (q.cluster.prev != null ) {
				q.cluster.prev.next = q.cluster.next;
			}

			// make sure q's nodes clusters are all pointing to p's cluster
			while (q != null){
				q.cluster  = p.cluster;
				q = q.next;
			}


		} else { // if q is larger than p, p merges into q, same implementation but nodes reversed

			q.cluster.size += p.cluster.size; // add sizes

			q.cluster.addLast(p); // add p's list to q's list


			// get rid of p's cluster
			if (headCluster == p.cluster) {
				headCluster = p.cluster.next;
			}

			if (p.cluster.next != tailCluster) {
				p.cluster.next.prev = p.cluster.prev;
			}

			if (p.cluster.prev != null) {
				p.cluster.prev.next = p.cluster.next;
			}

			// p's nodes cluster point to q's cluster
			while (p != null) {
				p.cluster = q.cluster;
				p = p.next;
			}


		}

		countClusters--;


		
	}
	
	@Override
	public String toString() {
		// prints all clusters information and elements (nothing to be changed here)
		StringBuilder s = new StringBuilder(); int num=0;
		for (Dnode d=headCluster.next; d!=tailCluster; d=d.next) {
			s.append("Cluster ");
		    s.append(++num); s.append(" (size="); s.append(d.size); s.append("): ");
			for (Node n=d.first; n!=null; n=n.next) {
				s.append(n.element.toString());
				s.append(',');
			}
			s.append("\n");
		}
		return s.toString();
	}
	
}
