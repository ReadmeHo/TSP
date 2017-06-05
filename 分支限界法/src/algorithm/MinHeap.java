package algorithm;

public class MinHeap {
    public HeapNode[] heapArray; // 堆容器
    private int maxSize; // 堆的最大大小
    private int currentSize = 0; // 堆大小
    // 构造函数
    public MinHeap(int _maxSize) {
	maxSize = _maxSize;
	heapArray = new HeapNode[maxSize];
	currentSize = 0;
    }
    // filterDown自上而下调整
    public void filterDown(int start, int endOfHeap) {
	int i = start;
	int j = 2 * i + 1; // j是i的左子女位置
	HeapNode temp = heapArray[i];
	while (j <= endOfHeap){ // 检查是否到最后位置
	    if (j < endOfHeap&& heapArray[j].cc > heapArray[j + 1].cc)
		j++;// 让j指向两子女中的小者
	    if (temp.cc <= heapArray[j].cc)
		break;
	    else{ 
		heapArray[i] = heapArray[j];
		i = j;
		j = 2 * j + 1;
	    }
	}// 否则小者上移，i，j下降
	heapArray[i] = temp;
    }
    // filterUp自下而上的调整:从结点start开始到0为止，自下向上比较，如果子女的值小于双亲结点的值则互相交换
    public void filterUp(int start) {
	int j = start;
	int i = (j - 1) / 2;
	HeapNode temp = heapArray[j];
	while (j > 0) { 
            // 双亲结点值小，不调整
	    if (heapArray[i].cc <= temp.cc)
		break;// 沿双亲结点路径向上直达根节点
            // 双亲结点值大，调整
	    else{
		heapArray[j] = heapArray[i];
		j = i;
		i = (i - 1) / 2;
	    }
	    heapArray[j] = temp; // 回送
	}
    }
    // put插入结点
    public void put(HeapNode node) {
	HeapNode newNode = node;
	heapArray[currentSize] = newNode;
	filterUp(currentSize);
	currentSize++;
    }
    // 删除堆中的最小值
    public HeapNode removeMin() {
	HeapNode root = heapArray[0];
	heapArray[0] = heapArray[currentSize - 1];
	currentSize--;
	filterDown(0, currentSize - 1);
	return root;
    }
}
