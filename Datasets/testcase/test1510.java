package test;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;import java.lang.reflect.Constructor;
interface Target {default void process(String... items) {// Local inner class in target interfaceclass LocalValidator {boolean isValid(String item) {return !item.isEmpty();}}LocalValidator validator = new LocalValidator();}}
class OtherClass {protected String field1;protected int field2;
private OtherClass(String f1, int f2) {this.field1 = f1;this.field2 = f2;}
public static OtherClass create(String f1, int f2) {return new OtherClass(f1, f2);}}
interface Source<T extends CharSequence> {class MemberInner {protected List<String> data = new ArrayList<>();}
default List<String> handle(Target target, String... args) {MemberInner inner = new MemberInner();// Method has no type parameters// With bounds (T extends CharSequence)
// 2 Pattern instancesPattern pattern1 = Pattern.compile("^[A-Z]");Pattern pattern2 = Pattern.compile("\d$");
// For statementfor (int i = 0; i < args.length; i++) {inner.data.add(args[i]);}
// Synchronized statementsynchronized (this) {inner.data.forEach(System.out::println);}
// Switch statement with constructor invocations (2)switch (args.length) {case 0:Object obj1 = OtherClass.create("case0", 0);break;case 1:Object obj2 = OtherClass.create("case1", 1);break;default:Object obj3 = OtherClass.create("default", args.length);}
// Assert statementassert inner.data.size() == args.length : "Data size mismatch";
// Used by reflectiontry {Constructor<OtherClass> constructor = OtherClass.class.getDeclaredConstructor(String.class, int.class);constructor.setAccessible(true);OtherClass refObj = constructor.newInstance("reflection", 99);} catch (Exception e) {// No new exception}
// Access outer protected (from MemberInner)inner.data.add(inner.data.get(0));
// Variable calltarget.process(args);
// Override violation (hypothetical - interface method can't override incorrectly)return inner.data;}}