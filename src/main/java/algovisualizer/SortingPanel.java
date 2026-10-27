package algovisualizer;

import javax.swing.*;
import java.awt.*;

public class SortingPanel extends JPanel {
    private final int[] array;

    public SortingPanel(int[] array){
        this.array = array;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(array == null || array.length == 0){
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int barWidth = panelWidth / array.length;
        int maxValue = 100;

        for(int i = 0; i < array.length; i++){
            int barHeight = (int) ((double) array[i] / maxValue * panelHeight);

            int x = i * barWidth;
            int y = panelHeight - barHeight;

            g.setColor(Color.BLUE);
            g.fillRoundRect(x, y, barWidth - 10, barHeight, 20, 20);
        }
    }
}
