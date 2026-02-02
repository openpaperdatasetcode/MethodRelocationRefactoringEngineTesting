package test;
import java.lang.reflect.Method;
protected class TargetClass extends TargetParentClass {public static class TargetStaticNested {int nestedField = 1;}TargetStaticNested staticNestedInstance = new TargetStaticNested();}
class TargetParentClass {int superField = 1;}
abstract class SourceClass {TargetClass targetField = new TargetClass();
public class SourceInner1 {public class SourceInnerRec {/**
Javadoc for getTargetInstance method
@param param TargetClass parameter
@return TargetClass instance*/private TargetClass getTargetInstance(TargetClass param) {class LocalType<T extends TargetClass> {T typeInstance;LocalType(T instance) {super();this.typeInstance = instance;}}
LocalType<TargetClass> localVar = new LocalType<>(param);int val = localVar.typeInstance.staticNestedInstance.nestedField;val = localVar.typeInstance.superField;
try {Method refMethod = SourceInnerRec.class.getDeclaredMethod("getTargetInstance", TargetClass.class);refMethod.setAccessible(true);TargetClass reflectedResult = (TargetClass) refMethod.invoke(this, param);return reflectedResult;} catch (Exception e) {return localVar.typeInstance;}}
private TargetClass getTargetInstance(int param) {return new TargetClass();}}}
public class SourceInner2 {void useInnerRecMethod() {TargetClass result = new SourceInner1().new SourceInnerRec().getTargetInstance(targetField);}}}