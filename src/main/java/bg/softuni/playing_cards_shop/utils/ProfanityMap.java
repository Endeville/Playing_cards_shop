package bg.softuni.playing_cards_shop.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ProfanityMap {
    private Set<String> words;
    private File file;

    public ProfanityMap() throws IOException {
        this.words=new HashSet<>();
        this.file=new File("src/main/resources/static/text/profanity.txt");
        var br=new BufferedReader(new FileReader(this.file));

        var line=br.readLine().trim();
        while(line!=null){
            words.add(line);
            line=br.readLine();
        }
    }

    public ProfanityMap(File file) throws IOException {
        this.words=new HashSet<>();
        this.file=file;
        var br=new BufferedReader(new FileReader(this.file));

        var line=br.readLine().trim();
        while(line!=null){
            words.add(line);
            line=br.readLine();
        }
    }

    public boolean contains(String key){
        return this.words.contains(key);
    }

    public Set<String> words(){
        return this.words;
    }

    public boolean search(List<String> params){
        var search= String.join(" ", params);
        Pattern pattern;
        Matcher matcher;
        for (String s : this.words) {
            pattern=Pattern.compile(s);
            matcher=pattern.matcher(search);
            if(matcher.find()){
                return false;
            }
        }

        return true;
    }

    public boolean search(String[] params){
        return this.search(Arrays.stream(params).toList());
    }

    public File getFile() {
        return file;
    }

    public ProfanityMap setFile(File file) throws IOException {
        return new ProfanityMap(file);
    }
}
