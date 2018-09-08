import time
import sys
filename = sys.argv[1]
file = open(filename,"r")
line = file.readline()
previousKey=None
wordCounts=""
idx=None


def OutputPreviousKey(idx,word,wordCounts):
    if previousKey is not None:
        print("{0},{1},{2}".format(idx,word,wordCounts))
def lineIsTuple(line):
    return len(line.split(","))==2
def processConescutiveLinesBlock(consecutiveLinesBlock):
    if(lineIsTuple(consecutiveLinesBlock[0])):
        pass
    else:
        word=consecutiveLinesBlock[0].split(",")[0]
        wordCounts = " ".join(consecutiveLinesBlock[0].split(",")[1:]).rstrip()
        for line in consecutiveLinesBlock[1:]:
            print("{0},{1},{2}".format(line.split(",")[1].strip(),word.strip(),wordCounts))
line = file.readline()
consecutiveLinesBlock = []
while True:
    word = line.split(",")[0]
    if previousKey is None:
        previousKey=word
        consecutiveLinesBlock.append(line)
        line = file.readline()
        continue
    if(word==previousKey):
        consecutiveLinesBlock.append(line)
    else:
        processConescutiveLinesBlock(consecutiveLinesBlock)
        previousKey=word
        consecutiveLinesBlock=[]
        consecutiveLinesBlock.append(line)
    line = file.readline()
    if not line:
        break
    #time.sleep(0.5)


file.close()
