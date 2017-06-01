#pragma once

class Ant{
	protected: 
		int START_CITY;//初始城市
		int CURRENT_CITY;//当前城市
		int ALLOWED[N];//禁忌表
		Tour CURRENT_TOUR;
		int CURRENT_TOUR_INDEX;
	public:
		Ant(int start_city);
		void moveTO(int to_city);
};