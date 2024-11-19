# 1954. 달팽이 숫자 D2

# 달팽이는 N * N 크기
# 달팽이는 N X N matrix 이다.

T = int(input())

for test_case in range(1, T+1):
    N = int(input())

    snail = [[0] * N for _ in range(N)]
    x, y, i = 0, 0, 1

    # t % 4 : [0 ~ 3]
    for t in range(2*N-1):
        case = t % 4
        limit = (t+1) // 4

        # -> 방향 이동
        if case == 0:
            while(y < N - limit):
                snail[x][y] = i
                i += 1
                y += 1
            y -= 1
            x += 1

        # 아래 방향 이동
        if case == 1:
            while (x < N - limit):
                snail[x][y] = i
                i += 1
                x += 1
            x -= 1
            y -= 1


        # <- 방향 이동
        if case == 2:
            while (y >= limit):
                snail[x][y] = i
                i += 1
                y -= 1
            y += 1
            x -= 1

        # 위 방향 이동
        if case == 3:
            while (x >= limit):
                snail[x][y] = i
                i += 1
                x -= 1
            x += 1
            y += 1

    print(f"#{test_case}")
    for row in snail:
        s = ""
        for num in row:
            s += str(num) + " "
        print(s)