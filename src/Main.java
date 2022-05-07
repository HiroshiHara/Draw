import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.Command;
import command.MacroCommand;
import drawer.ColorCommand;
import drawer.DrawCanvas;
import drawer.DrawCommand;
import drawer.RadiusCommand;

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
	 * 描画円サイズ入力フィールド
	 */
	private JFormattedTextField g_radiusField = new JFormattedTextField(new DecimalFormat("###"));
	
	/**
	 * 描画円サイズ変更ボタン
	 */
	private JButton g_radiusButton = new JButton("Size Change");
	
	/**
	 * アンドゥボタン
	 */
	private JButton g_undoButton = new JButton("Undo");
	
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
		g_radiusField.setColumns(3);
		
		g_radiusField.addActionListener(this);
		g_undoButton.addActionListener(this);
		g_radiusButton.addActionListener(this);
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
		// 円サイズ設定配置用の横並びボックス
		Box p_radiusButtonBox = new Box(BoxLayout.X_AXIS);
		p_radiusButtonBox.add(g_radiusField);
		p_radiusButtonBox.add(g_radiusButton);
		// その他ボタン配置用の横並びボックス
		Box p_otherButtonBox = new Box(BoxLayout.X_AXIS);
		p_otherButtonBox.add(g_undoButton);
		p_otherButtonBox.add(g_clearButton);
		// 全部品配置用の縦並びボックス
		Box p_mainBox = new Box(BoxLayout.Y_AXIS);
		p_mainBox.add(p_colorButtonBox);
		p_mainBox.add(p_radiusButtonBox);
		p_mainBox.add(p_otherButtonBox);
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
		// アンドゥボタンの時
		if (x_event.getSource() == g_undoButton) {
			g_commandHisotry.undo();
			g_canvas.repaint();
		}
		// クリアボタンの時
		if (x_event.getSource() == g_clearButton) {
			g_commandHisotry.clear();
			g_canvas.repaint();
		}
		// サイズ変更ボタンの時
		if (x_event.getSource() == g_radiusButton) {
			String p_text = g_radiusField.getText();
			if (p_text.isEmpty()) {
				JFrame p_errFrame = new JFrame();
				JOptionPane.showMessageDialog(p_errFrame, "整数を入力してください。");
			} else {
				int p_radius = Integer.valueOf(p_text);
				Command p_cmd = new RadiusCommand(g_canvas, p_radius);
				g_commandHisotry.append(p_cmd);
				p_cmd.execute();
			}
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
