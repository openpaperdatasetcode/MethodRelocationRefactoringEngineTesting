package test;
import java.util.function.Function;
class SourceClass<T extends CharSequence> extends ParentClass {public static class StaticNested {}
class MemberInner {protected void moveMethod (ProtectedTarget target, T... values) {
Function<T, Object> mapper1 = ParentClass::process1;Function<T, Object> mapper2 = ParentClass::process2;
for (T val : values) {
if (val == null) {throw new NullPointerException ("Value cannot be null");}
Object result1 = mapper1.apply (val);Object result2 = mapper2.apply (val);target.inner.handle (result1, result2);
// switch caseswitch (val.length()) {case 1:target.inner.log("Short value");break;case 2:target.inner.log("Medium value");break;default:target.inner.log("Long value");}}}}}
abstract class ParentClass {protected static <T extends CharSequence> Object process1(T val) {return val.toString().toUpperCase();}
protected static <T extends CharSequence> Object process2(T val) {return val.toString().toLowerCase();}}
protected class ProtectedTarget {final Inner inner = new Inner();
class Inner {public void handle(Object obj1, Object obj2) {}public void log(String msg) {}}
{Runnable anon = new Runnable () {@Overridepublic void run () {inner.log ("Anonymous inner class running");}};}}