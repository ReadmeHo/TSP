#include "StdAfx.h"
#include "Calculate.h"

double city[6][2]={{150,100},{270,100},{131,244},{73,118},{127,144},{60,188}};//城市坐标
doubleMatrix D;//邻接矩阵
int result[6];//最优解
//c矩阵决定哪些边不画
double c[6][6]={
	1,1,1,1,1,1,
	1,1,1,1,1,1,
	1,1,1,1,1,1,
	1,1,1,1,1,1,
	1,1,1,1,1,1,
	1,1,1,1,1,1
};

Calculate::Calculate(void)
{
}

Calculate::~Calculate(void)
{
}
// 两城市间的几何距离
double Calculate::dist(double C[][2],int i, int j){
	return sqrt(pow((C[i][0]-C[j][0]),2.0)+pow((C[i][1]-C[j][1]),2.0));
}
// 计算邻接矩阵
void Calculate::calc_dist(double D[][N],double C[][2]){
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			D[i][j]=dist(C,i,j);
}
// 两城市间的最大距离
double Calculate::max_dist(doubleMatrix D){
	double max_dist=0.0;
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			if(D[i][j]>max_dist)
				max_dist=D[i][j];
	return max_dist;
}
// 经过n个城市的一条路径长度
double Calculate::calc_length(Tour* tour,doubleMatrix D){
	double l=0.0;
	for(int n=0;n<N;n++){
		int i=(*tour)[n][0];
		int j=(*tour)[n][1];
		l=l+D[i][j];
	}
	return l;
}
// 已经过城市的数量
int Calculate::sum_sequence(int aarray[], int count){
	int sum=0;
	for(int i=0;i<count;i++)
		sum=sum+aarray[i];
	return sum;
}
