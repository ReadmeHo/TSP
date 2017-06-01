#include "StdAfx.h"
#include "Ant.h"
#include "NNAnt.h"

typedef double doubleMatrix[N][N];
extern doubleMatrix D;

NNAnt::NNAnt(int start_city):Ant(start_city){
}
//找出一个城市周围最短的边
int NNAnt::choose(){
	double best_length=(double)N*Calculate::max_dist(D);
	int best_choose=-1;
	for(int j=0;j<N;j++)
		if((ALLOWED[j]==1)&&(D[CURRENT_CITY][j]<best_length)){
			best_choose=j;
			best_length=D[CURRENT_CITY][j];
		}
	return best_choose;
}
//沿着找出的最短的边进行搜索
Tour* NNAnt::search(){
	CURRENT_CITY=START_CITY;
	CURRENT_TOUR_INDEX=0;
	for(int i=0;i<N;i++)
		ALLOWED[i]=1;
	ALLOWED[CURRENT_CITY]=0;
	while(Calculate::sum_sequence(ALLOWED,N)>0)
		moveTO(choose());
	ALLOWED[START_CITY]=1;
	moveTO(START_CITY);
	return &CURRENT_TOUR;
}
NNAnt::~NNAnt(void)
{
}
