public class AttendanceRule implements EligibilityRule {
    public boolean fails(StudentProfile s) {
        return s.attendancePct < 75;
    }

    public String reason() {
        return "attendance below 75";
    }
}