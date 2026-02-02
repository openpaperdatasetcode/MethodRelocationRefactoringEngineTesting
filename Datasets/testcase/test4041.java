package same.pkg;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
class GrandparentClass {protected String grandparentField = "grandparent_data";}
public abstract class SourceClass extends GrandparentClass {private TargetClass targetField;
static class FirstStaticNested {static <T> List<T> wrapData(T data) {List<T> list = new ArrayList<>();list.add(data);return list;}}
static class SecondStaticNested {static boolean validateTarget(TargetClass target) {return target != null;}}
@FunctionalInterfaceprivate interface DataSupplier {String get() throws IllegalArgumentException;}
public List<String> processTarget(TargetClass target) throws IllegalArgumentException {if (!SecondStaticNested.validateTarget(target)) {throw new NullPointerException("TargetClass cannot be null");}
this.targetField = target;private boolean isFieldSet = this.targetField != null;assert isFieldSet : "TargetClass field not initialized";
DataSupplier supplier = target::getData;List<String> result;try {result = FirstStaticNested.wrapData(supplier.get());} catch (Exception e) {Supplier<Object> errorHandler = SourceClass::handleError;result = FirstStaticNested.wrapData(errorHandler.get().toString());return result;}
return result;}
public List<String> processTarget(TargetClass target, String extra) throws IllegalArgumentException {List<String> baseResult = processTarget(target);baseResult.add(extra);return baseResult;}
protected static Object handleError() {return "error_processed";}
@Overridepublic abstract List<String> processTarget(TargetClass target) throws IllegalArgumentException;}
private abstract class TargetClass {private String targetData;
public TargetClass(String data) {this.targetData = data;}
public String getData() throws IllegalArgumentException {if (targetData == null || targetData.isEmpty()) {throw new IllegalArgumentException("Target data is invalid");}return targetData;}
public abstract void updateData(String newData);}