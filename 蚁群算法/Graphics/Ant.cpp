#include "StdAfx.h"
#include "Ant.h"

Ant::Ant(int start_city){
	START_CITY=start_city;
}
//蚂蚁移动到下一城市
void Ant::moveTO(int to_city){
	ALLOWED[to_city]=0;
	CURRENT_TOUR[CURRENT_TOUR_INDEX][0]=CURRENT_CITY;
	CURRENT_TOUR[CURRENT_TOUR_INDEX][1]=to_city;
	CURRENT_TOUR_INDEX++;
	CURRENT_CITY=to_city;
}