package cn.rectcircle.dragonbook.ch02;

import java.io.IOException;

/**
 * 十以内自然数+-法 原始文法
 * 
 * <pre>
 * expr -> expr + term
 *      |   expr - term
 *      |   term
 * term -> 1|2|3|4|5|6|7|8|9|0
 * </pre>
 * 
 * 消除左递归后的文法
 * 
 * <pre>
 * expr -> term rest
 * rest -> + term rest
 *      |  - term rest
 *      |  ε
 * term -> 1|2|3|4|5|6|7|8|9|0
 * </pre>
 */
class Parser {
	static int lookahead;

	public Parser() throws IOException {
		lookahead = System.in.read();
	}

	/**
	 * 非终结符号expr
	 */
	void expr() throws IOException {
		term();
		rest();
	}

	/**
	 * rest
	 */
	void rest() throws IOException {
		// 尾递归，可以优化为循环
		if (lookahead=='+'){
			match('+'); term(); System.out.print('+'); rest();
		} else if(lookahead=='-'){
			match('-'); term(); System.out.print('-'); rest();
		} else if(lookahead=='\n' || lookahead == -1) { // 相当于空串 ε 流式处理的终结符
			return;
		} else {
			throw new Error("syntax error");
		}
	}

	/**
	 * 终结符号数字
	 */
	void term() throws IOException {
		if(Character.isDigit((char) lookahead)){
			System.out.write((char) lookahead); match(lookahead);
		} else {
			throw new Error("syntax error");
		}
	}

	/**
	 * 移动游标
	 */
	void match(int t) throws IOException {
		if (lookahead==t) lookahead = System.in.read();
		else throw new Error("syntax error");
	}
}

/**
 * 解析10以内自然数加减法是否存在语法错误，并转换为后缀表达式并输出
 */
public class Postfix {
	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();
		parser.expr();
		System.out.println();
	}
}