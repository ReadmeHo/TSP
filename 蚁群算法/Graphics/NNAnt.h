#pragma once

class NNAnt :Ant{
	public:
		NNAnt(int start_city);
		int choose();
		Tour* search();
		~NNAnt(void);
};
