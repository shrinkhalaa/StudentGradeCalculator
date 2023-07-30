import javax.swing.JOptionPane;

class Student {
    private String name;
    private double[] assignmentScores;
    private double examMark;

    public Student(String name, double[] assignmentScores, double examMark) {
        this.name = name;
        this.assignmentScores = assignmentScores;
        this.examMark = examMark;
    }

    public String getName() {
        return name;
    }

    public double[] getAssignmentScores() {
        return assignmentScores;
    }

    public double getExamMark() {
        return examMark;
    }
}

class GradeCalculator {
    private static final double ASSIGNMENT_WEIGHT = 0.4;
    private static final double EXAM_WEIGHT = 0.6;
    private static final double MIN_PASS_GRADE = 50.0;

    public static double calculateOverallGrade(double[] assignmentScores, double examMark) {
        if (assignmentScores.length == 0) {
            throw new IllegalArgumentException("Assignment scores array cannot be empty.");
        }

        double totalAssignmentScore = 0;
        for (double score : assignmentScores) {
            totalAssignmentScore += score;
        }

        double assignmentAverage = totalAssignmentScore / assignmentScores.length;
        return assignmentAverage * ASSIGNMENT_WEIGHT + examMark * EXAM_WEIGHT;
    }

    public static String getFinalGrade(double overallGrade) {
        if (overallGrade >= MIN_PASS_GRADE) {
            if (overallGrade >= 90.0) {
                return "A";
            } else if (overallGrade >= 80.0) {
                return "B";
            } else if (overallGrade >= 70.0) {
                return "C";
            } else if (overallGrade >= 60.0) {
                return "D";
            } else {
                return "E";
            }
        } else {
            return "F";
        }
    }
}

public class StudentGradeCalculator {
    public static void main(String[] args) {
        // Collect student information from user input
        String name = JOptionPane.showInputDialog(null, "Enter student's name:");
        
        int numAssignments = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of assignments:"));
        double[] assignmentScores = new double[numAssignments];

        for (int i = 0; i < numAssignments; i++) {
            double score = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter score for assignment " + (i + 1) + ":"));
            assignmentScores[i] = score;
        }

        double examMark = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter exam mark:"));

        // Create a new Student object
        Student student = new Student(name, assignmentScores, examMark);

        try {
            // Calculate overall grade
            double overallGrade = GradeCalculator.calculateOverallGrade(student.getAssignmentScores(), student.getExamMark());

            // Get final grade
            String finalGrade = GradeCalculator.getFinalGrade(overallGrade);

            // Prepare the message for the dialog box
            String message = "Student Name: " + student.getName() + "\n"
                    + "Overall Grade: " + overallGrade + "\n"
                    + "Final Grade: " + finalGrade;

            // Display the result in a dialog box
            JOptionPane.showMessageDialog(null, message, "Grade Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



