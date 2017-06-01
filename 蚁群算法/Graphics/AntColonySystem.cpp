#include "StdAfx.h"
#include "Ant.h"
#include "ACSAnt.h"
#include "NNAnt.h"
#include "AntColonySystem.h"

extern doubleMatrix D;

AntColonySystem::AntColonySystem(double alpha,double beta,double rho,double q0,double alpha1){
	ALPHA=alpha;
	BETA=beta;
	RHO=rho;
	Q0=q0;
	ALPHA1=alpha1;
}

AntColonySystem::~AntColonySystem(void)
{
}

double AntColonySystem::calc_tau0(void){
	double best_length=(double)N*Calculate::max_dist(D);
	for(int n=0;n<N;n++){
		NNAnt* nnANT=new NNAnt(n);
		Tour* tour;
		tour=nnANT->search();
		double tour_length=Calculate::calc_length(tour,D);
		if(tour_length<best_length)
			best_length=tour_length;
		delete nnANT;
	}
	return 1.0/((double)N*best_length);
}

// 初始化TAU0
void AntColonySystem::init_tau_by_value(double TAU0){
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			TAU[i][j]=TAU0;
}
//用矩阵表示的初始信息量
void AntColonySystem::init_tau_by_value(doubleMatrix matrix){
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			TAU[i][j]=matrix[i][j];
}

// 蚂蚁均匀分布在城市上
void AntColonySystem::init_uniform(void){
	for(int k=0;k<M;k++)
		ANTS[k]=new ACSAnt(this,(k%N));
}
// 随机分布
void AntColonySystem::init_random(void){
	for(int k=0;k<M;k++)
		ANTS[k]=new ACSAnt(this,(int)((double)N*(rand()/(double)RAND_MAX)));
}
// 每个城市最多有一只蚂蚁
void AntColonySystem::init_randomMOAPC(void){
	bool MOAPCarry[N];
	assert(M<N);
	for(int n=0;n<N;n++)
		MOAPCarry[n]=false;
	for(int k=0;k<M;k++){
		int c;
		do
			c=(int)((double)N*(rand()/(double)RAND_MAX));
		while(MOAPCarry[c]);
		MOAPCarry[c]=true;
		ANTS[k]=new ACSAnt(this,c);
	}
}
// η的确定
double AntColonySystem::ETA(int i, int j){
	return (1.0/D[i][j]);
}
// 概率转移规则
double AntColonySystem::transition(int i, int j){
	if(i!=j)
		return (pow(TAU[i][j],ALPHA1)*pow(ETA(i,j),BETA));
	else
		return (0.0);
}

double AntColonySystem::sum_transition(int i,int allowed[]){
	double sum=0.0;
	for(int j=0;j<N;j++)
		sum+=((double)allowed[j]*transition(i,j));
	return sum;
}
// 在蚁群系统中执行此操作，在蚂蚁系统中则不执行
void AntColonySystem::local_update_rule(int i, int j){
	Q0=Q0+TAU[i][j];
	TAU[j][i]=TAU[i][j];
}
// 清除全局更新轨迹量
void AntColonySystem::clear_global_update(void){
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			dTAU[i][j]=0.0;
}
// 进行全局更新
void AntColonySystem::add_global_updat(Tour* tour,double length){
	for(int n=0;n<N;n++){
		int i=(*tour)[n][0];
		int j=(*tour)[n][1];
		dTAU[i][j]=dTAU[i][j]+(1.0/length);
		dTAU[j][i]=dTAU[i][j]+(1.0/length);
	}
}
// 全局更新规则
void AntColonySystem::global_update_rule(void){
	for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			TAU[i][j]=(1.0-ALPHA)*TAU[i][j]+ALPHA*dTAU[i][j];
}

// 用矩阵表示更新后的轨迹量
doubleMatrix* AntColonySystem::get_tau(void){
	return &TAU;
}
// 找出当前循环的最短路径，并进行全局激素更新
Tour* AntColonySystem::search(int T){
	Tour* tour;
	double best_length=(double)N*Calculate::max_dist(D),tour_length;
	for(int t=0;t<T;t++){
		for(int k=0;k<M;k++){
			tour=ANTS[k]->search();
			tour_length=Calculate::calc_length(tour,D);
			if(tour_length<best_length){
				for(int i=0;i<N;i++){
					this->best_tour[i][0]=(*tour)[i][0];
					this->best_tour[i][1]=(*tour)[i][1];
				}
				best_length=tour_length;
				clear_global_update();
				add_global_updat(tour,tour_length);
			}
		}
		global_update_rule();
	}
	return (&best_tour);
}
