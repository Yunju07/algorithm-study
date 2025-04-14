from itertools import combinations
import copy

# 0 - 빈칸
# 1 - 벽
# 2 - 바이러스

def safe_area(maps):
  area = 0
  row = len(maps)
  col = len(maps[0])

  # 0,0 부터 오른,아래 방향으로 퍼져나가기
  for i in range(row):
    for j in range(col):
      if maps[i][j] == 2:
        if (i+1 < row) and maps[i+1][j] == 0:
          maps[i+1][j] = 2

        if (j+1 < col) and maps[i][j+1] == 0:
          maps[i][j+1] = 2

  # 각각 한번씩 더 반복
  for i in range(row-1, -1, -1):
    for j in range(col-1, -1, -1):
      if maps[i][j] == 2:
        if (i-1 >= 0) and maps[i-1][j] == 0:
          maps[i-1][j] = 2

        if (j-1 >= 0) and maps[i][j-1] == 0:
          maps[i][j-1] = 2
      
  for i in range(row):
    for j in range(col):
      if maps[i][j] == 2:
        if (i+1 < row) and maps[i+1][j] == 0:
          maps[i+1][j] = 2

        if (j+1 < col) and maps[i][j+1] == 0:
          maps[i][j+1] = 2
  
  # row, col 부터 왼, 위 방향으로 퍼져나가기
  for i in range(row-1, -1, -1):
    for j in range(col-1, -1, -1):
      if maps[i][j] == 2:
        if (i-1 >= 0) and maps[i-1][j] == 0:
          maps[i-1][j] = 2

        if (j-1 >= 0) and maps[i][j-1] == 0:
          maps[i][j-1] = 2
      
      if maps[i][j] == 0:
        area += 1
  
  return area

N, M = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(N)]
max_safe = 0

indexs = []
for i in range(N):
  for j in range(M):
    indexs.append([i,j])

# 지도의 모든 좌표 중 3개의 조합에 대해 모두 검사하기
for coords in combinations(indexs, 3):
  new_maps = copy.deepcopy(maps)
  is_break = False
 
  for index in range(3):
    x, y = coords[index][0], coords[index][1]
    
    if new_maps[x][y] != 0:
      is_break = True
      continue
    new_maps[x][y] = 1 # 벽 세우기
  
  if is_break:
    continue
  
  # 벽 세우기 끝 -> 안전 영역 크기 구하기
  area = safe_area(new_maps)
  max_safe = max(max_safe, area)

print(max_safe)