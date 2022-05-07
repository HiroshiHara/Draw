package drawer;

/**
 * 「描画対象」を表現するインタフェース
 * @author mrbob
 *
 */
public interface Drawable {
	/**
	 * 描画処理の抽象クラス
	 * @param x_positionX X座標
	 * @param x_positionY Y座標
	 */
	public abstract void draw(int x_positionX, int x_positionY);
}
