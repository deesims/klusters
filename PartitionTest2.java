public class PartitionTest2 {

	public static void main(String[] args) {

		Partition<Character> p=new Partition<Character>();
		Partition.Node[] node = new Partition.Node[26];
		
		System.out.println("**Start testing make clusters...");
		
		for (char c='A'; c <= 'Z'; c++) {
			node[c-'A']=p.makeCluster(c);	// storing nodes of single clusters created for A..Z 
											// into positions 0..25
		}
		
		System.out.println(p); // note: toString() is already implemented in class Partition
		 
		System.out.println("Start testing 'union' (will have 6 clusters of sizes 5,1,6,9,1,4)");

		p.union(node[20],node[11]);

		//System.out.println("Nodes trying to clutter: " + node[20].getElement() + " <--> " + node[11].getElement());

		//System.out.println("First Union:  \n" + p);

		p.union(node[8],node[0]);

		//System.out.println("Nodes trying to clutter: " + node[8].getElement() + " <--> " + node[0].getElement());

		//System.out.println("Second union: \n " + p);



		//System.out.println("Nodes trying to clutter: " + node[0].getElement() + " <--> " + node[2].getElement());
		p.union(node[0],node[2]);
		//System.out.println("Third union: " + p);

		p.union(node[11], node[8]);



	    // very clever way to give a random set
	    for (int i='D'-'A'; i<= 'H'-'A'; i++) { // note that the -'A' is mapping A..Z into 0..25
	    	p.union(node[9],node[i]);
		}



	    for (int i='M'-'A'; i<= 'T'-'A'; i++) {
	    	p.union(node[10],node[i]);
	    	//System.out.println("Nodes trying to clutter: " + node[10].getElement() + "<-->" + node[i].getElement());
	    	//System.out.println(p);
	    }
	    
	    p.union(node['Z'-'A'],node['Y'-'A']);
		//System.out.println("Nodes trying to clutter: " + node['Z'-'A'].getElement() + "<-->" + node['Y'-'A'].getElement());
		//System.out.println(p);
	    p.union(node['X'-'A'],node['W'-'A']);
		//System.out.println("Nodes trying to clutter: " + node['X'-'A'].getElement() + "<-->" + node['W'-'A'].getElement());
		//System.out.println(p);
	    p.union(node['Z'-'A'], node['W'-'A']);
		//System.out.println("Nodes trying to clutter: " + node['Z'-'A'].getElement() + "<-->" + node['W'-'A'].getElement());
		//System.out.println(p);

	    System.out.println(p); // note: toString() is already implemented in class Partition



	    System.out.println("Now join cluster of V with cluster of Q:");


	    System.out.println("Nodes trying to cluster: " + node['V'-'A'].getElement() + "<-->" + node['Q'-'A'].getElement());
	    p.union(node['V'-'A'],node['Q'-'A']);
	    System.out.println(p); // note: toString() is already implemented in class Partition
	    
	    System.out.println("Now test 'find' operations:");
	    System.out.print("find(A)=find(B)? ");
	    if (p.find(node[0])==p.find(node[1])) System.out.println("yes");
	    else System.out.println("no");
	    
	    System.out.print("find(U)=find(A)? ");
	    if (p.find(node['U'-'A'])==p.find(node['A'-'A'])) System.out.println("yes");
	    else System.out.println("no");
		
	    System.out.print("find(J)=find(P)? ");
	    if (p.find(node['J'-'A'])==p.find(node['P'-'A'])) System.out.println("yes");
	    else System.out.println("no");
	    
	    System.out.print("find(T)=find(M)? ");
	    if (p.find(node['T'-'A'])==p.find(node['M'-'A'])) System.out.println("yes");
	    else System.out.println("no");


	    System.out.println("**END TEST ");

	}

}
