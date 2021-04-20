import javax.swing.*;
import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class Chart {
    private JPanel panel;

    public Chart(){
        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("Test Chart","x", "y", ds, PlotOrientation.VERTICAL, true, true,false);

        ChartFrame frame = new ChartFrame("Results", chart);
        frame.pack();
        frame.setVisible(true);
    }


    private XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("series1", data);

        return ds;
    }
}