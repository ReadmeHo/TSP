#pragma once

class AntColonySystem;
class ACSAnt :Ant{
	public:
		ACSAnt(AntColonySystem* acs,int start_city);
		~ACSAnt(void);
	private:
		AntColonySystem* ACS;
	public:
		int choose();
		Tour* search();
};
