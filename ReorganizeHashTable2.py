import fileinput
previousKey=None
wordCount = []

def OutputPreviousKey():
    if previousKey is not None:
        print("{0},1,{1}".format(previousKey,wordcount))

for line in fileinput.input():
    (word,event,count)=line.split(",")
    if(word == previousKey):
        wordcount.append(tuple((event,count.rstrip())))
    else:
        OutputPreviousKey()
        previousKey = word
        wordcount=[]
        wordcount.append(tuple((event,count.rstrip())))
OutputPreviousKey()
