#include "StdAfx.h"
#include "Ant.h"
#include "ACSAnt.h"
#include "AntColonySystem.h"

ACSAnt::ACSAnt(AntColonySystem* acs,int start_city):Ant(start_city){
	ACS=acs;
}

ACSAnt::~ACSAnt(void)
{
}
//选择移动方向
int ACSAnt::choose(){
	double q=rand()/(double)RAND_MAX;
	if(q<ACS->Q0){
		double best_value=-1.0;
		int best_choose=-1;
		for(int j=0;j<N;j++)
			if((ALLOWED[j]==1)&&(ACS->transition(CURRENT_CITY,j)>best_value)){
				best_choose=j;
				best_value=ACS->transition(CURRENT_CITY,j);
			}
		return best_choose;
	}
	//按概率选择移动方向
	double sum=ACS->sum_transition(CURRENT_CITY,ALLOWED);
	double p=rand()/(double)RAND_MAX;
	double p_j=0.0;
	for(int j=0;j<N;j++){
		if(ALLOWED[j]==1)
			p_j=p_j+ACS->transition(CURRENT_CITY,j)/sum;
		if((p<p_j)&&(ALLOWED[j]==1))
			return j;
	}
	return -1;
}
//选择移动方向，应用局部更新公式进行激素更新
Tour* ACSAnt::search(){
	CURRENT_CITY=START_CITY;
	int tocity;
	CURRENT_TOUR_INDEX=0;
	for(int i=0;i<N;i++)
		ALLOWED[i]=1;
	ALLOWED[CURRENT_CITY]=0;
	int LAST_CITY;
	while(1){
		LAST_CITY=CURRENT_CITY;
		tocity=choose();
		if(tocity==-1)
			break;
		moveTO(tocity);
		ACS->local_update_rule(LAST_CITY,CURRENT_CITY);
	}
	ALLOWED[START_CITY]=1;
	ACS->local_update_rule(CURRENT_CITY,START_CITY);
	moveTO(START_CITY);
	return &CURRENT_TOUR;
}
