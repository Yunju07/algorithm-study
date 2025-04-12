# 지도 N x M
# 주사위 좌표 (x, y)

def check(r, c, N, M):
  if r < 0 or r > N-1:
    return False
  
  if c < 0 or c > M-1:
    return False
  
  return True

def main():
  N, M, x, y, cmd_num = map(int, input().split())

  # 초기 주사위 배열
  dice_r = [0,0,0]
  dice_c = [0,0,0,0]

  map_= []
  for _ in range(N):
    map_.append(list(map(int, input().split())))

  # 명령어 처리 시작
  cmd = list(map(int, input().split()))
  for c in cmd:
    # 동쪽
    if c == 1:
      i = check(x, y+1, N, M)  
      if i:
        x, y = x, y+1
        # 주사위 이동
        a = dice_r.pop()
        b = dice_c.pop()
        dice_r.insert(0, b)
        dice_c.append(a)
        dice_c[1] = dice_r[1]

    # 서쪽
    elif c == 2:
      i = check(x, y-1, N, M)
      if i:
        x, y = x, y-1
        # 주사위 이동
        a = dice_r.pop(0)
        b = dice_c.pop()
        dice_r.append(b)
        dice_c.append(a)
        dice_c[1] = dice_r[1]
        
    # 북쪽
    elif c == 3:
      i = check(x-1, y, N, M)
      if i:
        x, y = x-1, y
        # 주사위 이동
        a = dice_c.pop(0)
        dice_c.append(a)
        dice_r[1] = dice_c[1]

    # 남쪽
    elif c == 4:
      i = check(x+1, y, N, M)
      if i:
        x, y = x+1, y
        # 주사위 이동
        a = dice_c.pop()
        dice_c.insert(0, a)
        dice_r[1] = dice_c[1]

    # 주사위와 지도의 상호작용
    if i:
      if map_[x][y] == 0:
        map_[x][y] = dice_c[3]
      
      else: 
        dice_c[3] = map_[x][y]
        map_[x][y] = 0

      # 출력
      print(dice_r[1])

  return

if __name__ == "__main__":
  main()