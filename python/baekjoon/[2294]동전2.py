# n가지 종류로 동전의 값이 k가 되는 경우의 수 중 최소의 동전 사용하기
# 특정 종류의 동전은 얼마든지 사용가능
  
def main():
    n, k = map(int, input().split())
    coins = [int(input()) for _ in range(n)]

    dp = [float('inf')] * (k + 1)
    dp[0] = 0  

    for coin in coins:
        for i in range(coin, k + 1):
          dp[i] = min(dp[i], dp[i-coin]+1) 

    if dp[k] == float('inf'):
       dp[k] = -1

    print(dp[k])

if __name__ == "__main__":
  main()

# ✅ dp[i] = min(dp[i], dp[i-coin]+1) 의 의미
# "현재 금액(i)을 만들기 위한 최소한의 동전의 수, dp[i]는
# coin 하나를 제외한 나머지 금액을 만드는 데 필요한 최소 동전 개수인, dp[i - coin]에
#  지금 사용 중인 동전 1개 추가한 것과 현재 것 중 더 작은 것을 선택한다.