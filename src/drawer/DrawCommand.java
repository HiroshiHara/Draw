package drawer;

import java.awt.Point;

import command.Command;

/**
 * 「点の描画命令」を表現するクラス
 * @author mrbob
 *
 */
public class DrawCommand implements Command {
	/**
	 * 描画を行う対象
	 */
	protected Drawable g_drawable;
	
	/**
	 * 描画を行う位置
	 */
	protected Point g_position;
	
	/**
	 * コンストラクタ
	 * @param x_drawable 描画対象
	 * @param x_position 描画位置
	 */
	public DrawCommand(Drawable x_drawable, Point x_position) {
		this.g_drawable = x_drawable;
		this.g_position = x_position;
	}
	
	/**
	 * 描画処理の実行
	 * @Override
	 */
	public void execute() {
		g_drawable.draw(g_position.x, g_position.y);
	}
}
