package cn.rectcircle.dragonbook.ch02.lexer;

/**
 * 关键字or保留字词素
 */
public class Word extends Token {
	public final String lexeme;

	public Word(int tag, String lexeme) {
		super(tag);
		this.lexeme = lexeme;
	}

	@Override
	public String toString() {
		switch (tag) {
		case Tag.TRUE:
			return "<true>";
		case Tag.FALSE:
			return "<false>";
		case Tag.ID:
			return String.format("<id, %s>", lexeme);
		default:
			return "<?>";
		}
	}
}