package drawer;

import java.awt.Color;

/**
 * 「描画対象」を表現するインタフェース
 * @author mrbob
 *
 */
public interface Drawable {
	/**
	 * 描画処理の抽象メソッド
	 * @param x_positionX X座標
	 * @param x_positionY Y座標
	 */
	public abstract void draw(int x_positionX, int x_positionY);
	
	/**
	 * 描画色設定の抽象メソッド
	 * @param x_color 描画色
	 */
	public abstract void setColor(Color x_color);
	
	/**
	 * 描画円サイズ設定の抽象メソッド
	 * @param x_radius 描画円サイズ
	 */
	public abstract void setRadius(int x_radius);
}
