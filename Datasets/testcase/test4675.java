package test;
interface BaseInterface {Object handle(Object obj) throws Exception;}
interface SourceInterface extends BaseInterface {default void createInner() {class SourceInner {private Object processTarget(TargetInterface target, int depth) throws Exception {if (depth <= 0) return null;
staticLabel: {class TargetWrapper {private TargetInterface targetField;
public TargetWrapper(TargetInterface target) {this.targetField = target;}
public Object getTargetField() {return this.targetField;}}
TargetWrapper wrapper = new TargetWrapper(target);System.out.println(wrapper.getTargetField());
if (depth == 2) {break staticLabel;}}
target.getInner().execute();return processTarget(target, depth - 1);}}
TargetInterface target = new TargetInterface() {@Overridepublic Inner getInner() {return () -> System.out.println("Target inner executed");}
@Overridepublic int calculate(int a, int b) {return a + b;}};
SourceInner inner = new SourceInner();try {inner.processTarget(target, 3);} catch (Exception e) {e.printStackTrace();}}
@Overrideprivate Object handle(Object obj) throws Exception {if (obj instanceof TargetInterface) {TargetInterface target = (TargetInterface) obj;return target.calculate(1, 2);}return null;}}
protected interface TargetInterface {Inner getInner();
final int calculate(int a, int b) {class LocalCalculator {int computeSum(int x, int y) {return x + y;}}return new LocalCalculator().computeSum(a, b);}
interface Inner {void execute();}}