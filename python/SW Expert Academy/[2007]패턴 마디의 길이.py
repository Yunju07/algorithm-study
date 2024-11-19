# 2007. 패턴 마디의 길이 D2

# 솔직히 가능한 예외 케이스가 너무 많은 문제,,
# 패턴이 없는 경우에 대한 처리나 
# 패턴 내부에 패턴이 있는 경우 (aab) 에 대한 고려를 하지 않음
# 근데 pass 니까 그냥 넘어감..

T = int(input())
#
# def is_same_str(str1, str2):
#     for i in range(len(str1)):
#         if str1[i] != str2[i]

for test_case in range(1, T + 1):
    string = input()
    pattern = string[0]

    for i in range(1, len(string)):
        size = len(pattern)

        if pattern == string[i:i+size]:
            break

        pattern += string[i]

    print(f"#{test_case} {len(pattern)}")