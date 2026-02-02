// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import com.other.OtherHelper;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public class SourceClass {// Static nested classpublic static class SourceStatic {public static String STATIC_DATA = "source_static";}
// Member inner classpublic class SourceInner {public List<String> normalMethod(TargetClass.InnerRec targetRec) {List<String> result = new ArrayList<>();
// Variable callresult.add(targetRec.value());
// Access instance methodresult.add(targetRec.process(2));
// Raw typeList rawList = new ArrayList();rawList.add(targetRec.id());result.add(rawList.get(0).toString());
// Depends on static fieldresult.add(TargetClass.StaticNested.STATIC_FIELD + "_" + SourceStatic.STATIC_DATA);
// Switch caseswitch (targetRec.id() % 3) {case 0:result.add("case0");break;case 1:result.add("case1");break;default:result.add("default");}
// Functional interface with static inner_class method (super call)Supplier<List<String>> supplier = TargetClass.Inner::staticProcess;result.addAll(supplier.get());
// Requires try-catchtry {result.add(String.valueOf(Integer.parseInt(targetRec.value())));} catch (NumberFormatException e) {result.add("parse_error");}
// Private ConstructorInvocation with ClassName.field (same package others)OtherHelper helper = new OtherHelper();result.add(helper.createWithField(targetRec).toString());
return result;}}}
// Other package: com.other (same as target package)package com.other;
import com.target.TargetClass;
public class OtherHelper {public TargetClass.InnerRec createWithField(TargetClass.InnerRec base) {class PrivateCreator {private TargetClass.InnerRec create() {return new TargetClass.InnerRec(base.id() + TargetClass.StaticNested.STATIC_FIELD,base.value() + "_new");}}return new PrivateCreator().create();}}
// Target package: com.targetpackage com.target;
import java.util.ArrayList;import java.util.List;
class TargetClass {// Static nested classpublic static class StaticNested {public static int STATIC_FIELD = 100;}
// Member inner classpublic class Inner {protected static List<String> staticProcess() {List<String> list = new ArrayList<>();list.add("static_process");list.addAll(superStaticProcess());return list;}
private static List<String> superStaticProcess() {return new ArrayList<>(List.of("super_process"));}}
public record InnerRec(int id, String value) {public String process(int multiplier) {return value.repeat(multiplier);}}}