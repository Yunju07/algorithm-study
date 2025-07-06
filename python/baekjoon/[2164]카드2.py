from collections import deque

def game(nums):
     # 맨 앞 카드 버림
    nums.popleft() 

    # 그다음 맨 앞 카드를 뒤로 보냄
    if nums:
        nums.append(nums.popleft())  

N = int(input())
nums = deque(range(1, N + 1))

while len(nums) > 1:
    game(nums)

print(nums[0])
