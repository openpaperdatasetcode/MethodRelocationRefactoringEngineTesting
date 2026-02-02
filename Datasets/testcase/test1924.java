package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
@MyAnnotationstrictfp class SourceClass permits SubSourceClass {static class StaticNested {}
Object method(TargetClass targetParam) {class LocalType {}LocalType local = new LocalType();
TargetClass.MemberInner inner = targetParam.new MemberInner();TargetClass.MemberInner.InnerRec innerRec = inner.new InnerRec();
Runnable r = new Runnable() {public void run() {innerRec.setValue("anonymous");}};r.run();
try {Supplier<List<String>> supplier;if (TargetClass.STATIC_FIELD > 0) {supplier = innerRec::getList;} else {supplier = inner::getDefaultList;}return supplier.get();} catch (Exception e) {return super.getClass();}}}
class SubSourceClass extends SourceClass {}
private class TargetClass {static int STATIC_FIELD = 1;
class MemberInner {List<String> getDefaultList() {return new ArrayList<>();}
class InnerRec {private String value;
void setValue(String val) {this.value = val;}
List<String> getList() {List<String> list = new ArrayList<>();list.add(value);return list;}}}}
@interface MyAnnotation {}