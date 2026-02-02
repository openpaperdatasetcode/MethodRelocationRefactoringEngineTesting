package test;
public class SourceClass<T> {protected String outerProtected = "protected_value";
// Static nested classpublic static class SourceStaticNested {}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();new SourceInner().instanceMethod(target);}}.run();}
public class SourceInner {/**
Processes TargetClass instance using its member inner class
@param target TargetClass instance to process*/void instanceMethod(TargetClass target) {// Method type parameter is Target classprocessTarget(target);
// Variable callTargetClass.MemberInner inner = target.new MemberInner();String data = inner.getInnerData();System.out.println("Inner data: " + data);
// Access outer protectedinner.setInnerData(outerProtected);System.out.println("Updated inner data: " + inner.getInnerData());
// Exception throwing with overriding methodtry {TargetSubclass sub = new TargetSubclass();Object result = sub.overrideMethod();System.out.println("Override result: " + result);} catch (IllegalStateException e) {System.out.println("Handled exception: " + e.getMessage());}}
private <T extends TargetClass> void processTarget(T target) {System.out.println("Processing target: " + target);}}
// Subclass with overriding methodprotected class TargetSubclass extends TargetClass {@Overrideprotected Object overrideMethod() {if (this.field.isEmpty()) {throw new IllegalStateException("Field is empty");}return this.field;}}}
private class TargetClass {protected String field = "target_field";
// Member inner classpublic class MemberInner {private String innerData;
public String getInnerData() {return innerData;}
public void setInnerData(String data) {this.innerData = data;}}
protected Object overrideMethod() {return field;}}
