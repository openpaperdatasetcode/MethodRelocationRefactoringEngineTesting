package test.source;
import test.target.ParentTargetClass;import java.util.List;import java.util.ArrayList;
private sealed class SourceClass<T> permits SourceSubClass {private T sourceField;
public void processData(ParentTargetClass<String> targetParam, String... args) throws IllegalArgumentException {if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
do {try {for (int i = 0; i < args.length; i++) {if (args[i] == null) {continue;}TargetInnerClass targetInner = ParentTargetClass.createTargetInner(targetParam, args[i]);sourceField = (T) targetInner.process(args[i]);System.out.println("Processed: " + sourceField);}} catch (IllegalArgumentException e) {System.err.println("Error processing: " + e.getMessage());}} while (args.length > 0);
class LocalInnerClass {void useTarget() {targetParam.execute(args);}}new LocalInnerClass().useTarget();}
class MemberInnerClass {void callTargetMethod(ParentTargetClass<String> target) {target.execute("inner", "call");}}}
non-sealed class SourceSubClass<T> extends SourceClass<T> {}
// -----------------------------------------------------------------------------
package test.target;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class ParentTargetClass<T> extends GenericSuperClass<T> {private T targetField;
public void execute(String... params) {Runnable runnable = new Runnable() {@Overridepublic void run() {for (String param : params) {System.out.println("Target executing: " + param);}}};runnable.run();}
public static <T> TargetInnerClass<T> createTargetInner(ParentTargetClass<T> outer, T data) {return outer.new TargetInnerClass<>(data);}
public class TargetInnerClass<T> {private T innerField;
public TargetInnerClass(T data) {this.innerField = data;}
public ParentTargetClass<T> process(T input) {this.innerField = input;return ParentTargetClass.this;}}}
class GenericSuperClass<T> {}
// -----------------------------------------------------------------------------
package test.caller;
import test.source.SourceClass;import test.source.SourceSubClass;import test.target.ParentTargetClass;import java.util.List;
public class MethodCaller {private static List<String> fetchData(Supplier<List<String>> supplier) {return supplier.get();}
public static void main(String[] args) throws IllegalArgumentException {SourceClass<String> source = new SourceSubClass<>();ParentTargetClass<String> target = new ParentTargetClass<>();
// Lambda expression with method referenceList<String> result = fetchData(() -> MethodCaller::processTargetData);
source.processData(target, "arg1", "arg2", "arg3");}
private static List<String> processTargetData() {List<String> data = new ArrayList<>();data.add("caller_data");return data;}}
