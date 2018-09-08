import csv
import sys
import numpy as np
import ast
import time
import operator #58773
VOCAB_SIZE=int(sys.argv[1])
classLabelFile = sys.argv[2]
classLabelCountsForANYFile=sys.argv[3]
indexWordCounterFile = sys.argv[4]
with open(classLabelFile,"r") as infile:
    reader= csv.reader(infile)
    classLabelsCount=dict((rows[0],np.float(rows[1])) for rows in reader)
    infile.close()
with open(classLabelCountsForANYFile,"r") as infile:
    reader = csv.reader(infile)
    classLabelsCountForANY = dict((rows[0],np.float(rows[1])) for rows in reader)
    infile.close()
infile = csv.reader(open(indexWordCounterFile,"r"))
logProbabilites={}

previousId = None

classLabelsCountYANY=classLabelsCount.pop('Y=ANY')
for row in infile:
    docId = row[0]
    word = row[1]
    wordCounts = "),(".join(row[2].split(")  ("))
    wordCounts =  ",".join(wordCounts.split("  "))
    wordCounts = dict(ast.literal_eval(wordCounts))

#If this shit is happening for the first time define logprobs
    if previousId is None:
        previousId = docId
        for classlabel in classLabelsCount.keys():
            logProbabilites[classlabel] = np.log(classLabelsCount[classlabel]/classLabelsCountYANY)
            #print(logProbabilites[classlabel])
            #time.sleep(1)
    #This block above happens only for inital one

    if(docId==previousId):
        for classlabel in classLabelsCount.keys():
            #print(classlabel+" and "+"W={}".format(word))
            numerator = np.float(wordCounts.get(classlabel+" and "+"W={}".format(word),0))+1
            denominator = np.float(classLabelsCountForANY.get(classlabel+" and "+"W=ANY",0)+VOCAB_SIZE)
            logProbabilites[classlabel] += np.log(numerator/denominator)
        previousId=docId
    else:
        #print("here")
        print("{0},{1}".format(previousId,max(logProbabilites.items(), key=operator.itemgetter(1))[0]))
        for classlabel in classLabelsCount.keys():
            logProbabilites[classlabel] = np.log(classLabelsCount[classlabel]/classLabelsCountYANY)
        for classlabel in classLabelsCount.keys():
            numerator = np.float(wordCounts.get(classlabel+" and "+"W={}".format(word),0))+1
            denominator = np.float(classLabelsCountForANY.get(classlabel+" and "+"W=ANY",0)+VOCAB_SIZE)
            logProbabilites[classlabel] += np.log(numerator/denominator)
        previousId=docId
print("{0},{1}".format(previousId,max(logProbabilites.items(), key=operator.itemgetter(1))[0]))
