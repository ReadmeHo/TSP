#pragma once

class ACSAnt;
class AntColonySystem{
	public:
		AntColonySystem(double alpha,double beta,double rho,double q0,double alphal);
		~AntColonySystem(void);
		double Q0;//若将Q0设为零，则为蚂蚁系统
		Tour best_tour;
	private:
		double ALPHA;//信息素轨迹的相对重要性
		double BETA;//能见度的相对重要性
		double RHO;//信息素轨迹的持久性
		double TAU0;//初始化信息素的量
		double ALPHA1;
		doubleMatrix TAU;
		doubleMatrix dTAU;
		ACSAnt* ANTS[M];
	public:
		double calc_tau0(void);	
		void init_tau_by_value(double value);//初始化TAU0
		void init_tau_by_value(doubleMatrix matrix);
		void init_uniform(void);// 蚂蚁均匀分布在城市上	
		void init_random(void);// 随机分布
		void init_randomMOAPC(void);// 每个城市最多有一只蚂蚁	
		double ETA(int i, int j);// η的确定
		double transition(int i, int j);// 概率转移规则
		double sum_transition(int i,int allowed[]);
		void local_update_rule(int i, int j);// 在蚁群系统中执行此操作，在蚂蚁系统中则不执行
		void clear_global_update(void);// 清除全局更新轨迹量
		void add_global_updat(Tour* tour,double length );// 进行全局更新
		void global_update_rule(void);// 全局更新规则
		doubleMatrix* get_tau(void);// 用矩阵表示更新后的轨迹量
		Tour* search(int T);// 找出当前循环的最短路径，并进行全局激素更新
};
