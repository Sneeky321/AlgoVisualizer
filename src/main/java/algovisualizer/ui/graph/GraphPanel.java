package algovisualizer.ui.graph;

import algovisualizer.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GraphPanel extends JPanel {

    private final int NODE_RADIUS = 27;
    private final Color EDGE_COLOR = new Color(100, 120, 145);
    private final Color NODE_COLOR = new Color(55, 115, 165);
    private final Color NODE_BORDER_COLOR = new Color(120, 175, 220);
    private final Color TEXT_COLOR = new Color(240, 245, 250);
    private final Color BACKGROUND_COLOR = new Color(30, 40, 50);

    private Graph graph;

    private Set<String> visitedVertices = new HashSet<>();
    private String currentVertex = null;

    public GraphPanel(Graph graph){
        this.graph = graph;

        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(650, 450));
        setMinimumSize(new Dimension(350, 300));
    }

    public void setGraph(Graph graph){
        this.graph = graph;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(graph == null || graph.getVertices().isEmpty()){
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        List<String> vertices = new ArrayList<>(graph.getVertices());
        Collections.sort(vertices);

        int count = vertices.size();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int circleRadius = Math.min(getWidth(), getHeight() / 2 - NODE_RADIUS - 30);
        circleRadius = Math.max(circleRadius, 0);

        Map<String, Point> positions = new HashMap<>();

        for(int i = 0; i < count; i++){
            double angle = 2 * Math.PI * i / count - Math.PI / 2;

            int x = centerX + (int) (circleRadius * Math.cos(angle));
            int y = centerY + (int) (circleRadius * Math.sin(angle));

            positions.put(vertices.get(i), new Point(x, y));
        }

        g2.setColor(EDGE_COLOR);
        g2.setStroke(new BasicStroke(2.5f));

        for(int i = 0; i < count; i++){
            String vertex = vertices.get(i);
            Point start = positions.get(vertex);

            for(String neighbour : graph.getNeighbours(vertex)){
                int neighbourIndex = vertices.indexOf(neighbour);

                if(neighbourIndex > i){
                    Point end = positions.get(neighbour);

                    double dx = end.x - start.x;
                    double dy = end.y - start.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    if(distance == 0){
                        continue;
                    }

                    double offsetX = dx / distance * NODE_RADIUS;
                    double offsetY = dy / distance * NODE_RADIUS;

                    int x1 = (int) (start.x + offsetX);
                    int y1 = (int) (start.y + offsetY);

                    int x2 = (int) (end.x - offsetX);
                    int y2 = (int) (end.y - offsetY);

                    g2.drawLine(x1, y1, x2, y2);
                }
            }
        }

        for(String vertex : vertices){
            Point position = positions.get(vertex);

            int x = position.x - NODE_RADIUS;
            int y = position.y - NODE_RADIUS;

            g2.setColor(new Color(15, 22, 30));
            g2.fillOval(
                    x + 3,
                    y + 4,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2
            );

            if(vertex.equals(currentVertex)){
                g2.setColor(new Color(220, 180, 50));
            } else if(visitedVertices.contains(vertex)){
                g2.setColor(new Color(60, 155, 110));
            } else {
                g2.setColor(NODE_COLOR);
            }

            g2.fillOval(
                    x,
                    y,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2
            );

            g2.setColor(NODE_BORDER_COLOR);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(
                    x,
                    y,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2
            );

            g2.setColor(TEXT_COLOR);
            g2.setFont(new Font("SansSerif", Font.BOLD, 16));

            FontMetrics fm = g2.getFontMetrics();

            int textX = position.x - fm.stringWidth(vertex) / 2;
            int textY = position.y + (fm.getAscent() - fm.getDescent()) / 2;

            g2.drawString(vertex, textX, textY);
        }

        g2.dispose();
    }

    public void setVisitedVertices(Set<String> visitedVertices){
        this.visitedVertices = new HashSet<>(visitedVertices);
        repaint();
    }

    public void setCurrentVertex(String currentVertex){
        this.currentVertex = currentVertex;
        repaint();
    }
}
