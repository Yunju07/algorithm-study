def solution(survey, choices):
    answer = ''
    
    # score
    r, t, c, f, j, m, a, n = 0, 0, 0, 0, 0, 0, 0, 0
    
    for i in range(len(survey)):
        s = survey[i]
        choice = choices[i]
        
        # 선택지에 따라 분기
        idx = 0
        score = 0
        if choice < 4:
            idx = 0
            score = 4-choice
        if choice > 4:
            idx = 1
            score = choice-4
        if choice == 4:
            continue;
        
        # 점수 추가
        type = s[idx]  # 선택한 유형
        
        if type == 'R':
            r += score
        if type == 'T':
            t += score
        if type == 'C':
            c += score
        if type == 'F':
            f += score
        if type == 'J':
            j += score
        if type == 'M':
            m += score
        if type == 'A':
            a += score
        if type == 'N':
            n += score
    
        
    # 최종 점수에 따른 성격 결정      
    if r >= t:
        answer += "R"
    else:
        answer += "T"
        
    if c >= f:
        answer += "C"
    else:
        answer += "F"
       
    if j >= m:
        answer += "J"
    else:
        answer += "M"
    
    if a >= n:
        answer += "A"
    else:
        answer += "N"
    
    return answer