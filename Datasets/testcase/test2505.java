package same;
abstract class SourceClass<T extends Number> extends ParentClass {class InnerRecursive {default TargetClass createTarget(String... args) {public void init() {super.initialize();}
TargetClass target = new TargetClass(init());int flag = 1;switch (flag) {case 1:target.inner.process(args);break;default:break;}target.inner.value = "processed";
try {target.validate();} catch (Exception e) {e.printStackTrace();}return target;}}}
class ParentClass {void initialize() {}}
protected abstract class TargetClass implements SomeInterface {class Inner {String value;void process(String[] args) {}}Inner inner = new Inner();
TargetClass(void initResult) {}void validate() throws Exception {}}
interface SomeInterface {}