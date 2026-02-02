package same.pkg;
import java.util.ArrayList;import java.util.List;
public record TargetClass<T>(T data) {public static class TargetInner {
private U innerData;
public TargetInner(U innerData) {this.innerData = innerData;}
public U getInnerData() {return innerData;}
public void setInnerData(U innerData) {this.innerData = innerData;}}}
non-sealed record SourceClass(String name) {private static final int PRIVATE_CONST = 5;
static class StaticNested {<V> V convert(TargetClass.TargetInner<V> targetInner) {return targetInner.getInnerData();}}
protected <T> Object process(TargetClass.TargetInner<T> targetParam) {labeledBlock: {TargetClass.TargetInner<T> localTarget = targetParam;StaticNested nested = new StaticNested();
try {T converted = nested.convert(localTarget);localTarget.setInnerData(converted);
public boolean isValid = localTarget.getInnerData() != null;assert isValid : "Target inner data cannot be null";
class LocalInner {Object wrapResult() {return new ArrayList<>(List.of(name, localTarget.getInnerData(), PRIVATE_CONST));}}return new LocalInner().wrapResult();} catch (NullPointerException e) {e.printStackTrace();break labeledBlock;}}return null;}
protected Object process(TargetClass.TargetInner<String> targetParam, int extra) {return process(targetParam);}}