package algorithm;


public class Bttsp {
    public static int n;          //图G的顶点个数
    public static int []x;        //当前解
    public static int [] bestx;  //当前最优解
    public static float bestc;   //当前最优值
    public static float cc;      //当前费用
    public static float [][]a;   //图G的临界矩阵

    public static float tsp(int[] v){
	x=new int[n+1];//置x为单位排列
	for(int i=1;i<=n;i++)
	    x[i]=i;
	bestc=Float.MAX_VALUE;
	bestx=v;
	cc=0;        
	backtrack(2);//搜索x[2:n]的全排列
	return bestc;
    }
    private static boolean istrue(int k,int i,int j){
	if(k==1){
	    if(a[x[n-1]][x[n]]<Float.MAX_VALUE){
		if(a[x[n]][1]<Float.MAX_VALUE){
		    if(bestc==Float.MAX_VALUE||cc+a[x[n-1]][x[n]]+a[x[n]][1]<bestc){
			return true;
		    }
		    else
			return false;
		}
		else
		    return false;
	    }
	    else
		return false;
	}
	else{
	    if(a[x[i-1]][j]<Float.MAX_VALUE){
		    if((bestc==Float.MAX_VALUE||cc+a[x[i-1]][x[j]]<bestc)){
			return true;
		    }
		    else
			return false;
		}
		else
		    return false;
	}	    
    }
    private static void backtrack(int i){
	if(i==n){
	    if(istrue(1,0,0)){
		for(int j=1;j<=n;j++)
		  bestx[j]=x[j];
		bestc=cc+a[x[n-1]][x[n]]+a[x[n]][1];    
	    }
	}
	else{
	    for(int j=i;j<=n;j++)
		if(istrue(0,i,j)){
		    swap(x,i,j);
		    cc+=a[x[i-1]][x[i]];
		    backtrack(i+1);
		    cc-=a[x[i-1]][x[i]];
		    swap(x,i,j);
		}
	}
    }
    static void swap(int[] a,int i1,int j1){
	int temp;   
	temp=a[i1];
	a[i1]=a[j1];
	a[j1]=temp;
    }  
}
