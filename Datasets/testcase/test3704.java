import java.util.ArrayList;import java.util.List;
@interface SourceAnnotation {strictfp <T extends TargetAnnotation> List<String> instanceMethod(T target) throws InvalidAnnotationException {List<String> result = new ArrayList<>();TargetAnnotation.MemberInner inner = target.memberInner();
variableCall(inner);result.add(inner.process(target.value()));
if (inner.getCount() < 0) {throw new InvalidAnnotationException("Invalid count in inner class");}
return result;}
private void variableCall(TargetAnnotation.MemberInner inner) {inner.increment();}}
/**
TargetAnnotation with member inner class for auxiliary operations.
Provides overloaded methods for value processing.*/public @interface TargetAnnotation {String value() default "";
class MemberInner {private int count = 0;
public void increment() {count++;}
public int getCount() {return count;}
public String process(String input) {return input + "_processed";}
public String process(int input) {return "num_" + input;}}
MemberInner memberInner() default @MemberInner;}
class AnnotationProcessor {private TargetAnnotation callInSwitch(int type) {TargetAnnotation target = new TargetAnnotation() {};TargetAnnotation.MemberInner inner = target.memberInner();
switch (type) {case 1:inner.process(target.value());break;case 2:inner.process(100);break;default:break;}return target;}}
class InvalidAnnotationException extends Exception {public InvalidAnnotationException(String message) {super(message);}}