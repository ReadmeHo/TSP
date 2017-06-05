package algorithm;

public class HeapNode implements Comparable{
    float lcost;//子树费用的下界
    float cc;//当前费用
    float rcost;//x[s:n-1]中顶点最小出边费用和
    int s;//当前路径包含的节点数
    int []x;//根结点到当前结点的路径为x[0:s]，需要进一步搜索的顶点是x[s+1,n-1]           
    //构造方法
    HeapNode(float lc,float ccc,float rc,int ss,int []xx){
        lcost=lc;
        cc=ccc;
        rcost=rc;
        s=ss;
        x=xx;
    }
    public int compareTo(Object x) {
        int result=1;
        float xlc=((HeapNode)x).lcost;
        if(lcost<xlc)
            result=-1;
        if(lcost==xlc)
            result=0;
        return result;
    }
}
