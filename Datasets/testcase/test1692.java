package test;
import java.util.List;
public record SourceClass(String id) {class MemberInner1 {}class MemberInner2 {}
/**
Generic method with enhanced for and if statements*/protected <T extends Number> void genericMethod(TargetClass.StaticNested.InnerRec param) {super.toString();variableCall();
for (int num : List.of(1, 2, 3)) {if (num > 2) {String result = new OthersClass().new InnerClass().method();throw new IllegalArgumentException(result);}}}
private void variableCall() {}}
public record TargetClass(String value) implements Runnable {static class StaticNested {class InnerRec {}}
@Overridepublic void run() {}}
class OthersClass {class InnerClass {String method() {return "others";}}}