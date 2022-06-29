package application.back;
import java.io.*;

public class Parser {

            private Parser(){}

            public static void loadData(String path){
                String line = "";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    int index = 1;
                    while((line = reader.readLine()) != null){
                          String tokens[] =  line.split(" ");
                          for(int i = 0; i < tokens.length; i ++){
                              System.out.print(tokens[i]);
                          }
                        System.out.println();
                        index++;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
}
