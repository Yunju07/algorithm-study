# vps 체크로직
# 1. ')' 가  '(' 보다 많아지는 경우 바로 NO 반환
# 2. 다 검사했는데 두 괄호의 갯수가 동일하지 않으면 NO


def check_vps(string):
  count_a, count_b = 0, 0

  for char in string:
    if char == '(':
      count_a += 1
    
    if char == ')':
      count_b += 1

    # 1번 조건
    if count_b > count_a:
      return "NO"

  # 2번 조건
  if count_a != count_b:
    return "NO"

  return "YES"


test_case = int(input())

for idx in range(test_case):
  string = list(input())
  print(check_vps(string))
