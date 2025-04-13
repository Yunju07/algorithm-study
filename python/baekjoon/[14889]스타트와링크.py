from itertools import combinations

N = int(input())
peoples = [list(map(int, input().split())) for _ in range(N)]
result = float('inf')

# 두 팀 간의 능력치 차이를 구하는 함수
def diff(ateam, bteam):
  # start team
  a_power = 0
  for i in ateam:
    for j in ateam:
      if i == j:
        continue
      a_power += peoples[i][j]

  # linke team
  b_power = 0
  for i in bteam:
    for j in bteam:
      if i == j:
        continue
      b_power += peoples[i][j]

  return abs(a_power - b_power)

# 가능한 모든 팀 조합에 대해 능력치 최소를 찾기
members = list(range(N))
for start_team in combinations(members, N // 2):
  link_team = list(set(members) - set(start_team))

  current_result = diff(start_team, link_team)
  result = min(result, current_result)

print(result)