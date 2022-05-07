import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import command.Command;
import command.MacroCommand;
import drawer.DrawCanvas;
import drawer.DrawCommand;

/**
 * 描画ツールを実行する
 * @author mrbob
 *
 */
public class Main extends JFrame implements ActionListener, MouseMotionListener, WindowListener{
	
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
	 * 消去ボタン
	 */
	private JButton g_clearButton = new JButton("clear");
	
	/**
	 * コンストラクタ
	 */
	public Main(String x_title) {
		super(x_title);
		
		// 各種イベントリスナーの設定
		this.addWindowListener(this);
		g_canvas.addMouseMotionListener(this);
		g_clearButton.addActionListener(this);
		
		// ボックスの生成
		// ボタン配置用の横並びボックス
		Box p_buttonBox = new Box(BoxLayout.X_AXIS);
		p_buttonBox.add(g_clearButton);
		// 全部品配置用の縦並びボックス
		Box p_mainBox = new Box(BoxLayout.Y_AXIS);
		p_mainBox.add(p_buttonBox);
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
		// イベント起点がクリアボタンの時実行
		if (x_event.getSource() == g_clearButton) {
			g_commandHisotry.clear();
			g_canvas.repaint();
		}
	}
	
	/**
	 * MouseMotionListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void mouseMoved(MouseEvent x_event) {}
	
	/**
	 * MouseMotionListenerインタフェースの実装<br>
	 * マウスをドラッグした時、描画命令を生成し履歴に追加したのち、即実行する
	 * @Override
	 */
	public void mouseDragged(MouseEvent x_event) {
		Command p_cmd = new DrawCommand(g_canvas, x_event.getPoint());
		g_commandHisotry.append(p_cmd);
		p_cmd.execute();
	}

	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowOpened(WindowEvent x_event) {}

	/**
	 * WindowListenerインタフェースの実装<br>
	 * ウィンドウの閉じるボタン押下時の処理
	 * @Override
	 */
	public void windowClosing(WindowEvent x_event) {
		System.exit(0);
	}

	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowClosed(WindowEvent x_event) {}
	
	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowIconified(WindowEvent x_event) {}
	
	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowDeiconified(WindowEvent x_event) {}

	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowActivated(WindowEvent x_event) {}

	/**
	 * WindowListenerインタフェースの実装<br>
	 * 処理なし
	 * @Override
	 */
	public void windowDeactivated(WindowEvent x_event) {}
	
	public static void main(String[] args) {
		new Main("Command Pattern Sample");
	}
}
