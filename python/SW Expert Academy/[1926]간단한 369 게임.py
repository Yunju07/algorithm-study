# 1926. 간단한 369 게임 D2

def check_369(num):
    if num == 0:
        return ""

    if num % 3 == 0 :
        return "-"

    if num % 6 == 0:
        return "-"

    if num % 9 == 0:
        return "-"

    return ""

# N : 10 ~ 1000
N = int(input())

total_output = ""
for i in range(1, N+1):
    num = i
    round_output = ""
    while(1):
        round_output += check_369(i % 10)

        if (i < 10):
            break
        i = i // 10
    
    # 모든 자리에 3,6,9 숫자가 없는 경우 그냥 숫자 반환
    if round_output == "":
        round_output += str(num)

    total_output += round_output + " "

print(total_output)