# 20019. 회문의 회문 D3

# 회문(palindrome) -> 문자열이 대칭

# 길이가 홀수인 문자열이 회문의 회문
# -> 처음 (N-1)/2글자가 회문
# -> 마지막 (N-1)/2글자가 회문

# 판단법
# 1. S가 회문인지 판단
# 2. 앞뒤 글자의 회문 판단 -> 회문 판단은 함수로 분리

def isPalindrome(S):
    for i in range(len(S)//2):
        if S[i] != S[len(S) - i - 1]:
            return False

    return True


T = int(input())

for test_case in range(1, T + 1):
    NO = f"#{test_case} NO"
    YES = f"#{test_case} YES"
    S = input()
    length = len(S)

    if length % 2 == 0:
        print(NO)
        continue

    if not isPalindrome(S):
        print(NO)
        continue

    post = S[:(length-1)//2]
    back = S[(length-1)//2+1:]
    if isPalindrome(post) and isPalindrome(back):
        print(YES)
        continue

    print(NO)