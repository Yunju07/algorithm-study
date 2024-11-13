# 정렬의 기준 - A[i,j] = (i-1) x N + j
# 1 부터 N*N 까지가 순서대로 들어가 있으면 댐

# 정렬을 위한 연산 -> 이 연산을 최소 몇 회 해야함?
# 1. 1 이상 N 이하의 정수 x를 고른다.
# 2. x*x 크기의 부분행렬을 전치 시킨다.

# 부분 행렬은 1~x 까지이므로, 제일 큰 N사이즈 행렬부터 연산이 필요한지
# 점검하여 점차 작아지자
# x는 N ~ 2 까지 하면 되겟다

def transpose_first(matrix, x):
    for i in range(x):
        temp = matrix[i][0]
        matrix[i][0] = matrix[0][i]
        matrix[0][i] = temp

    return matrix

T = int(input())

for a in range(T):
    # N * N 행렬 생성
    N = int(input())
    matrix = []
    for b in range(N):
        row = input().split()
        matrix.append(row)

    count = 0
    for i in range(N-1):
        x = N - i   # 부분 행렬의 크기

        # 행렬 전치가 필요한 경우
        # matrix에는 값이 문자열로 저장되어 있다는 점을 주의하자!!
        if int(matrix[0][x-1]) != x:
            matrix = transpose_first(matrix, x)
            count += 1

    print(count)