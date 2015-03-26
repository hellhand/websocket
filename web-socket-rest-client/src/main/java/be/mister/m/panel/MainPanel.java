package be.mister.m.panel;

import be.mister.m.MessageHandler;
import be.mister.m.websocket.WebsocketClientEndpoint;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.DefaultHighlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Marc
 */
public class MainPanel extends javax.swing.JFrame implements MessageHandler {

    private javax.swing.JButton closeButton;
    private javax.swing.JLabel dataLAbel;
    private javax.swing.JTextField dataTextField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JTextArea resultTextArea;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlTextField;

    private WebsocketClientEndpoint websocketClientEndpoint;

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();

        websocketClientEndpoint = new WebsocketClientEndpoint(this);
    }

    private void initComponents() {

        urlLabel = new javax.swing.JLabel();
        urlTextField = new javax.swing.JTextField();
        dataLAbel = new javax.swing.JLabel();
        dataTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlLabel.setText("URL : ");
        dataLAbel.setText("Data : ");

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        resultTextArea.setEditable(false);
        resultTextArea.setHighlighter(new BasicTextUI.BasicHighlighter());
        jScrollPane1.setViewportView(resultTextArea);

        openButton.setText("Open");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    websocketClientEndpoint.connect(new URI(urlTextField.getText()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                websocketClientEndpoint.sendMessage(dataTextField.getText());
            }
        });
        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    websocketClientEndpoint.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fileMenu.setText("File");

        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(dataLAbel)
                                                        .addComponent(urlLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(urlTextField)
                                                        .addComponent(dataTextField)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 341, Short.MAX_VALUE)
                                                .addComponent(openButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(closeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendButton)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(urlLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dataLAbel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sendButton)
                                        .addComponent(closeButton)
                                        .addComponent(openButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(1);
    }

    @Override
    public void handleMessage(String message) {
        this.resultTextArea.append(message);
        this.resultTextArea.append("\n");
    }
}