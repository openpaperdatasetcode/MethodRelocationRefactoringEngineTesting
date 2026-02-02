// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.ArrayList;import java.util.List;import java.io.IOException;
sealed class SourceClass<T> permits SourceSubclass {protected T outerProtectedField;
public SourceClass(T field) {this.outerProtectedField = field;}
public class MemberInner {public void instanceMethod(TargetClass<String>.NestedRec innerRec) throws IOException {// NullPointerExceptionif (innerRec == null) {throw new NullPointerException("Inner record cannot be null");}
// Super constructor invocation in anonymous subclassList<String> baseList = new ArrayList<>() {{super();}};
// Varargs method with protected modifier (property assignment)List<String> combinedList = processVarargs(innerRec.value(),outerProtectedField.toString(),"additional");
// Variable call and access instance methodint count = 0;while (count < 5) {if (count % 2 == 0) {count++;continue; // Continue statement}baseList.add(innerRec.processValue(count));count++;}
// Access outer protected fieldbaseList.add("Outer protected: " + outerProtectedField);
// Assert statementassert baseList.size() > 0 : "List cannot be empty";
// Override violation (calling final method)String invalidCall = innerRec.finalMethod();
// Return statementif (combinedList.isEmpty()) {return;}
baseList.addAll(combinedList);}
// Protected varargs method with super callprotected List<String> processVarargs(String... items) {List<String> result = new ArrayList<>();result.addAll(superProcess(items)); // super.methodName()return result;}
private List<String> superProcess(String... items) {List<String> result = new ArrayList<>();for (String item : items) {result.add(item.toUpperCase());}return result;}}
public static class StaticNested {}}
final class SourceSubclass<T> extends SourceClass<T> {public SourceSubclass(T field) {super(field);}}
// Target package: com.targetpackage com.target;
import java.util.List;import java.util.function.Consumer;
class TargetClass<T> {public T targetField;
public TargetClass(T field) {this.targetField = field;
// Anonymous inner classConsumer<T> consumer = new Consumer<>() {@Overridepublic void accept(T t) {System.out.println("Processing target field: " + t);}};consumer.accept(targetField);}
public record NestedRec(T value) {public String processValue(int num) {return value.toString() + "_" + num;}
public final String finalMethod() {return "Final: " + value;}}}