package same;
abstract class SourceClass<T> permits ConcreteSource<T> {private T outerField;
class InnerRec {private Object handle(TargetClass<T> target) {class LocalInner {void init() {TargetClass<T>.Inner inner = new TargetClass<T>.Inner(target);}}new LocalInner().init();
if (SourceClass.this.outerField == null) {throw new NullPointerException();}
TargetClass<T>.Inner inner = target.new Inner();Object result = null;if (inner.isValid()) {result = inner.getValue().getDetails().process();} else {result = "invalid";}
return result;}}
static class StaticNested {}}
class ConcreteSource<T> extends SourceClass<T> {}
package same;
protected class TargetClass<T> {class Inner {private T value;
Inner() {}
Inner(TargetClass<T> outer) {this.value = outer.getValue();}
boolean isValid() {return value != null;}
Details<T> getValue() {return new Details<>(value);}
class Details {
private U data;
Details(U data) {this.data = data;}
Object getDetails() {return this;}
Object process() {return data;}}}
private T getValue() {return null;}}