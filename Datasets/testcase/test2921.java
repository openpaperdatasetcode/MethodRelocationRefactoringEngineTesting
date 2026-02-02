package samepkg;
import java.lang.reflect.Type;import com.google.common.reflect.TypeToken;
protected class SourceClass extends ParentClass {public TargetClass targetField; // Per condition: source contains target's fieldpublic static String STATIC_FIELD = "staticValue"; // Static field dependency
public class MemberInnerClass {}
public void outerMethod() {class LocalInnerClass {} // Local inner class}
// Overloading method 1protected int process() {return process(targetField);}
// Overloading method 2 (to be refactored)protected int process(TargetClass target) {// Type declaration statementClass<?> targetType = target.getClass();
// 2 default TypeLiteral expressionsTypeLiteral<String> type1 = new TypeLiteral<>() {};TypeLiteral<Integer> type2 = new TypeLiteral<>() {};
// Enhanced for statementint sum = 0;for (String item : new String[]{"a", "b"}) {sum += item.length();}
// Variable call + depends on static fieldsum += STATIC_FIELD.length();target.setValue(sum);
// Overriding method (property assignment position)overrideMethod(target);
// Chain call: obj.m1().m2().m3()int chainResult = target.m1().m2().m3();return sum + chainResult;}
// Overriding method (matches method_feature)@Overridepublic int overrideMethod(TargetClass target) {target.setProperty(STATIC_FIELD); // Property assignmentreturn 1;}}
// Parent class for overridingabstract class ParentClass {public abstract int overrideMethod(TargetClass target);}
// TypeLiteral simulation (matches exp requirement)class TypeLiteral<T> {protected final Type type;
@SuppressWarnings("unchecked")public TypeLiteral() {this.type = new TypeToken<T>(getClass()) {}.getType();}}
package samepkg;
protected class TargetClass {private int value;private String property;
// Chain call methodspublic TargetClass m1() {return this;}
public TargetClass m2() {return this;}
public int m3() {return value;}
// Getter/Setterpublic void setValue(int value) {this.value = value;}
public void setProperty(String property) {this.property = property;}}