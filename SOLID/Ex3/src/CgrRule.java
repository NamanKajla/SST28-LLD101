public class CgrRule implements EligibilityRule {
    public boolean fails(StudentProfile s) {
        return s.cgr < 8.0;
    }
    public String reason() {
        return "CGR below 8.0";
    }
}

