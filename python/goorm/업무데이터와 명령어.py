# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

def cmd_F(works):
	max_data = 0
	if len(works) != 0:
		max_data = max(works)

	works.insert(0, max_data+1)
	return

def cmd_B(works):
	max_data = 0
	if len(works) != 0:
		max_data = max(works)

	works.append(max_data+1)
	return

def cmd_P(works, done_works):
	done_works.append(works.pop(0))
	return 

def cmd_Z(works, cmds, done_works):
	first_cmd = cmds[len(cmds)-1]

	while len(cmds):
		cmd = cmds.pop()
		
		if cmd != first_cmd:
			cmds.append(cmd)
			return
		cancle_cmd(works, cmd, done_works)
		
	return 

def cancle_cmd(works, cmd, done_works):
	if cmd == "F":
		works.pop(0)
		
	if cmd == "B":
		works.pop()
		
	if cmd == "P":
		works.insert(0, done_works.pop())
	
	
def main():
	N, K = map(int, input().split())
	cmd_inputs = input()
	
	works = []    # 업무 데이터
	cmds = []     # 명령어 목록
	done_works = []   # 수행된 업무 목록들
	
	for cmd in cmd_inputs:
		if cmd == "F":
			cmd_F(works)
			cmds.append(cmd)
	
		if cmd == "B":
			cmd_B(works)
			cmds.append(cmd)
			
		if cmd == "P":
			cmd_P(works, done_works)
			cmds.append(cmd)
			
		if cmd == "Z":
			cmd_Z(works, cmds, done_works)
		
	
	print(works[K-1])

if __name__ == "__main__":
	main()