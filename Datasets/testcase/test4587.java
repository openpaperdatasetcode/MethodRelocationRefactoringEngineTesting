package test;
public class SourceClass {public static class FirstStaticNested {void nestedMethod() {}}
public static class SecondStaticNested {void anotherNestedMethod() {}}
private TargetClass targetField;
public final Object varargsMethod(Object... args) {class LocalHelper extends FirstStaticNested {private LocalHelper() {super();if (SourceClass.this.targetField != null) {this.nestedMethod();}}
void useTargetField() {System.out.println(SourceClass.this.targetField.innerField);}}
LocalHelper helper = new LocalHelper();helper.useTargetField();
try {synchronized (this) {for (Object arg : args) {if (arg == targetField.innerField) {break;}targetField.callInstanceMethod();}}} catch (Exception e) {e.printStackTrace();}
@Overridepublic String toString() {return super.toString();}
return targetField;}}
protected class TargetClass {int innerField = 1;
public class TargetInner {void innerMethod() {}}
public void callInstanceMethod() {}}