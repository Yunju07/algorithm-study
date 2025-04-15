from collections import deque

# 정점의 개수 N(1 ≤ N ≤ 1,000), 
# 간선의 개수 M(1 ≤ M ≤ 10,000), 
# 탐색을 시작할 정점의 번호 V
N, M, V = map(int, input().split())

# 그래프를 초기화하기
graph = {i: [] for i in range(1, N+1)}

# 양방향 간선 정보
for _ in range(M):
  a, b = map(int, input().split())
  graph[a].append(b)
  graph[b].append(a)

# 정점 번호가 작은 것부터 방문하도록 정렬
for node in graph:
  graph[node].sort()

# DFS 구현 
def dfs(v, visited):
  visited.add(v)
  print(v, end=" ")
  for neighbor in graph[v]:
    if neighbor not in visited:
      dfs(neighbor, visited)

# BFS 구현
def bfs(v):
  visited = set()
  queue = deque([v])
  visited.add(v)

  while queue:
    current = queue.popleft()
    print(current, end=" ")
    for neighbor in graph[current]:
      if neighbor not in visited:
        visited.add(neighbor)
        queue.append(neighbor)


dfs(V, set())
print()
bfs(V)