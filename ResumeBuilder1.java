import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ResumeBuilder1 extends JFrame {
    // Input fields for resume data
    private JTextField nameField, emailField, phoneField, addressField;
    private JTextField experienceField, skillsField, hobbiesField;
    private JTextField tenthMarksField, twelfthMarksField, cgpaField;
    private JTextField degreeField, projectsField;
    private JButton generateButton;

    public ResumeBuilder1() {
        // Frame setup
        setTitle("Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245)); // Light gray background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title label
        JLabel titleLabel = new JLabel("Resume Builder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color for title
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Input fields with labels
        String[] labels = {
            "Full Name:", "Email:", "Phone:", "Address:",
            "Work Experience (years):", "Skills (comma-separated):", "Hobbies:",
            "10th Marks (%):", "12th Marks (%):", "CGPA:",
            "Degree:", "Projects:"
        };
        JTextField[] fields = {
            nameField = new JTextField(20),
            emailField = new JTextField(20),
            phoneField = new JTextField(20),
            addressField = new JTextField(20),
            experienceField = new JTextField(20),
            skillsField = new JTextField(20),
            hobbiesField = new JTextField(20),
            tenthMarksField = new JTextField(20),
            twelfthMarksField = new JTextField(20),
            cgpaField = new JTextField(20),
            degreeField = new JTextField(20),
            projectsField = new JTextField(20)
        };

        // Add labels and fields to the panel
        gbc.gridwidth = 1;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            mainPanel.add(label, gbc);

            gbc.gridx = 1;
            fields[i].setFont(new Font("Arial", Font.PLAIN, 14));
            fields[i].setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
            mainPanel.add(fields[i], gbc);
        }

        // Generate button
        generateButton = new JButton("Generate Resume");
        generateButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateButton.setBackground(new Color(0, 102, 204));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(generateButton, gbc);

        // Button action listener
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateResume();
            }
        });

        // Add main panel to frame with scroll pane
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        // Styling for professional look
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    private void generateResume() {
        // Collect input data
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String experience = experienceField.getText().trim();
        String skills = skillsField.getText().trim();
        String hobbies = hobbiesField.getText().trim();
        String tenthMarks = tenthMarksField.getText().trim();
        String twelfthMarks = twelfthMarksField.getText().trim();
        String cgpa = cgpaField.getText().trim();
        String degree = degreeField.getText().trim();
        String projects = projectsField.getText().trim();

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Name, Email, and Phone fields.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create resume content with a professional template
        StringBuilder resumeContent = new StringBuilder();
        resumeContent.append("====================================\n");
        resumeContent.append("           RESUME\n");
        resumeContent.append("====================================\n\n");
        resumeContent.append("PERSONAL INFORMATION\n");
        resumeContent.append("------------------------------------\n");
        resumeContent.append("Name: ").append(name).append("\n");
        resumeContent.append("Email: ").append(email).append("\n");
        resumeContent.append("Phone: ").append(phone).append("\n");
        resumeContent.append("Address: ").append(address.isEmpty() ? "N/A" : address).append("\n");
        resumeContent.append("\nEDUCATION\n");
        resumeContent.append("------------------------------------\n");
        resumeContent.append("10th Marks: ").append(tenthMarks.isEmpty() ? "N/A" : tenthMarks + "%").append("\n");
        resumeContent.append("12th Marks: ").append(twelfthMarks.isEmpty() ? "N/A" : twelfthMarks + "%").append("\n");
        resumeContent.append("CGPA: ").append(cgpa.isEmpty() ? "N/A" : cgpa).append("\n");
        resumeContent.append("Degree: ").append(degree.isEmpty() ? "N/A" : degree).append("\n");
        resumeContent.append("\nPROFESSIONAL DETAILS\n");
        resumeContent.append("------------------------------------\n");
        resumeContent.append("Work Experience: ").append(experience.isEmpty() ? "N/A" : experience + " years").append("\n");
        resumeContent.append("Skills: ").append(skills.isEmpty() ? "N/A" : skills).append("\n");
        resumeContent.append("Projects: ").append(projects.isEmpty() ? "N/A" : projects).append("\n");
        resumeContent.append("\nHOBBIES\n");
        resumeContent.append("------------------------------------\n");
        resumeContent.append(hobbies.isEmpty() ? "N/A" : hobbies).append("\n");
        resumeContent.append("\n====================================\n");

        // File chooser to save the resume
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Resume");
        fileChooser.setSelectedFile(new File(name.replaceAll("\\s+", "_") + "_Resume.txt"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(resumeContent.toString());
                JOptionPane.showMessageDialog(this, "Resume saved successfully to " + fileToSave.getAbsolutePath(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving resume: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Run on EDT for thread safety
        SwingUtilities.invokeLater(() -> {
            ResumeBuilder builder = new ResumeBuilder();
            builder.setVisible(true);
        });
    }
}