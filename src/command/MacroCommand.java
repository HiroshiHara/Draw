package command;

import java.util.Iterator;
import java.util.Stack;

/**
 * 「複数の命令をまとめた命令」を表現するクラス
 * @author mrbob
 *
 */
public class MacroCommand implements Command {
	/**
	 * 命令の集合
	 */
	private Stack<Command> g_commands = new Stack<>();
	
	/**
	 * 処理の実行
	 * @Override
	 */
	public void execute() {
		Iterator<Command> p_it = g_commands.iterator(); 
		while (p_it.hasNext()) {
			p_it.next().execute();
		}
	}
	
	/**
	 * 命令の追加
	 * @param x_cmd Commandインタフェースを実装したオブジェクト
	 */
	public void append(Command x_cmd) {
		if (x_cmd != this) {
			g_commands.push(x_cmd);
		}
	}
	
	/**
	 * 最後の命令を取り消す
	 */
	public void undo() {
		if (!g_commands.empty()) {
			g_commands.pop();
		}
	}
	
	/**
	 * 全ての命令を削除
	 */
	public void clear() {
		g_commands.clear();
	}
}
