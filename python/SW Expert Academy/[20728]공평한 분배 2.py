# 20728. 공평한 분배 2 D3

T = int(input())

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, K = map(int, input().split())

    # 정렬 시간 복잡도 - O(N logN)
    candies = sorted(list(map(int, input().split())))   
    
    a = N - K
    min_diff = candies[-1]
    for i in range(a+1):
        diff = candies[i+K-1] - candies[i]
        if diff < min_diff:
            min_diff =diff

    print(f"#{test_case} {min_diff}")


# 시간 초과난 초기 코드
# 조합 생성이 오래걸림 min, max도 O(N)의 시간 복잡도를 가짐
# from itertools import combinations
#
# T = int(input())
#
# # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
# for test_case in range(1, T + 1):
#     N, K = map(int, input().split())
#     candies = list(map(int, input().split()))
#
#     com = combinations(candies, K)
#     difference = []
#     for item in com:
#         difference.append(max(item)-min(item))
#
#
#     min_diff = min(difference)
#     print(f"#{test_case} {min_diff}")