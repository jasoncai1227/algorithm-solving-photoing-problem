import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

class Main
{
	private static class Student{
		private int left;
		private int right;
		private int height;
		
		public Student(int left, int right, int height){
			this.left=left;
			this.right=right;
			this.height=height;
		}
		
		public int getLeft() {return left;}
		public int getRight() {return right;}
		public int getHeight() {return height;}
	}
	
	
	public static void main(String[] args)
	{
		// read the input data into a few lists
		// you could use a different structure if you wanted
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		ArrayList<Integer> lefts = new ArrayList<Integer>(); 
		ArrayList<Integer> rights = new ArrayList<Integer>();
		ArrayList<Integer> heights = new ArrayList<Integer>();
		for(int i = 1; i <= n; ++i)
		{
			int left = reader.nextInt();
			int right = reader.nextInt();
			int height = reader.nextInt();
			lefts.add(left);
			rights.add(right);
			heights.add(height);
		}
		reader.close();
		
		// TODO: implement your algorithm
		ArrayList<Student> students_list=new ArrayList<Student> ();
		
		for(int i=0;i<n;i++){
			Student student = new Student(lefts.get(i),rights.get(i),heights.get(i));
			students_list.add(student);
		}
		
		int interval=0;
		for(int a=0;a<n;a++){
			if(interval<students_list.get(a).getRight()){interval=students_list.get(a).getRight();}
			else continue;
		}
		int[] current_heights = new int[interval+1];
		
		TreeMap<Integer,Integer> new_heights=new TreeMap<Integer,Integer>();
		
		points_divide(new_heights,students_list,current_heights,0,n);
		
		ArrayList<Integer> remove_list=new ArrayList<Integer>();
		int temp=0;
		for(int l=0;l<interval+1;l++){
			if(new_heights.get(l)==null){continue;}
			if(l==0){new_heights.get(l);continue;}
			if(temp==new_heights.get(l)){
			remove_list.add(l);	
			}
			temp=new_heights.get(l);
		}
		for(int q=0;q<remove_list.size();q++){
			new_heights.remove(remove_list.get(q));
		}
		Set heightSet = new_heights.entrySet();
		Iterator iter = heightSet.iterator();
		while(iter.hasNext()) {
     		 Map.Entry each = (Map.Entry)iter.next();
      		 System.out.print(each.getKey() + " ");
      		 System.out.println(each.getValue());
    	} 
		
		// output one line for each point (x,y) in the solution
		// the integers should just be separated by a single space
		// (your program should not output any other text)
		//System.out.println("1 3");
		//System.out.println("3 7");
		// etc...
		// of course, you'll want to use a loop, and not hard-code the strings
	}
	
