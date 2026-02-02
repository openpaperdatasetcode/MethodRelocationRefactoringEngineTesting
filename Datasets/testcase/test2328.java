package same.pkg;
import java.lang.annotation.*;
@interface Source {class MemberInner {}
// Anonymous inner class in annotation (using initializer)Runnable anon = new Runnable() {@Overridepublic void run() {}};
// Default method in annotation (simulating normal method)@Deprecateddefault void normalMethod(Target<?> targetParam) {// Constructor invocation (for inner class)Target.MemberInner targetInner = new Target.MemberInner();
// Type declaration statementTarget<String> stringTarget;
// CharacterLiteral with numbers=2char c1 = 'a';char c2 = 'b';default int charCount = 2;
// Variable callstringTarget = (Target<String>) targetParam;String val = stringTarget.value();
// Access instance field (simulated via annotation method)String fieldVal = stringTarget.field();}
// Dummy method to simulate instance fieldString field() default "";}
abstract interface Target<T> {class MemberInner {}
T value();
// Dummy method to simulate instance fieldString field();}