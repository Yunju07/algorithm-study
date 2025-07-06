from collections import defaultdict

# 과일 탕후루
# 슬라이싱 윈도우

N = int(input())
S = list(map(int, input().split()))

count = defaultdict(int)
left = 0
max_length = 0

for right in range(N):
  count[S[right]] += 1

  if len(count) > 2:
    count[S[left]] -= 1
    if count[S[left]] == 0:
      del count[S[left]]
    left += 1
  current_length = right - left + 1
  max_length = max(max_length, current_length)  
  
print(max_length)