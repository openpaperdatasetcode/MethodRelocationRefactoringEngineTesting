package test;
import java.util.List;import java.util.ArrayList;
// Generic source class (public modifier, type parameter + static nested class + anonymous inner class)public class SourceClass<T> {private T privateField; // For access_outer_private
// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {new SourceInnerRec<>().process();}};}
// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec<V> {// Abstract method feature (1, source, abstract, new ClassName().method(), pos: switch)public abstract List<String> abstractFeatureMethod();
// Concrete implementation for abstract featurepublic class AbstractFeatureImpl implements SourceAbstractInterface {@Overridepublic List<String> abstractFeatureMethod() {return new ArrayList<>(List.of("feature-result"));}}
// Varargs method (public access modifier, returns base type)public int varargsMethod(TargetClass<T>... targetParams) {int result = 0;AbstractFeatureImpl featureImpl = new AbstractFeatureImpl();
for (TargetClass<T> target : targetParams) {// Access outer private fieldT outerPrivateVal = SourceClass.this.privateField;
// Variable calltarget.targetMethod(outerPrivateVal);target.createAnonymousInner();
// Abstract method feature in switchswitch (target.getCode()) {case 1:List<String> featResult = new AbstractFeatureImpl().abstractFeatureMethod();result += featResult.size();break;}
// Requires_try_catchtry {target.processWithRisk(outerPrivateVal);} catch (IllegalArgumentException e) {// Handle exception}}
return result;}
private void process() {}}
// Interface for abstract feature methodpublic interface SourceAbstractInterface {List<String> abstractFeatureMethod();}}
// Generic target class (protected modifier, anonymous inner class)protected class TargetClass {
private int code = 1;
public void targetMethod(U data) {}
public int getCode() {return code;}
// Anonymous inner class (target_feature)public void createAnonymousInner() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
public void processWithRisk(U data) {if (data == null) {throw new IllegalArgumentException("Data cannot be null");}}}