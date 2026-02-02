package test;
import java.io.IOException;
abstract class SourceClass<T> {class SourceInner {}
void localInnerMethod() {class LocalInner {}}
public void setValue(TargetClass<T> targetParam) throws IOException {// Variable callT var = targetParam.value;targetParam.setValue(var);}}
abstract class TargetClass {
U value;
class TargetInner {void process(U val) {}}
protected String callMethod() throws Exception {SourceClass source = new SourceClass<>() {};
source.setValue(this);
TargetInner inner = new TargetInner();
return (param) -> {
inner.process(param);
throw new Exception("Lambda exception");
}.toString();
}
public void setValue(U val) {this.value = val;}}