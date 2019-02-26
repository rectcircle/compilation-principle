package cn.rectcircle.dragonbook.ch02.lexer;

/**
 * Token词法单元父类
 */
public class Token {
	public final int tag;

	public Token(int tag) {
		this.tag = tag;
	}
}