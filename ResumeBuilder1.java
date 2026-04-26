import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// iText Imports (iText 7)
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.properties.TextAlignment;

public class ResumeBuilder1 extends JFrame {
    private JTextField nameField, emailField, phoneField, addressField;
    private JTextField experienceField, skillsField, hobbiesField;
    private JTextField tenthMarksField, twelfthMarksField, cgpaField;
    private JTextField degreeField, projectsField;
    private JButton generateButton;

    public ResumeBuilder1() {
        setTitle("Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel titleLabel = new JLabel("Resume Builder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        String[] labels = {
            "Full Name:", "Email:", "Phone:", "Address:",
            "Work Experience (years):", "Skills (comma-separated):", "Hobbies:",
            "10th Marks (%):", "12th Marks (%):", "CGPA:", "Degree:", "Projects:"
        };
        JTextField[] fields = {
            nameField = new JTextField(20), emailField = new JTextField(20),
            phoneField = new JTextField(20), addressField = new JTextField(20),
            experienceField = new JTextField(20), skillsField = new JTextField(20),
            hobbiesField = new JTextField(20), tenthMarksField = new JTextField(20),
            twelfthMarksField = new JTextField(20), cgpaField = new JTextField(20),
            degreeField = new JTextField(20), projectsField = new JTextField(20)
        };

        gbc.gridwidth = 1;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i + 1;
            mainPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            mainPanel.add(fields[i], gbc);
        }

        generateButton = new JButton("Generate PDF Resume");
        generateButton.setBackground(new Color(0, 102, 204));
        generateButton.setForeground(Color.WHITE);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = labels.length + 1;
        mainPanel.add(generateButton, gbc);

        generateButton.addActionListener(e -> generatePDF());

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
    }

    private void generatePDF() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(name.replace(" ", "_") + "_Resume.pdf"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                // 1. Initialize PDF writer and documents
                PdfWriter writer = new PdfWriter(fileChooser.getSelectedFile());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // 2. Add Header
                document.add(new Paragraph(name.toUpperCase())
                        .setFontSize(20).setBold().setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph(emailField.getText() + " | " + phoneField.getText() + " | " + addressField.getText())
                        .setTextAlignment(TextAlignment.CENTER).setFontSize(10));
                document.add(new LineSeparator(new SolidLine()));

                // 3. Education Section
                document.add(new Paragraph("\nEDUCATION").setBold().setFontSize(14));
                document.add(new Paragraph("Degree: " + degreeField.getText()));
                document.add(new Paragraph("CGPA: " + cgpaField.getText() + " | 12th: " + twelfthMarksField.getText() + "% | 10th: " + tenthMarksField.getText() + "%"));

                // 4. Skills & Projects
                document.add(new Paragraph("\nTECHNICAL SKILLS").setBold().setFontSize(14));
                document.add(new Paragraph(skillsField.getText()));

                document.add(new Paragraph("\nPROJECTS").setBold().setFontSize(14));
                document.add(new Paragraph(projectsField.getText()));

                // 5. Experience
                document.add(new Paragraph("\nEXPERIENCE").setBold().setFontSize(14));
                document.add(new Paragraph(experienceField.getText() + " years of experience."));

                document.close();
                JOptionPane.showMessageDialog(this, "Resume PDF generated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResumeBuilder1().setVisible(true));
    }
}
