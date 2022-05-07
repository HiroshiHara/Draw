package command;

/**
 * 「命令」を表現するインタフェース
 * @author mrbob
 *
 */
public interface Command {
	/**
	 * 処理を実行する。<br>
	 * 具体的な処理は本インタフェースの実装先に移譲する。
	 */
	public abstract void execute();
}
