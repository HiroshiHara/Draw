package drawer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import command.MacroCommand;

/**
 * 「描画対象」を実装したクラス
 * @author mrbob
 *
 */
public class DrawCanvas extends Canvas implements Drawable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 描画色
	 */
	private Color g_color;
	
	/**
	 * 描画する円の半径
	 */
	private int g_radius;
	
	/**
	 * 命令の履歴
	 */
	private MacroCommand g_commandHistory;
	
	/**
	 * コンストラクタ
	 */
	public DrawCanvas(int x_width, int x_height, MacroCommand x_commandHistory) {
		// 描画領域の大きさを設定
		setSize(x_width, x_height);
		// 描画領域の背景色を設定
		setBackground(Color.white);
		this.g_commandHistory = x_commandHistory;
		// 背景色と描画する円の半径を初期化
		init();
	}
	
	/**
	 * 背景色と描画する円の半径を初期化<br>
	 * 背景色設定の初期化命令を履歴に追加する
	 */
	public void init() {
		this.g_color = Color.red;
		this.g_radius = 6;
		g_commandHistory.append(new ColorCommand(this, g_color));
	}
	
	/**
	 * 全ての命令の履歴を再実行
	 * @Override
	 */
	public void paint(Graphics x_graphics) {
		g_commandHistory.execute();
	}
	
	/**
	 * 描画処理
	 * @Override
	 */
	public void draw(int x_positionX, int x_positionY) {
		Graphics p_graphics = getGraphics();
		// 描画する円の色を設定
		p_graphics.setColor(g_color);
		// 円を描画する
		p_graphics.fillOval(x_positionX - g_radius, x_positionY - g_radius, g_radius * 2, g_radius * 2);
	}
	
	/**
	 * 描画色設定処理
	 * @Override
	 */
	public void setColor(Color x_color) {
		this.g_color = x_color;
	}
}
