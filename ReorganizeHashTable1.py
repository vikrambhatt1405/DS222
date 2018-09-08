#file = open(r"HashTable.csv","r",encoding='utf-8')
#lines = file.readlines()
#file.close()
import fileinput
firstime=True
for line in fileinput.input():
#for line in lines[1:]:
    if(firstime):
        firstime=False
        continue
    event,count=line.split(",")
    word=event.split(" and ")#word is a list be careful
    if(word[0]==event):# if event is in the form Y=something,12 then nothing to split by "and"
        continue
    word=word[1].split("=")[1]
    print("{0},{1},{2}".format(word,event,count.replace("\n","")))
