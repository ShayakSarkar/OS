#include <omp.h>
#include <stdio.h>
int main(){

	int sum=0;
	#pragma omp parallel
	{	
		for(int i=0;i<100;i++)
			sum+=i;
	}
	printf("%d",sum);
	return 0;

}

