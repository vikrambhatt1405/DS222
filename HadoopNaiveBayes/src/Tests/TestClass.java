package Tests;

import java.io.*;
public class TestClass {
    public static void main(String[] args) throws Exception {
                String filename = "/home/vikrambhatt/hashtable.txt";
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                ParseRecord recordParser = new ParseRecord();
                try{
                    while((line = reader.readLine())!=null){
                        recordParser.parse(line);
                        System.out.println("In the main: "+recordParser.getWord());

                    }
                }finally{
                    System.out.println("Closing the file.........");
                    reader.close();
                }
            }
}


