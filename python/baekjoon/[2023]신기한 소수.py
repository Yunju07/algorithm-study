import math

# 신기한 소수
# 1. 소수이다
# 2. 왼쪽부터 1자리, 2자리, .. N자리까지 모두 소수이다.

# 4자리 수이면, 1, 10, 100, 1000으로 나눈 것 까지 모두 소수면 됨

# 소수인지 판단
def is_prime(num):
  if num < 2:
    return False
  for i in range(2, int(math.sqrt(num))+1):
    if num % i == 0:
      return False
  return True

# 신기한 소수인지 판단
def is_amazing_prime(num, N):
  if not is_prime(num):
    return False
  
  for i in range(0, N):
    if not is_prime(num // (10 ** i)):
      return False

  return True

def main():
  N = int(input())
  amazing_primes = []

  a = 10 ** (N-1)
  b = 10 ** N
  for num in range(a, b):
    if is_amazing_prime(num, N):
      print(num)
    #amazing_primes.append(num)

if __name__ == "__main__":
  main()


# 소수란 ?
# 1과 자기 자신으로만 나누어떨어지는 수
# 2, 3, 5, 7, ...

# 파이썬에서 소수를 계산하거나 판단하는 방법
# 1. 1 부터 자기자신까지 모두 일일히 나누어보고 판단한다? -> 매우 큰 오버헤드

# 2. 제곱근까지만 나누어보기
# 어떤 수 n이 소수인지 확인할 때, 2부터 sqrt(n)까지만 나누어보면 충분