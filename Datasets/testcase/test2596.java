package test.same;
import java.util.ArrayList;import java.util.List;
public class SourceClass {class MemberInner {}
private List<String> instanceMethod() {TargetClass target = new TargetClass() {@Overridevoid createLocal() {class LocalInner {}}};target.createLocal();TargetClass.LocalInner.InnerRec rec = new TargetClass.LocalInner.InnerRec();Object var = rec.field;
String[] array = { OthersClass.varargsMethod(rec.field, "a", "b") };return new ArrayList<>(List.of(array));}
void createLocal() {class LocalHelper {}}}
abstract class TargetClass implements SomeInterface {void createLocal() {class LocalInner {record InnerRec() {String field;}}}}
interface SomeInterface {}
class OthersClass {private static String varargsMethod(Object... args) {return args[0].toString();}}