2차원 배열(그리드) 내에서 세로 및 가로로 인접한 같은 값의 연결 영역(덩어리) 중 가장 큰 영역을 찾으려면 **깊이 우선 탐색(DFS)** 또는 **너비 우선 탐색(BFS)** 알고리즘을 사용할 수 있다. 이 문제는 흔히 **Flood Fill** 문제라고도 하며, 각 셀을 방문하며 같은 값이 이어져 있는지 확인하는 방식으로 해결할 수 있다.

---
-> BFS 를 사용하여 해결하기

### 알고리즘 개요
1. 방문 여부 체크:  
  동일한 셀을 반복해서 방문하지 않도록 `visited` 배열 생성

2. 각 셀의 인접 영역 탐색:  
  2차원 배열의 각 셀에 대해 아직 방문하지 않은 경우, BFS(또는 DFS)를 사용하여 인접(상하좌우)한 같은 값의 셀을 모두 탐색

3. 최대 영역 갱신:  
  각 영역의 크기를 비교하여, 가장 큰 영역의 셀 개수를 저장


### 예제 코드
```python
from collections import deque

def largest_connected_area(grid):
    # 배열이 비어있으면 0을 반환
    if not grid or not grid[0]:
        return 0

    rows, cols = len(grid), len(grid[0])
    visited = [[False for _ in range(cols)] for _ in range(rows)]
    # 상하좌우 (4방향) 탐색을 위한 이동 좌표
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def bfs(start_r, start_c, target_value):
        queue = deque([(start_r, start_c)])
        visited[start_r][start_c] = True
        area = 1  # 시작 셀 포함

        while queue:
            r, c = queue.popleft()
            # 4방향으로 인접 셀 확인
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                if 0 <= nr < rows and 0 <= nc < cols:
                    if not visited[nr][nc] and grid[nr][nc] == target_value:
                        visited[nr][nc] = True
                        queue.append((nr, nc))
                        area += 1
        return area

    max_area = 0
    # 모든 셀을 순회하며 아직 방문하지 않은 셀에 대해 BFS 실행
    for r in range(rows):
        for c in range(cols):
            if not visited[r][c]:
                current_area = bfs(r, c, grid[r][c])
                max_area = max(max_area, current_area)
    
    return max_area

# 예시 배열
grid = [
    [1, 1, 0, 2],
    [1, 0, 0, 2],
    [0, 0, 1, 1],
    [2, 2, 1, 1]
]

# 결과 출력: 동일한 값으로 연결된 가장 큰 영역의 크기를 출력
print(largest_connected_area(grid))  
```

### 코드 설명
- 방문 배열 생성:  
  visited 배열을 만들어 각 셀을 처음 방문하는지 추적

- BFS 함수:  
  bfs 함수는 시작점과 타겟 값을 받아, 해당 값과 같은 셀을 상하좌우로 연결되어 있는지 확인하며 카운트

  - queue를 사용하여 탐색할 좌표를 저장하고, 각 셀을 방문 표시
  - 인접한 셀 중 같은 값이며 방문하지 않았다면 queue에 추가하고, 카운트를 증가

- 전체 탐색:  
  2차원 배열의 모든 셀에 대해 아직 방문하지 않은 경우 BFS를 호출하여 연결된 영역의 크기를 계산하고, 최대 크기를 갱신

---

+추가 학습
- 그리드
- 깊이 우선 탐색(DFS)
- 너비 우선 탐색(BFS)