public class CreditsRule implements EligibilityRule {
    public boolean fails(StudentProfile s) {
        return s.earnedCredits < 20;
    }

    public String reason() {
        return "credits below 20";
    }
}