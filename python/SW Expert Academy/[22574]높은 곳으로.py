
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, P = map(int, input().split()) # N: 기회, P: 폭탄이 있는 층

    floor = 0
    for i in range(1, N + 1):
      # 폭탄이 있으면 이동안함
      if (floor + i) == P:
        floor -= 1
        continue
      
      # 최대층 초과확인
      if (floor + i) > 10**9 + 1:
        continue
      
      floor += i

    print(floor)
        
    

    
