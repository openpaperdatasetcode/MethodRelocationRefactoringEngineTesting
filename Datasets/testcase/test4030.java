package same.pkg;
interface DataProcessor {int process(Object obj);}
class ParentGenericClass<T> {protected T parentData;}
public class SourceClass<T extends Number> extends ParentGenericClass<T> implements DataProcessor {private TargetClass<T> targetField;private String outerPrivate = "private";
/**
Accessor method to retrieve target's processed value.
@return processed integer value from target*/protected int getTargetValue() {class LocalInner {String getPrivateField() {return outerPrivate;}}
Runnable anon = new Runnable() {@Overridepublic void run() {new LocalInner().getPrivateField();}};anon.run();
TargetClass.StaticNested<T> nested = new TargetClass.StaticNested<>();nested.process(this);return nested.calculate(targetField);}
@Overridepublic int process(Object obj) {if (obj instanceof TargetClass) {TargetClass<T> target = (TargetClass<T>) obj;return target.compute(targetField.data);} else {return 0;}}}
protected class TargetClass {
U data;
static class StaticNested<V> {int calculate(TargetClass<V> target) {return ((Number) target.data).intValue();}
void process(SourceClass<V> source) {source.process(this);}}
int compute(U value) {return ((Number) value).intValue() * 2;}}