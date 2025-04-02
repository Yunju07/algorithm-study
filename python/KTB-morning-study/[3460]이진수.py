# 양의 정수 n이 주어졌을 때, 이를 이진수로 나타냈을 때 1의 위치를 모두 찾는 프로그램을 작성하시오. 
# 최하위 비트(least significant bit, lsb)의 위치는 0이다.

# 입력
# 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, n이 주어진다. (1 ≤ T ≤ 10, 1 ≤ n ≤ 106)

# 출력
# 각 테스트 케이스에 대해서, 1의 위치를 공백으로 구분해서 줄 하나에 출력한다. 위치가 낮은 것부터 출력한다.

def two_jeansu(n):
  binary = []

  while True:
    if (n<2):
      binary.append(n)
      return binary
    
    binary.append(n%2)
    n = n//2


T = int(input())

for i in range(T):
  n = int(input())
  binary = list(map(int, bin(n)[2:]))[::-1]

  result = ""
  for i, bit in enumerate(binary):
    if bit == 1:
      result += str(i) +" "
  
  print(result)


# 개선사항
# 1. enumerate()를 활용하여, 인덱스와 요소를 동시에 접근하도록 개선
# 2. 이진수로 변환해수는 내장함수 bin() 적용
  # bin 함수는 2진수임을 나타내는 접두사 '0b' 가 붙어 있음으로, 접두사 제외 [2:]
  # 뒤집어서 LSB부터 0번 인덱스에 저장하기 위하여 [::-1] 