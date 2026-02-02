package same;
interface Source {Target sourceTargetField = new Target();static class SourceStaticNested {}
class SourceInner {class SourceInnerRec {private Target.TargetInner overloadingMethod(int param) {volatile void tryStatementInSource() {try {ThisFieldHolder holder = new ThisFieldHolder();holder.thisField = "valid_value";if (holder.thisField == null) {throw new Exception("This.field is null");}} catch (Exception e) {e.printStackTrace();}}tryStatementInSource();
Target.TargetInner inner = sourceTargetField.new TargetInner();if (param > 0) {new Target().getInnerAccessor();new Target().getInnerAccessor();} else {inner.defaultMethod();}
private int prefix1 = ++param;private int prefix2 = ++param;private int prefix3 = ++param;
Object var = inner;SourceStaticNested rawNested = new SourceStaticNested();return inner;}
private Target.TargetInner overloadingMethod(String param) {return sourceTargetField.new TargetInner();}
private void callInnerClassMethod() {int count = 0;while (count < 2) {new InnerGenericClass<String>().genericMethod(param);count++;}}
private class InnerGenericClass<T> {private void genericMethod(T param) {}}}}
private static class ThisFieldHolder {String thisField;}}
protected interface Target {class TargetInner {public void defaultMethod() {}}
default void getInnerAccessor() {new TargetInner().defaultMethod();}}