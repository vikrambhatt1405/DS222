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
line = file.readline()
id=0
while True:
    parts = [p for p in line.split("\t")]
    article_text = process(parts[1:])
    #print(article_text)
    article_text = article_text.rstrip()
    print("{0},{1}".format(id,parts[0]))
    id += 1
    line = file.readline()
    if not line:
        break
file.close()
