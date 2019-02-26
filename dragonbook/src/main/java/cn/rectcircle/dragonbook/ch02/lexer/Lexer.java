package cn.rectcircle.dragonbook.ch02.lexer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 四则运算词法分析器，实现的文法如下
 * 
 * <pre>
expr -> expr + term
        |   expr - term
        |   term

term -> term * factor
        |   term / factor
        |   factor

factor -> (expr)
          |   num
          |   id
 * </pre>
 */
public class Lexer {
	public int line = 1;
	private char peek = ' ';
	/**
	 * 关键字/保留字/标识符保持单例
	 */
	private Map<String, Word> words = new HashMap<>();

	public Lexer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}

	/**
	 * 将关键字词/标识符素放入
	 */
	private void reserve(Word t) {
		words.put(t.lexeme, t);
	}

	public Token scan() throws IOException {
		// 处理多余空白字符
		for (;; peek = (char) System.in.read()) {
			if (peek == ' ' || peek == '\t') {
				continue;
			} else if (peek == '\n') {
				line++;
			} else {
				break;
			}
		}
		// 下面根据第一个有效字符的情况进行预读
		// 第一个字符是数字：解析成int
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				peek = (char) System.in.read();
			} while (Character.isDigit(peek));
			return new Num(v);
		}
		// 第一个字符是字母
		if (Character.isLetter(peek)) {
			StringBuilder sb = new StringBuilder();
			do {
				sb.append(peek);
				peek = (char) System.in.read();
			} while (Character.isLetterOrDigit(peek));
			String s = sb.toString();
			Word w = words.get(s);
			if (w != null) {
				return w;
			}
			w = new Word(Tag.ID, s);
			reserve(w);
			return w;
		}
		Token t = new Token(peek);
		peek = ' ';
		return t;
	}

}