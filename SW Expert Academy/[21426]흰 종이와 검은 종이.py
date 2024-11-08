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


'''
아래의 구문은 input.txt 를 read only 형식으로 연 후,
앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
아래 구문을 사용하기 위해서는 import sys가 필요합니다.
단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
#import sys
#sys.stdin = open("input.txt", "r")

def paper(a, b, c, d, e, f, g, h):
  # 흰 종이 좌표 : (a, b) (c, d)
  # 검은 종이 좌표 : (e, f) (g, h)
  # 왼쪽 아래 좌표는 흰 종이가 커야하고
  # 오른쪽 위 좌표는 검은 종이가 큰 것이 좋다

  if e>=c or f>=d or a>=g or b>=h:
    return([[a, b, c, d]])

  remains = []

  x1 = e - a
  y1 = f - b
  x2 = c - g
  y2 = d - h
  
  # 모서리에 생기는 여분 사각형
  if x1 > 0 and y1 > 0:
    remains.append([a, b, e, f])

  if x1 > 0 and y2 > 0:
    remains.append([a, h, e, d])
  
  if x2 > 0 and y1 > 0:
    remains.append([g, b, c, f])
  
  if x2 > 0 and y2 > 0:
    remains.append([g, h, c, d])
  
  # 중간에 생기는 사각형
  if x1 > 0 and (h-f) > 0:
    if y2 < 0:
      remains.append([a, f, e, d])
    if y1 < 0:
      remains.append([a, b, e, h])
    else:
      remains.append([a, f, e, h])
      

  if y1 > 0 and (g-e) > 0:
    if x1 < 0:
      remains.append([a, b, g, f])
    if x2 < 0:
      remains.append([e, b, c, f])
    else:
      remains.append([e, b, g, f])
  
  if x2 > 0 and (h-f) > 0:
    if y2 < 0:
      remains.append([g, f, c, d])
    if y1 < 0:
      remains.append([g, b, c, h])
    else:
      remains.append([g, f, c, h])
  
  if y2 > 0 and (g-e) > 0:
    if x1 < 0:
      remains.append([a, h, g, d])
    if x2 < 0:
      remains.append([e, h, c, d])
    else:
      remains.append([e, h, g, d])
  
  
  return remains



T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1): 
  #흰 종이 좌표
  x1, y1, x2, y2 = map(int, input().split())
   
  # 검은 종이 좌표
  x3, y3, x4, y4 = map(int, input().split())
  x5, y5, x6, y6 = map(int, input().split())
  
  remain1 = paper(x1, y1, x2, y2, x3, y3, x4, y4)

  total_remain = []
  for a in remain1:
    total_remain += paper(a[0], a[1], a[2], a[3], x5, y5, x6, y6)
  
  if total_remain:
    print("YES")
  
  else:
    print("NO")
  