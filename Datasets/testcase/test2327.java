package same.pkg;
import java.io.IOException;
public record Source(String value) {// Two static nested classesstatic class StaticNested1 {}static class StaticNested2 {}
class SourceInner {private void instanceMethod(Target targetParam) {// Type declaration statementTarget localTarget;
// Variable calllocalTarget = targetParam;String targetValue = localTarget.value();
// Requires try-catchtry {targetParam.methodThatThrows();} catch (IOException e) {e.printStackTrace();}}}}
non-sealed record Target(String value) {void methodThatThrows() throws IOException {// Local inner class in targetclass LocalInner {}throw new IOException("Test exception");}}