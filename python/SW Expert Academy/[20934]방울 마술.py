# 20934. 방울 마술 D3

# 컵의 갯수 : 3
# K : 방울이 울린 횟수 (방울있던 컵이 움직인 횟수)

T = int(input())

for test_case in range(1, T + 1):
    S, K = map(str, input().split())
    K = int(K)

    is_center = False
    # 각 위치 별, 방울이 있을 확률 초기화
    left, center, right = 0, 0, 0
    if S == "o..":
        left = 1
    if S == ".o.":
        is_center = True
        center = 1
    if S == "..o":
        right = 1

    for i in range(K):
        # 공이 중앙인 경우
        if is_center:
            left = 0.5
            right = 0.5
            center = 0
            is_center = False

        # 공이 중앙인 아닌 경우
        elif not is_center:
            center = 1
            left = 0
            right = 0
            is_center = True

    max = 0
    if left < center:
        max = 1
    if left < right:
        max = 2

    print(f"#{test_case} {max}")