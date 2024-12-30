# 입력 예시시
# 5 5
# .....
# .....
# .....
# .....
# .....

# 출력 예시
# 5

# 감시탑이 없는 행의 갯수와 열의 갯수를 각각 구한다
# 둘 중 더 큰 값을 반환
N, M = map(int, input().split())

land = []
for i in range(N):
	# 행의 갯수 만큼 입력
	row = list(input())
	land.append(row)

row_count = 0
col_count = M
# 감시탑이 없는 행 탐색
for i in range(N):
	if not 'X' in land[i]:
		row_count += 1

# 감시탑이 없는 열 탐색
for j in range(M):
	for i in range(N):
		if land[i][j] == 'X':
			col_count -= 1
			break

print(max(row_count, col_count))
