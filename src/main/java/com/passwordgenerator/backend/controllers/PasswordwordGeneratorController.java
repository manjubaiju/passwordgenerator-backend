package com.passwordgenerator.backend.controllers;

import com.passwordgenerator.backend.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class PasswordwordGeneratorController {
    ArrayList<String> wordArrayList = new ArrayList<String>();
    ArrayList<String> personList = new ArrayList<String>();

    @GetMapping("/words")
    public Map<String, Object> showWords() {
        return Collections.singletonMap("words", wordArrayList.toArray());
    }

    @PostMapping("/words")
    public WordCreator addWords(@RequestBody WordCreator word) {
        //code
        String  words = word.getWord();

        if (!wordArrayList.contains(word)) {
            wordArrayList.add(words);
        }

        System.out.println(wordArrayList);
        return word;

    }

    @GetMapping("/generatePwd")
    public String generatePwd() {

        int iMaxLength = 3;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for( String words: wordArrayList) {
            for (int i = 0; i < iMaxLength; i++) {
                sb.append(words.charAt(rnd.nextInt(words.length())));
            }
        }
        System.out.println("Generated Pwd "+sb.toString() );
        return sb.toString();
    }

    @DeleteMapping("/words")
    public void removeWords(@RequestBody WordCreator word) {
        //code
        String  words = word.getWord();
        wordArrayList.remove(words);
        System.out.println("After Removing :"+wordArrayList);


    }

}