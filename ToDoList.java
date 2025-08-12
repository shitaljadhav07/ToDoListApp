package ToDoList;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ToDoList extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private DefaultListModel<String> taskListModel; // To store tasks
    private JList<String> taskList; // To display tasks

    // Custom panel for background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ToDoList frame = new ToDoList();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ToDoList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Use BackgroundPanel
        JPanel contentPane = new BackgroundPanel("src/Images/Todolist.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title in black, centered horizontally
        JLabel lblNewLabel = new JLabel("My To Do List", SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.BLACK); // black text
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(0, 20, getWidth(), 30);
        contentPane.add(lblNewLabel);

        // Task label in black
        JLabel lblTask = new JLabel("Task:", SwingConstants.RIGHT);
        lblTask.setForeground(Color.BLACK);
        lblTask.setFont(new Font("Arial", Font.BOLD, 14));
        lblTask.setBounds(80, 80, 50, 20);
        contentPane.add(lblTask);

        // Text field
        textField = new JTextField();
        textField.setBounds(140, 80, 200, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        // Task list area
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(80, 110, 280, 100);
        contentPane.add(scrollPane);

        // Add button
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(140, 220, 80, 25);
        btnAdd.addActionListener(e -> {
            String task = textField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                textField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a task.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        contentPane.add(btnAdd);

        // Delete button
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(230, 220, 80, 25);
        btnDelete.addActionListener(e -> {
            int[] selectedIndices = taskList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                ArrayList<String> toRemove = new ArrayList<>();
                for (int index : selectedIndices) {
                    toRemove.add(taskListModel.getElementAt(index));
                }
                for (String task : toRemove) {
                    taskListModel.removeElement(task);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        contentPane.add(btnDelete);
    }
}
