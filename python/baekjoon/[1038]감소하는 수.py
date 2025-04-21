# 1차 풀이: 9876543210 이라는 감소하는 수의 최대 범위 아래에서 모든 수에 대해 탐색하면서
# N번째가 있는지 탐색 -> 불필요한 탐색이 너무 많아 시간이 초과
# 해결책: 해당 범위 내에서 존재하는 감소하는 수는 한정적임 -> 얘를 구하자

# === 문제 풀이 ===
# N 번째 감소하는 수를 구해라
N = int(input())

results = []

def make_combination(current, last_digit):
    results.append(int(current))  # 지금까지 만든 숫자 저장
    for next_digit in range(last_digit):
        make_combination(current + str(next_digit), next_digit)

# 1자리 ~ 10자리 숫자 생성 시작
for start in range(10):
    make_combination(str(start), start)

results.sort()

if N >= len(results):
    print(-1)
else:
    print(results[N])
  


# === 1차 풀이 ===
# 값이 감소하는 수인지 구하는 함수
# def is_decline(num):
#   digits = []
#   while num > 0:
#     digits.append(num % 10)
#     num //= 10
  
#   is_decline = True
#   for i in range(len(digits)-1):
#     if digits[i] >= digits[i+1]:
#       is_decline = False
#       break

#   return is_decline

# num = 0
# index = -1
# while(True):
#   if is_decline(num):
#     index += 1 
  
#   if index == N:
#     print(num)
#     break

#   num += 1
#   if num > 9876543210:
#     print(-1)
#     break
  
  
