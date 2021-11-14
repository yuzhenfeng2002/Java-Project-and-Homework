import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            TextComponentFrame frame = new TextComponentFrame();
            frame.setTitle("Homework of Yuzhen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class TextComponentFrame extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private JPanel filePanel;
    private JTextField fileNameField;
    private JButton loadFileButton;
    private JButton saveFileButton;

    private JScrollPane scrollPane;
    private JTextArea contentArea;

    private JPanel stylePanel;
    private JComboBox<String> fontOpt;
    private JComboBox<String> fontStyleOpt;
    private JSlider sizeOpt;
    private JComboBox<String> locOpt;
    private JTextField styleFileNameField;
    private JButton changeStyleButton;
    private JButton loadStyleButton;
    private JButton saveStyleButton;

    private void setStyle(String font, int fontStyle, int size, String loc)
    {
        contentArea.setFont(new Font(font, fontStyle, size));
        switch (loc) {
            case "左右布局": {
                getLayout().removeLayoutComponent(filePanel);
                getLayout().removeLayoutComponent(stylePanel);
                setSize(HEIGHT, WIDTH);
                filePanel.setLayout(new GridLayout(13, 1));
                stylePanel.setLayout(new GridLayout(13, 1));
                add(filePanel, BorderLayout.WEST);
                add(stylePanel, BorderLayout.EAST);
                add(scrollPane, BorderLayout.CENTER);
                break;
            }
            case "上下布局": {
                getLayout().removeLayoutComponent(filePanel);
                getLayout().removeLayoutComponent(stylePanel);
                setSize(WIDTH, HEIGHT);
                filePanel.setLayout(new GridLayout(1, 4));
                stylePanel.setLayout(new GridLayout(7, 2));
                add(filePanel, BorderLayout.NORTH);
                add(stylePanel, BorderLayout.SOUTH);
                add(scrollPane, BorderLayout.CENTER);
                break;
            }
        }
    }

    public TextComponentFrame()
    {
        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        scrollPane = new JScrollPane(contentArea);

        filePanel = new JPanel();
        filePanel.setLayout(new GridLayout(1, 4));
        fileNameField = new JTextField();
        loadFileButton = new JButton("加载文件");
        saveFileButton = new JButton("保存文件");
        filePanel.add(new JLabel("请输入文件名："));
        filePanel.add(fileNameField);
        filePanel.add(loadFileButton);
        filePanel.add(saveFileButton);

        stylePanel = new JPanel();
        stylePanel.setLayout(new GridLayout(7, 2));
        fontOpt = new JComboBox<>(new String[]{"Serif", "Agency FB", "Arial", "Calibri", "Cambrian",
                "Century Gothic", "Comic Sans MS", "Courier New", "Forte", "Garamond",
                "Monospaced", "Segoe UI", "Times New Roman", "Trebuchet MS"});
        fontStyleOpt = new JComboBox<>(new String[]{"Plain", "Bold", "Italic"});
        sizeOpt = new JSlider(10, 72, 24);
        sizeOpt.setPaintLabels(true);
        sizeOpt.setPaintTicks(true);
        locOpt = new JComboBox<>(new String[]{"上下布局", "左右布局"});
        styleFileNameField = new JTextField();
        changeStyleButton = new JButton("更改样式");
        loadStyleButton = new JButton("加载样式");
        saveStyleButton = new JButton("保存样式");
        stylePanel.add(new JLabel("字体"));
        stylePanel.add(fontOpt);
        stylePanel.add(new JLabel("字型"));
        stylePanel.add(fontStyleOpt);
        stylePanel.add(new JLabel("字号"));
        stylePanel.add(sizeOpt);
        stylePanel.add(new JLabel("布局"));
        stylePanel.add(locOpt);
        stylePanel.add(new JLabel("请输入样式文件名："));
        stylePanel.add(styleFileNameField);
        stylePanel.add(changeStyleButton);
        stylePanel.add(loadStyleButton);
        stylePanel.add(saveStyleButton);

        add(filePanel, BorderLayout.NORTH);
        add(stylePanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();

        loadFileButton.addActionListener(new LoadFileAction());
        saveFileButton.addActionListener(new SaveFileAction());
        changeStyleButton.addActionListener(new ChangeStyleAction());
        loadStyleButton.addActionListener(new LoadStyleAction());
        saveStyleButton.addActionListener(new SaveStyleAction());

        setSize(WIDTH, HEIGHT);
        setLocationByPlatform(true);
    }

    private class LoadFileAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String fileName = fileNameField.getText();
            String content;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                reader.close();
                JOptionPane.showMessageDialog(contentArea, "Successfully loaded!");
            } catch (FileNotFoundException ex)
            {
                int option = JOptionPane.showConfirmDialog(contentArea, "File not found. Create a new file?");
                if (option == JOptionPane.YES_OPTION)
                {
                    try {
                        (new File(fileName)).createNewFile();
                    } catch (IOException ex1) {
                        JOptionPane.showMessageDialog(contentArea, ex1.getMessage());
                    }
                }
                content = "";
            } catch (Exception ex)
            {
                content = "";
                JOptionPane.showMessageDialog(contentArea, ex.getMessage());
            }
            contentArea.setText(content);
        }
    }

    private class SaveFileAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = fileNameField.getText();
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(contentArea.getText());
                writer.close();
                JOptionPane.showMessageDialog(contentArea, "Successfully saved!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(contentArea, ex.getMessage());
            }
        }
    }

    private class ChangeStyleAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String font = (String) fontOpt.getSelectedItem();
            int fontStyle = fontStyleOpt.getSelectedIndex();
            int size = sizeOpt.getValue();
            String loc = (String) locOpt.getSelectedItem();
            setStyle(font, fontStyle, size, loc);
        }
    }

    private class LoadStyleAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String fileName = styleFileNameField.getText();
            Properties properties = new Properties();
            try {
                FileInputStream reader = new FileInputStream(fileName);
                properties.load(reader);
                reader.close();
                JOptionPane.showMessageDialog(contentArea, "Successfully loaded!");
                String font = properties.getProperty("font", "Serif");
                int fontStyle = Integer.parseInt(properties.getProperty("fontStyle", "0"));
                int size = Integer.parseInt(properties.getProperty("size", "24"));
                String loc = properties.getProperty("loc", "上下布局");
                setStyle(font, fontStyle, size, loc);
                fontOpt.setSelectedItem(font);
                fontStyleOpt.setSelectedItem(fontStyle);
                sizeOpt.setValue(size);
                locOpt.setSelectedItem(loc);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(contentArea, ex.getMessage());
            }
        }
    }

    private class SaveStyleAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = styleFileNameField.getText();
            Properties properties = new Properties();
            properties.setProperty("font", (String) fontOpt.getSelectedItem());
            properties.setProperty("fontStyle", String.valueOf(fontStyleOpt.getSelectedIndex()));
            properties.setProperty("size", String.valueOf(sizeOpt.getValue()));
            properties.setProperty("loc", (String) locOpt.getSelectedItem());
            try {
                FileOutputStream writer = new FileOutputStream(fileName);
                properties.store(writer, "Style Settings");
                writer.close();
                JOptionPane.showMessageDialog(contentArea, "Successfully saved!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(contentArea, ex.getMessage());
            }
        }
    }
}
