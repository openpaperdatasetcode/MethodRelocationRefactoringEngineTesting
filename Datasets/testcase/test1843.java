package test;
protected class SourceClass extends SourceParent {public int value = 5;
TargetClass normalMethod(TargetClass target) {// Constructor invocationTargetClass newTarget = new TargetClass(target.field);
// Super constructor invocation in anonymous subclassTargetClass subTarget = new TargetClass(10) {{super(target.field * 2);}};
// Lambda expressionRunnable printer = () -> System.out.println(this.value);printer.run();
// Variable callint sum = target.field + newTarget.field;newTarget.field = sum;
// Overload existsString str1 = process(target.field);String str2 = process(target.field, "prefix_");
// Requires try-catchtry {TargetClass.StaticNested nested = new TargetClass.StaticNested();newTarget.field = nested.parse(str1);} catch (NumberFormatException e) {newTarget.field = 0;}
// Instance code blocks with call method{String result = TargetClass.InnerClass.format(target.field);newTarget.info = result;}
return newTarget;}
// Overloaded methodsprivate String process(int num) {return String.valueOf(num);}
private String process(int num, String prefix) {return prefix + num;}}
class SourceParent {}
protected class TargetClass extends TargetParent {int field;String info;
public TargetClass(int field) {this.field = field;}
// Static nested classpublic static class StaticNested {public int parse(String s) throws NumberFormatException {return Integer.parseInt(s);}}
// Inner class with overloadingpublic static final class InnerClass {public static String format(int num) {return "Formatted: " + num;}
public static String format(int num, int scale) {return "Formatted (" + scale + "): " + (num * scale);}}}
class TargetParent {}