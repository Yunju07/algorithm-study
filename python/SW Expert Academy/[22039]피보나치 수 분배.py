# 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
# 아래 표준 입출력 예제 필요시 참고하세요.

# 표준 입력 예제
'''
a = int(input())                        정수형 변수 1개 입력 받는 예제
b, c = map(int, input().split())        정수형 변수 2개 입력 받는 예제 
d = float(input())                      실수형 변수 1개 입력 받는 예제
e, f, g = map(float, input().split())   실수형 변수 3개 입력 받는 예제
h = input()                             문자열 변수 1개 입력 받는 예제
'''

# 표준 출력 예제
'''
a, b = 6, 3
c, d, e = 1.0, 2.5, 3.4
f = "ABC"
print(a)                                정수형 변수 1개 출력하는 예제
print(b, end = " ")                     줄바꿈 하지 않고 정수형 변수와 공백을 출력하는 예제
print(c, d, e)                          실수형 변수 3개 출력하는 예제
print(f)                                문자열 1개 출력하는 예제
'''


def fibonacci_sum(fa, fb):
    fc = fa + fb
    return fb, fc

def fibonacci_division(list):
    sumA, sumB = [], []
    result = []
    
    while list:
       sumA.append(list.pop())
       result.append('A')
       
       if list:
          sumB.append(list.pop())
          result.append('B')

       if list:
          sumB.append(list.pop())
          result.append('B')

     
    if sum(sumA) == sum(sumB):
        return result
    else:
        result = ["impossible"]
        return result
    

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    
    if N <= 1:
      print("impossible")
      continue

    # 피보나치 수열 구하기
    fa, fb = 1, 1
    fibonacci_list = [1, 1]
    i=2
    while True:
       if i == N:
          break
       i += 1

       fa, fb = fibonacci_sum(fa, fb)
       fibonacci_list.append(fb)

    # 피보나치 수열 분배
    result = fibonacci_division(fibonacci_list)
    result.reverse()
    
    result_str = ""
    for str in result:
       result_str += str

    print(result_str)
      
