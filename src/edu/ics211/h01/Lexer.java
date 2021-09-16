/**
 *
 */
package edu.ics211.h01;

import java.io.InputStream;

/**
 * @author esb 2021/08/24
 *
 */
public interface Lexer {

    // normal lexical scanning, plus removal of Java comments
    String[] lexicalTokens(String in);
    String[] lexicalTokens(InputStream in);
}