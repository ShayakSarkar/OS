#include <stdio.h>
#include <pthread.h>

void function(int* a){
	printf("Hello World\n\n");
}
int main(){
	int a=2;
	*(&function)(&a);
}
