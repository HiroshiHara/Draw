package drawer;

import command.Command;

/**
 * 「描画円サイズ設定命令」を表現するクラス
 * @author mrbob
 *
 */
public class RadiusCommand implements Command {
	/**
	 * 描画を行う対象
	 */
	protected Drawable g_drawable;
	
	/**
	 * 描画円サイズ
	 */
	protected int g_radius;
	
	/**
	 * コンストラクタ
	 * @param x_drawable 描画対象
	 * @param x_radius 描画円サイズ
	 */
	public RadiusCommand(Drawable x_drawable, int x_radius) {
		this.g_drawable = x_drawable;
		this.g_radius = x_radius;
	}
	
	public void execute() {
		g_drawable.setRadius(g_radius);
	}
}
