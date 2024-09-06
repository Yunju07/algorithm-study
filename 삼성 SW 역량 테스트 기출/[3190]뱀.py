
def main():
  # N = 보드의 크기
  # K = 사과의 갯수
  N = int(input())
  K = int(input())


  # 사과의 위치
  apple_location = []
  for _ in range(K):
    location = list(map(int, input().strip().split()))[:2]
    apple_location.append(location)


  # 보드 생성 (파이썬 2차원 배열)
  # 0 없음 / 1 사과 / 2 뱀?
  board = [[0] * N for _ in range(N)]
  for a in apple_location:
    board[a[0]-1][a[1]-1] = 1


  # 뱀의 방향 변환 정보
  L = int(input())  # 횟수
  change_list = [] # 변환 정보
  for _ in range(L):
    a, b = input().split(' ')
    change_list.append([int(a),b])

  # 게임 시작
  t = 0   # 시간(초)
  head_dir = 1  # 머리방향 / 0(위) 1(오른쪽) 2(아래) 3(왼쪽)
  dummy = [[0,0]]   # 초기 뱀의 위치
  change = change_list.pop(0)

  while 1:
    t += 1 
    head = dummy[0]  

    # 이동할 칸
    if head_dir == 0:
      r, c = head[0]-1, head[1]

    elif head_dir == 1:
      r, c = head[0], head[1]+1

    elif head_dir == 2:
      r, c = head[0]+1, head[1]

    elif head_dir == 3:
      r, c = head[0], head[1]-1


    # 게임 종료 조건 확인
    if r < 0 or r > N-1: # 1. 벽에 부딪힘
      print(t)
      #print("벽에 부딪힘, row: "+ str(r))
      return
    if c < 0 or c > N-1: # 1. 벽에 부딪힘
      print(t)
      #print("벽에 부딪힘, col: "+ str(c))
      return
    
    if [r, c] in dummy: # 2. 자기 자신과 부딪힘
      print(t)
      #print("자기자신과 부딪힘, 위치: "+ str([r,c]))
      return

    dummy.insert(0, [r,c])
    
    # 사과가 있는 지 확인
    if board[r][c] == 1:
      board[r][c] = 0
    
    else:
      dummy.pop()

    # 방향 변환
    if change[0] == t:
      if change[1] == 'D':
        head_dir = (head_dir + 1) % 4

      elif change[1] == 'L':
        head_dir = (head_dir - 1) % 4

      if change_list:
        change = change_list.pop(0)
    
    # print("시간: "+str(t))
    # print("보드")
    # for row in board:
    #   print(row)
    # print("뱀")
    # print(dummy)
  
    
if __name__ == "__main__":
  main()