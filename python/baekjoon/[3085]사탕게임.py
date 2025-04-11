# 인접한 두 칸을 골라서 사탕을 바꾼다
# 가로로 바꿀 수도 있고, 세로로 바꿀 수도 있음
import copy

def find_max(board, r, c):
  result = 1
  # 가로로 가장 긴 것
  for i in range(r):
    candies = board[i]
    max_len = 1
    current_len = 1

    for j in range(c-1):
      if candies[j] == candies[j+1]:
        current_len += 1
        max_len = max(max_len, current_len)
      else:
        current_len = 1
        
    result = max(max_len, result)

  # 세로로 가장 긴 것
  for i in range(c):
    candies = [row[i] for row in board]
    max_len = 1
    current_len = 1

    for j in range(c-1):
      if candies[j] == candies[j+1]:
        current_len += 1
        max_len = max(max_len, current_len)
      else:
        current_len = 1

    result = max(max_len, result)

  return result

def main():
  N = int(input())
  board = []

  for i in range(N):
    row = list(input())
    board.append(row)

  # 사탕은 한 줄(가로세로포함)에서 총 N-1번 바꾸게 됨
  # 가로로 바꾸기
  max_eats = 1  # 사탕은 최소 1개는 먹게 됨
  for row in range(N):
    for col in range(N-1):
      new_board = copy.deepcopy(board)
      c1, c2 = new_board[row][col], new_board[row][col+1]

      if c1 == c2:
        continue
      
      temp = new_board[row][col]
      new_board[row][col] = new_board[row][col+1]
      new_board[row][col+1] = temp

      # 교환하게 되었을 때의 최대 먹는 사탕
      current_max = find_max(new_board, N, N)
      max_eats = max(max_eats, current_max)

  # 세로로 바꾸기기
  for row in range(N-1):
    for col in range(N):
      new_board = copy.deepcopy(board)
      c1, c2 = new_board[row][col], new_board[row+1][col]

      if c1 == c2:
        continue
      
      temp = new_board[row][col]
      new_board[row][col] =  new_board[row+1][col]
      new_board[row+1][col] = temp

      # 교환하게 되었을 때의 최대 먹는 사탕
      current_max = find_max(new_board, N, N)
      max_eats = max(max_eats, current_max)
  
  print(max_eats)

if __name__ == "__main__":
  main()