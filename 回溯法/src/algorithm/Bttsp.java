package algorithm;


public class Bttsp {
    public static int n;          //图G的顶点个数
    public static float [][]distance;   //图G的邻接矩阵
    public static int [][] location;//城市坐标
    public static int []x;        //当前解
    public static int [] bestx;  //当前最优解
    public static float bestc;   //当前最优值
    public static float cc;      //当前费用
    
    public Bttsp(int [][] xy1){	
	n=xy1.length;
	location=xy1;
	distance=new float[n+1][n+1];
    }
    public static void distance(){
	for(int i=0;i<n;i++)
	    for(int j=0;j<n;j++)
		distance[i+1][j+1]=(float)Math.sqrt(Math.pow(location[i][0]-location[j][0], 2)+Math.pow(location[i][1]-location[j][1], 2));
    }
    public static void tsp(){
        distance();
	x=new int[n+1];//置x为单位排列
	for(int i=1;i<=n;i++)
	    x[i]=i;
	bestc=Float.MAX_VALUE;
	bestx=new int[n];
	cc=0;        
	backtrack(2);//搜索x[2:n]的全排列
    }
    public static boolean isshorterway(int k,int i,int j){
        boolean result;
	if(k==1)
	    if(distance[x[n-1]][x[n]]<Float.MAX_VALUE)
		if(distance[x[n]][1]<Float.MAX_VALUE)
		    if(bestc==Float.MAX_VALUE||cc+distance[x[n-1]][x[n]]+distance[x[n]][1]<bestc)
			result=true;		    
		    else
			result=false;		
		else
		    result=false;	    
	    else
		result=false;	
	else
	    if(distance[x[i-1]][j]<Float.MAX_VALUE)
                if((bestc==Float.MAX_VALUE||cc+distance[x[i-1]][x[j]]<bestc))
                    result=true;		    
                else
                    result=false;		
            else
                result=false;	
        return result;
    }
    public static void backtrack(int i){
	if(i==n){
	    if(isshorterway(1,0,0)){
		for(int j=0;j<n;j++)
                    bestx[j]=x[j+1];
		bestc=cc+distance[x[n-1]][x[n]]+distance[x[n]][1];    
	    }
	}
	else
	    for(int j=i;j<=n;j++)
		if(isshorterway(0,i,j)){
		    swap(x,i,j);
		    cc+=distance[x[i-1]][x[i]];
		    backtrack(i+1);
		    cc-=distance[x[i-1]][x[i]];
		    swap(x,i,j);
		}	
    }
    public static void swap(int[] a,int i1,int j1){
	int temp;   
	temp=a[i1];
	a[i1]=a[j1];
	a[j1]=temp;
    }  
}
