import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {class InnerClass extends TargetClass.SubClass {public InnerClass(TargetClass.StaticNested nested) {super(() -> this.processNested(nested));}
void process(TargetClass targetParam) {List<T> dataList = new ArrayList<>();
{Object result = super.callSubMethod();dataList.add((T) result);}
try {synchronized (this) {for (int i = 0; i < 3; i++) {if (targetParam == null) {throw new NullPointerException("Target parameter is null");}targetParam.StaticNested.processData(dataList);}}} catch (NullPointerException e) {// Handle NPE without throwing new exception}}
private TargetClass processNested(TargetClass.StaticNested nested) {return new TargetClass();}}
{Runnable r1 = new Runnable() {public void run() {new InnerClass(new TargetClass.StaticNested()).process(new TargetClass());}};Runnable r2 = new Runnable() {public void run() {TargetClass.StaticNested.staticMethod();}};}}
public class TargetClass {public static class StaticNested {public static void processData(List<?> data) {}public static void staticMethod() {}}
public static class SubClass {private final Supplier<TargetClass> supplier;
public SubClass(Supplier<TargetClass> supplier) {this.supplier = supplier;}
public Object callSubMethod() {return supplier.get();}}}
interface Supplier<T> {T get();}