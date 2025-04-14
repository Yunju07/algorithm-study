# 정사각형 4개를 이어 붙인 폴리오미노 -> 테트로미노
# 5종류 -> 테트리스 게임에 나오는 그 5종류

# 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램
# N x M 크기의 종이
N, M = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(N)]
result = 0

# 지도와 모양이 주어졌을 때 가장 큰 영역의 합을 반환하는 함수
def max_area(maps, offset):
  max_sum = 0
  row = len(maps)
  col = len(maps[0])

  for i in range(row):
    for j in range(col):      
      current_sum = 0
      is_possible = True
      
      for index in range(4):
        x, y = i + offset[index][0], j + offset[index][1]
        if x < 0 or x >= row or y < 0 or y >= col:
          is_possible = False
          continue
        current_sum += maps[x][y]
      
      if (is_possible == True):
        # print("-----------")
        # print("i, j:" + str(i) + ", " + str(j))
        # print(offset)
        # print(current_sum)
        max_sum = max(max_sum, current_sum)
        
  return max_sum

# 테트로미노의 종류별로 가능한 모든 합을 계산하여서 최대값을 구하자

tetromino_shapes = [
  # 1. 일자
    [[0,0],[0,1],[0,2],[0,3]],
    [[0,0],[1,0],[2,0],[3,0]],
    
    # 2. 정사각형
    [[0,0],[0,1],[1,0],[1,1]],
    
    # 3. L형 -> 회전, 대칭으로 8종류
    [[0,0],[1,0],[2,0],[2,1]],
    [[0,1],[1,1],[2,1],[2,0]],
    [[0,0],[0,1],[1,1],[2,1]],
    [[0,0],[0,1],[1,0],[2,0]],
    [[0,0],[1,0],[1,1],[1,2]],
    [[0,2],[1,0],[1,1],[1,2]],
    [[0,0],[0,1],[0,2],[1,0]],
    [[0,0],[0,1],[0,2],[1,2]],
    
    # 4. ㅗ, ㅜ, ㅏ, ㅓ
    [[0,0],[0,1],[0,2],[1,1]],
    [[1,0],[1,1],[1,2],[0,1]],
    [[0,0],[1,0],[2,0],[1,1]],
    [[0,1],[1,1],[2,1],[1,0]],
    
    # 5. S/Z자
    [[0,1],[0,2],[1,0],[1,1]], # S
    [[0,0],[1,0],[1,1],[2,1]], # S 회전
    [[0,0],[0,1],[1,1],[1,2]], # Z
    [[0,1],[1,0],[1,1],[2,0]] # Z 회전 
]

for offset in tetromino_shapes:
  area = max_area(maps, offset)
  result = max(result, area)

print(result)