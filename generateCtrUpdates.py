import re
import nltk
import sys
from nltk.corpus import stopwords
stopwords = set(stopwords.words('english'))
def process(parts):
    for part in parts:
            pattern = r'^<.*> <.*> "'
            for p in re.findall(pattern,part):
                part = part.replace(p,' ').lower()
            pattern = r'"@.*$'
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')
            pattern = r'\\u[0-9]*'
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')
            pattern = r'([^a-zA-Z_ ])'#'|()'
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')
            pattern = r' [a-zA-Z]{1} '
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')
            pattern = r' [a-zA-Z]{1}$'
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')
            pattern = r'^[a-zA-Z]{1} '
            for p in re.findall(pattern,part):
                part = part.replace(p,' ')

    return part

filename = sys.argv[1]
file = open(filename,"r")
vocabulary = set()
line = file.readline()
while True:
    parts = [p for p in line.split("\t")]
    article_text = process(parts[1:])
    #print(article_text)
    article_text = article_text.rstrip()
    for label in parts[0].split(","):
        print("Y={0},1".format(label.rstrip()))
        print("Y=ANY,1")
        for word in article_text.split(" "):
            if(word.rstrip()!="" and not word in stopwords):
                vocabulary.add(word.rstrip())
                print("Y={0} and W={1},1".format(label.rstrip(),word.rstrip()))
                print("Y={0} and W=ANY,1".format(label.rstrip()))
    line = file.readline()
    if not line:
        break
file.close()


print(len(vocabulary))
