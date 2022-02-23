package com.skyit.multipledatabases;

import com.skyit.multipledatabases.tokendomain.Token;
import com.skyit.multipledatabases.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TokenController {

    private TokenRepository tokenRepository;

    @GetMapping("/data")
    public ResponseEntity<?> getAll(){

        try {
            List<Token> tokenList =  new ArrayList<>();
            tokenList.addAll(tokenRepository.findAll());
            for (Token token:tokenList){
                System.out.println(token.getToken());
            }
            return new ResponseEntity<>(tokenList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

    @GetMapping("/add")
    public ResponseEntity<?> getadd(){
        try{
            tokenRepository.save(new Token(1,"Sandaruwan"));
            tokenRepository.save(new Token(2,"Lakshitha"));
            tokenRepository.save(new Token(2,"Nadun"));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

}
