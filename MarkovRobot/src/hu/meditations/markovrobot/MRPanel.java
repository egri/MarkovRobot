package hu.meditations.markovrobot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MRPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    Runner MRRun;

	public MRPanel() {
        MRRun = new Runner();
        JButton startButton = new JButton("Start");       
        JButton stopButton  = new JButton("Stop");
        JButton resetButton  = new JButton("Reset");
        startButton.addActionListener(new StartAction());
        stopButton.addActionListener(new StopAction());
        resetButton.addActionListener(new ResetAction());

        //... Layout inner panel with two buttons horizontally
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        //... Layout outer panel with button panel above animation
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(MRRun, BorderLayout.CENTER);
        
        //MRRun.run();
	}

    class StartAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MRRun.start();
//            MRRun.run();
        }
    }

    class StopAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MRRun.stop();
        }
    }

    class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MRRun.stop();
            MRRun.reset();
        }
    }

}