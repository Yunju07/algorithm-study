# 3키로그램과 5키로그램봉지

N = int(input())
bags = [3, 5]

dp = [float('inf')] * (N + 1)
dp[0] = 0

for bag in bags:
  for i in range(bag, N + 1):
    dp[i] = min(dp[i], dp[i-bag]+1)

if dp[N] == float('inf'):
  dp[N] = -1

print(dp[N])