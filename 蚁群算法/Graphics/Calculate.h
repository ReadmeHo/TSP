#pragma once

#define N 6//定义城市数为6
#define M 6//设置蚂蚁数为6
#define Q 100//影响轨迹更新规则的Q值大小

typedef int Tour[N][N];
typedef double doubleMatrix[N][N];

class Calculate{
	public:
		Calculate(void);
		~Calculate(void);
		static double dist(double C[][2],int i, int j);// 两城市间的几何距离
		static void calc_dist(double D[][N],double C[][2]);// 计算邻接矩阵
		static double max_dist(doubleMatrix D);// 两城市间的最大距离
		static double calc_length(Tour* tour,doubleMatrix D);// 经过n个城市的一条路径长度
		static int sum_sequence(int aarray[], int count);// 用矩阵表示的经过n个城市的路径长度
};
