package algovisualizer.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int[] array;
    private int firstIndex = -1;
    private int secondIndex = -1;

    public SortingPanel(int size){
        setBackground(Color.WHITE);
        generateArray(size);
    }

    public void generateArray(int size){
        Random random = new Random();
        array = new int[size];

        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt(1,101);
        }

        firstIndex = -1;
        secondIndex = -1;
        repaint();
    }

    public int[] getArray(){
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }

    public void setHighlitedIndices(int firstIndex, int secondIndex){
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
        repaint();
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

            if(i == firstIndex || i == secondIndex){
                g.setColor(Color.RED);
            } else{
                g.setColor(Color.BLUE);
            }
            g.fillRoundRect(x, y, Math.max(1, barWidth - 10), barHeight, 20, 20);
        }
    }
}
