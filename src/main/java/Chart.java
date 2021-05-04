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
        JFreeChart chart = ChartFactory.createXYLineChart("Parties number Chart","Parties", "Number", ds, PlotOrientation.VERTICAL, false, false,false);

        ChartFrame frame = new ChartFrame("Parties number Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }


    private XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        Integer parties = Database.GetPartiesNumber();
        System.out.println("Parties:" + parties);

        double[][] data = { {1, 2}, {parties, parties} };

        ds.addSeries("All parties", data);

        return ds;
    }
}