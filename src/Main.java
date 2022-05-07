import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import command.Command;
import command.MacroCommand;
import drawer.ColorCommand;
import drawer.DrawCanvas;
import drawer.DrawCommand;

/**
 * 描画ツールを実行する
 * @author mrbob
 *
 */
public class Main extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 命令の履歴
	 */
	private MacroCommand g_commandHisotry = new MacroCommand();
	
	/**
	 * 描画領域
	 */
	private DrawCanvas g_canvas = new DrawCanvas(400, 400, g_commandHisotry);
	
	/**
	 * 描画色「赤」ボタン
	 */
	private JButton g_redButton = new JButton("red");
	
	/**
	 * 描画色「青」ボタン
	 */
	private JButton g_blueButton = new JButton("blue");
	
	/**
	 * 描画色「黄」ボタン
	 */
	private JButton g_yellowButton = new JButton("yellow");
	
	/**
	 * 描画色「緑」ボタン
	 */
	private JButton g_greenButton = new JButton("green");
	
	/**
	 * 消去ボタン
	 */
	private JButton g_clearButton = new JButton("clear");
	
	/**
	 * コンストラクタ
	 */
	public Main(String x_title) {
		super(x_title);
		
		// 各種イベントリスナーの設定
		// (WindowsListtenerとMouseMotionListenerはAdapterで必要なメソッドだけ実装)
		this.addWindowListener(new WindowAdapter() {
			// ウィンドウを閉じたときの処理
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		g_canvas.addMouseMotionListener(new MouseMotionAdapter() {
			// マウスをドラッグした時、描画命令を生成し履歴に追加したのち、即実行する
			@Override
			public void mouseDragged(MouseEvent x_event) {
				Command p_cmd = new DrawCommand(g_canvas, x_event.getPoint());
				g_commandHisotry.append(p_cmd);
				p_cmd.execute();
			}
		});
		g_redButton.addActionListener(this);
		g_blueButton.addActionListener(this);
		g_yellowButton.addActionListener(this);
		g_greenButton.addActionListener(this);
		g_clearButton.addActionListener(this);
		
		// ボックスの生成
		// 色指定ボタン配置用の横並びボックス
		Box p_colorButtonBox = new Box(BoxLayout.X_AXIS);
		p_colorButtonBox.add(g_redButton);
		p_colorButtonBox.add(g_blueButton);
		p_colorButtonBox.add(g_yellowButton);
		p_colorButtonBox.add(g_greenButton);
		// 消去ボタン配置用の横並びボックス
		Box p_clearButtonBox = new Box(BoxLayout.X_AXIS);
		p_clearButtonBox.add(g_clearButton);
		// 全部品配置用の縦並びボックス
		Box p_mainBox = new Box(BoxLayout.Y_AXIS);
		p_mainBox.add(p_colorButtonBox);
		p_mainBox.add(p_clearButtonBox);
		p_mainBox.add(g_canvas);
		// コンテナにメインボックスを配置
		getContentPane().add(p_mainBox);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * AcrionListenerインタフェースの実装<br>
	 * クリアボタン押下時に描画履歴を削除して再描画する
	 * @Override
	 */
	public void actionPerformed(ActionEvent x_event) {
		// クリアボタンの時
		if (x_event.getSource() == g_clearButton) {
			g_commandHisotry.clear();
			g_canvas.repaint();
		}
		// 赤ボタンの時
		if (x_event.getSource() == g_redButton) {
			Command p_cmd = new ColorCommand(g_canvas, Color.red);
			g_commandHisotry.append(p_cmd);
			p_cmd.execute();
		}
		// 青ボタンの時
		if (x_event.getSource() == g_blueButton) {
			Command p_cmd = new ColorCommand(g_canvas, Color.blue);
			g_commandHisotry.append(p_cmd);
			p_cmd.execute();
		}
		// 黄ボタンの時
		if (x_event.getSource() == g_yellowButton) {
			Command p_cmd = new ColorCommand(g_canvas, Color.yellow);
			g_commandHisotry.append(p_cmd);
			p_cmd.execute();
		}
		// 緑ボタンの時
		if (x_event.getSource() == g_greenButton) {
			Command p_cmd = new ColorCommand(g_canvas, Color.green);
			g_commandHisotry.append(p_cmd);
			p_cmd.execute();
		}
	}
	
	public static void main(String[] args) {
		new Main("Command Pattern Sample");
	}
}
