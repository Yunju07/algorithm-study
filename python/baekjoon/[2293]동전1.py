# n가지 종류로 동전의 값이 k가 되는 경우의 수를 모두 구하기
# 특정 종류의 동전은 얼마든지 사용가능

def count_coins(coins, remains, c_index, total_count, last_index):
  coin = coins[c_index]

  # 마지막 종류의 동전이라면 완성할 수 있는지 여부 판단해서 반환
  if c_index == last_index:
    if remains % coin == 0:
      total_count+=1
    return total_count
  
  quo = remains // coin
  # 현재 동전은 0 ~ quo 사이로 들어갈 수 있음
  for i in range(quo+1):
    new_remains = remains - (coin * i)

    if new_remains == 0:
      total_count += 1

    else:
      total_count = count_coins(coins, new_remains, c_index+1, total_count, last_index)

  return total_count  
  
def main():
  n, k = map(int, input().split())

  coins = []
  for i in range(n):
    coins.append(int(input()))

  result = count_coins(coins, k, 0, 0, n-1)

  print(result)

if __name__ == "__main__":
  main()