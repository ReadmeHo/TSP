package algorithm;

public class Bbtsp{ 
    public static int n;//城市数量
    public static float [][]distance;//图G的邻接矩阵
    public static int [][] location;//城市坐标
    public static float [] minOut;
    public static int [] x;//当前最优回路
    public static HeapNode enode;
    public static MinHeap heap;
    public static float bestc;
    
    public Bbtsp(int [][] xy1){	
	n=xy1.length;
	location=xy1;
	distance=new float[n+1][n+1];
    }
    //解货郎担问题的优先队列式分支限界法
    public static void bbTsp(){	
        init();//初始化 
        search();//搜索排列空间树
	copy();//将最优解复制到v[1:n]  
    }
    //初始化
    public static void init(){
	distance();
	bestc=Float.MAX_VALUE;
	heap = new MinHeap(100);
	minOut=new float[n+1];
	float minSum=calculateminSum(0);	
	x=new int[n];
        for(int i=0;i<n;i++)
            x[i]=i+1;  
        enode=new HeapNode(0,0,minSum,0,x);	
    }	
    //根据城市坐标计算邻接矩阵
    public static void distance(){
	for(int i=0;i<n;i++)
	    for(int j=0;j<n;j++)
		distance[i+1][j+1]=(float)Math.sqrt(Math.pow(location[i][0]-location[j][0], 2)+Math.pow(location[i][1]-location[j][1], 2));
    }    
    //计算minOut[i]和minSum
    public static float calculateminSum(float minSum){
        float result=0;
        for(int i=0;i<n;i++){
            float min=Float.MAX_VALUE;
            for(int j=0;j<n;j++)            
                if(distance[i][j]<Float.MAX_VALUE && distance[i][j]<min)
                    min=distance[i][j];
            if(min==Float.MAX_VALUE){
                result=Float.MAX_VALUE;//无回路
                break;
            }
            minOut[i]=min;
            minSum+=min;
        }
        if(result!=Float.MAX_VALUE){
            System.out.println("minOut[i]=顶点i的最小出边费用 " );
            for(int k=0;k<n;k++)
                System.out.print(minOut[k]+"  ");
            System.out.println("minSum "+minSum);
            result=minSum;
        }
        return result;
    }
    //搜索排列空间树   
    public static void search(){
        //非叶结点
	while(enode!=null && enode.s<n-1){
            x=enode.x;
	    //当前扩展结点是叶结点的父结点
            if(enode.s==n-2){
                //再加2条边构成回路,若所构成回路优于当前最优解，则找到费用更小的回路
                if(isshorterway(distance,x,enode,bestc))
                    shorterway();
	    }
	    //当前扩展结点不是叶结点的父结点
            else
		//产生当前扩展结点的儿子结点
                for(int i=enode.s+1;i<n;i++)
                    if(distance[x[enode.s]][x[i]]<Float.MAX_VALUE)
			childnode(i);//可行儿子结点                                                
            //取下一扩展结点
            enode=(HeapNode)heap.removeMin();            
        }        
    }
    //再加2条边构成回路,若所构成回路优于当前最优解，则找到费用更小的回路
    public static boolean isshorterway(float[][]a, int[]x,HeapNode enode,float bestc){
	boolean result;
	if(a[x[n-2]][x[n-1]]<Float.MAX_VALUE )
	    if(a[x[n-1]][1]<Float.MAX_VALUE)
		if(enode.cc+a[x[n-2]][x[n-1]]+a[x[n-1]][1]<bestc)
		    result=true;
		else
		    result=false;	    
	    else
		result=false;	
	else 
	    result=false;
	return result;
    }    
    //可行儿子节点
    public static void childnode(int i){
	float cc=enode.cc+distance[x[enode.s]][x[i]];
	float rcost=enode.rcost-minOut[x[enode.s]];
	float b=cc+rcost;//下界     
	//子树可能含最优解，结点插入最小堆
	if(b<bestc){
	    int []xx=new int[n];
	    for(int j=0;j<n;j++)
		xx[j]=x[j];
	    xx[enode.s+1]=x[i];
	    xx[i]=x[enode.s+1];                          
	    HeapNode node=new HeapNode(b,cc,rcost,enode.s+1,xx);
	    heap.put(node);
	}
    }
    //找到费用更小的回路
    public static void shorterway(){
	bestc=enode.cc+distance[x[n-2]][x[n-1]]+distance[x[n-1]][1];
	enode.cc=bestc;
	enode.lcost=bestc;
	enode.s++;
	heap.put(enode);
    }
    //复制最优解
    public static void copy(){
	x=enode.x;
        int[] t=new int[n];
        for(int i=n-1;i>=0;i--)
            if(i==0)
                t[i]=x[i];
            else
                t[n-i]=x[i];        
        for(int i=0;i<n;i++)
            System.out.print("  "+t[i]);        
    }            
}