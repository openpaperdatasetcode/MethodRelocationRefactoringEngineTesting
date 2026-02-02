package test;
import java.lang.reflect.Method;import java.util.List;
interface MyInterface<T> {}
public class TargetClass<T> {protected int field1 = 10;protected int field2 = 20;
class InnerClass {String getString() {return String.valueOf(field1 + field2);}}
{new Runnable() {@Overridepublic void run() {System.out.println(field1);}};}}
abstract class SourceClass<T> implements MyInterface<T> {@MyAnnotationpublic int instanceMethod(TargetClass<String> target) {int sum = 0;
// EnhancedForStatement with protected modifierprotected class EnhancedForContainer {void process(List<Integer> list) {for (int num : list) {sum += target.super.field1 + target.super.field2;}}}new EnhancedForContainer().process(List.of(1, 2));
// For statementfor (int i = 0; i < 2; i++) {sum += i;}
// Switch caseswitch (sum) {case 60:sum *= 2;break;default:sum /= 2;}
// Type declaration statementTargetClass.InnerClass inner = target.new InnerClass();
// Used by reflectiontry {Method method = inner.getClass().getDeclaredMethod("getString");String result = (String) method.invoke(inner);sum += Integer.parseInt(result);} catch (Exception e) {}
return sum;}}
@interface MyAnnotation {}