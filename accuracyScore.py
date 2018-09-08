import csv
import numpy as np
predictedLabelsFile = csv.reader(open("PredictedClasses.csv","r"))
testLabelsFile = csv.reader(open("TestLabels.csv","r"))
predictedLabels=[]
groundTruths_1=[]
groundTruths_2=[]
for row in predictedLabelsFile:
    predictedLabels.append(row[1].split("=")[1])
for row in testLabelsFile:
    groundTruths_1.append(row[1])
for row in testLabelsFile:
    groundTruths_2.append(row[2])
matches_1 = np.array([1 for i, j in zip(predictedLabels,groundTruths_1) if i == j])
matches_2 = np.array([1 for i, j in zip(predictedLabels,groundTruths_2) if i == j])
#print(matches)
score = (np.sum(matches_1)+np.sum(matches_2))/len(predictedLabels)
print("Accuracy:{0}".format(score))
