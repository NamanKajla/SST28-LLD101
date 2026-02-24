public class DisciplinaryRule implements EligibilityRule {
    public boolean fails(StudentProfile s) {
        return s.disciplinaryFlag != LegacyFlags.NONE;
    }

    public String reason() {
        return "disciplinary flag present";
    }
}