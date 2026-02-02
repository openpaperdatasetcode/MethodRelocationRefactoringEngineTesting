package test.source;
protected class SourceClass {private Object getValue() {super.toString();Object obj = new Object();test.target.TargetClass.NestedClass nested = new test.target.TargetClass.NestedClass();int num = 2;Object result;do {result = nested.field;} while (num > 0);try {Class<?> cls = Class.forName("test.target.TargetClass$NestedClass");java.lang.reflect.Field field = cls.getDeclaredField("field");field.setAccessible(true);obj = field.get(nested);} catch (Exception e) {}return obj;}}
package test.target;
private class TargetClass {private static class NestedClass {protected Object field;}}
package test.sub;
import test.source.SourceClass;
protected class SubClass extends SourceClass {protected void callMethod() {new SourceClass();do {this.getValue();} while (false);}}