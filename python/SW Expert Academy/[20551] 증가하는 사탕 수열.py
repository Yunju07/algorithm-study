# 20551. 증가하는 사탕 수열 D3

# 3개의 상자 - A, B, C 개의 사탕
# 세현이는 A < B < C 이고, 다 1개 이상이기를 원함

# 1. 사탕을 먹어서 없애서 위의 조건을 만족시킬 수 있는지.
# 2. 최소 몇 개의 사탕을 먹어야하는지

# 출력
# 불만족 시, -1
# 만족 시, 최소 사탕 개수

T = int(input())

for test_case in range(1, T+1):
    A, B, C = map(int, input().split())

    # 불만족
    if C < 3 or B < 2:
        print(f"#{test_case} -1")

    # 최소 사탕 갯수 구하기
    eating = 0

    # -> B상자에서 먹을 캔디 갯수
    B_eating = (B - C + 1) if (B - C + 1) > 0 else 0
    B -= B_eating

    # -> A상자에서 먹을 캔디 갯수
    A_eating = (A - B + 1) if (A - B + 1) > 0 else 0

    total_eating = B_eating + A_eating

    print(f"#{test_case} {total_eating}")
