import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class to display the swing interface.
 * @author Ajinkya Nimbalkar AndrewId: animbalk
 *
 */
public class FinalGUI extends JFrame implements ActionListener{
    
    private JTextField itemnametxt1 = new JTextField();
    private JTextField pricetxt = new JTextField();
    private JTextField itemnametxt2 = new JTextField();
    private JTextField quantitytxt = new JTextField();
    private JTextField cashtxt = new JTextField();
    private JButton additembtn1 = new JButton();
    private JButton additembtn2 = new JButton();
    private JButton paybtn = new JButton();
    private JButton cleartextbtn = new JButton();
    private JTextArea resulttext = new JTextArea(20, 45);
    
    private Map<String, FinalData> sellmap;
    private Double totalcost = new Double(0.0);
    private Integer prev = new Integer(0);
    
    public FinalGUI() {
        initialize();
        sellmap = new HashMap<String, FinalData>();
    }
    
    /**
     * initializes the variables in finalgui class.
     */
    private void initialize() {
        setSize(600, 600);
        setLayout(new BorderLayout());
        JPanel northpane = new JPanel();
        northpane.setLayout(new GridLayout(4,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Java Cash Register");
        
        JPanel toppane1 = makepane1();
        northpane.add(toppane1);
        JPanel toppane2 = makepane2();
        northpane.add(toppane2);
        JPanel toppane3 = makepane3();
        northpane.add(toppane3); 
        JPanel emptypane = new JPanel();
        northpane.add(emptypane);
        this.add(northpane, BorderLayout.NORTH);
        JPanel textpane = maketextpane();
        this.add(textpane, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    private JPanel makepane1() {
        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        JLabel itemnamelbl = new JLabel();
        itemnamelbl.setText("Name of New Item to Sell: ");
        itemnametxt1.setColumns(5);
        JLabel pricelbl = new JLabel();
        pricelbl.setText("Price: ");
        pricetxt.setColumns(5);
        additembtn1.setText("Add Item to Store");
        additembtn1.addActionListener(this);
        pane1.add(itemnamelbl);
        pane1.add(itemnametxt1);
        pane1.add(pricelbl);
        pane1.add(pricetxt);
        pane1.add(additembtn1);
        return pane1;
    }
    
    private JPanel makepane2() {
        JPanel pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        JLabel quantitylbl = new JLabel();
        quantitylbl.setText("Quantity being purchased: ");
        quantitytxt.setColumns(5);
        JLabel itemnamelbl = new JLabel();
        itemnamelbl.setText("Item Name: ");
        itemnametxt2.setColumns(5);
        additembtn2.setText("Add Item to Customer Bill");
        additembtn2.addActionListener(this);
        pane2.add(quantitylbl);
        pane2.add(quantitytxt);
        pane2.add(itemnamelbl);
        pane2.add(itemnametxt2);
        pane2.add(additembtn2);
        return pane2;
    }
    
    private JPanel makepane3() {
        JPanel pane3 = new JPanel();
        pane3.setLayout(new FlowLayout());
        JLabel cashlbl = new JLabel();
        cashlbl.setText("Cash Tendered: ");
        cashtxt.setColumns(5);
        paybtn.setText("Pay");
        paybtn.addActionListener(this);
        cleartextbtn.setText("Clear Text Area");
        cleartextbtn.addActionListener(this);
        pane3.add(cashlbl);
        pane3.add(cashtxt);
        pane3.add(paybtn);
        pane3.add(cleartextbtn);
        return pane3;
    }
    
    private JPanel maketextpane() {
        JPanel textpane = new JPanel();
        Font bigFont = new Font(Font.SERIF, Font.BOLD, 15);
        resulttext.setFont(bigFont);
        resulttext.setLineWrap(true);
        resulttext.setWrapStyleWord(true);
        resulttext.setEditable(false);
        textpane.add(resulttext);
        JScrollPane scroller = new JScrollPane(resulttext);
        textpane.add(scroller);
        return textpane;
    }
    
    public void addtosellmap(Map<String, FinalData> m) {
        double price;
        String itemname;
        FinalData dataobj;
        String displaystr = new String();
        
        if(itemnametxt1.getText().isEmpty()) {
            resulttext.setText(resulttext.getText() + "Item name field is empty\n");
            return;
        }
        if(pricetxt.getText().isEmpty()) {
            resulttext.setText(resulttext.getText() + "Price field is empty\n");
            return;
        }
        try {
            price = Double.valueOf(pricetxt.getText());
        } catch (IllegalArgumentException e) {
            resulttext.setText(resulttext.getText() + "Price is not a number\n");
            return;
        }
        if(price < 0) {
            resulttext.setText(resulttext.getText() + "Price is not greater than zero\n");
            return;    
        }
        itemname = itemnametxt1.getText();
        dataobj = new FinalData(itemname, price);
        if(m.containsKey(itemname)) {
            resulttext.setText(resulttext.getText() + "Item already for sale at this store\n");
            return;   
        }
        else {
            m.put(itemname, dataobj);
        }
        displaystr = ("New item for sale: " + itemname + 
                "\t\t $ " + pricetxt.getText() + "\n");
        resulttext.setText(resulttext.getText() + displaystr);
        
        itemnametxt1.setText("");
        pricetxt.setText("");
 
    }
    
    public void addtocustomerbill (Map<String, FinalData> m) {
        int quantity;
        String itemname;
        FinalData dataobj;
        double priceperitem;
        double totalprice;
        String displaystr = new String();
        
        if(quantitytxt.getText().isEmpty()) {
            resulttext.setText(resulttext.getText() + "Quantity field is empty\n");
            return;
        }
        if(itemnametxt2.getText().isEmpty()) {
            resulttext.setText(resulttext.getText() + "Item name field is empty\n");
            return;
        }
        
        
        
        try {
            quantity = Integer.valueOf(quantitytxt.getText());
        } catch (IllegalArgumentException e) {
            resulttext.setText(resulttext.getText() + "Quantity is not an integer\n");
            return;
        }
        if(quantity < 0) {
            resulttext.setText(resulttext.getText() + "Quantity should be greater than zero\n");
            return;    
        }
        itemname = itemnametxt2.getText();
        if(m.containsKey(itemname)) {
            dataobj = m.get(itemname);
            priceperitem = dataobj.getprice();
            totalprice = priceperitem*quantity;
        }
        else {
            resulttext.setText(resulttext.getText() + 
                    "No such item\n");
            return;
        }
        
        displaystr = (quantity + "\t" + itemname + "\t\tat $ " + priceperitem
                + " each\t$ " + totalprice + "\n");
        resulttext.setText(resulttext.getText() + displaystr);
        itemnametxt2.setText("");
        quantitytxt.setText("");
        totalcost += totalprice;
 
    }
    
    public void calctransaction() {
        double cashtaken;
        String displaystr = new String();
        
        if(cashtxt.getText().isEmpty()) {
            resulttext.setText(resulttext.getText() + "Cash field is empty\n");
            return;
        }
        
        try {
            cashtaken = Double.valueOf(cashtxt.getText());
        } catch (IllegalArgumentException e) {
            resulttext.setText(resulttext.getText() + "Cash tendered is not a number\n");
            return;
        }
        if(cashtaken < 0) {
            resulttext.setText(resulttext.getText() + "Cash should be greater than zero\n");
            return;    
        }
        
        if(cashtaken < totalcost) {
            resulttext.setText(resulttext.getText() + "Not enough cash."
                    + "Total cost = " + String.valueOf(totalcost) + "\n");
            return;                
        }
        
        displaystr = ("\n\t\tTotal\t\t$ " + String.valueOf(totalcost) +
                "\n" + "\t\tCash Tendered\t$ " + cashtxt.getText() +
                "\n" + "\t\tChange\t\t$ " + String.valueOf(cashtaken - totalcost)
                + "\n");   
        
        resulttext.setText(resulttext.getText() + displaystr);
        
        cashtxt.setText("");
        totalcost = 0.0;
        prev = -1;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == additembtn1) {
            if(prev != 0) {
                resulttext.setText("");
            }
            addtosellmap(sellmap);
            prev = 0;
        }
        if (e.getSource() == cleartextbtn) {
            resulttext.setText("");
        }
        if (e.getSource() == additembtn2) {
            if (prev != 1) {
                resulttext.setText("");
            }
            addtocustomerbill(sellmap);
            prev = 1;
        }
        if (e.getSource() == paybtn) {
            if (prev != 1) {
                resulttext.setText("");
            }
            calctransaction();
        }
    }              
    
    public static void main (String[] args) {
        FinalGUI finalgui = new FinalGUI(); 
    }

}
