import java.util.*;

public class MyGraph implements Graph<Integer,Double>{
	
	private int numVertices, numEdges; // number of edges and number of edges
	private ArrayList<MyVertex> vertices; // list of vertices idexed by the vertex index
	private LinkedList<MyEdge> edges; // list of edges
    private MyEdge [][] adjacencyMatrix; // This will be an numVertices by numVertices adjacency matrix. 
    									 // if vertex index i and index j are connected by edge e
    									 // then adjacencyMatrix[i,j]=e
	
    /**
     *  MyVertex: nested class implementing the Vertex interface 
     *  
     */
	public class MyVertex implements Vertex<Integer>{
		private int index;   // index of vertex in adjacency matrix valie in (0..numVertices-1)
		private String name; // label/name of vertex
		private int degree; // degree of vertex
		
		public MyVertex(int index, String name) {
			this.index=index; 
			this.name=name;
			this.degree=0;
		}

		public String getName() {
			return name;
		}
		@Override
		public Integer getElement() {
			return index;
		}
		@Override
		public String toString() {
			return "v"+index+":"+name;
		}

		public int getDegree(){
			return this.degree;
		}
		
	}
	
	 /**
     *  MyEdge: nested class implementing the Edge interface 
     *  
     */
	public class MyEdge implements Comparable<MyEdge>, Edge<Double>  {
        private Double dist;
        private MyVertex v1, v2; 
        public MyEdge(double dist, MyVertex v1, MyVertex v2) {
        	this.dist=dist;
        	this.v1=v1;
        	this.v2=v2;
        }
		@Override
		public Double getElement() {
			return dist;
		}
		
		@Override
		public String toString() {
			return "e={"+v1+","+v2+"}"+" dist="+dist;
		}
		
		@Override
		public int compareTo(MyEdge o) {
			return (this.dist).compareTo(o.dist);
		}

		public MyVertex getV1(){
        	return this.v1;
		}

		public MyVertex getV2(){
			return this.v2;
		}
		
	}
	
	/** 
	 * MyGraph() the constructor for the class
	 * the array names has length equals to the number n of vertices and specify the labels for vertices 0..n-1
	 * the arrays end1, end2, dist have length equals to the number m of edges
	 * Values end1[i] and end2[i] represent the indices (between 0..n-1) of the vertices that are endpoints of edge i
	 * dist[i] is the distance/weight of edge i
	*/
	
	public MyGraph(String [] names, int [] end1, int[] end2, double [] dist) {
		
		
		// part 1 create vertices 
		numVertices=names.length; // sets number of vertices which is the length of array names
		
		// *** start student to-do 1
		// complete here what is required for vertex creation

        vertices = new ArrayList<MyVertex>();

		for (int i=0; i < numVertices; i ++){
			MyVertex v = new MyVertex(i, names[i]);
			System.out.println(v);
			vertices.add(i, v);
		}


		// *** end student to-do 1
		
		// part 2: create edges
		numEdges=end1.length; // sets the number of edges which is the length of arrays end1,end2,dist
		if ((end2.length!=numEdges) || (dist.length!=numEdges)) {
			throw new IllegalArgumentException("Uneven array sizes for 2nd 3rd 4th arguments.");
		}


		// initialize adjacency matrix with null
		adjacencyMatrix=new MyEdge[numVertices][numVertices];
		edges=new LinkedList<MyEdge>();
		for (int i= 0; i< numVertices; i++)
			for (int j=0; j< numVertices; j++) {
				adjacencyMatrix[i][j]=null;
			}


		// *** start student to-do 2
		// part to be completed below: create edges placing in LinkedList edges as well as
		// adding the edge reference to the correct position in adjacency matrix

        for (int i=0; i < end1.length; i ++){
		    MyEdge e = new MyEdge(dist[i], vertices.get(end1[i]), vertices.get(end2[i]));
		    edges.add(i, e);
		    System.out.println(e);
		    adjacencyMatrix[end1[i]][end2[i]] = e;
		    adjacencyMatrix[end2[i]][end1[i]] = e;
		    e.getV1().degree ++;
		    e.getV2().degree ++;
        }

		//System.out.println(">>>>>> MyGraph() to-do2 needs to be implemented<<<<<<<<<<");
		//
		// *** end student to-do 2
		
	}

	@Override
	public int numVertices() { // to be implemented by student in O(1)
		//System.out.println(">>>>>> numVertices() needs to be implemented<<<<<<<<<<");
		return numVertices;
	}

