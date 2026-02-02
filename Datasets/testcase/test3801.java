package samepkg;
protected class SourceClass permits SubSource {class MemberInner {class RecursiveInner {strictfp RecursiveInner() {new TargetClass.TargetInner();int num = 1;String result = switch (num) {case 1 -> "one";default -> "other";};TargetClass.targetField = 5;return new RecursiveInner();}}}
void methodWithLocal() {class LocalInner {private Object callMethod() {return new Object();}}LocalInner li = new LocalInner();while (true) {Object obj = li::callMethod;}}}
public class TargetClass {/**
Javadoc for TargetClass
*/
static int targetField;
class TargetInner {@Overridepublic String toString() {return super.toString();}}}
class SubSource extends SourceClass {}
