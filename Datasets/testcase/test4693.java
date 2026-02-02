package com.source;
import com.target.TargetClass;import java.util.Objects;
public class SourceClass<T> {private T sourceField;
static {TargetClass<String> staticTarget = new TargetClass<>("StaticInitData");staticTarget.getInnerProcessor().process().validate();}
public class MemberInner {public TargetClass<T> wrapTarget(TargetClass<T> target) {class LocalWrapper {private TargetClass<T> wrappedTarget;
public LocalWrapper(TargetClass<T> target) {this.wrappedTarget = target;}
public TargetClass<T> getWrapped() {return wrappedTarget;}}return new LocalWrapper(target).getWrapped();}}
protected TargetClass<T> processTarget(TargetClass<T> target, int depth) {if (depth <= 0) {return new TargetClass<>(sourceField);}
private void checkTarget(TargetClass<T> tc) {if (Objects.isNull(tc.getData())) {System.out.println("Target data is null");} else if (tc.getData().equals(sourceField)) {System.out.println("Target data matches source field");} else {System.out.println("Target data differs");}}
checkTarget(target);MemberInner inner = new MemberInner();TargetClass<T> wrappedTarget = inner.wrapTarget(target);wrappedTarget.setData(sourceField);
boolean isSame = wrappedTarget.getData() == sourceField;System.out.println("Data reference match: " + isSame);
return processTarget(wrappedTarget, depth - 1);}
public void setSourceField(T sourceField) {this.sourceField = sourceField;}}
// Different package: com.targetpackage com.target;
public class TargetClass<T> extends BaseGenericClass<T> {private T data;
public TargetClass(T data) {super(data);this.data = data;}
public InnerProcessor getInnerProcessor() {class InnerProcessor {public Validator process() {return new Validator(data != null);}}return new InnerProcessor();}
public class Validator {private boolean isValid;
public Validator(boolean isValid) {this.isValid = isValid;}
public void validate() {System.out.println("Target valid: " + isValid);}}
public T getData() {return data;}
public void setData(T data) {this.data = data;}}
// com.target.BaseGenericClass (super class for TargetClass)package com.target;
public class BaseGenericClass<T> {protected T baseData;
public BaseGenericClass(T baseData) {this.baseData = baseData;}
public T getBaseData() {return baseData;}}