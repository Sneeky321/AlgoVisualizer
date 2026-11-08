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

    private static final Color BACKGROUND = new Color(30, 40, 50);

    public SortingPanel(int size){
        setBackground(BACKGROUND);
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

    public void setHighlightedOperation(int firstIndex, int secondIndex, SortEvent.Type currentOperation){
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

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int padding = 20;

        int panelWidth = getWidth() - 2 * padding;
        int panelHeight = getHeight() - 2 * padding - 20;
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

            g2.setColor(barColor);

            g2.fillRoundRect(x, y, Math.max(1, barWidth - 10), barHeight, 12, 12);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));

            String value = String.valueOf(array[i]);

            FontMetrics metrics = g2.getFontMetrics();
            int textWidth = metrics.stringWidth(value);

            int textX = (x + (barWidth - textWidth) / 2) - 5;
            int textY = Math.max(padding + metrics.getAscent(), y - 6);

            g2.drawString(value, textX, textY);
        }

        g2.dispose();

    }
}
