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