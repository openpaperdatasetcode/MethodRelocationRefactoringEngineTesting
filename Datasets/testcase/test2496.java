package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
abstract class EnumSuperType {protected String superField;
public EnumSuperType(String superField) {this.superField = superField;}
protected List<String> getSuperData(String suffix) {List<String> data = new ArrayList<>();data.add(superField + "_" + suffix);return data;}}
// Final target enum with extends and local inner classfinal enum TargetEnum extends EnumSuperType {VALUE_A("a"),VALUE_B("b");
private String targetField;
TargetEnum(String targetField) {super(targetField.toUpperCase());this.targetField = targetField;// Local inner class in targetclass EnumInitializer {void setup() {targetField = targetField + "_init";}}new EnumInitializer().setup();}
public String getTargetField() {return targetField;}}
// Private source enum with member inner class and anonymous inner classprivate enum SourceEnum {INSTANCE;
private String outerPrivate = "source_private";
// Member inner classclass SourceInner {public String getInnerData() {return outerPrivate + "_inner";}}
// Anonymous inner classSupplier<String> dataSupplier = new Supplier<>() {@Overridepublic String get() {return outerPrivate + "_supplied";}};
// Overloading methods (overloading type)private int process(TargetEnum target) {return process(target, 0);}
private int process(TargetEnum target, int offset) {// Variable callObject varCall = target.getTargetField();
// Access outer privateString privateData = outerPrivate;
// Constructor parameter list with target method call (superTypeReference.methodName(arguments))SourceInner inner = new SourceInner(target.super.getSuperData(privateData) // Super type reference method call);
// Combine results for base type returnString combined = target.getTargetField() + "" + privateData + "" + offset;return combined.length();}
// Inner class constructor accepting List<String> from target methodclass SourceInner {private List<String> superData;
public SourceInner(List<String> superData) {this.superData = superData;}}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;
public class MoveMethodTest3200 {@Testpublic void testOverloadingMethod() throws Exception {// Access private SourceEnum via reflectionSourceEnum source = SourceEnum.INSTANCE;Method processMethod = SourceEnum.class.getDeclaredMethod("process", TargetEnum.class);processMethod.setAccessible(true);
TargetEnum target = TargetEnum.VALUE_A;int result = (int) processMethod.invoke(source, target);
// Expected length: "a_init_source_private_0" → 18assertEquals(18, result);}}