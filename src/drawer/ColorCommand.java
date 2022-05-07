package drawer;

import java.awt.Color;

import command.Command;

/**
 * 「描画色設定命令」を表現するクラス
 * @author mrbob
 *
 */
public class ColorCommand implements Command {
	/**
	 * 描画を行う対象
	 */
	protected Drawable g_drawable;
	
	/**
	 * 描画色
	 */
	protected Color g_color;
	
	/**
	 * コンストラクタ
	 * @param x_drawable 描画対象
	 * @param x_color 描画色
	 */
	public ColorCommand(Drawable x_drawable, Color x_color) {
		this.g_drawable = x_drawable;
		this.g_color = x_color;
	}
	
	public void execute() {
		g_drawable.setColor(g_color);
	}
}
