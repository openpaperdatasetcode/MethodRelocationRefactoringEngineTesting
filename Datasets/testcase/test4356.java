package test;
interface TargetInterface {}
public class TargetClass implements TargetInterface {private int targetField;
public static class TargetStaticNested {public static int getStaticValue() {return 100;}}
public int getTargetField() {return targetField;}
public void setTargetField(int targetField) {this.targetField = targetField;}}
abstract class SourceClass {private int sourceField;
class MemberInner {public int getSourceFieldAccessor() {return sourceField;}
public void setSourceFieldAccessor(int value) {sourceField = value;}}
private SourceClass(TargetClass target) {class LocalInner {void processTarget(TargetClass tc) {variableCall(tc);tc.setTargetField(SourceClass.getSourceStaticMethod(tc.getTargetField()));}}
LocalInner localInner = new LocalInner();localInner.processTarget(target);
MemberInner memberInner = new MemberInner();memberInner.setSourceFieldAccessor(target.getTargetField());accessInstanceMethod(memberInner);}
private void variableCall(TargetClass target) {target.setTargetField(target.getTargetField() + 50);}
private void accessInstanceMethod(MemberInner memberInner) {sourceField = memberInner.getSourceFieldAccessor() * 2;}
public static int getSourceStaticMethod(int param) {return param + TargetClass.TargetStaticNested.getStaticValue();}
public static SourceClass createInstance(TargetClass target) {return new SourceClass(target) {};}}