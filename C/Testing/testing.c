#include<stdio.h>
#include<string.h>

int main(){
    char testVar[5];
    scanf("%5s", testVar);
    int size = sizeof(testVar);
    int len = strlen(testVar);
    printf("%d, %d\n", len, size);
    printf("%s", testVar);
    return 0;
}