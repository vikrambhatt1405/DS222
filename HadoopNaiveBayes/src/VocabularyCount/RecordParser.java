package VocabularyCount;
public class RecordParser {
    private String article;
    private String[] labels;
    public void parse(String record){
        String[] stopWords= {"i", "me", "my", "myself", "we", "our", "ours", "ourselves",
                             "you", "you\"re", "you\"ve", "you\"ll", "you\"d", "your", "yours",
                             "yourself", "yourselves", "he", "him", "his", "himself", "she",
                             "she\"s", "her", "hers", "herself", "it", "it\"s", "its", "itself",
                             "they", "them", "their", "theirs", "themselves", "what", "which",
                             "who", "whom", "this", "that", "that\"ll", "these", "those", "am",
                             "is", "are", "was", "were", "be", "been", "being", "have", "has",
                             "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and",
                             "but", "if", "or", "because", "as", "until", "while", "of", "at", "by",
                             "for", "with", "about", "against", "between", "into", "through", "during",
                             "before", "after", "above", "below", "to", "from", "up", "down", "in", "out",
                             "on", "off", "over", "under", "again", "further", "then", "once", "here",
                             "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more",
                             "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so",
                             "than", "too", "very", "s", "t", "can", "will", "just", "don", "don\"t", "should", "should\"ve",
                             "now", "d", "ll", "m", "o", "re", "ve", "y", "ain", "aren", "aren\"t", "couldn", "couldn\"t", "didn",
                             "didn\"t", "doesn", "doesn\"t", "hadn", "hadn\"t", "hasn", "hasn\"t", "haven", "haven\"t", "isn", "isn\"t",
                             "ma", "mightn", "mightn\"t", "mustn", "mustn\"t", "needn", "needn\"t", "shan", "shan\"t", "shouldn",
                             "shouldn\"t", "wasn", "wasn\"t", "weren", "weren\"t", "won", "won\"t", "wouldn", "wouldn\"t"};
        String[] arrOfStr = record.split("\t",2);
        labels = arrOfStr[0].split(",",-1);
        article = arrOfStr[1].toLowerCase();
        for(String stopWord:stopWords){
            article=article.replaceAll("\\b"+stopWord+"\\b"," ");
        }

        article=article.replaceAll("^<.*> <.*>"," ");
        article=article.replaceAll("@.*$"," ");
        article=article.replaceAll("\\\\u[0-9]*","");
        article=article.replaceAll("([^a-zA-Z ])"," ");
        article=article.replaceAll(" +"," ");
        //article=article.replaceAll(" [a-zA-Z]+$","");
        //article=article.replaceAll("^[a-zA-Z]+","");
        article=article.replaceAll("\\b(\\w)\\1+\\b","");
        article=article.replaceAll("\\b[a-z]\\b","");
        article=article.replaceAll(" +"," ");
        StanfordLemmatizer slem = new StanfordLemmatizer();
        article=String.join(" ",slem.lemmatize(article));
        //System.out.println(article);



    }
    public String getArticle(){
        return article;
    }
    public String[] getLabels(){
        return labels;
    }

}
