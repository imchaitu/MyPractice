''' Read input from STDIN. Print your output to STDOUT '''
    #Use input() to read input from STDIN and use print to write your output to STDOUT

def main():
 testCases = int(input().strip())
 results = []
 count = 0
 while count < testCases:
  fighters = int(input().strip())

  villainsEnergy = None
  playersEnergy = None

  villainsEnergy = list(map(int, input().strip().split(' ')))
  if len(villainsEnergy) > fighters:
   raise Exception('Given strengths number ' + str(len(villainsEnergy)) +  ' is greater than villains count ' + str(fighters) + '.')

  playersEnergy = list(map(int,input().strip().split(' ')))
  if len(playersEnergy) > fighters:
   raise Exception('Given strengths number ' + str(len(playersEnergy)) + ' is greater than players count ' + str(fighters) + '.')

  fights = 0
  res = 'WIN'
  while fights < fighters:
   if max(villainsEnergy) >= max(playersEnergy):
    res = 'LOSE'
    break
   else:
    res = 'WIN'
   villainsEnergy.remove(max(villainsEnergy))
   playersEnergy.remove(max(playersEnergy))
   fights += 1
  results.append(res)
  count += 1

 for r in results:
  print(r)

main()

