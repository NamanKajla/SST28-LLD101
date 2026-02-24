import java.util.List;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();
    private final OnboardingPrinter printer = new OnboardingPrinter();

    public OnboardingService(FakeDb db) {
        this.repo = new FakeDbRepository(db);
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        StudentData data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(
                id, data.name, data.email, data.phone, data.program
        );

        repo.save(rec);
        printer.printSuccess(rec, repo.count());
    }
}