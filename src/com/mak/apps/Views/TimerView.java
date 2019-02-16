package com.mak.apps.Views;

import com.mak.apps.Utilities.RandomFns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimerView extends JPanel {
    private Timer timer;
    private long startTime = -1;
    private long duration;

    private JLabel label;

    private SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");

    public TimerView(long duration){
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.duration = duration;
        setLayout(new GridBagLayout());
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                }
                label.setText(df.format(duration - clockTime));
            }
        });
        timer.setInitialDelay(0);
        label = new JLabel("...");
        add(label);
        timer.start();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 100);
    }

    public void StopTimer(){
        timer.stop();
        label.setText(df.format(0));
    }
}