	private static void points_divide(TreeMap<Integer,Integer> points, ArrayList<Student> lists, int[] current_height, int start, int end){
		int length=end-start;
		if(length<=1){
			return;
		}
		int mid = start + (length / 2);
		points_divide(points,lists,current_height,start,mid);
		points_divide(points,lists,current_height,mid,end);
		
		boolean isLastMerge=false;
		int firstStart = start;
        int firstEnd = start + (length / 2);
        int secondStart = firstEnd;
        int secondEnd = end;
		
		
		//ArrayList<Student> temp=new ArrayList<Student>();
		int i = firstStart;
        int j = secondStart;
		int a;
		int l;
		while(i<firstEnd||j<secondEnd){
			if (i >= firstEnd) {
				
				if(current_height[lists.get(j).getLeft()]<lists.get(j).getHeight()){
					points.put(lists.get(j).getLeft(),lists.get(j).getHeight());
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
					/*for(l=lists.get(j).getLeft()+1;l<lists.get(j).getRight();l++){
						points.remove(l);
					}*/
				}
				else if(current_height[lists.get(j).getLeft()]>lists.get(j).getHeight()){
					
					points.put(lists.get(j).getLeft(),current_height[lists.get(j).getLeft()]);
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
				}
				else if(current_height[lists.get(j).getLeft()]==lists.get(j).getHeight()){
					points.put(lists.get(j).getLeft(),current_height[lists.get(j).getLeft()]);
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
				}
				 for(a=lists.get(j).getLeft();a<lists.get(j).getRight();a++){
						if(current_height[a]>lists.get(j).getHeight()){continue;}
						else current_height[a]=lists.get(j).getHeight(); 
					}
				//temp.add(lists.get(j));
            	j++;
			}
        	 else if (j >= secondEnd) {
				
				if(current_height[lists.get(i).getLeft()]<lists.get(i).getHeight()){
					points.put(lists.get(i).getLeft(),lists.get(i).getHeight());
					points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
					/*for(l=lists.get(i).getLeft()+1;l<lists.get(i).getRight();l++){
						points.remove(l);
					}*/
				}
				else if(current_height[lists.get(i).getLeft()]>lists.get(i).getHeight()){
				
					points.put(lists.get(i).getLeft(),current_height[lists.get(i).getLeft()]);
					points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
					
				}
				else if(current_height[lists.get(i).getLeft()]==lists.get(i).getHeight()){
					points.put(lists.get(i).getLeft(),current_height[lists.get(i).getLeft()]);
					points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
				}
				
				for(a=lists.get(i).getLeft();a<lists.get(i).getRight();a++){
					if(current_height[a]>lists.get(i).getHeight()){continue;}
					else current_height[a]=lists.get(i).getHeight(); 
				}
				
				//temp.add(lists.get(i));
            	i++;}
		else {
			   if(lists.get(i).getLeft()<lists.get(j).getLeft()){
				  
				  if(current_height[lists.get(i).getLeft()]<lists.get(i).getHeight()){
					points.put(lists.get(i).getLeft(),lists.get(i).getHeight());
				    points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
					/*for(l=lists.get(i).getLeft()+1;l<lists.get(i).getRight();l++){
						points.remove(l);
					}*/
					}
				 else if(current_height[lists.get(i).getLeft()]>lists.get(i).getHeight()){
					
					points.put(lists.get(i).getLeft(),current_height[lists.get(i).getLeft()]);
					points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
				 }
				  else if(current_height[lists.get(i).getLeft()]==lists.get(i).getHeight()){
					points.put(lists.get(i).getLeft(),current_height[lists.get(i).getLeft()]);
					points.put(lists.get(i).getRight(),current_height[lists.get(i).getRight()]);
				  }
				  for(a=lists.get(i).getLeft();a<lists.get(i).getRight();a++){
					if(current_height[a]>lists.get(i).getHeight()){continue;}
					else current_height[a]=lists.get(i).getHeight(); 
				  }
				 // temp.add(lists.get(i));
            	  i++;}
			   else{
				   
				   if(current_height[lists.get(j).getLeft()]<lists.get(j).getHeight()){
					points.put(lists.get(j).getLeft(),lists.get(j).getHeight());
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
					/*for(l=lists.get(j).getLeft()+1;l<lists.get(j).getRight();l++){
						points.remove(l);
					}*/
					}
				    else if(current_height[lists.get(j).getLeft()]>lists.get(j).getHeight()){
					
					points.put(lists.get(j).getLeft(),current_height[lists.get(j).getLeft()]);
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
					}
				    else if(current_height[lists.get(j).getLeft()]==lists.get(j).getHeight()){
					points.put(lists.get(j).getLeft(),current_height[lists.get(j).getLeft()]);
					points.put(lists.get(j).getRight(),current_height[lists.get(j).getRight()]);
				    }
				    for(a=lists.get(j).getLeft();a<lists.get(j).getRight();a++){
						if(current_height[a]>lists.get(j).getHeight()){continue;}
						else current_height[a]=lists.get(j).getHeight(); 
					}
				  //  temp.add(lists.get(j));
            	    j++;}
			}
		}
		 
        /*   for (int k = start; k < end; k++) {
            lists.set(k, temp.get(k - start));
        	}*/
		}
	}

