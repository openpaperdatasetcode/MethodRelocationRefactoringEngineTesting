package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
class SourceClass {static class StaticNested {static Object createInstance() {return new SourceClass();}}
class MemberInner {TargetClass.InnerRec getInnerRec(TargetClass target) {return target.new InnerRec();}}
// Accessor methodObject getValue(TargetClass target) {if (target == null) {return null;}
// Raw type usageList rawList = new ArrayList();rawList.add(target.field);
// Constructor invocation with ClassName.field=1TargetClass.StaticHelper helper = new TargetClass.StaticHelper(TargetClass.STATIC_FIELD);
// Synchronized constructor-like behavior (emulated with factory method)for (int i = 0; i < 2; i++) {TargetClass.Inner inner = new TargetClass.Inner(this);rawList.add(inner.process(i));}
// Continue statementfor (int i = 0; i < 5; i++) {if (i == 3) {continue;}target.field = i;}
// Ternary operator with method reference to others classSupplier<List<String>> supplier = target.field instanceof Integer? OthersClass::getList: OthersClass::getEmptyList;rawList.addAll(supplier.get());
// Access outer this in inner classnew MemberInner().getInnerRec(target).setOuter(SourceClass.this);
return rawList;}}
abstract class TargetClass implements SomeInterface {static final int STATIC_FIELD = 1;Object field;
static class StaticHelper {StaticHelper(int value) {// Constructor with ClassName.field}}
class Inner {private SourceClass outerSource;
// Synchronized constructor emulationsynchronized Inner(SourceClass source) {this.outerSource = source;}
Object process(int value) {return value * 2;}}
class InnerRec {void setOuter(SourceClass outer) {// Uses outer this from SourceClass}}}
interface SomeInterface {}
class OthersClass {static List<String> getList() {List<String> list = new ArrayList<>();list.add("item");return list;}
static List<String> getEmptyList() {return new ArrayList<>();}}