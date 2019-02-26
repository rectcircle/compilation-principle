package cn.rectcircle.dragonbook.ch02.symbols;

import java.util.HashMap;
import java.util.Map;

/**
 * 符号表（作用域），是一个树形结构。 每个符号表为一个节点，其存在一个指向父节点（上层作用域）的指针
 */
public class Env {
	private Map<String, Symbol> table;
	protected Env prev;

	public Env(Env prev) {
		this.table = new HashMap<>();
		this.prev = prev;
	}

	public void put(String s, Symbol sym) {
		table.put(s, sym);
	}

	public Symbol get(String s) {
		for (Env e = this; e != null; e = e.prev) {
			Symbol sym = e.table.get(s);
			if (sym != null) {
				return sym;
			}
		}
		return null;
	}

}