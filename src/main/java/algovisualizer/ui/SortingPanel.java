package algovisualizer.ui;

import algovisualizer.sorting.SortEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int[] array;
    private int firstIndex = -1;
    private int secondIndex = -1;
    private SortEvent.Type currentOperation;

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

        System.out.println(Arrays.toString(array));

        firstIndex = -1;
        secondIndex = -1;
        currentOperation = null;
        repaint();
    }

    public int[] getArray(){
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }

    public void setHighligtedOperation(int firstIndex, int secondIndex, SortEvent.Type currentOperation){
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
        this.currentOperation = currentOperation;
        repaint();
    }

    public void clearHighlight(){
        firstIndex = -1;
        secondIndex = -1;
        currentOperation = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(array == null || array.length == 0){
            return;
        }

        int padding = 20;

        int panelWidth = getWidth() - 2 * padding;
        int panelHeight = getHeight() - 2 * padding;
        int barWidth = panelWidth / array.length;
        int maxValue = 100;

        for(int i = 0; i < array.length; i++){
            int barHeight = Math.max(
                    8,
                    (int) ((double) array[i] / maxValue * panelHeight)
            );

            int x = padding + i * barWidth;
            int y = padding + panelHeight - barHeight;

            Color barColor = Color.BLUE;

            if(i == firstIndex || i == secondIndex){
                if(currentOperation == SortEvent.Type.COMPARE){
                    barColor = Color.YELLOW;
                } else if(currentOperation == SortEvent.Type.SWAP){
                    barColor = Color.RED;
                } else if(currentOperation == SortEvent.Type.WRITE){
                    barColor = Color.GREEN;
                }
            }

            g.setColor(barColor);

            g.fillRoundRect(x, y, Math.max(1, barWidth - 10), barHeight, 20, 20);
        }

    }
}