	@Override
	public int numEdges() { // to be implemented by student in O(1)
		//System.out.println(">>>>>> numEdges() needs to be implemented<<<<<<<<<<");
		return numEdges;
	}

	@Override
	public Iterable<Vertex<Integer>> vertices() { // to be implemented by student in O(n) where n is number of vertices
		//System.out.println(">>>>>> vertices() needs to be implemented<<<<<<<<<<");

		Iterator<MyVertex> i = vertices.iterator();

		ArrayList<Vertex<Integer>> r = new ArrayList<Vertex<Integer>>();
		while (i.hasNext()){
			Vertex<Integer> v = i.next();
			//System.out.println("inserted into list: " + v);
			r.add(v);
		}

		Iterable<Vertex<Integer>> iterable = new Iterable<Vertex<Integer>>() {
			@Override
			public Iterator<Vertex<Integer>> iterator() {
				return r.iterator();
			}
		};

		return iterable;

		//return (Iterable<Vertex<Integer>>) new ArrayList<Vertex<Integer>>(); // now it returns a dummy value to be corrected
	}

	@Override
	public Iterable<Edge<Double>> edges() { // to be implemented by student in O(m) where m is number of edges
		//System.out.println(">>>>>> edges() needs to be implemented<<<<<<<<<<");

		Iterator<MyEdge> i = edges.iterator();
		ArrayList<Edge<Double>> e = new ArrayList<Edge<Double>>();
		while (i.hasNext()) {
			Edge<Double> edge = i.next();
			e.add(edge);
		}

		Iterable<Edge<Double>> iterable = new Iterable<Edge<Double>>() {
			@Override
			public Iterator<Edge<Double>> iterator() {
				return e.iterator();
			}
		};

		return iterable;


		//return (Iterable<Edge<Double>>) new LinkedList<Edge<Double>>(); // now is returns a dummy value to be corrected
	}

	@Override
	public int degree(Vertex<Integer> v) throws IllegalArgumentException { // to be implemented by student in O(1)
		//System.out.println(">>>>>> degree(v) needs to be implemented<<<<<<<<<<");

		return ((MyVertex)v).getDegree();
	}

	@Override
	public Iterable<Edge<Double>> incidentEdges(Vertex<Integer> v) { // to be implemented by student in O(n), n number of vertices
		//System.out.println(">>>>>> incidentEdges(v) needs to be implemented<<<<<<<<<<");

		int index = v.getElement();
		ArrayList<Edge<Double>> edgeList = new ArrayList<Edge<Double>>();
		for (int j=0; j < adjacencyMatrix[index].length; j ++){
			if ((adjacencyMatrix[index][j]) != null){
				edgeList.add(adjacencyMatrix[index][j]);
			}
		}

		Iterable<Edge<Double>> iterable = new Iterable<Edge<Double>>() {
			@Override
			public Iterator<Edge<Double>> iterator() {
				return edgeList.iterator();
			}
		};


		return iterable; // return dummy value to be corrected
	}

	@Override
	public Edge<Double> getEdge(Vertex<Integer> u, Vertex<Integer> v)
			throws IllegalArgumentException { // to be implemented by student in O(1)

		// System.out.println(">>>>>> getEdge(u,v) needs to be implemented<<<<<<<<<<");


		return adjacencyMatrix[u.getElement()][v.getElement()]; // return dummy value to be corrected
	}

	@Override
	public Vertex<Integer>[] endVertices(Edge<Double> e)
			throws IllegalArgumentException { // to be implemented by student in O(1)
		//System.out.println(">>>>>> endVertices(e) needs to be implemented<<<<<<<<<<");

		Vertex<Integer>[] vertexList = new MyVertex[2];


		vertexList[0] = ((MyEdge) e).getV1();
		vertexList[1] = ((MyEdge) e).getV2();


		return vertexList; // return dummy value to be corrected
	}

	@Override
	public Vertex<Integer> opposite(Vertex<Integer> v, Edge<Double> e)
			throws IllegalArgumentException { // to be implemented by student in O(1)
		//System.out.println(">>>>>> opposite(v,e) needs to be implemented<<<<<<<<<<");

		MyVertex v1 = ((MyEdge) e).getV1();
		MyVertex v2 = ((MyEdge) e).getV2();

		if (v1 == v){
			return v2;
		} else {
			return v1;
		}
	}
	

}
