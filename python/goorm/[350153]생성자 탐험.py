# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

def make_divideSum(g):
	sum = g
	
	while True:
		sum += g % 10
		g //= 10
		
		if g < 10:
			sum += g
			break	
	
	return sum

N = int(input())
generator = N // 3

while True:
	divideSum = make_divideSum(generator)
	
	if divideSum == N:
		break
	
	generator += 1
	
	if generator == N:
		generator = 0
		break
		
print(generator)

