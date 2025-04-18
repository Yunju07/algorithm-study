# N 번째 감소하는 수를 구해라
N = int(input())

# 값이 감소하는 수인지 구하는 함수
def is_decline(num):
  digits = []
  while num > 0:
    digits.append(num % 10)
    num //= 10
  
  is_decline = True
  for i in range(len(digits)-1):
    if digits[i] >= digits[i+1]:
      is_decline = False
      break

  return is_decline

num = 0
index = -1
while(True):
  if is_decline(num):
    index += 1 
  
  if index == N:
    print(num)
    break

  num += 1
  if num > 9876543210:
    print(-1)
    break
  
  
