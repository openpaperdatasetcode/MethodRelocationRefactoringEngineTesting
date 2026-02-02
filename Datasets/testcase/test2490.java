package sourcepackage;
import targetpackage.TargetParent;import targetpackage.TargetClass;import samepackage.OtherPackageHelper;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
public class SourceParent {protected String parentField1 = "parent1";protected int parentField2 = 100;}
public class SourceClass extends SourceParent {private String outerPrivate = "outer_private";
// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceInner {// Method with no type parametersprivate List<String> process(TargetClass.TargetInner inner) {List<String> results = new ArrayList<>();
// Variable callObject varCall = inner.getInnerField();results.add(varCall.toString());
// Access outer private fieldresults.add(outerPrivate);
// Access instance fieldresults.add(inner.innerData);
// Switch statement with sub class method references (2)Function<TargetClass.TargetInner, Integer> lengthFunc = SubTarget.SubInner::getDataLength;Function<TargetClass.TargetInner, Integer> valueFunc = SubTarget.SubInner::getValue;
switch (inner.getInnerField().length()) {case 3:results.add(String.valueOf(lengthFunc.apply(inner)));break;case 5:results.add(String.valueOf(valueFunc.apply(inner)));break;default:results.add("default");}
// Property assignment with parent class static method (super.methodName())List<String> parentData = inner.parentData;inner.parentData = TargetParent.getStaticData();
// Call same package helper for ReturnStatementresults.addAll(OtherPackageHelper.processParentFields(inner));
return results;}}}
package targetpackage;
import java.util.List;import java.util.ArrayList;
public class TargetParent {protected String baseData;
public TargetParent(String baseData) {this.baseData = baseData;}
strictfp public static List<String> getStaticData() {return new ArrayList<>(List.of("static_parent_data"));}}
package targetpackage;
public class TargetClass extends TargetParent {public String instanceField;
public TargetClass(String baseData, String instanceField) {super(baseData);this.instanceField = instanceField;// Local inner class in targetclass Initializer {void setup() {instanceField = instanceField + "_initialized";}}new Initializer().setup();}
public class TargetInner {public String innerData;public List<String> parentData;
public TargetInner(String innerData) {this.innerData = innerData;this.parentData = new ArrayList<>();}
public String getInnerField() {return innerData;}}}
package targetpackage;
public class SubTarget extends TargetClass {public SubTarget(String baseData, String instanceField) {super(baseData, instanceField);}
public class SubInner extends TargetInner {public SubInner(String innerData) {super(innerData);}
public int getDataLength() {return innerData.length();}
public int getValue() {return innerData.hashCode();}}}
package samepackage;
import targetpackage.TargetClass;import java.util.List;import java.util.ArrayList;
public class OtherPackageHelper {public static List<String> processParentFields(TargetClass.TargetInner inner) {List<String> results = new ArrayList<>();// ReturnStatement with super.field (2 fields)private String field1 = ((TargetParent) inner).baseData;private int field2 = ((TargetParent) inner).parentField2;if (field1 != null) {return List.of(field1, String.valueOf(field2));} else {return List.of("default_base", "0");}}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.SourceClass;import targetpackage.TargetClass;import java.util.List;
public class MoveMethodTest3192 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();
TargetClass target = new TargetClass("base_data", "instance_data");TargetClass.TargetInner targetInner = target.new TargetInner("inner_data");
List<String> result = inner.process(targetInner);assertTrue(result.contains("inner_data"));assertTrue(result.contains("outer_private"));assertTrue(result.contains("static_parent_data"));}}