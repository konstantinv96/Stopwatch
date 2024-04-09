import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 3600000;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    String hoursString = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime+=1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            setTime();
        }
    });

    Stopwatch(){

        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            if(!started){
                started = true;
                startButton.setText("STOP");
                start();
            }else{
                started = false;
                startButton.setText("START");
                stop();
            }
        }

        if(e.getSource() == resetButton){
            started = false;
            startButton.setText("START");
            reset();
        }
    }

    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    public void reset(){
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        setTime();
    }

    private void setTime() {
        secondsString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hoursString = String.format("%02d", hours);
        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
    }
}
