# n가지 종류로 동전의 값이 k가 되는 경우의 수를 모두 구하기
# 특정 종류의 동전은 얼마든지 사용가능
  
def main():
    n, k = map(int, input().split())
    coins = [int(input()) for _ in range(n)]

    dp = [0] * (k + 1)
    dp[0] = 1  # 금액 0을 만드는 방법 1가지

    for coin in coins:
        for amount in range(coin, k + 1):
            dp[amount] += dp[amount - coin]

    print(dp[k])

if __name__ == "__main__":
  main()

# ✅ dp[amount] += dp[amount - coin] 의 의미
# "현재 금액(amount)을 만들기 위한 방법의 수는,
# 바로 이전 금액(amount - coin)을 만들 수 있는 방법의 수에
# 현재 동전(coin)을 추가한 만큼이다."