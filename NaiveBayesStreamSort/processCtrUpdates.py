import fileinput
previousKey=None
sumForPreviousKey=0

def OutputPreviousKey():
    if previousKey is not None:
        print("{0},{1}".format(previousKey,sumForPreviousKey))

for line in fileinput.input():
    (event,delta)=line.split(",")
    if(event == previousKey):
        sumForPreviousKey += int(delta)
    else:
        OutputPreviousKey()
        previousKey = event
        sumForPreviousKey = int(delta)
OutputPreviousKey()