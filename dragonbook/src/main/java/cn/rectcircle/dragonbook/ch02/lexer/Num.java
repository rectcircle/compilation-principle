package cn.rectcircle.dragonbook.ch02.lexer;

/**
 * 数字词素
 */
public class Num extends Token{
	public final int value;

	public Num(int value) {
		super(Tag.NUM);
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("<Num, %d>", value);
	}
}