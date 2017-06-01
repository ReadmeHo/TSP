function[MaxE,MinE,Path,Energy]=Annealing(Path,Energy,Temperature,MaxE,MinE,NumCity,Distance,MaxTrialN)

TrialN=0;%记录迭代次数
for i=1:MaxTrialN
	NewPath=Path;%计算新的路径
	%随机选择两座城市
	Index=ceil(rand(2,1)*NumCity);
	Temp1=NewPath(Index);
	%交换这两座城市
	NewPath(Index(1,1))=Temp1(1,2);
	NewPath(Index(2,1))=Temp1(1,1);
	NewEnergy=sum(Distance((NewPath([1:NumCity,1])-1)*NumCity+NewPath([2:NumCity,1:2])));%计算新回路的路径长度
	%若新回路更短，则接受新回路
	if NewEnergy<Energy
		Energy=NewEnergy;%更新能量
		Path=NewPath;%更新回路
		MinE=min([MinE,Energy]);%更新最小能量
		MaxE=max([MaxE,Energy]);%更新最大能量
	%否则，按照一定概率决定是否接受新回路
	else
		if rand<1/(1+exp((NewEnergy-Energy)/Temperature));
			Energy=NewEnergy;
			Path=NewPath;
			MinE=min([MinE,Energy]);
			MaxE=max([MaxE,Energy]);
		end
	end
end