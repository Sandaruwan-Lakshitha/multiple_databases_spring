package com.skyit.multipledatabases.token;

import com.skyit.multipledatabases.tokendomain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TokenRepository")
public interface TokenRepository extends JpaRepository<Token,Long> {
}
